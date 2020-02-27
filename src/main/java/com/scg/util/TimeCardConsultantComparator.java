package com.scg.util;

import java.util.Comparator;

import com.scg.domain.TimeCard;

/**
 * Class for comparing two time cards in order of consultant, starting date, billable hours 
 * and non-billable hours.
 * @author Adam Spade
 */
public final class TimeCardConsultantComparator 
implements Comparator<TimeCard>
{
    /**
     * Comparator that compares consultant, starting date, total billable hours, and total non-billable 
     * hours of the two time cards.
     */
    private static Comparator<TimeCard> timeCardConsultantComparator = Comparator
            .comparing(TimeCard::getConsultant)
            .thenComparing(TimeCard::getWeekStartingDay)
            .thenComparing(TimeCard::getTotalBillableHours)
            .thenComparing(TimeCard::getTotalNonBillableHours);
    
    /**
     * Compare method to check equality between two time card objects sorting by consultant, starting date,
     * total billable hours and total non-billable hours.
     * @param firstTimeCard first time card to compare against second.
     * @param secondTimeCard second time card to compare against first.
     * @return timeCardConsultantComparator will be zero if tcards are equal, negative is first is less
     * than second, and positive if first is greater than second.
     */
    public int compare(TimeCard firstTimeCard, TimeCard secondTimeCard) {
        return timeCardConsultantComparator.compare(firstTimeCard, secondTimeCard);
    }
}
