package com.scg.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Formatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Class TimeCard creates and outputs a completed timecard for each consultant. This includes all
 * billable and non-billable hours, all hours total, day of week that work was done, skilltype,
 * and account name.
 * @author Adam Spade
 */
public final class TimeCard
extends Object 
implements Comparable<TimeCard>, Serializable
{    
    /**
     * 
     */
    private static final long serialVersionUID = -2627568581952841046L;
    // FORMATTERS \\
    /**
     * Divider formatter for splitting timecards.
     */
    private static final String DIVIDER = String.format("=================================="
                                                      + "=================================%n");
    /**
     * Header formatter of each time card with consultant name and day of week.
     */
    private static final String HEADER = "Consultant: %-27s Week Starting: %2$tb %2$td, %2$tY%n%n";
    /**
     * Column formatter for column names account, date, hours, and skill
     */
    private static final String COLUMN_NAMES = String.format("%-29s %-11s %5s %6s%n" +
            "----------------------------  ----------  -----  ------------------%n",
            "Account", "Date", "Hours", "Skill");
    /**
     * Billable time formatter.
     */
    private static final String BILLABLE_TIME = String.format("Billable Time:%n");
    /**
     * Non-Billable time formatter.
     */
    private static final String NON_BILLABLE_TIME = String.format("Non-billable Time: %n");
    /**
     * Summary formatter.
     */
    private static final String SUMMARY = String.format("Summary:%n");
    /**
     * Total billable hours formatter.
     */
    private static final String TOTAL_BILLABLE = "Total Billable:%32s%n";
    /**
     * Total non-billable hours formatter.
     */
    private static final String TOTAL_NON_BILLABLE = "Total Non-Billable:%28s%n";
    /**
     * Total of all hours formatter.
     */
    private static final String TOTAL_HOURS = "Total Hours:%35s%n";
    /**
     * Space formatter for adding blank rows as separators.
     */
    private static final String SPACE = String.format("%n");
    
    // MEMBER VARIABLES \\
    /**
     * ArrayList initializer for billable hours for client.
     */
    private List<ConsultantTime> billableHoursForClient = new ArrayList<>();
    /**
     * ArrayList initializer for consulting hours.
     */
    private List<ConsultantTime> consultingHours;
    /**
     * Consultant initializer.
     */
    private Consultant consultant;
    /**
     * Total billable hours initializer.
     */
    private int totalBillableHours;
    /**
     * Total hours initializer.
     */
    private int totalHours;
    /**
     * Total non-billable hours initializer.
     */
    private int totalNonBillableHours;
    /**
     * Day of week initializer.
     */
    private LocalDate weekStartingDay;
//  private static Comparator<TimeCard> timeCardComparator = Comparator
//  .comparing(TimeCard::getWeekStartingDay)
//  .thenComparing(TimeCard::getConsultant)
//  .thenComparing(TimeCard::getTotalBillableHours)
//  .thenComparing(TimeCard::getTotalNonBillableHours);
    
    // CONSTRUCTORS \\
    /**
     * Constructor for new time card.
     * @param consultant to list on the time card along with their info.
     * @param weekStartingDay first work day of the week.
     */
    public TimeCard(Consultant consultant, LocalDate weekStartingDay) {
        this.consultant = consultant;
        this.weekStartingDay = weekStartingDay;
        this.totalBillableHours = 0;
        this.totalNonBillableHours = 0;
        this.consultingHours = new ArrayList<>();
    }
    
    // GETTERS & SETTERS \\
    /**
     * Getter for billable hours for client.
     * @param clientName name of client.
     * @return billableHoursForClient list.
     */
    public List<ConsultantTime> getBillableHoursForClient(String clientName) {
        return consultingHours
                            .stream()
//                              .filter(x->x.isBillable()) alternative to line directly below
                            .filter(ConsultantTime::isBillable)
                            .filter(hrs -> clientName.equals(hrs.getAccount().getName()))
                            .collect(Collectors.toList());

    }

    /**
     * Getter for consultant name.
     * @return consultant name.
     */
    public Consultant getConsultant() {
        return this.consultant;
    }
    /**
     * Getter for consulting hours.
     * @return consultingHours list.
     */
    public List<ConsultantTime> getConsultingHours() {
        return this.consultingHours;
    }
    /**
     * Getter for total billable hours.
     * @return totalBillabeHhours.
     */
    public int getTotalBillableHours() {
        return this.totalBillableHours;
    }
    /**
     * Getter for total hours.
     * @return getTotalBillabeHours + getTotalNoneBillabeHours for hours total.
     */
    public int getTotalHours() {
        return getTotalBillableHours() + getTotalNonBillableHours();
    }
    /**
     * Getter for total non-billabe hours.
     * @return totalNonBillableHours.
     */
    public int getTotalNonBillableHours() {
        return this.totalNonBillableHours;
    }
    /**
     * Getter for starting day of week.
     * @return weekStartingDay.
     */
    public LocalDate getWeekStartingDay() {
        return this.weekStartingDay;
    }
        
    // ADDITIONAL METHODS \\
    /**
     * Method to add consulting time object either of two lists: billable and non-billable.
     * @param consultantTime time to be added to billabe and non-billabe lists.
     */
    public void addConsultantTime(ConsultantTime consultantTime) {
        consultingHours.add(consultantTime);
        final int addHours = consultantTime.getHours();
        if(consultantTime.isBillable()) {
            totalBillableHours += addHours;
        }
        else if(!consultantTime.isBillable()) {
            totalNonBillableHours += addHours;
        }
    }
    /**
     * Creates as string with consultant name and work week starting day.
     * @return string with consultant name and starting day of work week.
     */
    public String toString() {
        Formatter formatted = new Formatter();
        formatted.format(HEADER, getConsultant(), getWeekStartingDay());
        String report = formatted.toString();
        formatted.close();
        return report;
    }
    /**
     * Creates the entire string for output to the time card.
     * @return full time card as string.
     */
    public String toReportString() {  
        StringBuilder billYes = new StringBuilder();
        StringBuilder billNo = new StringBuilder();

        for(ConsultantTime getBill : consultingHours) {
            if(getBill.getAccount().isBillable()){
                billYes.append(getBill.toString());
            }
        }
        for(ConsultantTime getBill : consultingHours) {
            if(!getBill.getAccount().isBillable()){
                billNo.append(getBill.toString());
            }
        } 
        Formatter tb = new Formatter();
        tb.format(TOTAL_BILLABLE, getTotalBillableHours());
        Formatter nb = new Formatter();
        nb.format(TOTAL_NON_BILLABLE, getTotalNonBillableHours());
        Formatter th = new Formatter();
        th.format(TOTAL_HOURS, getTotalHours());
        
        StringBuilder timecard = new StringBuilder();
        timecard.append(DIVIDER)
        .append(toString())
        .append(BILLABLE_TIME)
        .append(COLUMN_NAMES)
        .append(billYes)
        .append(SPACE)
        .append(NON_BILLABLE_TIME)
        .append(COLUMN_NAMES)
        .append(billNo)
        .append(SPACE)
        .append(SUMMARY)
        .append(tb)
        .append(nb)
        .append(th)
        .append(DIVIDER);
        return timecard.toString();
    }

    /**
     * CompareTo method to check equality of time cards based on starting date, consultant, total
     * billable hours, and total non-billable hours.
     * @param other time card being compared against 'this' time card object.
     * @return zero if time cards are equal value, negative value if first time card is less than second
     * time card, and positive if first time card is greater than second time card.
     */
    @Override
    public int compareTo(TimeCard other) {
        int diff = 0;
        if(this != other) {
            if((diff = this.getWeekStartingDay().compareTo(other.getWeekStartingDay())) == 0)
            if((diff = this.getConsultant().getName().compareTo(other.getConsultant().getName())) == 0)
            if((diff = Integer.compare(this.getTotalBillableHours(), other.getTotalBillableHours())) == 0)
            if((diff = Integer.compare(this.getTotalNonBillableHours(), other.getTotalNonBillableHours())) == 0);
        }
        return diff;
//        if(this == other) return 0;
//        return timeCardComparator.compare(this, other);
    }
}