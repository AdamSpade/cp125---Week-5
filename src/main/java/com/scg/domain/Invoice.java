package com.scg.domain;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Properties;

import com.scg.util.Address;
import com.scg.util.StateCode;

/**
 * Class to create invoices for clients for work done by consultants during a particular billing period.
 * Each invoice contains line items with the work date, the consultant name, the billable hours worked,
 * and the charges accrued from the work done. The business name is listed as the header.
 * @author Adam Spade
 */
public final class Invoice 
{
    // FORMATTERS \\
    /**
     * Column name formatter.
     */
    private static final String COLUMN_NAMES = String.format("%s %18s %22s %20s %7s %n" +
            "-----------  --------------------------  -------------------  -----  -----------%n",
            "Date", "Consultant", "Skill", "Hours", "Charge");
    /**
     * Items to list per page.
     */
    private static final int ITEMS_PER_PAGE = 5;
    /**
     * Formatter for total hours and charges to list per invoice.
     */
    private static final String TOTAL = "Total: %1$62s %2$,10.2f%n";
    /**
     * Space formatter for adding blank rows as separators.
     */
    private static final String SPACE = String.format("%n");
    /**
     * Formatter for separating one page from another.
     */
    private static final String END_OF_PAGE = String.format("==================================="
                                                          + "==================================="
                                                          + "===========%n");

    // MEMBER VARIABLES \\
    /**
     * Initializer for line item list.
     */
    private List<InvoiceLineItem> newLineItem;
    /**
     * Initializer for client name.
     */
    private final ClientAccount client;
    /**
     * Initializer for invoice date.
     */
    private final LocalDate invoiceDate;
    /**
     * Initializer for invoice start date.
     */
    private final LocalDate startDate;
    /**
     * Initializer for total hours.
     */
    private int totalHours;
    /**
     * Initializer for total charges.
     */
    private int totalCharges;
    /**
     * Initializer for items to print.
     */
    private int itemsToPrint = 0;
    /**
     * Initializer for input stream.
     */
    private InputStream inputStream;
    /**
     * Initializer for business address.
     */
    private Address businessAddress;
    /**
     * Initialier for address state code.
     */
    private StateCode state;
    /**
     * Initializer for business name.
     */
    private String businessName;
    /**
     * Initializer for business street address.
     */
    private String businessStreet;
    /**
     * Initializer for business city address.
     */
    private String businessCity;
    /**
     * Initializer for business state code.
     */
    private String businessState;
    /**
     * Initializer for business zip code.
     */
    private String businessZip;
    
    // CONSTRUCTOR \\
    /**
     * Constructor for Invoice class containing client and billing period.
     * @param client for the invoice.
     * @param invoiceMonth month of the invoice.
     * @param invoiceYear year of the invoice.
     */
    public Invoice(final ClientAccount client, final Month invoiceMonth, final int invoiceYear) {
        this.newLineItem = new ArrayList<>();
        this.client      = client;
        this.invoiceDate = LocalDate.now();
        this.startDate   = LocalDate.of(invoiceYear, invoiceMonth, 1);
    }
    
