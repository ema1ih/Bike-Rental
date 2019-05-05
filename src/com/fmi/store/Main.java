package com.fmi.store;

import com.fmi.store.model.*;
import com.fmi.store.services.*;
import java.util.*;


public class Main {

    // function that reads the date from keyboard
    private static Date readDate(Scanner keyboard){

        int month, day, hrs, min;
        System.out.println("Year: 2019");
        System.out.print("Month[0-11]: "); month = keyboard.nextInt();
        System.out.print("Day[1-31]: "); day = keyboard.nextInt();
        System.out.print("Hours[0-23]: "); hrs = keyboard.nextInt();
        System.out.print("Minutes[0-59]: "); min = keyboard.nextInt();
        return new Date(2019,month,day,hrs,min);

    }

    // function that reads the location from keyboard
    private static Location readLocation(Scanner keyboard, Date date){

        String country, city;
        System.out.print("Country: "); country = keyboard.nextLine();
        System.out.print("City: "); city = keyboard.nextLine();
        return new Location(country, city, date);

    }

    // function that reads the user's preferences from keyboard
    private static UserPreferences readUserPreferences(Scanner keyboard){

        UserPreferences thisUserPreferences;
        Boolean securityLockBool, helmetBool;
        Integer numberHelmet;

        System.out.print("Do you want a security lock? [true/false] ");
        securityLockBool = keyboard.nextBoolean();
        System.out.print("Do you want a helmet? [true/false] ");
        helmetBool = keyboard.nextBoolean();
        keyboard.nextLine();
        if (helmetBool) {
            System.out.print("How many helmets? ");
            numberHelmet = keyboard.nextInt();
            keyboard.nextLine();
            thisUserPreferences = new UserPreferences(securityLockBool, true, numberHelmet);
        } else
            thisUserPreferences = new UserPreferences(securityLockBool, helmetBool);

        return thisUserPreferences;

    }

    // function that reads the user's payment info from keyboard
    private static Payment readPayment(Scanner keyboard){

        int year,month;
        Payment card; String cardNumber; Integer cvc;
        System.out.print("Payment\nCard number: "); cardNumber = keyboard.nextLine();
        System.out.print("Expiration date\nYear: "); year = keyboard.nextInt();
        System.out.print("Month[0-11]: "); month = keyboard.nextInt();
        System.out.print("CVC: "); cvc = keyboard.nextInt();
        Date expirationDate = new Date(year, month, 1);
        return new Payment(cardNumber, expirationDate, cvc);

    }

    // function that reads the reservation from keyboard
    private static Reservation readReservation(Scanner keyboard, User thisUser, Bike thisBike){

        System.out.println("Pick up date"); Date thisPickUpDate = readDate(keyboard);
        System.out.println("Drop off date"); Date thisDropOffDate = readDate(keyboard);

        keyboard.nextLine();
        System.out.println("Pick up location"); Location thisPickUpLocation = readLocation(keyboard, thisPickUpDate);
        System.out.println("Drop off location"); Location thisDropOffLocation = readLocation(keyboard, thisDropOffDate);

        UserPreferences thisUserPreferences = readUserPreferences(keyboard);
        Payment card = readPayment(keyboard);

        return new Reservation(0, thisUser, thisPickUpLocation, thisDropOffLocation, thisBike, thisUserPreferences, card);

    }

