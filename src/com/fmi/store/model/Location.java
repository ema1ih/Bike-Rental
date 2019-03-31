package com.fmi.store.model;

import java.util.Date;

public class Location {

    private String country;
    private String city;
    private Date date;


    public Location(String country, String city, Date date){
        this.country = country;
        this.city = city;
        this.date = date;
    }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public void printLocationInfo(){
        System.out.println("Location: " + getCountry() + " " +getCity() + " "
                + getDate().getDate() + "-" + getDate().getMonth() + "-" + getDate().getYear()
                + " " + getDate().getHours() + ":" + getDate().getMinutes());
    }
}
