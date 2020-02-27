package com.scg.domain;

import java.io.Serializable;

/**
 * Interface class that gives account name and determines if account is billable or not-billable
 * @author Adam Spade
 */
public interface Account
extends Serializable
{
    /**
     * Getter for the name of this account.
     * @return name of this account
     */
    public String getName();
    /**
     * Determines if this account is billable.
     * @return true if account is billable, else false
     */
    public boolean isBillable();
}