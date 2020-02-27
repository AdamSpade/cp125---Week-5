package com.scg.domain;

/**
 * Class that builds the footer used in the invoice class.
 * @author Adam Spade
 *
 */
final class InvoiceFooter 
{
    // MEMBER VARIABLES \\
    /**
     * Initializer for the business name.
     */
    private String businessName;
    /**
     * Initializer for the page number.
     */
    private int nextPage;
    
    // CONSTRUCTOR \\
    /**
     * Invoice footer constructor that passes the business name.
     * @param businessName to list on the invoice footer.
     */
    public InvoiceFooter(final String businessName) {
        this.businessName = businessName;
    }

    // ADDITIONAL METHODS \\
    /**
     * Method to increment through page numbers.
     */
    public void incrementPageNumber() {
        nextPage++;
    }
    /**
     * String format of the footer that includes the business name and page number.
     */
    @Override
    public String toString() {
        return String.format("%-70s Page: %s%n", businessName, nextPage);
    }
}