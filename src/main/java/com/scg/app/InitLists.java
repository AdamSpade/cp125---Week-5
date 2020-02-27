package com.scg.app;

import java.io.Console;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.scg.domain.ClientAccount;
import com.scg.domain.Consultant;
import com.scg.domain.TimeCard;

import edu.uw.ext.util.ListFactory;

public final class InitLists 
extends Object
{
    /** Character encoding to use. */
    private static final String ENCODING = "ISO-8859-1";
    
    private InitLists() {  
    }   
    public static void main(String[] args) {
        final List<ClientAccount> accounts = new ArrayList<>();
        final List<Consultant> consultants = new ArrayList<>();
        final List<TimeCard>     timeCards = new ArrayList<>();
        ListFactory.populateLists(accounts, consultants, timeCards);
        
        Console console = System.console();
        PrintWriter consoleWriter = (console != null) ? console.writer() : new PrintWriter(new OutputStreamWriter(System.out /*, ENCODING */), true);
        ListFactory.printTimeCards(timeCards, consoleWriter);
        serializeLists(accounts, timeCards);
    }
    
    private static void serializeLists(final List<ClientAccount> accounts, final List<TimeCard> timeCards) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("ClientList.ser"))) {
            out.writeObject(accounts);
            //out.close();
        } catch (IOException e) {
            System.out.println("Serialization of client account list failed per error: " + e);
        }
        
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("TimeCardList.ser"))) {
            out.writeObject(timeCards);
            //out.close();
        } catch (IOException e) {
            System.out.println("Serialization of timecard list failed per error: " + e);
        }
    }
}