    public static void main(String[] args) {


        String usersDataFile = "C:\\Users\\Ema\\Desktop\\java\\Bike Rental\\src\\UsersData.csv";
        String bikesDataFile = "C:\\Users\\Ema\\Desktop\\java\\Bike Rental\\src\\BikesData.csv";
        String reservationsDataFile = "C:\\Users\\Ema\\Desktop\\java\\Bike Rental\\src\\ReservationsData.csv";

        FileService.getInstance().readObjectsFromFile(usersDataFile, "User");
        FileService.getInstance().readObjectsFromFile(bikesDataFile, "Bike");
        FileService.getInstance().readObjectsFromFile(reservationsDataFile, "Reservation");
        ReservationService.calculateTotalPriceForAllReservations();

        Scanner keyboard = new Scanner(System.in);
        int answer;

        while(true) {

            System.out.println("\nMeniu: \n[1] Login \n[2] Create User \n[3] Exit ");
            answer = keyboard.nextInt();
            keyboard.nextLine();
            User thisUser = null;

            // login
            if (answer == 1) {
                System.out.print("Username: ");
                String username = keyboard.nextLine();
                System.out.print("Password: ");
                String password = keyboard.nextLine();

                if (UserService.isPasswordCorrect(username, password)) {
                    System.out.println("Hello " + username + "!");
                    thisUser = UserService.getOne(username);
                    AuditService.getInstance().addEventToHistory(username + " logged in", new Date());
                }
                else
                {
                    System.out.println("Wrong username or password!");
                    AuditService.getInstance().addEventToHistory(username + " failed to log in", new Date());
                }

            } // create user
            else if (answer == 2) {
                System.out.print("Username: ");
                String username = keyboard.nextLine();
                System.out.print("Password: ");
                String password = keyboard.nextLine();
                System.out.print("Age: ");
                Integer age = keyboard.nextInt();
                keyboard.nextLine();
                System.out.print("Email: ");
                String email = keyboard.nextLine();
                System.out.print("Phone number: ");
                String phoneNumber = keyboard.nextLine();
                System.out.print("Driving licence category([no] if you don't have one): ");
                String category = keyboard.nextLine();
                if (category.equals("no")) category = null;

                thisUser = UserService.createUser(username, password, age, email, phoneNumber, category);
                thisUser.printUser();
                FileService.getInstance().writeUserToFile(thisUser, usersDataFile);
                AuditService.getInstance().addEventToHistory(username + " signed up", new Date());

            }
            else break;

            // if the user signed in successfully or signed up
            if(thisUser != null){

                while(true) {

                    System.out.println("\nMeniu:\n[1] Print all bikes\n[2] Print bike's info by id\n[3] Check if you meet conditions for a bike"
                            + "\n[4] Create a reservation\n[5] Print all reservations\n[6] Exit");

                    answer = keyboard.nextInt();
                    keyboard.nextLine();

                    // print all bikes
                    if (answer == 1) {

                        BikeService.printAllBikesName();
                        AuditService.getInstance().addEventToHistory(thisUser.getUsername() + " printed all bikes", new Date());

                    } // print bike's info by id
                    else if (answer == 2) {

                        System.out.print("Bike's id: ");
                        int bikeId = keyboard.nextInt();
                        BikeService.getOne(bikeId).printBikeInfo();
                        AuditService.getInstance().addEventToHistory(thisUser.getUsername() + " printed bike " + bikeId, new Date());

                    } // check if the user meets conditions for a bike
                    else if(answer == 3){

                        System.out.print("Bike's id: ");
                        int bikeId = keyboard.nextInt();
                        if(UserService.doesUserMeetConditions(thisUser,BikeService.getOne(bikeId)))
                            System.out.println("You meet bike's conditions.");
                        else
                            System.out.println("You don't meet bike's conditions.");
                        AuditService.getInstance().addEventToHistory(thisUser.getUsername() + " checked if he/she met conditions for bike " + bikeId, new Date());

                    } // create a reservation
                    else if(answer == 4){

                        Reservation thisReservation;

                        System.out.print("Bike's id: ");
                        Bike thisBike = BikeService.getOne(keyboard.nextInt());
                        keyboard.nextLine();

                        if(!UserService.doesUserMeetConditions(thisUser,thisBike))
                            System.out.println("You don't meet bike's conditions.");
                        else {

                            thisReservation = readReservation(keyboard,thisUser,thisBike);
                            ReservationService.calculateTotalPrice(thisReservation);
                            System.out.println("\n");
                            thisReservation.printReservation();
                            AuditService.getInstance().addEventToHistory(thisUser.getUsername() + " created a reservation", new Date());

                            while (true) {

                                System.out.println("\n[1]Change bike \n[2]Change your preferences " +
                                        "\n[3]Confirm reservation \n[4]Cancel reservation");

                                answer = keyboard.nextInt();
                                keyboard.nextLine();

                                // change the bike
                                if (answer == 1) {

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
                                    AuditService.getInstance().addEventToHistory(thisUser.getUsername() + " changed his/her bike", new Date());

                                } // change the preferences
                                else if(answer == 2){

                                    thisReservation.setUserPreferences(readUserPreferences(keyboard));
                                    AuditService.getInstance().addEventToHistory(thisUser.getUsername() + " changed his/her preferences", new Date());

                                } // confirm the reservation
                                else if (answer == 3) {

                                    Reservation confirmedReservation = ReservationService.createReservation(thisReservation.getUser(), thisReservation.getPickUpInfo(), thisReservation.getDropOffInfo(),
                                            thisBike, thisReservation.getUserPreferences(), thisReservation.getCard());
                                    FileService.getInstance().writeReservationToFile(confirmedReservation,reservationsDataFile);
                                    AuditService.getInstance().addEventToHistory(thisUser.getUsername() + " confirmed the reservation", new Date());
                                    break;

                                } else break;
                            }
                        }
                    } // print all reservations
                    else if(answer == 5){

                        ReservationService.printAllReservations();
                        AuditService.getInstance().addEventToHistory(thisUser.getUsername() + " printed all reservations", new Date());

                    }
                    else break;
                }

            }
        }
    }
}
