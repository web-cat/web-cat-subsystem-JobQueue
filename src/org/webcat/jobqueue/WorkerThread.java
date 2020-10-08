/*==========================================================================*\
 |  Copyright (C) 2009-2021 Virginia Tech
 |
 |  This file is part of Web-CAT.
 |
 |  Web-CAT is free software; you can redistribute it and/or modify
 |  it under the terms of the GNU Affero General Public License as published
 |  by the Free Software Foundation; either version 3 of the License, or
 |  (at your option) any later version.
 |
 |  Web-CAT is distributed in the hope that it will be useful,
 |  but WITHOUT ANY WARRANTY; without even the implied warranty of
 |  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 |  GNU General Public License for more details.
 |
 |  You should have received a copy of the GNU Affero General Public License
 |  along with Web-CAT; if not, see <http://www.gnu.org/licenses/>.
\*==========================================================================*/

package org.webcat.jobqueue;

import java.io.PrintWriter;
import java.io.StringWriter;
import org.apache.log4j.Logger;
import org.jfree.util.Log;
import org.webcat.core.Application;
import org.webcat.woextensions.ECAction;
import org.webcat.woextensions.WCEC;
import org.webcat.woextensions.WCFetchSpecification;
import com.webobjects.eoaccess.EOGeneralAdaptorException;
import com.webobjects.eoaccess.EOUtilities;
import com.webobjects.eocontrol.EOEditingContext;
import com.webobjects.eocontrol.EOEnterpriseObject;
import com.webobjects.eocontrol.EOFetchSpecification;
import com.webobjects.foundation.NSArray;
import er.extensions.eof.ERXQ;
import er.extensions.eof.ERXS;

//-------------------------------------------------------------------------
/**
 * Implements a single worker thread on a single host, operating on a
 * shared database-backed queue of jobs represented as {@link JobBase}
 * subclass objects.
 *
 * @param <Job> The subclass of {@link JobBase} that this worker thread
 *     works on.
 *
 * @author  Stephen Edwards
 */
