package com.scg.domain;

import java.time.LocalDate;

/**
 * Invoice line item class which builds a single line to be added to the invoice. Line includes
 * the date of work, consultant name, skilltype, hours worked, and charges for hours worked.
 * @author Adam Spade
 *
 */
public final class InvoiceLineItem 
{
    // Similar to timecard line item
    // FORMATTER \\
    /**
     * Formatter for the line time to be added to the invoice.
     */
    private static final String INVOICE_LINE_ITEM = "%1$tm/%1$td/%1$tY   "
                                                  + "%2$s %3$29s %4$8d   %5$,6.2f%n";
    
    // MEMBER VARIABLES \\
    /**
     * Initializer for date.
     */
    private final LocalDate date;
    /**
     * Initializer for consultant.
     */
    private final Consultant consultant;
    /**
     * Initializer for skill.
     */
    private final Skill skill;
    /**
     * Initializer for hours.
     */
    private final int hours;
    /**
     * Initializer for charge.
     */
    private final int charge;
    
    // CONSTRUCTORS \\
    /**
     * Constructor for line item that includes the date for work done, consultant name, skill type,
     * hours of work done, and charges for work done.
     * @param date of work done.
     * @param consultant name.
     * @param skill skiltype.
     * @param hours of work done.
     */
    public InvoiceLineItem(final LocalDate date, final Consultant consultant, 
                           final Skill skill, final int hours) {
                                    this.date = date;
                                    this.consultant = consultant;
                                    this.skill = skill;
                                    this.hours = hours;
                                    this.charge = skill.getRate() * hours;
    }

    // GETTERS & SETTERS \\
    /**
     * Getter for the date.
     * @return date of the work done on the line item.
     */
    public LocalDate getDate() {
        return date;
    }
    /**
     * Getter for consultant name.
     * @return consultant name on the line item.
     */
    public Consultant getConsultant() {
        return consultant;
    }
    /**
     * Getter for skill.
     * @return skill on the line item.
     */
    public Skill getSkill() {
        return skill;
    }
    /**
     * Getter for hours.
     * @return hours on the line item.
     */
    public int getHours() {
        return hours;
    }
    /**
     * Getter for charge.
     * @return charge on the line item.
     */
    public int getCharge() {
        return charge ;
    }

    /**
     * String representation of the invoice line item.
     * @return invoice line item that includes work date, consultant name, skill type, hours,
     * and charges for work done.
     */
    @Override
    public String toString() {
        return String.format(INVOICE_LINE_ITEM, 
                             getDate(), 
                             getConsultant().getName(), 
                             getSkill(), 
                             getHours(), 
                             (double)getCharge());
    }
}