package com.scg.util;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Address class to create a formatted address block.
 * @author Adam Spade
 */
public final class Address 
implements Comparable<Address>, Serializable
{
    // MEMBER VARIABLES \\
    /**
     * Serial Version ID.
     */
    private static final long serialVersionUID = 4264783999494410853L;
    /**
     * Comparator that checks for address equality based on state, postal code and street number values.
     */
    private static Comparator<Address> addressComparator = Comparator
            .comparing(Address::getState)
            .thenComparing(Address::getPostalCode)
            .thenComparing(Address::getStreetNumber);
    /**
     * Street number initializer.
     */
    private final String streetNumber;
    /**
     * City initializer.
     */
    private final String city;
    /**
     * State code initializer.
     */
    private final StateCode state;
    /**
     * Postal Code initializer.
     */
    private final String postalCode;
    
    // CONSTRUCTOR \\
    /**
     * Address constructor used to build the address.
     * @param streetNumber of the address.
     * @param city in the address.
     * @param state in the address.
     * @param postalCode of the address.
     */
    public Address(final String streetNumber, final String city, 
                   final StateCode state, final String postalCode) {
                        this.streetNumber = streetNumber;
                        this.city = city;
                        this.state = state;
                        this.postalCode = postalCode;
    }
    
    // GETTERS & SETTERS \\
    /**
     * Getter for street number
     * @return street number of address.
     */
    public String getStreetNumber() {
        return streetNumber;
    }

    /**
     * Getter for city. 
     * @return city in the address.
     */
    public String getCity() {
        return city;
    }

    /**
     * Getter for State code.
     * @return state.
     */
    public StateCode getState() {
        return state;
    }

    /**
     * Getter for zip code.
     * @return postal code.
     */
    public String getPostalCode() {
        return postalCode;
    }

    // ADDITIONAL METHODS \\
    /**
     * Method for calculating the hash code of address.
     * @return result hashcode value.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((city == null) ? 0 : city.hashCode());
        result = prime * result + ((postalCode == null) ? 0 : postalCode.hashCode());
        result = prime * result + ((state == null) ? 0 : state.hashCode());
        result = prime * result + ((streetNumber == null) ? 0 : streetNumber.hashCode());
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
        Address other = (Address) obj;
        if (city == null) {
            if (other.city != null)
                return false;
        } else if (!city.equals(other.city))
            return false;
        if (postalCode == null) {
            if (other.postalCode != null)
                return false;
        } else if (!postalCode.equals(other.postalCode))
            return false;
        if (state != other.state)
            return false;
        if (streetNumber == null) {
            if (other.streetNumber != null)
                return false;
        } else if (!streetNumber.equals(other.streetNumber))
            return false;
        return true;
    }
    
//    Prints this address in the form:
//    street number
//    city, state postal code
    /**
     * String format of address.
     * @return address with street number, city, state, and zip code.
     */
    @Override
    public String toString() {
        return String.format("%s%n%s, %s %s%n",
                getStreetNumber(), 
                getCity(), 
                getState(), 
                getPostalCode());
    }
    /**
     * CompareTo method that checks for address equality between 'this' address and 'other' address.
     * @param other address to compare against this one.
     * @return zero if address values are equal, negative if this address is less than other, and
     * positive if this address is greater than other address.
     */
    @Override
    public int compareTo(Address other) {
        if(this == other) return 0;
        return addressComparator.compare(this, other);
    } 
}