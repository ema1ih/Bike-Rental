package com.fmi.store.services;

import com.fmi.store.model.*;

import java.util.ArrayList;
import java.util.Date;

public class ReservationService {

    private static final ArrayList<Reservation> listOfReservations = new ArrayList<Reservation>();

    private static ReservationService ourInstance = new ReservationService();
    public static ReservationService getInstance() {
        return ourInstance;
    }

    private ReservationService() {
        /*
        Date pickUpDate = new Date(2019,3,28,10,30);
        Date dropOffDate = new Date(2019,3,30,14,20);
        Location myPickUpLocation = new Location("Romania","Bacau", pickUpDate);
        Location myDropOffLocation = new Location("Romania","Bacau", dropOffDate);
        Date expirationDate = new Date(2022,11,23);
        Payment myCard = new Payment("5213-5090-3647-8548", expirationDate, 408);

        UserPreferences motorcyclePreferences = new UserPreferences(false,true,2);
        UserPreferences ebikePreferences = new UserPreferences(true,false);
        UserPreferences scooterPreferences = new UserPreferences(true,true, 1);
        UserPreferences quadBikePreferences = new UserPreferences(false,true,3);

        listOfReservations.add(new Reservation(1,UserService.getOne(1),myPickUpLocation,myDropOffLocation,
                BikeService.getOne(1), motorcyclePreferences,myCard));
        listOfReservations.add(new Reservation(2,UserService.getOne(2),myPickUpLocation,myDropOffLocation,
                BikeService.getOne(2), ebikePreferences,myCard));
        listOfReservations.add(new Reservation(3,UserService.getOne(3),myPickUpLocation,myDropOffLocation,
                BikeService.getOne(3), scooterPreferences,myCard));
        listOfReservations.add(new Reservation(4,UserService.getOne(4),myPickUpLocation,myDropOffLocation,
                BikeService.getOne(4), quadBikePreferences,myCard));
        listOfReservations.add(new Reservation(5,UserService.getOne(5),myPickUpLocation,myDropOffLocation,
                BikeService.getOne(2), scooterPreferences,myCard));

        calculateTotalPriceForAllReservations();
        */

    }

    public static Reservation getOne(Integer reservationId){
        for(Reservation reservation: listOfReservations)
            if(reservation.getId().equals(reservationId))
                return reservation;
        return null;
    }

    public static Reservation createReservation(User user, Location pickUpInfo, Location dropOffInfo, Bike bike,
                                         UserPreferences userPreferences, Payment card){
        Integer lastReservationId = 0;
        if(!listOfReservations.isEmpty()) lastReservationId = listOfReservations.get(listOfReservations.size()-1).getId();
        Reservation newReservation =  new Reservation(lastReservationId+1,user,pickUpInfo,dropOffInfo,bike,userPreferences,card);
        listOfReservations.add(newReservation);
        calculateTotalPrice(newReservation);
        return newReservation;
    }

    public static Double additionalServicesPrice(UserPreferences userPreferences, BikeAdditionalServices bikeServices){
        Double price = 0d;
        if(userPreferences.getSecurityLock())
            price = bikeServices.getSecurityLockPrice();
        if(userPreferences.getHelmet())
            price += userPreferences.getNumberHelmets() * bikeServices.getHelmetPrice();
        return price;
    }

    public static Double bikePrice(Date pickUpDate, Date dropOffDate, Double bikePricePerDay){
        // calculate the number of days between pick up and drop off date
        int numberOfDays = (int)((dropOffDate.getTime() - pickUpDate.getTime())/(1000 * 60 * 60 * 24));
        return numberOfDays * bikePricePerDay;
    }

    public static void calculateTotalPrice(Reservation reservation){
        double bikePrice = bikePrice(reservation.getPickUpInfo().getDate(),
                reservation.getDropOffInfo().getDate(), reservation.getBike().getBikePricePerDay());
        double additionalServicesPrice = additionalServicesPrice(reservation.getUserPreferences(),
                reservation.getBike().getBikeServices());
        reservation.setTotalPrice(bikePrice + additionalServicesPrice);
    }

    public static void calculateTotalPriceForAllReservations(){
        for(Reservation reservation: listOfReservations)
            calculateTotalPrice(reservation);
    }

    public static void printAllReservations(){
        System.out.println("All reservations: " + "\n");
        for(Reservation reservation: listOfReservations) {
            reservation.printReservation();
            if(UserService.doesUserMeetConditions(reservation.getUser(),reservation.getBike()))
                System.out.println("User meets bike's conditions.");
            else
                System.out.println("User doesn't meet bike's conditions.");
            System.out.println("\n");
        }
    }
}
