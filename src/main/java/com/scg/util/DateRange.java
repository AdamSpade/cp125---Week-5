package com.scg.util;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;

/**
 * Class that creates a specified Date Range, which includes the start and end dates.
 * @author Adam Spade
 */
public final class DateRange 
extends Object
implements Serializable
{
    /**
     * 
     */
    private static final long serialVersionUID = 3768087309382219647L;
    // MEMBER VARIABLES \\
    /**
     * Initializer for startDate.
     */
    private LocalDate startDate;
    /**
     * Initializer for endDate.
     */
    private LocalDate endDate;

    // CONSTRUCTORS \\
    /**
     * Construct a Date Range given a Start Date and an End Date.
     * @param startDate first day of the Date Range.
     * @param endDate last day of the Date Range.
     */
    DateRange(final LocalDate startDate, final LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
    /**
     * Construct a Date Range for an entire month from the first day until the last day of month.
     * @param month that the Date Range is being constructed for.
     * @param year that the month the Date Range is being constructed for falls in.
     */
    DateRange(final Month month, final int year) {
        startDate = LocalDate.of(year, month, 1);
        endDate = startDate.plusMonths(1).minusDays(1);
//      endDate = startDate.plusDays(startDate.lengthOfMonth() - 1L);

    }
    /**
     * Construct a Date Range given two strings with the correct format.
     * @param start date of range. yyyy-MM-dd format.
     * @param end date of range. yyyy-MM-dd format.
     */
    DateRange(final String start, final String end) {
        startDate = LocalDate.parse(start, DateTimeFormatter.ISO_LOCAL_DATE);
        endDate = LocalDate.parse(end, DateTimeFormatter.ISO_LOCAL_DATE);
    }

    // GETTERS & SETTERS \\
    /**
     * Getter for the Start Date of the date range.
     * @return startDate of date range.
     */
    public LocalDate getStartDate() {
        return startDate;
    }
    /**
     * Getter for End Date of the date range.
     * @return endDate of the date range.
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    // ADDITIONAL METHODS \\
    /**
     * Method to check if date is within desired range. Returns true if date is within range.
     * @param date that is being verified is within date range.
     * @return true if date is within date range, else omit.
     */
    public boolean isInRange(LocalDate date) {
        return !(date.isBefore(startDate) || date.isAfter(endDate));
    }
}