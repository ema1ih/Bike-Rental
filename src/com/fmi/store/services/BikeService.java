package com.fmi.store.services;

import com.fmi.store.model.Bike;
import com.fmi.store.model.BikeAdditionalServices;
import com.fmi.store.model.BikeAdditionalServices;
import com.fmi.store.model.Conditions;
import com.fmi.store.model.ElectricBike;

import java.util.ArrayList;
import java.util.TreeSet;

public class BikeService {

    private static ArrayList<Bike> listOfBikes = new ArrayList<Bike>();

    private static BikeService ourInstance = new BikeService();
    public static BikeService getInstance() {
        return ourInstance;
    }


    private BikeService() {
        Conditions motorcycleConditions = new Conditions(18, new TreeSet<String>(){{add("A1");}});
        Conditions ebikeConditions = new Conditions(12);
        Conditions scooterConditions = new Conditions(21, new TreeSet<String>(){{add("A1"); add("A");}});
        Conditions quadBikeConditions = new Conditions(17, new TreeSet<String>(){{add("B1");}});

        BikeAdditionalServices motorcycleServices = new BikeAdditionalServices(5.0,10.0);
        BikeAdditionalServices ebikeServices = new BikeAdditionalServices(0.5,2.0);
        BikeAdditionalServices scooterServices = new BikeAdditionalServices(1.0,2.5);
        BikeAdditionalServices quadBikeServices = new BikeAdditionalServices(0.0,2.0);

        Bike motorcycle = new Bike(1,"Motorcycle Yamaha YBR 125","etc","Motorcycle",
                60.0, motorcycleConditions, motorcycleServices);
        Bike ebike = new ElectricBike(2,"Ebike Lombardo", "etc", "Electric Bike",
                25.0, ebikeConditions, ebikeServices, "Bosch Lithium", 500);
        Bike scooter = new Bike(3,"Scooter Peugeot Citystar 125cc","etc","Scooter",
                41.0, scooterConditions, scooterServices);
        Bike quadBike = new Bike(4,"KYMCO MXU 150", "etc", "Quad Bike",
                25.0, quadBikeConditions, quadBikeServices);

        listOfBikes.add(motorcycle);
        listOfBikes.add(ebike);
        listOfBikes.add(scooter);
        listOfBikes.add(quadBike);
    }

    public static Bike getOne(Integer bikeId){
        for(Bike bike : listOfBikes) {
            if(bike.getId().equals(bikeId)) {
                return bike;
            }
        }
        return null;
    }

    public static void printAllBikes(){
        for(Bike bike: listOfBikes)
            bike.printBikeInfo();
        System.out.println("\n");
    }

    public static void printAllBikesName(){
        for(Bike bike: listOfBikes)
            System.out.println(bike.getId() + " " + bike.getName());
    }

}
