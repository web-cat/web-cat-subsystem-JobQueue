/*==========================================================================*\
 |  _WorkerDescriptor.java
 |*-------------------------------------------------------------------------*|
 |  Created by eogenerator
 |  DO NOT EDIT.  Make changes to WorkerDescriptor.java instead.
 |*-------------------------------------------------------------------------*|
 |  Copyright (C) 2008 Virginia Tech
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
 * WorkerDescriptor.java.
 *
 * @author Generated by eogenerator
 * @version version suppressed to control auto-generation
 */
public abstract class _WorkerDescriptor
    extends er.extensions.ERXGenericRecord
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
     * _WorkerDescriptor object given required
     * attributes and relationships.
     * @param editingContext The context in which the new object will be
     * inserted
     * @param isAllocated
     * @param isRunning
     * @return The newly created object
     */
    public static WorkerDescriptor create(
        EOEditingContext editingContext,
        boolean isAllocated,
        boolean isRunning
        )
    {
        WorkerDescriptor eoObject = (WorkerDescriptor)
            EOUtilities.createAndInsertInstance(
                editingContext,
                _WorkerDescriptor.ENTITY_NAME);
        eoObject.setIsAllocated(isAllocated);
        eoObject.setIsRunning(isRunning);
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
            NSArray results = EOUtilities.objectsMatchingKeyAndValue( ec,
                ENTITY_NAME, "id", new Integer( id ) );
            if ( results != null && results.count() > 0 )
            {
                obj = (WorkerDescriptor)results.objectAtIndex( 0 );
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
        return forId( ec, er.extensions.ERXValueUtilities.intValue( id ) );
    }


    //~ Constants (for key names) .............................................

    // Attributes ---
    public static final String ID_ON_HOST_KEY = "idOnHost";
    public static final String IS_ALLOCATED_KEY = "isAllocated";
    public static final String IS_RUNNING_KEY = "isRunning";
    // To-one relationships ---
    public static final String HOST_KEY = "host";
    public static final String QUEUE_KEY = "queue";
    // To-many relationships ---
    // Fetch specifications ---
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
            return er.extensions.ERXConstant.ZeroInteger;
        }
    }

    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>idOnHost</code> value.
     * @return the value of the attribute
     */
    public int idOnHost()
    {
        Number result =
            (Number)storedValueForKey( "idOnHost" );
        return ( result == null )
            ? 0
            : result.intValue();
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>idOnHost</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setIdOnHost( int value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setIdOnHost("
                + value + "): was " + idOnHost() );
        }
        Number actual =
            er.extensions.ERXConstant.integerForInt( value );
        setIdOnHostRaw( actual );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>idOnHost</code> value.
     * @return the value of the attribute
     */
    public Number idOnHostRaw()
    {
        return (Number)storedValueForKey( "idOnHost" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>idOnHost</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setIdOnHostRaw( Number value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setIdOnHostRaw("
                + value + "): was " + idOnHostRaw() );
        }
        takeStoredValueForKey( value, "idOnHost" );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>isAllocated</code> value.
     * @return the value of the attribute
     */
    public boolean isAllocated()
    {
        Number result =
            (Number)storedValueForKey( "isAllocated" );
        return ( result == null )
            ? false
            : ( result.intValue() > 0 );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>isAllocated</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setIsAllocated( boolean value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setIsAllocated("
                + value + "): was " + isAllocated() );
        }
        Number actual =
            er.extensions.ERXConstant.integerForInt( value ? 1 : 0 );
        setIsAllocatedRaw( actual );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>isAllocated</code> value.
     * @return the value of the attribute
     */
    public Number isAllocatedRaw()
    {
        return (Number)storedValueForKey( "isAllocated" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>isAllocated</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setIsAllocatedRaw( Number value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setIsAllocatedRaw("
                + value + "): was " + isAllocatedRaw() );
        }
        takeStoredValueForKey( value, "isAllocated" );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>isRunning</code> value.
     * @return the value of the attribute
     */
    public boolean isRunning()
    {
        Number result =
            (Number)storedValueForKey( "isRunning" );
        return ( result == null )
            ? false
            : ( result.intValue() > 0 );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>isRunning</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setIsRunning( boolean value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setIsRunning("
                + value + "): was " + isRunning() );
        }
        Number actual =
            er.extensions.ERXConstant.integerForInt( value ? 1 : 0 );
        setIsRunningRaw( actual );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve this object's <code>isRunning</code> value.
     * @return the value of the attribute
     */
    public Number isRunningRaw()
    {
        return (Number)storedValueForKey( "isRunning" );
    }


    // ----------------------------------------------------------
    /**
     * Change the value of this object's <code>isRunning</code>
     * property.
     *
     * @param value The new value for this property
     */
    public void setIsRunningRaw( Number value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setIsRunningRaw("
                + value + "): was " + isRunningRaw() );
        }
        takeStoredValueForKey( value, "isRunning" );
    }


    // ----------------------------------------------------------
    /**
     * Retrieve the entity pointed to by the <code>host</code>
     * relationship.
     * @return the entity in the relationship
     */
    public net.sf.webcat.jobqueue.HostDescriptor host()
    {
        return (net.sf.webcat.jobqueue.HostDescriptor)storedValueForKey( "host" );
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
    public void setHost( net.sf.webcat.jobqueue.HostDescriptor value )
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
        net.sf.webcat.jobqueue.HostDescriptor value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setHostRelationship("
                + value + "): was " + host() );
        }
        if ( value == null )
        {
            net.sf.webcat.jobqueue.HostDescriptor object = host();
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
    public net.sf.webcat.jobqueue.QueueDescriptor queue()
    {
        return (net.sf.webcat.jobqueue.QueueDescriptor)storedValueForKey( "queue" );
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
    public void setQueue( net.sf.webcat.jobqueue.QueueDescriptor value )
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
        net.sf.webcat.jobqueue.QueueDescriptor value )
    {
        if (log.isDebugEnabled())
        {
            log.debug( "setQueueRelationship("
                + value + "): was " + queue() );
        }
        if ( value == null )
        {
            net.sf.webcat.jobqueue.QueueDescriptor object = queue();
            if ( object != null )
                removeObjectFromBothSidesOfRelationshipWithKey( object, "queue" );
        }
        else
        {
            addObjectToBothSidesOfRelationshipWithKey( value, "queue" );
        }
    }


    //~ Instance/static variables .............................................

    static Logger log = Logger.getLogger( WorkerDescriptor.class );
}
