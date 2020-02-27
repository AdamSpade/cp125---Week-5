package com.scg.app;

import java.io.Console;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.List;

import com.scg.domain.ClientAccount;
import com.scg.domain.Invoice;
import com.scg.domain.TimeCard;

import edu.uw.ext.util.ListFactory;

public final class Assignment05 
{
    /** Character encoding to use. */
    private static final String ENCODING = "ISO-8859-1";
    
    private List<TimeCard> timeCards;
    private List<ClientAccount> clientAccounts;
    
    public static void main(String[] args) throws Exception {
        Assignment05 assignment = new Assignment05();
        assignment.deserializeLists();
        List<Invoice> invoices = ListFactory.createInvoices(assignment.clientAccounts,
                                                            assignment.timeCards);
        Console console = System.console();
        try {
            PrintWriter consoleWriter = (console != null) ? console.writer() : new PrintWriter(new OutputStreamWriter(System.out, ENCODING), true);
            ListFactory.printInvoices(invoices, consoleWriter);
        } catch (IOException ex) {
            System.out.println("Error printing invoices.");
        }
    }
    
    private void deserializeLists() {
        final Object clientList = deserializeFiles("ClientList.ser");
        if(clientList instanceof List<?>) {
            for(Object client : ((List<?>) clientList)) {
                if(!(client instanceof ClientAccount)) {
                    throw new IllegalArgumentException("Not Client Account List.");
                }
            }
            @SuppressWarnings("unchecked")
            final List<ClientAccount> deserializeClientList = (List<ClientAccount>) clientList;
            clientAccounts = deserializeClientList;
        }
        @SuppressWarnings("unchecked")
        final List<TimeCard> deserializeTCardList = (List<TimeCard>) deserializeFiles("TimeCardList.ser");
        timeCards = deserializeTCardList;
    }
    
    private static Object deserializeFiles(final String file) {
        Object newObject = null;
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            newObject = in.readObject();
        } catch (ClassNotFoundException ex) {
            System.out.println("Class type: " + ex + " not found");
        } catch (IOException ex) {
            System.out.println("File: " + ex + "unreadable");
        }
        return newObject;
    }
}
