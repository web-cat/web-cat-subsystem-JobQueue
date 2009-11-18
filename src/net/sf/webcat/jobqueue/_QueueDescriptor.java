/*==========================================================================*\
 |  _QueueDescriptor.java
 |*-------------------------------------------------------------------------*|
 |  Created by eogenerator
 |  DO NOT EDIT.  Make changes to QueueDescriptor.java instead.
 |*-------------------------------------------------------------------------*|
 |  Copyright (C) 2006-2009 Virginia Tech
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

package net.sf.webcat.jobqueue;

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import java.util.Enumeration;
import org.apache.log4j.Logger;

// -------------------------------------------------------------------------
/**
 * An automatically generated EOGenericRecord subclass.  DO NOT EDIT.
 * To change, use EOModeler, or make additions in
 * QueueDescriptor.java.
 *
 * @author Generated by eogenerator
 * @version version suppressed to control auto-generation
 */
public abstract class _QueueDescriptor
    extends er.extensions.eof.ERXGenericRecord
{
    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new _QueueDescriptor object.
     */
    public _QueueDescriptor()
    {
        super();
    }


    // ----------------------------------------------------------
    /**
     * A static factory method for creating a new
     * _QueueDescriptor object given required
     * attributes and relationships.
     * @param editingContext The context in which the new object will be
     * inserted
     * @param jobEntityName
     * @param newestEntryId
     * @return The newly created object
     */
    public static QueueDescriptor create(
        EOEditingContext editingContext,
        String jobEntityName,
        long newestEntryId
        )
    {
        QueueDescriptor eoObject = (QueueDescriptor)
            EOUtilities.createAndInsertInstance(
                editingContext,
                _QueueDescriptor.ENTITY_NAME);
        eoObject.setJobEntityName(jobEntityName);
        eoObject.setNewestEntryId(newestEntryId);
        return eoObject;
    }


    // ----------------------------------------------------------
    /**
     * Get a local instance of the given object in another editing context.
     * @param editingContext The target editing context
     * @param eo The object to import
     * @return An instance of the given object in the target editing context
     */
    public static QueueDescriptor localInstance(
        EOEditingContext editingContext, QueueDescriptor eo)
    {
        return (eo == null)
            ? null
            : (QueueDescriptor)EOUtilities.localInstanceOfObject(
                editingContext, eo);
    }


    // ----------------------------------------------------------
    /**
     * Look up an object by id number.  Assumes the editing
     * context is appropriately locked.
     * @param ec The editing context to use
     * @param id The id to look up
     * @return The object, or null if no such id exists
     */
    public static QueueDescriptor forId(
        EOEditingContext ec, int id )
    {
        QueueDescriptor obj = null;
        if (id > 0)
        {
            NSArray results = EOUtilities.objectsMatchingKeyAndValue( ec,
                ENTITY_NAME, "id", new Integer( id ) );
            if ( results != null && results.count() > 0 )
            {
                obj = (QueueDescriptor)results.objectAtIndex( 0 );
            }
        }
        return obj;
    }


    // ----------------------------------------------------------
    /**
     * Look up an object by id number.  Assumes the editing
     * context is appropriately locked.
     * @param ec The editing context to use
     * @param id The id to look up
     * @return The object, or null if no such id exists
     */
    public static QueueDescriptor forId(
        EOEditingContext ec, String id )
    {
        return forId( ec, er.extensions.foundation.ERXValueUtilities.intValue( id ) );
    }


    //~ Constants (for key names) .............................................

    // Attributes ---
    public static final String DEFAULT_JOB_WAIT_KEY = "defaultJobWait";
    public static final String JOB_COUNT_KEY = "jobCount";
    public static final String JOB_ENTITY_NAME_KEY = "jobEntityName";
    public static final String JOBS_COUNTED_WITH_WAITS_KEY = "jobsCountedWithWaits";
    public static final String MOST_RECENT_JOB_WAIT_KEY = "mostRecentJobWait";
    public static final String NEWEST_ENTRY_ID_KEY = "newestEntryId";
    public static final String REQUIRES_EXCLUSIVE_HOST_ACCESS_KEY = "requiresExclusiveHostAccess";
    public static final String TOTAL_WAIT_FOR_JOBS_KEY = "totalWaitForJobs";
    // To-one relationships ---
    // To-many relationships ---
    public static final String WORKERS_KEY = "workers";
    // Fetch specifications ---
    public static final String JOB_ENTITY_NAME_FSPEC = "jobEntityName";
    public static final String ENTITY_NAME = "QueueDescriptor";


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Get a local instance of this object in another editing context.
     * @param editingContext The target editing context
     * @return An instance of this object in the target editing context
     */
    public QueueDescriptor localInstance(EOEditingContext editingContext)
    {
        return (QueueDescriptor)EOUtilities.localInstanceOfObject(
            editingContext, this);
    }


    // ----------------------------------------------------------
    /**
     * Get a list of changes between this object's current state and the
     * last committed version.
     * @return a dictionary of the changes that have not yet been committed
     */
    public NSDictionary changedProperties()
    {
        return changesFromSnapshot(
            editingContext().committedSnapshotForObject(this) );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>id</code> value.
     * @return the value of the attribute
     */
    public Number id()
    {
        try
        {
            return (Number)EOUtilities.primaryKeyForObject(
                editingContext() , this ).objectForKey( "id" );
        }
        catch (Exception e)
        {
            return er.extensions.eof.ERXConstant.ZeroInteger;
        }
    }

    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>defaultJobWait</code> value.
     * @return the value of the attribute
     */
    public long defaultJobWait()
    {
        Long result =
            (Long)storedValueForKey( "defaultJobWait" );
        return ( result == null )
            ? 0L
            : result.longValue();
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>defaultJobWait</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setDefaultJobWait( long value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setDefaultJobWait("
                + value + "): was " + defaultJobWait() );
        }
        Long actual =
            new Long( value );
            setDefaultJobWaitRaw( actual );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>defaultJobWait</code> value.
     * @return the value of the attribute
     */
    public Long defaultJobWaitRaw()
    {
        return (Long)storedValueForKey( "defaultJobWait" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>defaultJobWait</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setDefaultJobWaitRaw( Long value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setDefaultJobWaitRaw("
                + value + "): was " + defaultJobWaitRaw() );
        }
        takeStoredValueForKey( value, "defaultJobWait" );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>jobCount</code> value.
     * @return the value of the attribute
     */
    public long jobCount()
    {
        Long result =
            (Long)storedValueForKey( "jobCount" );
        return ( result == null )
            ? 0L
            : result.longValue();
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>jobCount</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setJobCount( long value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setJobCount("
                + value + "): was " + jobCount() );
        }
        Long actual =
            new Long( value );
            setJobCountRaw( actual );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>jobCount</code> value.
     * @return the value of the attribute
     */
    public Long jobCountRaw()
    {
        return (Long)storedValueForKey( "jobCount" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>jobCount</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setJobCountRaw( Long value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setJobCountRaw("
                + value + "): was " + jobCountRaw() );
        }
        takeStoredValueForKey( value, "jobCount" );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>jobEntityName</code> value.
     * @return the value of the attribute
     */
    public String jobEntityName()
    {
        return (String)storedValueForKey( "jobEntityName" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>jobEntityName</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setJobEntityName( String value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setJobEntityName("
                + value + "): was " + jobEntityName() );
        }
        takeStoredValueForKey( value, "jobEntityName" );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>jobsCountedWithWaits</code> value.
     * @return the value of the attribute
     */
    public long jobsCountedWithWaits()
    {
        Long result =
            (Long)storedValueForKey( "jobsCountedWithWaits" );
        return ( result == null )
            ? 0L
            : result.longValue();
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>jobsCountedWithWaits</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setJobsCountedWithWaits( long value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setJobsCountedWithWaits("
                + value + "): was " + jobsCountedWithWaits() );
        }
        Long actual =
            new Long( value );
            setJobsCountedWithWaitsRaw( actual );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>jobsCountedWithWaits</code> value.
     * @return the value of the attribute
     */
    public Long jobsCountedWithWaitsRaw()
    {
        return (Long)storedValueForKey( "jobsCountedWithWaits" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>jobsCountedWithWaits</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setJobsCountedWithWaitsRaw( Long value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setJobsCountedWithWaitsRaw("
                + value + "): was " + jobsCountedWithWaitsRaw() );
        }
        takeStoredValueForKey( value, "jobsCountedWithWaits" );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>mostRecentJobWait</code> value.
     * @return the value of the attribute
     */
    public long mostRecentJobWait()
    {
        Long result =
            (Long)storedValueForKey( "mostRecentJobWait" );
        return ( result == null )
            ? 0L
            : result.longValue();
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>mostRecentJobWait</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setMostRecentJobWait( long value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setMostRecentJobWait("
                + value + "): was " + mostRecentJobWait() );
        }
        Long actual =
            new Long( value );
            setMostRecentJobWaitRaw( actual );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>mostRecentJobWait</code> value.
     * @return the value of the attribute
     */
    public Long mostRecentJobWaitRaw()
    {
        return (Long)storedValueForKey( "mostRecentJobWait" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>mostRecentJobWait</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setMostRecentJobWaitRaw( Long value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setMostRecentJobWaitRaw("
                + value + "): was " + mostRecentJobWaitRaw() );
        }
        takeStoredValueForKey( value, "mostRecentJobWait" );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>newestEntryId</code> value.
     * @return the value of the attribute
     */
    public long newestEntryId()
    {
        Long result =
            (Long)storedValueForKey( "newestEntryId" );
        return ( result == null )
            ? 0L
            : result.longValue();
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>newestEntryId</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setNewestEntryId( long value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setNewestEntryId("
                + value + "): was " + newestEntryId() );
        }
        Long actual =
            new Long( value );
            setNewestEntryIdRaw( actual );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>newestEntryId</code> value.
     * @return the value of the attribute
     */
    public Long newestEntryIdRaw()
    {
        return (Long)storedValueForKey( "newestEntryId" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>newestEntryId</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setNewestEntryIdRaw( Long value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setNewestEntryIdRaw("
                + value + "): was " + newestEntryIdRaw() );
        }
        takeStoredValueForKey( value, "newestEntryId" );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>requiresExclusiveHostAccess</code> value.
     * @return the value of the attribute
     */
    public boolean requiresExclusiveHostAccess()
    {
        Integer result =
            (Integer)storedValueForKey( "requiresExclusiveHostAccess" );
        return ( result == null )
            ? false
            : ( result.intValue() > 0 );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>requiresExclusiveHostAccess</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setRequiresExclusiveHostAccess( boolean value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setRequiresExclusiveHostAccess("
                + value + "): was " + requiresExclusiveHostAccess() );
        }
        Integer actual =
            er.extensions.eof.ERXConstant.integerForInt( value ? 1 : 0 );
            setRequiresExclusiveHostAccessRaw( actual );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>requiresExclusiveHostAccess</code> value.
     * @return the value of the attribute
     */
    public Integer requiresExclusiveHostAccessRaw()
    {
        return (Integer)storedValueForKey( "requiresExclusiveHostAccess" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>requiresExclusiveHostAccess</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setRequiresExclusiveHostAccessRaw( Integer value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setRequiresExclusiveHostAccessRaw("
                + value + "): was " + requiresExclusiveHostAccessRaw() );
        }
        takeStoredValueForKey( value, "requiresExclusiveHostAccess" );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>totalWaitForJobs</code> value.
     * @return the value of the attribute
     */
    public long totalWaitForJobs()
    {
        Long result =
            (Long)storedValueForKey( "totalWaitForJobs" );
        return ( result == null )
            ? 0L
            : result.longValue();
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>totalWaitForJobs</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setTotalWaitForJobs( long value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setTotalWaitForJobs("
                + value + "): was " + totalWaitForJobs() );
        }
        Long actual =
            new Long( value );
            setTotalWaitForJobsRaw( actual );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>totalWaitForJobs</code> value.
     * @return the value of the attribute
     */
    public Long totalWaitForJobsRaw()
    {
        return (Long)storedValueForKey( "totalWaitForJobs" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>totalWaitForJobs</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setTotalWaitForJobsRaw( Long value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setTotalWaitForJobsRaw("
                + value + "): was " + totalWaitForJobsRaw() );
        }
        takeStoredValueForKey( value, "totalWaitForJobs" );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve the entities pointed to by the <code>workers</code>
     * relationship.
     * @return an NSArray of the entities in the relationship
     */
    @SuppressWarnings("unchecked")
    public NSArray<net.sf.webcat.jobqueue.WorkerDescriptor> workers()
    {
        return (NSArray)storedValueForKey( "workers" );
    }


    // ----------------------------------------------------------
    /**
     * Replace the list of entities pointed to by the
     * <code>workers</code> relationship.
     *
     * @param value The new set of entities to relate to
     */
    public void setWorkers( NSMutableArray<net.sf.webcat.jobqueue.WorkerDescriptor>  value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setWorkers("
                + value + "): was " + workers() );
        }
        takeStoredValueForKey( value, "workers" );
    }


    // ----------------------------------------------------------
    /**
     * Add a new entity to the <code>workers</code>
     * relationship (DO NOT USE--instead, use
     * <code>addToWorkersRelationship()</code>.
     * This method is provided for WebObjects use.
     *
     * @param value The new entity to relate to
     */
    public void addToWorkers( net.sf.webcat.jobqueue.WorkerDescriptor value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "addToWorkers("
                + value + "): was " + workers() );
        }
        NSMutableArray<net.sf.webcat.jobqueue.WorkerDescriptor> array =
            (NSMutableArray<net.sf.webcat.jobqueue.WorkerDescriptor>)workers();
        willChange();
        array.addObject( value );
    }


    // ----------------------------------------------------------
    /**
     * Remove a specific entity from the <code>workers</code>
     * relationship (DO NOT USE--instead, use
     * <code>removeFromWorkersRelationship()</code>.
     * This method is provided for WebObjects use.
     *
     * @param value The entity to remove from the relationship
     */
    public void removeFromWorkers( net.sf.webcat.jobqueue.WorkerDescriptor value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "RemoveFromWorkers("
                + value + "): was " + workers() );
        }
        NSMutableArray<net.sf.webcat.jobqueue.WorkerDescriptor> array =
            (NSMutableArray<net.sf.webcat.jobqueue.WorkerDescriptor>)workers();
        willChange();
        array.removeObject( value );
    }


    // ----------------------------------------------------------
    /**
     * Add a new entity to the <code>workers</code>
     * relationship.
     *
     * @param value The new entity to relate to
     */
    public void addToWorkersRelationship( net.sf.webcat.jobqueue.WorkerDescriptor value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "addToWorkersRelationship("
                + value + "): was " + workers() );
        }
        addObjectToBothSidesOfRelationshipWithKey(
            value, "workers" );
    }


    // ----------------------------------------------------------
    /**
     * Remove a specific entity from the <code>workers</code>
     * relationship.
     *
     * @param value The entity to remove from the relationship
     */
    public void removeFromWorkersRelationship( net.sf.webcat.jobqueue.WorkerDescriptor value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "removeFromWorkersRelationship("
                + value + "): was " + workers() );
        }
        removeObjectFromBothSidesOfRelationshipWithKey(
            value, "workers" );
    }


    // ----------------------------------------------------------
    /**
     * Create a brand new object that is a member of the
     * <code>workers</code> relationship.
     *
     * @return The new entity
     */
    public net.sf.webcat.jobqueue.WorkerDescriptor createWorkersRelationship()
    {
        if (log.isDebugEnabled())
        {
            log.debug( "createWorkersRelationship()" );
        }
        EOClassDescription eoClassDesc = EOClassDescription
            .classDescriptionForEntityName( "WorkerDescriptor" );
        EOEnterpriseObject eoObject = eoClassDesc
            .createInstanceWithEditingContext( editingContext(), null );
        editingContext().insertObject( eoObject );
        addObjectToBothSidesOfRelationshipWithKey(
            eoObject, "workers" );
        return (net.sf.webcat.jobqueue.WorkerDescriptor)eoObject;
    }


    // ----------------------------------------------------------
    /**
     * Remove and then delete a specific entity that is a member of the
     * <code>workers</code> relationship.
     *
     * @param value The entity to remove from the relationship and then delete
     */
    public void deleteWorkersRelationship( net.sf.webcat.jobqueue.WorkerDescriptor value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "deleteWorkersRelationship("
                + value + "): was " + workers() );
        }
        removeObjectFromBothSidesOfRelationshipWithKey(
            value, "workers" );
        editingContext().deleteObject( value );
    }


    // ----------------------------------------------------------
    /**
     * Remove (and then delete, if owned) all entities that are members of the
     * <code>workers</code> relationship.
     */
    public void deleteAllWorkersRelationships()
    {
        if (log.isDebugEnabled())
        {
            log.debug( "deleteAllWorkersRelationships(): was "
                + workers() );
        }
        Enumeration objects = workers().objectEnumerator();
        while ( objects.hasMoreElements() )
            deleteWorkersRelationship(
                (net.sf.webcat.jobqueue.WorkerDescriptor)objects.nextElement() );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve objects using a fetch specification.
     *
     * @param context The editing context to use
     * @param fspec The fetch specification to use
     *
     * @return an NSArray of the entities retrieved
     */
    @SuppressWarnings("unchecked")
    public static NSArray<QueueDescriptor> objectsWithFetchSpecification(
        EOEditingContext context,
        EOFetchSpecification fspec)
    {
        return context.objectsWithFetchSpecification(fspec);
    }


    // ----------------------------------------------------------
    /**
     * Retrieve objects using a qualifier.
     *
     * @param context The editing context to use
     * @param qualifier The qualifier to use
     *
     * @return an NSArray of the entities retrieved
     */
    @SuppressWarnings("unchecked")
    public static NSArray<QueueDescriptor> objectsMatchingQualifier(
        EOEditingContext context,
        EOQualifier qualifier)
    {
        return objectsMatchingQualifier(context, qualifier, null);
    }


    // ----------------------------------------------------------
    /**
     * Retrieve objects using a qualifier and sort orderings.
     *
     * @param context The editing context to use
     * @param qualifier The qualifier to use
     * @param sortOrderings The sort orderings to use
     *
     * @return an NSArray of the entities retrieved
     */
    @SuppressWarnings("unchecked")
    public static NSArray<QueueDescriptor> objectsMatchingQualifier(
        EOEditingContext context,
        EOQualifier qualifier,
        NSArray<EOSortOrdering> sortOrderings)
    {
        EOFetchSpecification fspec = new EOFetchSpecification(
            ENTITY_NAME, qualifier, sortOrderings);

        return objectsWithFetchSpecification(context, fspec);
    }


    // ----------------------------------------------------------
    /**
     * Retrieve objects using a list of keys and values to match.
     *
     * @param context The editing context to use
     * @param keysAndValues a list of keys and values to match, alternating
     *     "key", "value", "key", "value"...
     *
     * @return an NSArray of the entities retrieved
     */
    @SuppressWarnings("unchecked")
    public static NSArray<QueueDescriptor> objectsMatchingValues(
        EOEditingContext context,
        Object... keysAndValues)
    {
        if (keysAndValues.length % 2 != 0)
        {
            throw new IllegalArgumentException("There should a value " +
                "corresponding to every key that was passed.");
        }

        NSMutableDictionary<String, Object> valueDictionary =
            new NSMutableDictionary<String, Object>();

        for (int i = 0; i < keysAndValues.length; i += 2)
        {
            Object key = keysAndValues[i];
            Object value = keysAndValues[i + 1];

            if (!(key instanceof String))
            {
                throw new IllegalArgumentException("Keys should be strings.");
            }

            valueDictionary.setObjectForKey(value, key);
        }

        return objectsMatchingValues(context, valueDictionary);
    }


    // ----------------------------------------------------------
    /**
     * Retrieve objects using a dictionary of keys and values to match.
     *
     * @param context The editing context to use
     * @param keysAndValues a dictionary of keys and values to match
     *
     * @return an NSArray of the entities retrieved
     */
    @SuppressWarnings("unchecked")
    public static NSArray<QueueDescriptor> objectsMatchingValues(
        EOEditingContext context,
        NSDictionary<String, Object> keysAndValues)
    {
        return EOUtilities.objectsMatchingValues(context, ENTITY_NAME,
            keysAndValues);
    }


    // ----------------------------------------------------------
    /**
     * Retrieve a single object using a list of keys and values to match.
     *
     * @param context The editing context to use
     * @param keysAndValues a list of keys and values to match, alternating
     *     "key", "value", "key", "value"...
     *
     * @return the single entity that was retrieved
     *
     * @throws EOObjectNotAvailableException
     *     if there is no matching object
     * @throws EOUtilities.MoreThanOneException
     *     if there is more than one matching object
     */
    @SuppressWarnings("unchecked")
    public static QueueDescriptor objectMatchingValues(
        EOEditingContext context,
        Object... keysAndValues) throws EOObjectNotAvailableException,
                                        EOUtilities.MoreThanOneException
    {
        if (keysAndValues.length % 2 != 0)
        {
            throw new IllegalArgumentException("There should a value " +
                "corresponding to every key that was passed.");
        }

        NSMutableDictionary<String, Object> valueDictionary =
            new NSMutableDictionary<String, Object>();

        for (int i = 0; i < keysAndValues.length; i += 2)
        {
            Object key = keysAndValues[i];
            Object value = keysAndValues[i + 1];

            if (!(key instanceof String))
            {
                throw new IllegalArgumentException("Keys should be strings.");
            }

            valueDictionary.setObjectForKey(value, key);
        }

        return objectMatchingValues(context, valueDictionary);
    }


    // ----------------------------------------------------------
    /**
     * Retrieve an object using a dictionary of keys and values to match.
     *
     * @param context The editing context to use
     * @param keysAndValues a dictionary of keys and values to match
     *
     * @return the single entity that was retrieved
     *
     * @throws EOObjectNotAvailableException
     *     if there is no matching object
     * @throws EOUtilities.MoreThanOneException
     *     if there is more than one matching object
     */
    @SuppressWarnings("unchecked")
    public static QueueDescriptor objectMatchingValues(
        EOEditingContext context,
        NSDictionary<String, Object> keysAndValues)
        throws EOObjectNotAvailableException,
               EOUtilities.MoreThanOneException
    {
        return (QueueDescriptor) EOUtilities.objectMatchingValues(
            context, ENTITY_NAME, keysAndValues);
    }


    // ----------------------------------------------------------
    /**
     * Retrieve object according to the <code>JobEntityName</code>
     * fetch specification.
     *
     * @param context The editing context to use
     * @param jobEntityNameBinding fetch spec parameter
     * @return an NSArray of the entities retrieved
     */
    public static NSArray<QueueDescriptor> objectsForJobEntityName(
            EOEditingContext context,
            String jobEntityNameBinding
        )
    {
        EOFetchSpecification spec = EOFetchSpecification
            .fetchSpecificationNamed( "jobEntityName", "QueueDescriptor" );

        NSMutableDictionary<String, Object> bindings =
            new NSMutableDictionary<String, Object>();

        if ( jobEntityNameBinding != null )
        {
            bindings.setObjectForKey( jobEntityNameBinding,
                                      "jobEntityName" );
        }
        spec = spec.fetchSpecificationWithQualifierBindings( bindings );

        NSArray<QueueDescriptor> result = objectsWithFetchSpecification( context, spec );
        if (log.isDebugEnabled())
        {
            log.debug( "objectsForJobEntityName(ec"
                + ", " + jobEntityNameBinding
                + "): " + result );
        }
        return result;
    }


    // ----------------------------------------------------------
    /**
     * Produce a string representation of this object.  This implementation
     * calls UserPresentableDescription(), which uses WebObjects' internal
     * mechanism to print out the visible fields of this object.  Normally,
     * subclasses would override userPresentableDescription() to change
     * the way the object is printed.
     *
     * @return A string representation of the object's value
     */
    public String toString()
    {
        return userPresentableDescription();
    }


    //~ Instance/static variables .............................................

    static Logger log = Logger.getLogger( QueueDescriptor.class );
}
