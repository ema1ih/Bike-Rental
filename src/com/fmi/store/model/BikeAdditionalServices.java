package com.fmi.store.model;

public class BikeAdditionalServices {

    Double securityLockPrice;
    Double helmetPrice;


    public BikeAdditionalServices(Double securityLockPrice, Double helmetPrice){
        this.securityLockPrice = securityLockPrice;
        this.helmetPrice = helmetPrice;
    }


    public Double getSecurityLockPrice() { return securityLockPrice; }
    public void setSecurityLockPrice(Double securityLockPrice) { this.securityLockPrice = securityLockPrice; }

    public Double getHelmetPrice() { return helmetPrice; }
    public void setHelmetPrice(Double helmetPrice) { this.helmetPrice = helmetPrice; }

    public void printBikeServices(){
        System.out.println("Security lock price: " + getSecurityLockPrice() +
                "\nHelmet price: " + getHelmetPrice());
    }


}
