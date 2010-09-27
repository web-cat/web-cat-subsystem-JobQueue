/*==========================================================================*\
 |  _WorkerDescriptor.java
 |*-------------------------------------------------------------------------*|
 |  Created by eogenerator
 |  DO NOT EDIT.  Make changes to WorkerDescriptor.java instead.
 |*-------------------------------------------------------------------------*|
 |  Copyright (C) 2006-2010 Virginia Tech
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

import com.webobjects.eoaccess.*;
import com.webobjects.eocontrol.*;
import com.webobjects.foundation.*;
import er.extensions.eof.ERXEOControlUtilities;
import er.extensions.eof.ERXKey;
import org.apache.log4j.Logger;

// -------------------------------------------------------------------------
/**
 * An automatically generated EOGenericRecord subclass.  DO NOT EDIT.
 * To change, use EOModeler, or make additions in
 * WorkerDescriptor.java.
 *
 * @author Generated by eogenerator
 * @version version suppressed to control auto-generation
 */
public abstract class _WorkerDescriptor
    extends er.extensions.eof.ERXGenericRecord
{
    //~ Constructors ..........................................................

    // ----------------------------------------------------------
    /**
     * Creates a new _WorkerDescriptor object.
     */
    public _WorkerDescriptor()
    {
        super();
    }


    // ----------------------------------------------------------
    /**
     * A static factory method for creating a new
     * WorkerDescriptor object given required
     * attributes and relationships.
     * @param editingContext The context in which the new object will be
     * inserted
     * @param currentJobIdValue
     * @param isAliveValue
     * @return The newly created object
     */
    public static WorkerDescriptor create(
        EOEditingContext editingContext,
        long currentJobIdValue,
        boolean isAliveValue
        )
    {
        WorkerDescriptor eoObject = (WorkerDescriptor)
            EOUtilities.createAndInsertInstance(
                editingContext,
                _WorkerDescriptor.ENTITY_NAME);
        eoObject.setCurrentJobId(currentJobIdValue);
        eoObject.setIsAlive(isAliveValue);
        return eoObject;
    }


    // ----------------------------------------------------------
    /**
     * Get a local instance of the given object in another editing context.
     * @param editingContext The target editing context
     * @param eo The object to import
     * @return An instance of the given object in the target editing context
     */
    public static WorkerDescriptor localInstance(
        EOEditingContext editingContext, WorkerDescriptor eo)
    {
        return (eo == null)
            ? null
            : (WorkerDescriptor)EOUtilities.localInstanceOfObject(
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
    public static WorkerDescriptor forId(
        EOEditingContext ec, int id )
    {
        WorkerDescriptor obj = null;
        if (id > 0)
        {
            NSArray<WorkerDescriptor> results =
                objectsMatchingValues(ec, "id", new Integer(id));
            if (results != null && results.count() > 0)
            {
                obj = results.objectAtIndex(0);
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
    public static WorkerDescriptor forId(
        EOEditingContext ec, String id )
    {
        return forId( ec, er.extensions.foundation.ERXValueUtilities.intValue( id ) );
    }


    //~ Constants (for key names) .............................................

    // Attributes ---
    public static final String CURRENT_JOB_ID_KEY = "currentJobId";
    public static final ERXKey<Long> currentJobId =
        new ERXKey<Long>(CURRENT_JOB_ID_KEY);
    public static final String IS_ALIVE_KEY = "isAlive";
    public static final ERXKey<Integer> isAlive =
        new ERXKey<Integer>(IS_ALIVE_KEY);
    // To-one relationships ---
    public static final String HOST_KEY = "host";
    public static final ERXKey<org.webcat.jobqueue.HostDescriptor> host =
        new ERXKey<org.webcat.jobqueue.HostDescriptor>(HOST_KEY);
    public static final String QUEUE_KEY = "queue";
    public static final ERXKey<org.webcat.jobqueue.QueueDescriptor> queue =
        new ERXKey<org.webcat.jobqueue.QueueDescriptor>(QUEUE_KEY);
    // To-many relationships ---
    // Fetch specifications ---
    public static final String DESCRIPTORS_FOR_HOST_FSPEC = "descriptorsForHost";
    public static final String ENTITY_NAME = "WorkerDescriptor";


    //~ Methods ...............................................................

    // ----------------------------------------------------------
    /**
     * Get a local instance of this object in another editing context.
     * @param editingContext The target editing context
     * @return An instance of this object in the target editing context
     */
    public WorkerDescriptor localInstance(EOEditingContext editingContext)
    {
        return (WorkerDescriptor)EOUtilities.localInstanceOfObject(
            editingContext, this);
    }


    // ----------------------------------------------------------
    /**
     * Get a list of changes between this object's current state and the
     * last committed version.
     * @return a dictionary of the changes that have not yet been committed
     */
    @SuppressWarnings("unchecked")
    public NSDictionary<String, Object> changedProperties()
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
     * Retrieve this object's <code>currentJobId</code> value.
     * @return the value of the attribute
     */
    public long currentJobId()
    {
        Long result =
            (Long)storedValueForKey( "currentJobId" );
        return ( result == null )
            ? 0L
            : result.longValue();
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>currentJobId</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setCurrentJobId( long value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setCurrentJobId("
                + value + "): was " + currentJobId() );
        }
        Long actual =
            new Long( value );
            setCurrentJobIdRaw( actual );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>currentJobId</code> value.
     * @return the value of the attribute
     */
    public Long currentJobIdRaw()
    {
        return (Long)storedValueForKey( "currentJobId" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>currentJobId</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setCurrentJobIdRaw( Long value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setCurrentJobIdRaw("
                + value + "): was " + currentJobIdRaw() );
        }
        takeStoredValueForKey( value, "currentJobId" );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>isAlive</code> value.
     * @return the value of the attribute
     */
    public boolean isAlive()
    {
        Integer result =
            (Integer)storedValueForKey( "isAlive" );
        return ( result == null )
            ? false
            : ( result.intValue() > 0 );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>isAlive</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setIsAlive( boolean value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setIsAlive("
                + value + "): was " + isAlive() );
        }
        Integer actual =
            er.extensions.eof.ERXConstant.integerForInt( value ? 1 : 0 );
            setIsAliveRaw( actual );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>isAlive</code> value.
     * @return the value of the attribute
     */
    public Integer isAliveRaw()
    {
        return (Integer)storedValueForKey( "isAlive" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>isAlive</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setIsAliveRaw( Integer value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setIsAliveRaw("
                + value + "): was " + isAliveRaw() );
        }
        takeStoredValueForKey( value, "isAlive" );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve the entity pointed to by the <code>host</code>
     * relationship.
     * @return the entity in the relationship
     */
    public org.webcat.jobqueue.HostDescriptor host()
    {
        return (org.webcat.jobqueue.HostDescriptor)storedValueForKey( "host" );
    }


    // ----------------------------------------------------------
    /**
     * Set the entity pointed to by the <code>host</code>
     * relationship (DO NOT USE--instead, use
     * <code>setHostRelationship()</code>.
     * This method is provided for WebObjects use.
     *
     * @param value The new entity to relate to
     */
    public void setHost( org.webcat.jobqueue.HostDescriptor value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setHost("
                + value + "): was " + host() );
        }
        takeStoredValueForKey( value, "host" );
    }


    // ----------------------------------------------------------
    /**
     * Set the entity pointed to by the <code>host</code>
     * relationship.  This method is a type-safe version of
     * <code>addObjectToBothSidesOfRelationshipWithKey()</code>.
     *
     * @param value The new entity to relate to
     */
    public void setHostRelationship(
        org.webcat.jobqueue.HostDescriptor value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setHostRelationship("
                + value + "): was " + host() );
        }
        if ( value == null )
        {
            org.webcat.jobqueue.HostDescriptor object = host();
            if ( object != null )
                removeObjectFromBothSidesOfRelationshipWithKey( object, "host" );
        }
        else
        {
            addObjectToBothSidesOfRelationshipWithKey( value, "host" );
        }
    }


    // ----------------------------------------------------------
    /**
     * Retrieve the entity pointed to by the <code>queue</code>
     * relationship.
     * @return the entity in the relationship
     */
    public org.webcat.jobqueue.QueueDescriptor queue()
    {
        return (org.webcat.jobqueue.QueueDescriptor)storedValueForKey( "queue" );
    }


    // ----------------------------------------------------------
    /**
     * Set the entity pointed to by the <code>queue</code>
     * relationship (DO NOT USE--instead, use
     * <code>setQueueRelationship()</code>.
     * This method is provided for WebObjects use.
     *
     * @param value The new entity to relate to
     */
    public void setQueue( org.webcat.jobqueue.QueueDescriptor value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setQueue("
                + value + "): was " + queue() );
        }
        takeStoredValueForKey( value, "queue" );
    }


    // ----------------------------------------------------------
    /**
     * Set the entity pointed to by the <code>queue</code>
     * relationship.  This method is a type-safe version of
     * <code>addObjectToBothSidesOfRelationshipWithKey()</code>.
     *
     * @param value The new entity to relate to
     */
    public void setQueueRelationship(
        org.webcat.jobqueue.QueueDescriptor value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setQueueRelationship("
                + value + "): was " + queue() );
        }
        if ( value == null )
        {
            org.webcat.jobqueue.QueueDescriptor object = queue();
            if ( object != null )
                removeObjectFromBothSidesOfRelationshipWithKey( object, "queue" );
        }
        else
        {
            addObjectToBothSidesOfRelationshipWithKey( value, "queue" );
        }
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
    public static NSArray<WorkerDescriptor> objectsWithFetchSpecification(
        EOEditingContext context,
        EOFetchSpecification fspec)
    {
        return context.objectsWithFetchSpecification(fspec);
    }


    // ----------------------------------------------------------
    /**
     * Retrieve all objects of this type.
     *
     * @param context The editing context to use
     *
     * @return an NSArray of the entities retrieved
     */
    public static NSArray<WorkerDescriptor> allObjects(
        EOEditingContext context)
    {
        return objectsMatchingQualifier(context, null, null);
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
    public static NSArray<WorkerDescriptor> objectsMatchingQualifier(
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
    public static NSArray<WorkerDescriptor> objectsMatchingQualifier(
        EOEditingContext context,
        EOQualifier qualifier,
        NSArray<EOSortOrdering> sortOrderings)
    {
        EOFetchSpecification fspec = new EOFetchSpecification(
            ENTITY_NAME, qualifier, sortOrderings);
        fspec.setUsesDistinct(true);
        return objectsWithFetchSpecification(context, fspec);
    }


    // ----------------------------------------------------------
    /**
     * Retrieve the first object that matches a qualifier, when
     * sorted with the specified sort orderings.
     *
     * @param context The editing context to use
     * @param qualifier The qualifier to use
     * @param sortOrderings the sort orderings
     *
     * @return the first entity that was retrieved, or null if there was none
     */
    public static WorkerDescriptor firstObjectMatchingQualifier(
        EOEditingContext context,
        EOQualifier qualifier,
        NSArray<EOSortOrdering> sortOrderings)
    {
        NSArray<WorkerDescriptor> results =
            objectsMatchingQualifier(context, qualifier, sortOrderings);
        return (results.size() > 0)
            ? results.get(0)
            : null;
    }


    // ----------------------------------------------------------
    /**
     * Retrieve a single object using a list of keys and values to match.
     *
     * @param context The editing context to use
     * @param qualifier The qualifier to use
     *
     * @return the single entity that was retrieved
     *
     * @throws EOUtilities.MoreThanOneException
     *     if there is more than one matching object
     */
    public static WorkerDescriptor uniqueObjectMatchingQualifier(
        EOEditingContext context,
        EOQualifier qualifier) throws EOUtilities.MoreThanOneException
    {
        NSArray<WorkerDescriptor> results =
            objectsMatchingQualifier(context, qualifier);
        if (results.size() > 1)
        {
            throw new EOUtilities.MoreThanOneException(null);
        }
        return (results.size() > 0)
            ? results.get(0)
            : null;
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
    public static NSArray<WorkerDescriptor> objectsMatchingValues(
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
    public static NSArray<WorkerDescriptor> objectsMatchingValues(
        EOEditingContext context,
        NSDictionary<String, Object> keysAndValues)
    {
        return EOUtilities.objectsMatchingValues(context, ENTITY_NAME,
            keysAndValues);
    }


    // ----------------------------------------------------------
    /**
     * Retrieve the first object that matches a set of keys and values, when
     * sorted with the specified sort orderings.
     *
     * @param context The editing context to use
     * @param sortOrderings the sort orderings
     * @param keysAndValues a list of keys and values to match, alternating
     *     "key", "value", "key", "value"...
     *
     * @return the first entity that was retrieved, or null if there was none
     */
    public static WorkerDescriptor firstObjectMatchingValues(
        EOEditingContext context,
        NSArray<EOSortOrdering> sortOrderings,
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

        return firstObjectMatchingValues(
            context, sortOrderings, valueDictionary);
    }


    // ----------------------------------------------------------
    /**
     * Retrieves the first object that matches a set of keys and values, when
     * sorted with the specified sort orderings.
     *
     * @param context The editing context to use
     * @param sortOrderings the sort orderings
     * @param keysAndValues a dictionary of keys and values to match
     *
     * @return the first entity that was retrieved, or null if there was none
     */
    public static WorkerDescriptor firstObjectMatchingValues(
        EOEditingContext context,
        NSArray<EOSortOrdering> sortOrderings,
        NSDictionary<String, Object> keysAndValues)
    {
        EOFetchSpecification fspec = new EOFetchSpecification(
            ENTITY_NAME,
            EOQualifier.qualifierToMatchAllValues(keysAndValues),
            sortOrderings);
        fspec.setFetchLimit(1);

        NSArray<WorkerDescriptor> result =
            objectsWithFetchSpecification( context, fspec );

        if ( result.count() == 0 )
        {
            return null;
        }
        else
        {
            return result.objectAtIndex(0);
        }
    }


    // ----------------------------------------------------------
    /**
     * Retrieve a single object using a list of keys and values to match.
     *
     * @param context The editing context to use
     * @param keysAndValues a list of keys and values to match, alternating
     *     "key", "value", "key", "value"...
     *
     * @return the single entity that was retrieved, or null if there was none
     *
     * @throws EOUtilities.MoreThanOneException
     *     if there is more than one matching object
     */
    public static WorkerDescriptor uniqueObjectMatchingValues(
        EOEditingContext context,
        Object... keysAndValues) throws EOUtilities.MoreThanOneException
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

        return uniqueObjectMatchingValues(context, valueDictionary);
    }


    // ----------------------------------------------------------
    /**
     * Retrieve an object using a dictionary of keys and values to match.
     *
     * @param context The editing context to use
     * @param keysAndValues a dictionary of keys and values to match
     *
     * @return the single entity that was retrieved, or null if there was none
     *
     * @throws EOUtilities.MoreThanOneException
     *     if there is more than one matching object
     */
    public static WorkerDescriptor uniqueObjectMatchingValues(
        EOEditingContext context,
        NSDictionary<String, Object> keysAndValues)
        throws EOUtilities.MoreThanOneException
    {
        try
        {
            return (WorkerDescriptor)EOUtilities.objectMatchingValues(
                context, ENTITY_NAME, keysAndValues);
        }
        catch (EOObjectNotAvailableException e)
        {
            return null;
        }
    }


    // ----------------------------------------------------------
    /**
     * Retrieve the count of all objects of this type.
     *
     * @param context The editing context to use
     *
     * @return the count of all objects
     */
    public static int countOfAllObjects(EOEditingContext context)
    {
        return countOfObjectsMatchingQualifier(context, null);
    }


    // ----------------------------------------------------------
    /**
     * Retrieve the count of objects that match a qualifier.
     *
     * @param context The editing context to use
     * @param qualifier The qualifier to use
     *
     * @return the count of objects matching the qualifier
     */
    public static int countOfObjectsMatchingQualifier(
        EOEditingContext context, EOQualifier qualifier)
    {
        return ERXEOControlUtilities.objectCountWithQualifier(
                context, ENTITY_NAME, qualifier);
    }


    // ----------------------------------------------------------
    /**
     * Retrieve the count of objects using a list of keys and values to match.
     *
     * @param context The editing context to use
     * @param keysAndValues a list of keys and values to match, alternating
     *     "key", "value", "key", "value"...
     *
     * @return the count of objects that match the specified values
     */
    public static int countOfObjectsMatchingValues(
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

        return countOfObjectsMatchingValues(context, valueDictionary);
    }


    // ----------------------------------------------------------
    /**
     * Retrieve the count of objects using a dictionary of keys and values to
     * match.
     *
     * @param context The editing context to use
     * @param keysAndValues a dictionary of keys and values to match
     *
     * @return the count of objects that matched the specified values
     */
    public static int countOfObjectsMatchingValues(
        EOEditingContext context,
        NSDictionary<String, Object> keysAndValues)
    {
        return countOfObjectsMatchingQualifier(context,
                EOQualifier.qualifierToMatchAllValues(keysAndValues));
    }


    // ----------------------------------------------------------
    /**
     * Retrieve objects according to the <code>descriptorsForHost</code>
     * fetch specification.
     *
     * @param context The editing context to use
     * @param hostBinding fetch spec parameter
     * @return an NSArray of the entities retrieved
     */
    public static NSArray<WorkerDescriptor> descriptorsForHost(
            EOEditingContext context,
            org.webcat.jobqueue.HostDescriptor hostBinding
        )
    {
        EOFetchSpecification spec = EOFetchSpecification
            .fetchSpecificationNamed( "descriptorsForHost", "WorkerDescriptor" );

        NSMutableDictionary<String, Object> bindings =
            new NSMutableDictionary<String, Object>();

        if ( hostBinding != null )
        {
            bindings.setObjectForKey( hostBinding,
                                      "host" );
        }
        spec = spec.fetchSpecificationWithQualifierBindings( bindings );

        NSArray<WorkerDescriptor> result =
            objectsWithFetchSpecification( context, spec );
        if (log.isDebugEnabled())
        {
            log.debug( "descriptorsForHost(ec"
                + ", " + hostBinding
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

    static Logger log = Logger.getLogger( WorkerDescriptor.class );
}
