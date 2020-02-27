package com.scg.domain;

import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;

import com.scg.util.PersonalName;

/**
 * Class consultant name.
 * @author Adam Spade
 */
public class Consultant 
extends Object
implements Comparable<Consultant>, Serializable 
{
    // MEMBER VARIABLES \\
    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -3725246623522472152L;
    /**
     * Personal name initializer.
     */
    private final PersonalName name;
    
    // CONSTRUCTORS \\
    /**
     * Constructor to create new consultant.
     * @param name of consultant.
     */
    public Consultant(final PersonalName name) {
        this.name = name;
    }
    
    // INNER CLASS \\
    private static final class SerializationProxy implements Serializable{
        /**
         * Serial Version UID.
         */
        private static final long serialVersionUID = -7771234947557911446L;
        private final String first;
        private final String middle;
        private final String last;
    
        SerializationProxy(final Consultant consultant){
            final PersonalName name = consultant.getName();
            first = name.getFirstName();
            middle = name.getMiddleName();
            last = name.getLastName();
        }
        private Object readResolve() {
            //final String msg = String.format("De-Serialized consultant: %s, %s %s", first, middle, last);
            PersonalName personalName = new PersonalName(first, middle, last);
            Consultant consultant = new Consultant(personalName);
            return consultant;
        }
    }
    
    // GETTERS & SETTERS \\
    /**
     * Getter for name of consultant.
     * @return name of consultant.
     */
    public final PersonalName getName() {
        return this.name;
    } 
    
    // ADDITIONAL METHODS \\
    /**
     * Returns string of consultant's name.
     * @return name of consultant as string.
     */
    @Override
    public final String toString() {
        return name.toString();
    }

    /**
     * CompareTo method for checking equality between 'this' Consultant and the 'other' Consultant.
     * @param other consultant.
     * @return zero if consultant name values are equivalent, negative if first is less than second ,
     * and positive if first is greater than second.
     */
    @Override
    public final int compareTo(Consultant other) {
        int diff = 0;
        if(this != other) {
            if((diff = this.getName().compareTo(other.getName())) == 0);
        }
        return diff;
//        if(this == other) return 0;
//        return getName().compareTo(other.getName());
    }

    private Object writeReplace() {
        //final String msg = String.format("Serializing consultant: %s", getName());
        return new SerializationProxy(this);
    }
    
    private void readObject(final ObjectInputStream ois) 
    throws InvalidObjectException {
        throw new InvalidObjectException("Proxy required");
    }
}