public abstract class WorkerThread<Job extends JobBase>
    extends Thread
{
    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new object.
     * @param queueEntity The name of the entity representing the job
     * queue for this worker thread.
     */
    public WorkerThread(String queueEntity)
    {
        entity = queueEntity;
        setName(this.getClass().getSimpleName() + "-" + getId());
//        WCEC myec = localContext();
//        descriptor = new ManagedWorkerDescriptor(
//            WorkerDescriptor.registerWorker(
//                myec,
//                HostDescriptor.currentHost(myec),
//                QueueDescriptor.descriptorFor(myec, queueEntity),
//                this));
    }


    // ----------------------------------------------------------
    /**
     * Access the queue descriptor for this worker's job queue.
     * @return The queue descriptor
     */
    public ManagedQueueDescriptor queueDescriptor(EOEditingContext ec)
    {
        if (queueDescriptor == null)
        {
            queueDescriptor =
                new ManagedQueueDescriptor(descriptor(ec).queue());
        }
        return queueDescriptor;
    }


    // ----------------------------------------------------------
    /**
     * Access the host descriptor for this worker's host.
     * @return The host descriptor
     */
    public ManagedHostDescriptor hostDescriptor(EOEditingContext ec)
    {
        if (hostDescriptor == null)
        {
            hostDescriptor = new ManagedHostDescriptor(descriptor(ec).host());
        }
        return hostDescriptor;
    }


    // ----------------------------------------------------------
    /**
     * Access the descriptor for this worker.
     * @return The worker descriptor
     */
    public ManagedWorkerDescriptor descriptor(EOEditingContext ec)
    {
        if (managedDescriptor == null)
        {
            descriptor = WorkerDescriptor.registerWorker(
                ec,
                HostDescriptor.currentHost(ec),
                QueueDescriptor.descriptorFor(ec, entity),
                this);
            managedDescriptor = new ManagedWorkerDescriptor(descriptor);
        }
        return managedDescriptor;
    }


    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * The actual thread of execution, which cannot be overridden.
     */
    @SuppressWarnings("unchecked")
    public final void run()
    {
        // Make sure application is fully initialized before running.
        Application.waitForInitializationToComplete();

        boolean starting = true;

        while (true)
        {
            WCEC ec = WCEC.factoryWithToolOSC()._newEditingContext();
            try
            {
                ec.lock();
                if (starting)
                {
                    // have to put this down here, since it needs to be
                    // inside EC locking section
                    logDebug(ec, "started");
                    starting = false;
                }

                killCancelledJobs(ec);
                waitForAvailableJob(ec);

                long jobStartTime = System.currentTimeMillis();
                boolean jobFailed = false;

                try
                {
                    processJob(ec);
                }
                catch (Exception e)
                {
                    ec.revert();
                    jobFailed = true;

                    currentJob.setIsReady(false);
                    currentJob.setWorkerRelationship(null);

                    resetJob(ec);
                    sendJobSuspensionNotification(e);

                    currentJob = null;

                    ec.saveChanges();
                }

                // If the job set its own state back to not-ready,
                // consider it as failed so that we don't delete it and
                // can come back to it later.
                if (currentJob != null && !currentJob.isReady())
                {
                    jobFailed = true;

                    currentJob.setWorkerRelationship(null);
                    currentJob = null;

                    ec.saveChanges();
                }

                if (!jobFailed)
                {
                    long now = System.currentTimeMillis();
                    long jobDuration = now - jobStartTime;
                    long jobWait = now - currentJob.enqueueTime().getTime();
                    boolean wasCancelled = currentJob.isCancelled();
                    currentJob.delete();

                    try
                    {
                        ec.saveChanges();
                        currentJob = null;

                        // Update the wait statistics.
                        if (!wasCancelled)
                        {
                            queueDescriptor(ec).addCompletedJobStats(
                                jobDuration, jobWait);
                        }
                    }
                    catch (Exception e)
                    {
                        Number jobId = currentJob.id();

                        // Get a local instance of the job with that id
                        NSArray<Job> results =
                            EOUtilities.objectsMatchingKeyAndValue(
                                ec,
                                queueDescriptor(ec).jobEntityName(),
                                "id", jobId.intValue());

                        if (results != null && results.count() > 0)
                        {
                            currentJob = results.objectAtIndex(0);
                        }
                        else
                        {
                            currentJob = null;
                        }
                    }
                }
            }
            catch (Exception e)
            {
                // FIXME what should we do here?
                log.fatal("Exception in worker thread:", e);
            }
            finally
            {
                ec.unlock();
                ec.dispose();
            }

            // Clear out fields for next round
            resetForNextJob();
        }
    }


    //~ Protected Methods .....................................................

    // ----------------------------------------------------------
    /**
     * Subclasses should implement this method to process the
     * {@link #currentJob()}.  All other work involving finding the next
     * job, managing the job queue, and so on is already implemented in
     * this abstract base class.  If this method throws any exceptions,
     * they will force the current job to be suspended (paused) and then
     * the {@link #sendJobSuspensionNotification()} method will be
     * invoked.
     *
     * @throws Exception if an exception occurred
     */
    protected abstract void processJob(EOEditingContext ec) throws Exception;


    // ----------------------------------------------------------
    /**
     * Resets the state of the job when it is suspended due to an exception.
     * Subclasses can override this method to perform any additional
     * modification of specific job attributes.
     */
    protected void resetJob(EOEditingContext ec)
    {
        // Default implementation does nothing.
    }


    // ----------------------------------------------------------
    /**
     * Called to handle a cancellation request for the job owned by this
     * thread. The default behavior simply sets the isCancelled flag of the
     * thread to true so that it can be polled in the processJob method, but
     * subclasses may override this to provide their own cleanup logic if
     * necessary.
     *
     * Subclasses that override this method should always call the super
     * method first.
     */
    protected synchronized void cancelJob(EOEditingContext ec)
    {
        isCancelled = true;
    }


    // ----------------------------------------------------------
    /**
     * Gets a value indicating whether the thread should cancel what it is
     * doing at the earliest opportunity, due to a cancellation request from
     * the user.
     *
     * @return true if the thread should cancel itself at the earliest
     *     opportunity, otherwise false
     */
    protected synchronized boolean isCancelling(EOEditingContext ec)
    {
        if (currentJob(ec).isCancelled())
        {
            if (!isCancelled)
            {
                cancelJob(ec);
            }

            return true;
        }
        else
        {
            return false;
        }
    }


    // ----------------------------------------------------------
    /**
     * Access the current job that this thread is working on.
     * @return The current job, or null if there is none
     */
    protected Job currentJob(EOEditingContext ec)
    {
        if (currentJob != null && ec != currentJob.wcEditingContext())
        {
            throw new IllegalStateException("Job " + currentJob + " has EC "
                + currentJob.wcEditingContext() + " when expecting " + ec,
                new Exception("currentJob() called from here"));
        }
        return currentJob;
    }


    // ----------------------------------------------------------
    private void resetForNextJob()
    {
        currentJob = null;
        queueDescriptor = null;
        hostDescriptor = null;
        managedDescriptor = null;
        descriptor = null;
        isCancelled = false;
    }


    // ----------------------------------------------------------
    /**
     * Access this worker's local editing context.
     * @return The editing context
     */
//    protected WCEC localContext()
//    {
//        if (ec == null)
//        {
//            ec = WCEC.newAutoLockingEditingContext();
//            if (queueDescriptor != null)
//            {
//                queueDescriptor = new ManagedQueueDescriptor(
//                    (QueueDescriptor)queueDescriptor.localInstanceIn(ec));
//            }
//            if (hostDescriptor != null)
//            {
//                hostDescriptor = new ManagedHostDescriptor(
//                    (HostDescriptor)hostDescriptor.localInstanceIn(ec));
//            }
//            if (descriptor != null)
//            {
//                descriptor = new ManagedWorkerDescriptor(
//                    (WorkerDescriptor)descriptor.localInstanceIn(ec));
//            }
//        }
//        return ec;
//    }


    // ----------------------------------------------------------
    /**
     * Unlocks the thread's local editing context, recycles it, and then
     * relocks it.
     */
//    protected void renewContext()
//    {
//        // Unlock and release the current editing context
//        ec.dispose();
//        ec = null;
//
//        // Generate a fresh editing context, which will auto-lock on demand
//        localContext();
//    }


    // ----------------------------------------------------------
    /**
     * Notify the administrator and any other relevant personnel that the
     * current job has been suspended.  The job's "isReady" flag is already
     * cleared before this is called.
     *
     *
     * @param e the exception thrown by {@link #processJob()}
     */
    protected void sendJobSuspensionNotification(Exception e)
    {
        StringWriter sw = new StringWriter();

        if(currentJob.suspensionReason() != null)
        {
            sw.append(currentJob.suspensionReason());
            sw.append("\n\n");
        }

        sw.append("The worker thread's processJob() method threw the "
                + "following exception:\n\n");
        e.printStackTrace(new PrintWriter(sw));

        String additionalInfo = additionalSuspensionInfo();
        if (additionalInfo != null)
        {
            sw.append("\n");
            sw.append("Additional information about the job:\n");
            sw.append(additionalInfo);
        }

        String reason = sw.toString();
        currentJob.setSuspensionReason(reason);

        // TODO: replace this with a notification message
        log.error("processJob() threw the following exception:", e);
    }


    // ----------------------------------------------------------
    /**
     * Gets additional information about the job when it is suspended due to an
     * error. This information is included in the suspension reason that is
     * shown to the user. Subclasses should override this and provide extra
     * information based on fields in the corresponding JobBase subclass.
     *
     * @return additional information to be shown to the user when the job is
     *     suspended
     */
    protected String additionalSuspensionInfo()
    {
        return null;
    }


    // ----------------------------------------------------------
    /**
     * Waits for a candidate job to become available and tries to take
     * ownership of it. This method will not return until it successfully does
     * this, at which point the currentJob field will be set to that job.
     */
    protected void waitForAvailableJob(EOEditingContext ec)
    {
        boolean didGetJob = false;

        if (currentJob != null)
        {
            return;
        }

        do
        {
            // Get a candidate job for this thread to try to take ownership of.
            // A candidate will have a null worker relationship, meaning
            // nobody else successfully owns it yet.

            Job candidate = fetchNextCandidateJob(ec);

            if (candidate == null)
            {
                // If there aren't any jobs currently available, wait
                // until something arrives in the queue
                logDebug(ec, "waiting for queue to wake me");
                try
                {
                    queueDescriptor(ec).waitForNextJob();
                }
                catch (Exception e)
                {
                    // If this blows up, just repeat the loop and try again
                }
                logDebug(ec, "woken by the queue");
            }
            else
            {
                // Try to take ownership of the job by setting the worker
                // relationship to our worker descriptor and then saving the
                // changes. If this succeeds, we own the job. If there is an
                // optimistic locking failure, then another thread got it
                // first, so we go back to the top of the loop and try to get
                // another job.

                if (descriptor == null)
                {
                    // force it to be evaluated
                    descriptor(ec);
                }
                WorkerDescriptor worker = descriptor;
                ec.refreshObject(worker);

                logDebug(ec, "volunteering to run job " + candidate.id());
                didGetJob = candidate.volunteerToRun(worker);

                if (didGetJob)
                {
                    logDebug(
                        ec, "successfully acquired job " + candidate.id());
                    currentJob = candidate;
                }
            }
        }
        while (!didGetJob);
    }


    // ----------------------------------------------------------
    /**
     * Fetch the next job that this thread will try to take ownership of.
     *
     * @return a job that doesn't currently have any worker threads owning it
     */
    @SuppressWarnings("unchecked")
    protected Job fetchNextCandidateJob(EOEditingContext ec)
    {
        String entityName = queueDescriptor(ec).jobEntityName();

        EOFetchSpecification fetchSpec = new WCFetchSpecification<Job>(
            entityName,
            ERXQ.and(
                ERXQ.isNull(JobBase.WORKER_KEY),
                ERXQ.isFalse(JobBase.IS_CANCELLED_KEY),
                ERXQ.isTrue(JobBase.IS_READY_KEY)),
            ERXS.sortOrders(JobBase.ENQUEUE_TIME_KEY, ERXS.ASC));
        fetchSpec.setFetchLimit(1);

        NSArray<Job> jobs = ec.objectsWithFetchSpecification(fetchSpec);

        if (jobs.count() == 0)
        {
            return null;
        }
        else
        {
            return jobs.objectAtIndex(0);
        }
    }


    //~ Private methods .......................................................

    // ----------------------------------------------------------
    /**
     * Fetches cancelled jobs from the queue and deletes them.
     */
    private void killCancelledJobs(EOEditingContext ec)
    {
        Job cancelledJob = fetchNextCancelledJob(ec);

        while (cancelledJob != null)
        {
            cancelledJob.delete();

            // If there is an optimistic locking failure when we try to save
            // our changes, that's ok because another thread already cancelled
            // the job. Continue blissfully on by getting the next job.

            try
            {
                ec.saveChanges();
            }
            catch (Exception e)
            {
                ec.revert();
            }

            cancelledJob = fetchNextCancelledJob(ec);
        }
    }


    // ----------------------------------------------------------
    /**
     * Retrieves cancelled jobs of the type handled by this worker thread.
     *
     * @return an array of cancelled jobs
     */
    @SuppressWarnings("unchecked")
    private Job fetchNextCancelledJob(EOEditingContext ec)
    {
        String entityName = queueDescriptor(ec).jobEntityName();
        EOFetchSpecification fetchSpec = new EOFetchSpecification(
                entityName,
                ERXQ.and(
                        ERXQ.isNull(JobBase.WORKER_KEY),
                        ERXQ.isTrue(JobBase.IS_CANCELLED_KEY)),
                ERXS.sortOrders(JobBase.ENQUEUE_TIME_KEY, ERXS.ASC));
        fetchSpec.setFetchLimit(1);

        NSArray<Job> jobs = ec.objectsWithFetchSpecification(fetchSpec);

        if (jobs.count() == 0)
        {
            return null;
        }
        else
        {
            return jobs.objectAtIndex(0);
        }
    }


    // ----------------------------------------------------------
    private void logDebug(EOEditingContext ec, Object obj)
    {
        if (log.isDebugEnabled())
        {
            log.debug(queueDescriptor(ec).jobEntityName()
                + " worker thread " + getId() + ": " + obj);
        }
    }


    //~ Instance/static variables .............................................

    private final String            entity;
    private Job                     currentJob;
    private ManagedQueueDescriptor  queueDescriptor;
    private ManagedHostDescriptor   hostDescriptor;
    private ManagedWorkerDescriptor managedDescriptor;
    private WorkerDescriptor        descriptor;
    private boolean                 isCancelled;

    private static final Logger log = Logger.getLogger(WorkerThread.class);
}
