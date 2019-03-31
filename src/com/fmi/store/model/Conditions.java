package com.fmi.store.model;

import java.util.TreeSet;

public class Conditions {

    Integer minAge;
    TreeSet<String> category;

    public Conditions(Integer minAge, TreeSet<String> category){
        this.minAge = minAge;
        this.category = category;
    }

    // when a driver's licence is not required
    public Conditions(Integer minAge){
        this.minAge = minAge;
    }

    public Integer getMinAge() { return minAge; }
    public void setMinAge(Integer minAge) { this.minAge = minAge; }

    public TreeSet<String> getCategory() { return category; }
    public void setCategory(TreeSet<String> category) { this.category = category; }

    public void printConditions(){

        System.out.println("Minimum age: " + getMinAge());
        if(getCategory() != null) System.out.println("Driving licence category:" + " " + getCategory());
    }


}
