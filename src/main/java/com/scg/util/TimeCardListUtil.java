package com.scg.util;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.scg.domain.Consultant;
import com.scg.domain.TimeCard;

/**
 * Utility class used for processing TimeCards.
 * @author Adam Spade
 */
public final class TimeCardListUtil 
{
    /**
     * New TimeCardConsultantComparator object initializer.
     */
    private static final TimeCardConsultantComparator tCardConsComptor = new TimeCardConsultantComparator();
    
    // GETTERS & SETTERS \\
    /**
     * Getter for retrieving the time cards of a consultant.
     * @param timeCards the list of time cards for a consultant.
     * @param consultant the consultant whose time cards are being retrieved.
     * @return the time cards for the desired consultant.
     */
    public static List<TimeCard> getTimeCardsForConsultant(final List<TimeCard> timeCards, final Consultant consultant) {
        return timeCards.stream()
                        .filter(tcard -> tcard.getConsultant()
                        .equals(consultant))
                        .collect(Collectors.toList());
    }
    /**
     * Getter for retrieving the time cards within a specific date range.
     * @param timeCards that fall within the given date range.
     * @param dateRange that the time cards are being pulled from.
     * @return time cards from the desired date range.
     */
    public static List<TimeCard> getTimeCardsForDateRange(final List<TimeCard> timeCards, final DateRange dateRange) {
        return timeCards.stream()
                        .filter(tcard -> dateRange.isInRange(tcard.getWeekStartingDay()) || 
                                dateRange.isInRange(tcard.getWeekStartingDay()
                        .plusDays(6)))
                        .collect(Collectors.toList());
    }   
    
    // ADDITIONAL METHODS \\
    /**
     * Method for sorting the time cards by date.
     * @param timeCards that will be sorted by date.
     */
    public static void sortByStartDate(List<TimeCard> timeCards) {
        Collections.sort(timeCards);
    }
    /**
     * Method for sorting the consultants by name.
     * @param timeCards that will be sorted for the named consultant.
     */
    public static void sortByConsultantName(List<TimeCard> timeCards) {
        Collections.sort(timeCards, tCardConsComptor);
    }
}