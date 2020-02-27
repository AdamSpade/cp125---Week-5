package com.scg.util;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Class for first, middle, and last name of consultant.
 * @author Adam Spade
 *
 */
//@SuppressWarnings("serial")
public final class PersonalName
extends Object
implements Comparable<PersonalName>, Serializable
{
    /**
     * Serial Version ID.
     */
    private static final long serialVersionUID = 6857599177605227980L;
    // MEMBER VARIABLES \\
    /**
     * No last name initializer.
     */
    private static final String NLN = "NLN";
    /**
     * No first name initializer.
     */
    private static final String NFN = "NFN";
    /**
     * No middle name initializer.
     */
    private static final String NMN = "NMN";
    
    /**
     * Comparator to check name equality based on first name, then middle name, then last name filters.
     */
    private static Comparator<PersonalName> personalNameComparator = Comparator
            .comparing(PersonalName::getFirstName)
            .thenComparing(PersonalName::getMiddleName)
            .thenComparing(PersonalName::getLastName);
    /**
     * Last name initializer.
     */
    private String lastName;
    /**
     * First name initializer.
     */
    private String firstName;
    /**
     * Middle name initializer.
     */
    private String middleName;
    
    // CONSTRUCTORS \\
    /**
     * New personal name constructor with NLN, NFN, and NMN.
     */
    public PersonalName() {
        this(NLN, NFN, NMN);
    }
    /**
     * New personal name constructor with last and first names.
     * @param lastName of consultant.
     * @param firstName of consultant.
     */
    public PersonalName(String lastName, String firstName) {
        this(lastName, firstName, NMN);
    }
    /**
     * New personal name constructor with last, first, and middle name included.
      * @param lastName of consultant.
     * @param firstName of consultant.
     * @param middleName of consultant.
     */
    public PersonalName(String lastName, String firstName, String middleName) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
    }
    
    // GETTERS & SETTERS \\
    /**
     * Getter for last name.
     * @return last name.
     */
    public String getLastName() {
        return this.lastName;
    }
    /**
     * Setter for last name.
     * @param lastName of consultant.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    /**
     * Getter for first name.
     * @return first name.
     */
    public String getFirstName() {
        return this.firstName;
    }
    /**
     * Setter for first name.
     * @param firstName of consultant.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    /**
     * Getter for middle name.
     * @return middle name.
     */
    public String getMiddleName() {
        return this.middleName;
    }
    /**
     * Setter for middle name.
     * @param middleName of consultant.
     */
    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }
    
    // ADDITIONAL METHODS \\
    /**
     * Method for calculating hashcode of personal name.
     * @return result hashcode value.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
        result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
        result = prime * result + ((middleName == null) ? 0 : middleName.hashCode());
        return result;
    }
    /**
     * Method for checking equality of objects.
     * @return true if all else are false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PersonalName other = (PersonalName) obj;
        if (firstName == null) {
            if (other.firstName != null)
                return false;
        } else if (!firstName.equals(other.firstName))
            return false;
        if (lastName == null) {
            if (other.lastName != null)
                return false;
        } else if (!lastName.equals(other.lastName))
            return false;
        if (middleName == null) {
            if (other.middleName != null)
                return false;
        } else if (!middleName.equals(other.middleName))
            return false;
        return true;
    }
    /**
     * Creates string in order of last name, first name, middle name.
     * @return string of last, first, middle name of consultant.
     */
    public String toString() {
        StringBuilder bldr = new StringBuilder();
        bldr.append(getLastName())
        .append(", ")
        .append(getFirstName())
        .append(" ")
        .append(getMiddleName());
        
        return bldr.toString();
    }
    
    /**
     * CompareTo method that checks 'this' name value vs. 'other' name value.
     * @param other name that this name value is being equated against.
     * @return zero if names match, negative if this name value is less than other, positive if
     * this name value is greater than other.
     */
    @Override
    public int compareTo(final PersonalName other) {
        return (this == other) ? 0 : personalNameComparator.compare(this, other);
    }
}