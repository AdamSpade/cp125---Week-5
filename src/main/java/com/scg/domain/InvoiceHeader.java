package com.scg.domain;

import java.time.LocalDate;
import java.util.Formatter;

import com.scg.util.Address;

/**
 * Header class to be used in the invoice class. Contains company header, client name, and invoice date.
 * @author Adam Spade
 */
final class InvoiceHeader 
{
    // FORMATTERS \\
    /**
     * Formatter for the business name.
     */
    private static final String SCG = "%s%n";
    /**
     * Formatter for whom the invoice is for.
     */
    private static final String INVOICE_FOR = String.format("Invoice for: %n");
    /**
     * Formatter for the client name.
     */
    private static final String CLIENT = "%s%n";
    /**
     * Formatter for the billing month.
     */
    private static final String FOR_MONTH_OF = "Invoice For Month of: %1$tB %1$tY%n";
    /**
     * Formatter for the date the invoice was issued.
     */
    private static final String INVOICE_DATE = "Invoice Date: %1$tB %1$td, %1$tY%n";

    // MEMBER VARIABLES \\
    /**
     * Initializer for the business name.
     */
    private final String businessName;
    /**
     * Initializer for the busines address.
     */
    private final Address businessAddress;
    /**
     * Initializer for the client name.
     */
    private final ClientAccount client;
    /**
     * Initializer for the invoice date.
     */
    private final LocalDate invoiceDate;
    /**
     * Initializer for the invoice month.
     */
    private final LocalDate invoiceForMonth;
    
    // CONSTRUCTOR \\
    /**
     * Constructor for the invoice header class. Includes the business name, business address,
     * client name, invoice date, and invoice month.
     * @param businessName name of the business.
     * @param businessAddress address of the business.
     * @param client name of the client.
     * @param invoiceDate date of the invoice.
     * @param invoiceForMonth month of the invoice.
     */
    public InvoiceHeader(String businessName, Address businessAddress, ClientAccount client, 
            LocalDate invoiceDate, LocalDate invoiceForMonth) {
        this.businessName = businessName;
        this.businessAddress = businessAddress;
        this.client = client;
        this.invoiceDate = invoiceDate;
        this.invoiceForMonth = invoiceForMonth;
    }
    
    /**
     * To string format of the invoice header that includes the biz name and address, client the 
     * invoice is for, month the invoice is for and the invoice date.
     * @return invoice header in string format.
     */
    @Override
    public String toString() {
        Formatter invHead = new Formatter();
        invHead.format(SCG, businessName, businessAddress)
               .format(INVOICE_FOR)
               .format(CLIENT, client)
               .format(FOR_MONTH_OF, invoiceForMonth)
               .format(INVOICE_DATE, invoiceDate);
        String invoiceHead = invHead.toString();
        invHead.close();
        return invoiceHead;
    }   
}