    // GETTERS & SETTERS \\
    /**
     * Getter for client on invoice.
     * @return client.
     */
    public ClientAccount getClientAccount() {
        return client;
    }
    /**
     * Getter for the invoice month.
     * @return month on the invoice.
     */
    public Month getInvoiceMonth() {
        return startDate.getMonth();
    }
    /**
     * Getter for start date on invoice.
     * @return start date.
     */
    public LocalDate getStartDate() {
        return startDate;
    }
    /**
     * Getter for total charges on invoice.
     * @return total charges to list on invoice.
     */
    public int getTotalCharges() {
        return newLineItem.stream().mapToInt(x -> x.getCharge()).sum();
    }
    /**
     * Getter for total hours on invoice.
     * @return total hours to list on invoice.
     */
    public int getTotalHours() {
        return newLineItem.stream().mapToInt(x -> x.getHours()).sum();
    }    
    /**
     * Get address values from resource properties file through inputStream.
     * @return result of appended properties pulled from resource file.
     * @throws IOException if properties file not found.
     */
    private String getAV() throws IOException {
        StringBuilder result = new StringBuilder();
        Properties      prop = new Properties();
        String  propFileName = "invoice.properties";
        try {  
            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
            if(inputStream != null) {
                prop.load(inputStream);
            }
            else {
                throw new FileNotFoundException("property file ' " 
                                                + propFileName 
                                                + " ' not found in the classpath");
            }
        }
        catch (Exception e) {
        }
        businessName   = prop.getProperty("business.name");
        businessStreet = prop.getProperty("business.street");
        businessCity   = prop.getProperty("business.city");
        businessState  = prop.getProperty("business.state");
        businessZip    = prop.getProperty("business.zip");
        
        result.append(businessName)
        .append(SPACE)
        .append(businessStreet)
        .append(SPACE)
        .append(businessCity)
        .append(", ")
        .append(businessState)
        .append(" ")
        .append(businessZip)
        .append(SPACE);
        return result.toString();        
    }
    
    // ADDITIONAL METHODS \\
    /**
     * Add a line item to the invoice.
     * @param lineItem to be added to invoice.
     */
    public void addLineItem(final InvoiceLineItem lineItem) {
        newLineItem.add(lineItem);
        totalHours += lineItem.getHours();
        totalCharges += lineItem.getCharge();
    }
    /**
     * Method for extracting the billable hours from the timeCard to put on the invoice.
     * @param timeCard from which billable hours will be extracted for the invoice.
     */
    public void extractLineItems(final TimeCard timeCard) {
        final List<ConsultantTime> billableHours = timeCard
                .getBillableHoursForClient(client.getName());
        
        billableHours.stream()
                .filter(hrs -> hrs.getDate().getYear() == startDate.getYear()
                           && hrs.getDate().getMonth() == startDate.getMonth())
                .forEach(hrs -> addLineItem(new InvoiceLineItem(hrs.getDate(),
                        timeCard.getConsultant(), hrs.getSkillType(), hrs.getHours())));
    }

    /**
     * String format of client name and the start date.
     * @return client name and start date as string.
     */
    @Override
    public String toString() {
        return String.format("%s, 10%s %n", client.getName(), getStartDate());
    }
    
    /**
     * Creates a report and prints the invoice lines items to it as a formatted string.
     * @return the full invoice report as a formatted string.
     * @throws IOException if the getAV() method does not have a properties file load properly.
     */
    public String toReportString() throws IOException {
        StringBuilder invLine = new StringBuilder();
        InvoiceHeader invHead = new InvoiceHeader(getAV(), businessAddress, 
                                                  client, invoiceDate, startDate);
        InvoiceFooter invFoot = new InvoiceFooter(businessName);
        Formatter      totals = new Formatter();
        
        totals.format(TOTAL, getTotalHours(), (double)getTotalCharges());
        String str = totals.toString();
        totals.close();
        
        for(InvoiceLineItem invoiceLineItem : newLineItem) {
            invLine.append(invoiceLineItem);
            itemsToPrint++;
            if(itemsToPrint % ITEMS_PER_PAGE == 0) {
                invFoot.incrementPageNumber();
                invLine.append(SPACE)
                .append(invFoot)
                .append(END_OF_PAGE)
                .append(SPACE)
                .append(invHead)
                .append(SPACE);
            }
        }
        invFoot.incrementPageNumber();
        
        StringBuilder invoice = new StringBuilder();
        invoice.append(invHead)
        .append(SPACE)
        .append(COLUMN_NAMES)
        .append(invLine.toString())
        .append(SPACE)
        .append(str)
        .append(SPACE)
        .append(invFoot)
        .append(END_OF_PAGE);
        
        return invoice.toString();
    }
}