package com.scg.domain;

/**
 * Class for non-billable accounts including business development, sick leave, vacation.
 * @author Adam Spade
 */
public enum NonBillableAccount 
implements Account
{
    // CONSTANTS \\
    /**
     * Constant for Business Development.
     */
    BUSINESS_DEVELOPMENT("Business Development"),
    /**
     * Constant for Sick Leave.
     */
    SICK_LEAVE("Sick Leave"),
    /**
     * Constant for Vacation.
     */
    VACATION("Vacation");

    /**
     * String variable for name.
     */
    private final String name;
    
    /**
     * Constructor for new NonBillableAccount.
     * @param name of non-billable account.
     */
    private NonBillableAccount(final String name) {
        this.name = name;
    }
    
    // ADDITIONAL METHODS \\
    /**
     * getter for name of non-billable account.
     * @return name for non-billable account.
     */
    @Override
    public String getName() {
        return this.name;
    }
    /**
     * Determines if this account is billable.
     * @return false always for this one.
     */
    @Override
    public boolean isBillable() {
        return false;
    }
    /**
     * Returns name for this enumerated value.
     * @return name for this value.
     */
    public String toString() {
        return name;
    }
}