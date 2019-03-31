package com.fmi.store;

import com.fmi.store.model.*;
import com.fmi.store.services.*;

import java.util.*;


public class Main {

    public static void main(String[] args) {

        Scanner keyboard = new Scanner(System.in);
        Integer number = 1;

        while(true) {
            System.out.println("\nMeniu: \n[1] Login \n[2] Create User \n[3] Exit ");
            number = keyboard.nextInt();
            keyboard.nextLine();
            User thisUser = null;

            if (number == 1) {
                System.out.print("Username: ");
                String username = keyboard.nextLine();
                System.out.print("Password: ");
                String password = keyboard.nextLine();

                if (UserService.isPasswordCorrect(username, password))
                {
                    System.out.println("Hello " + username + "!");
                    thisUser = UserService.getOne(username);
                }
                else
                    System.out.println("Wrong username or password!");

            } else if (number == 2) {
                System.out.print("Username: ");
                String username = keyboard.nextLine();
                System.out.println("Password: ");
                String password = keyboard.nextLine();
                System.out.println("Age: ");
                Integer age = keyboard.nextInt();
                keyboard.nextLine();
                System.out.print("Email: ");
                String email = keyboard.nextLine();
                System.out.println("Phone number: ");
                String phoneNumber = keyboard.nextLine();
                System.out.println("Driving licence category([no] if you don't have one): ");
                String category = keyboard.nextLine();
                if (category.equals("no")) category = null;

                thisUser = UserService.createUser(username, password, age, email, phoneNumber, category);
                thisUser.printUser();
            }
            else break;

            if(thisUser != null){
                while(true) {
                    System.out.println("\nMeniu: ");
                    System.out.println("[1] Print all bikes");
                    System.out.println("[2] Print bike's info by id");
                    System.out.println("[3] Check if you meet conditions for a bike");
                    System.out.println("[4] Create a reservation");
                    System.out.println("[5] Print all reservations");
                    System.out.println("[6] Exit");

                    number = keyboard.nextInt();
                    keyboard.nextLine();

                    if (number == 1) {

                        BikeService.printAllBikesName();

                    } else if (number == 2) {

                        System.out.print("Bike's id: ");
                        BikeService.getOne(keyboard.nextInt()).printBikeInfo();

                    } else if(number == 3){

                        System.out.print("Bike's id: ");
                        if(UserService.doesUserMeetConditions(thisUser,BikeService.getOne(keyboard.nextInt())))
                            System.out.println("You meet bike's conditions.");
                        else
                            System.out.println("You don't meet bike's conditions.");

                    }else if(number == 4){

                        Reservation thisReservation;

                        System.out.print("Bike's id: ");
                        Bike thisBike = BikeService.getOne(keyboard.nextInt());
                        keyboard.nextLine();

                        if(!UserService.doesUserMeetConditions(thisUser,thisBike))
                            System.out.println("You don't meet bike's conditions.");
                        else {
                            Integer year, month, day, hrs, min;
                            System.out.print("Pick up date: \nYear: "); year = keyboard.nextInt();
                            System.out.print("Month: "); month = keyboard.nextInt();
                            System.out.print("Day: "); day = keyboard.nextInt();
                            System.out.print("Hours: "); hrs = keyboard.nextInt();
                            System.out.print("Minutes: "); min = keyboard.nextInt();
                            Date thisPickUpDate = new Date(year,month,day,hrs,min);

                            System.out.print("Drop off date: \nYear: "); year = keyboard.nextInt();
                            System.out.print("Month: "); month = keyboard.nextInt();
                            System.out.print("Day: "); day = keyboard.nextInt();
                            System.out.print("Hours: "); hrs = keyboard.nextInt();
                            System.out.print("Minutes: "); min = keyboard.nextInt();
                            Date thisDropOffDate = new Date(year,month,day,hrs,min);
                            keyboard.nextLine();

                            String country, city;
                            System.out.print("Pick up location: \nCountry: "); country = keyboard.nextLine();
                            System.out.print("City: "); city = keyboard.nextLine();
                            Location thisPickUpLocation = new Location(country, city, thisPickUpDate);

                            System.out.print("Drop off location: \nCountry: "); country = keyboard.nextLine();
                            System.out.print("City: "); city = keyboard.nextLine();
                            Location thisDropOffLocation = new Location(country, city, thisDropOffDate);

                            UserPreferences thisUserPreferences;
                            Boolean securityLockBool, helmetBool;
                            Integer numberHelmet;

                            System.out.print("Do you want a security lock? [true/false] ");
                            securityLockBool = keyboard.nextBoolean();
                            System.out.print("Do you want a helmet? [true/false] ");
                            helmetBool = keyboard.nextBoolean();
                            keyboard.nextLine();
                            if (helmetBool) {
                                System.out.print("How many? ");
                                numberHelmet = keyboard.nextInt();
                                keyboard.nextLine();
                                thisUserPreferences = new UserPreferences(securityLockBool, helmetBool, numberHelmet);
                            } else
                                thisUserPreferences = new UserPreferences(securityLockBool, helmetBool);

                            Payment card; String cardNumber; Integer cvc;
                            System.out.print("Payment: \nCard number: "); cardNumber = keyboard.nextLine();
                            System.out.print("Expiration date: \nYear: "); year = keyboard.nextInt();
                            System.out.print("Month: "); month = keyboard.nextInt();
                            System.out.print("CVC: "); cvc = keyboard.nextInt();
                            Date expirationDate = new Date(year, month, 29);
                            card = new Payment(cardNumber, expirationDate, cvc);

                            thisReservation = new Reservation(0, thisUser, thisPickUpLocation, thisDropOffLocation, thisBike, thisUserPreferences, card);
                            ReservationService.calculateTotalPrice(thisReservation);
                            System.out.println("\n");
                            thisReservation.printReservation();

                            while (true) {

                                System.out.println("\n[1]Change bike \n[2]Change your preferences \n[3]Confirm reservation \n[4]Exit");

                                number = keyboard.nextInt();
                                keyboard.nextLine();

                                if (number == 1) {
                                    thisBike = null;
                                    while (thisBike == null) {
                                        System.out.print("Bike's id: ");
                                        thisBike = BikeService.getOne(keyboard.nextInt());
                                        keyboard.nextLine();

                                        if (!UserService.doesUserMeetConditions(thisUser, thisBike))
                                        {
                                            System.out.println("You don't meet bike's conditions. Choose another bike.");
                                            thisBike = null;
                                        }
                                    }
                                }else if(number == 2){

                                    System.out.print("Do you want a security lock? [true/false] ");
                                    securityLockBool = keyboard.nextBoolean();
                                    System.out.print("Do you want a helmet? [true/false] ");
                                    helmetBool = keyboard.nextBoolean();
                                    if (helmetBool) {
                                        System.out.print("How many? ");
                                        numberHelmet = keyboard.nextInt();
                                        keyboard.nextLine();
                                        thisUserPreferences = new UserPreferences(securityLockBool, helmetBool, numberHelmet);
                                    } else
                                        thisUserPreferences = new UserPreferences(securityLockBool, helmetBool);

                                } else if (number == 3) {
                                    ReservationService.createReservation(thisReservation.getUser(), thisReservation.getPickUpInfo(), thisReservation.getDropOffInfo(),
                                            thisBike, thisUserPreferences, thisReservation.getCard());
                                    break;
                                } else break;
                            }
                        }
                    }else if(number == 5){
                        ReservationService.printAllReservations();
                    }
                    else break;
                }

            }
        }
    }
}
