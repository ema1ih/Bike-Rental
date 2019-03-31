package com.fmi.store.model;

import java.util.Date;

public class Payment {

    String cardNumber;
    Date expirationDate;
    Integer CVC;

    public Payment(String cardNumber, Date expirationDate, Integer CVC){
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.CVC = CVC;
    }

    public String getCardNumber() { return cardNumber; }
    public void setCardNumber(String cardNumber)  { this.cardNumber = cardNumber; }

    public Date getExpirationDate() { return expirationDate; }
    public void setExpirationDate(Date expirationDate) {this.expirationDate = expirationDate; }

    public Integer getCVC() {return CVC; }
    public void setCVC(Integer CVC) {this.CVC = CVC; }

    public void printCard(){
        System.out.println("Card: " + getCardNumber() + " " + getExpirationDate().getMonth()
                + "/" + getExpirationDate().getYear() + " " + getCVC());
    }


}
