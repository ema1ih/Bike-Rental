package com.fmi.store.model;

public class UserPreferences {

    Boolean securityLock;
    Boolean helmet;
    Integer numberHelmets;

    // if the user wants a helmet => how many?
    public UserPreferences(Boolean securityLock, Boolean helmet, Integer numberHelmets){
        this.securityLock = securityLock;
        this.helmet = helmet;
        this.numberHelmets = numberHelmets;
    }

    // if the user doesn't want a helmet
    public UserPreferences(Boolean securityLock, Boolean helmet){
        this.securityLock = securityLock;
        this.helmet = helmet;
        this.numberHelmets = 0;
    }

    public Boolean getSecurityLock() { return securityLock; }
    public void setSecurityLock(Boolean securityLock) { this.securityLock = securityLock; }

    public Boolean getHelmet() { return helmet; }
    public void setHelmet(Boolean helmet) { this.helmet = helmet; }

    public Integer getNumberHelmets() { return numberHelmets; }
    public void setNumberHelmets(Integer numberHelmets) { this.numberHelmets = numberHelmets; }

    public void printUserPreferences(){
        System.out.println("User's Preferences: " + getSecurityLock() +
                " " + getHelmet() + " " + getNumberHelmets());
    }

}
