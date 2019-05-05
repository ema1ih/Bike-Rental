package com.fmi.store.model;

import java.io.Serializable;

public class User implements Comparable<User>, Serializable {

    private Integer id;
    private String username;
    private String password;
    private Integer age;
    private String email;
    private String phoneNumber;

    public User(Integer id, String username, String password, Integer age, String email, String phoneNumber) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.age = age;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    @Override
    public int compareTo(User compareUser) {
        return this.username.compareTo(compareUser.getUsername());
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public void printUser(){
        System.out.println("User " + getId() + ": " + getUsername() + " " + getPassword()+ " " + getAge() +
                " " + getEmail() + " " + getPhoneNumber());
    }


}
