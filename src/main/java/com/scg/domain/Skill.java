package com.scg.domain;

/**
 * Enum class for billable skills with name and rate.
 * @author Adam Spade
 */
public enum Skill 
{
    // CONSTANTS \\
    /**
     * Project manager skill with 250/hr rate.
     */
    PROJECT_MANAGER("Project Manager", 250),
    /**
     * System architect skill with 200/hr rate.
     */
    SYSTEM_ARCHITECT("System Architect", 200),
    /**
     * Software engineer skill with 150/hr rate.
     */
    SOFTWARE_ENGINEER("Software Engineer", 150),
    /**
     * Software tester skill with 100/hr rate.
     */
    SOFTWARE_TESTER("Software Tester", 100),
    /**
     * Unknown skill with 50/hr rate.
     */
    UNKNOWN_SKILL("Unknown", 50);
    
    // MEMBER VARIABLES \\
    /**
     * Rate initializer.
     */
    private int rate;
    /**
     * Skill name initializer.
     */
    private String name;
    
    /**
     * Constructor for new skilltype.
     * @param name of skilltype.
     * @param rate of skilltype.
     */
    private Skill(String name, final int rate) {
        this.name = name;
        this.rate = rate;
    }   

    // GETTERS & SETTERS \\
    /**
     * Getter for skilltype rate.
     * @return rate.
     */
    public int getRate() {
        return this.rate;
    }
    
    // ADDITIONAL METHODS \\
    /**
     * String value of skilltype.
     * @return skilltype as string.
     */
    public String toString() {
        return name;
    }
}
