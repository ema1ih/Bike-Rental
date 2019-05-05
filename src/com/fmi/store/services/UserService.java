package com.fmi.store.services;

import com.fmi.store.model.*;
import java.util.*;

public class UserService{

    private static final ArrayList<User> listOfUsers = new ArrayList<User>();

    private static final UserService instance = new UserService();
    public static UserService getInstance() { return instance; }

    private UserService() {
        /*
        listOfUsers.add(new Driver(1, "andrei", "p1", 20, "@", "0712345678", "A1"));
        listOfUsers.add(new User(2, "adelin", "p2", 23, "@", "0712345678"));
        listOfUsers.add(new Driver(3, "ioana", "p3", 25, "@", "0712345678", "A"));
        listOfUsers.add(new Driver(4, "doru", "p4", 19, "@", "0712345678", "B1"));
        listOfUsers.add(new Driver(5, "ema", "p5", 20, "@", "0749228845", "B"));
        */
    }

    public static User getOne(Integer userId) {

        for(User user : listOfUsers) {
            if(user.getId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    public static User getOne(String username) {

        for(User user : listOfUsers) {
            if(user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static void printAllUsers(){
        System.out.println("Users:");
        for(User user : listOfUsers) {
            user.printUser();
        }
        //System.out.println("\n");
    }

    // if the list is sorted by id
    public static User createUser(String username, String password, Integer age, String email, String phoneNumber, String category){
        Integer lastUserId = 0;
        if(!listOfUsers.isEmpty()) lastUserId = listOfUsers.get(listOfUsers.size()-1).getId();
        User newUser;
        if(category != null) newUser = new Driver(lastUserId+1,username,password,age,email,phoneNumber,category);
            else  newUser = new User(lastUserId+1,username,password,age,email,phoneNumber);
        listOfUsers.add(newUser);
        return newUser;
    }

    public static void sortListOfUsers(){
        Collections.sort(listOfUsers); // sort by username
    }

    public static Boolean isPasswordCorrect(String username, String password){
        User thisUser = getOne(username);
        if(thisUser!=null && thisUser.getPassword().equals(password))
            return true;
        return false;
    }

    public static Boolean checkUserCategory(User user, TreeSet<String> categories){

        if(categories == null) return true; // a driver's licence is not required

        // a driver's licence is required:
        if(!(user instanceof Driver)) return false; // the user is not a driver
        if(categories.contains(((Driver) user).getCategory()))
            return true;
        return false;
    }

    public static Boolean doesUserMeetConditions(User user, Bike bike){
        Conditions conditions = bike.getConditions();
        if(user.getAge() < conditions.getMinAge()) return false;
        if(checkUserCategory(user,conditions.getCategory())) return true;
        return false;
    }


}
