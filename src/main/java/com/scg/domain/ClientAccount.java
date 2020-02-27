package com.scg.domain;

import java.util.Comparator;

import com.scg.util.Address;
import com.scg.util.PersonalName;

/**
 * Class for billable accounts with contact name and address.
 * @author Adam Spade
 *
 */
public final class ClientAccount 
implements Account, Comparable<ClientAccount>
{
    // MEMBER VARIABLES
    /**
     * String variable for name.
     */
    private final String name;
    /**
     * PersonalName variable for contact.
     */
    private PersonalName contact;
    /**
     * Address variable for address.
     */
    private Address address;
//    private static Comparator<ClientAccount> clientAccountComparator = Comparator
//            .comparing(ClientAccount::getName)
//            .thenComparing(ClientAccount::getContact)
//            .thenComparing(ClientAccount::getAddress);

    // CONSTRUCTOR \\
    /**
     * ClientAccount new instance constructor.
     * @param name of client.
     * @param contact name of consultant.
     * @param address of the client.
     */
    public ClientAccount(final String name, final PersonalName contact, final Address address) {
        this.name = name;
        this.contact = contact;
        this.address =  address;
    }
    
    // GETTER & SETTERS \\
    /**
     * getter for account name
     * @return name of property
     */
    @Override
    public String getName() {
        return name;
    }
    /**
     * getter for contact name
     * @return contact name
     */
    public PersonalName getContact() {
        return contact;
    }
    /**
     * setter for contact name
     * @param contact name
     */
    public void setContact(PersonalName contact) {
        this.contact = contact;
    }
    
    /**
     * Getter for address.
     * @return address.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Setter for address.
     * @param address being set.
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    // ADDITIONAL METHODS \\
    /**
     * determine if account is billable
     * @return true always for this one
     */
    @Override
    public boolean isBillable() {
        return true;
    }

    /**
     * String format for client account.
     * @return client account including name, address, and contact as string.
     */
    @Override
    public String toString() {
        return String.format("%s%n%s%s%n",
                getName(), getAddress().toString(), getContact().toString());
    }

    /**
     * CompareTo method that checks for equality between 'this' Client Account and the 'other'
     * Client Account.
     * @param other is the client account being compared against this one.
     * @return zero if accounts are equal, negative if first account is less than second, and 
     * positive if first is greater than second.
     */
    @Override
    public int compareTo(final ClientAccount other) {
        int diff = 0;
        if(this != other) {
            if((diff = this.getName().compareTo(other.getName())) == 0)
            if((diff = this.getContact().compareTo(other.getContact())) == 0)
            if((diff = this.getAddress().compareTo(other.getAddress())) == 0);
        }
        return diff;
                
//        if(this == other) 
//            return 0;
//        return clientAccountComparator.compare(this, other);
    }
}