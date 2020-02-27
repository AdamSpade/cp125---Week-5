package com.scg.domain;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Class for account name, date, hours, and skilltype to go on timecard.
 * @author Adam Spade
 */
public final class ConsultantTime 
extends Object 
implements Serializable
{
    // MEMBER VARIABLES \\
    /**
     * Serial Version UID.
     */
    private static final long serialVersionUID = -8195553192806518070L;
    /**
     * Account name initializer.
     */
    private Account account;
    /**
     * Date initializer.
     */
    private LocalDate date;
    /**
     * Hours initializer.
     */
    private int hours;
    /**
     * SkillType initializer.
     */
    private Skill skillType;

    // CONSTRUCTORS \\
    /**
     * Constructor for new ConsultantTime.
     * @param date of work done.
     * @param account type of work done (billable or non-billable).
     * @param skillType property
     * @param hours of work done. Always greater than zero.
     */
    public ConsultantTime(final LocalDate date, final Account account, 
                          final Skill skillType, final int hours) {
        this.date = date;
        this.account = account;
        this.skillType = skillType;
        setHours(hours);
    }
    
    // GETTERS & SETTERS \\
    /**
     * Getter for account name.
     * @return account name.
     */
    public Account getAccount() {
        return this.account;
    }
    /**
     * Setter for account name.
     * @param account name.
     */
    public void setAccount(Account account) {
        this.account = account;
    }
    /**
     * Getter for date.
     * @return date value.
     */
    public LocalDate getDate() {
        return this.date;
    }
    /**
     * Setter for date.
     * @param date value.
     */
    public void setDate(LocalDate date) {
        this.date = date;
    }
    /**
     * Getter for hours.
     * @return hours of work.
     */
    public int getHours() {
        return this.hours;
    }
    /**
     * Setter for hours.
     * @param hours of work done.
     * @throws IllegalArgumentException if hours are listed as zero or less.
     */
    public void setHours(int hours) 
    throws IllegalArgumentException{
        if(hours <= 0) {
            throw new IllegalArgumentException("hours must be greater than zero");
        }
        this.hours = hours;
    }

    /**
     * Getter for skill type.
     * @return skillType value.
     */
    public Skill getSkillType() {
        return this.skillType;
    }

    // ADDITIONAL METHODS \\
    /**
     * Method for calculating hashcode of consultant time.
     * @return result hashcode value.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((account == null) ? 0 : account.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        result = prime * result + hours;
        result = prime * result + ((skillType == null) ? 0 : skillType.hashCode());
        return result;
    }

    /**
     * Method for checking equality of objects.
     * @return true if all else are not false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ConsultantTime other = (ConsultantTime) obj;
        if (account == null) {
            if (other.account != null)
                return false;
        } else if (!account.equals(other.account))
            return false;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        if (hours != other.hours)
            return false;
        if (skillType != other.skillType)
            return false;
        return true;
    }
    /**
     * Determines if consultant time is billable.
     * @return true if time is billable, else false.
     */
    public boolean isBillable() {
        return this.getAccount().isBillable() ? true : false;
    }
    /**
     * Creates string of consultant time.
     * @return string of consultant time with account name, date, hours, skill type. 
     */
    public String toString() {
        String  line = String.format("%-29s %2$tm/%2$td/%2$tY", getAccount().getName(), getDate());
        String line2 = String.format("%7s %17s %n", getHours(), getSkillType());
        return  line + line2.toString();
    }
}