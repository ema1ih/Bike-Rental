package com.fmi.store.model;

public class Driver extends User{

    private String category;

    public Driver(Integer id, String username, String password, Integer age,
                   String email, String phoneNumber, String category){
        super(id,username,password,age,email,phoneNumber);
        this.category = category;
    }

    public String getCategory() { return category; }
    public void setCategory(String licenceCategory) { this.category = category; }

    @Override
    public void printUser(){
        super.printUser();
        System.out.println(getUsername() + "'s driving licence category: " + getCategory());
    }

}
