package com.fmi.store.model;

public class ElectricBike extends Bike {

    String batteryType;
    Integer batteryCapacity;

    public ElectricBike(Integer id, String name, String description, String type, Double bikePrice,
                        Conditions conditions, BikeAdditionalServices bikeServices, String batteryType,
                        Integer batteryCapacity){
        super(id,name,description,type,bikePrice,conditions,bikeServices);
        this.batteryType = batteryType;
        this.batteryCapacity = batteryCapacity;
    }

    public Integer getBatteryCapacity() { return batteryCapacity; }
    public void setBatteryCapacity(Integer batteryCapacity) { this.batteryCapacity = batteryCapacity; }

    public String getBatteryType() { return batteryType; }
    public void setBatteryType(String batteryType) { this.batteryType = batteryType; }

    @Override
    public void printBikeInfo(){
        super.printBikeInfo();
        System.out.println("Battery: " + getBatteryType() + " "  + getBatteryCapacity());
    }
}
