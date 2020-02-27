package com.scg.util;

/**
 * Enum class for State Codes that get fed into address blocks.
 * @author Adam Spade
 */
public enum StateCode 
{
    /**
     * State of California initializer.
     */
    CA("California"),
    /**
     * State of Washington initializer.
     */
    WA("Washington");
    
    /**
     * State name initializer.
     */
    private String name;
    
    /**
     * Constructor for StateCode.
     * @param name of state
     */
    private StateCode(String name) {
        this.name = name;
    }
}
