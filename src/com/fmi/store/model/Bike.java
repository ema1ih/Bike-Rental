package com.fmi.store.model;

public class Bike {

    Integer id;
    String name;
    String description;
    String type;
    Double bikePricePerDay;
    Conditions conditions;
    BikeAdditionalServices bikeServices;

    public Bike(Integer id, String name, String description, String type, Double bikePricePerDay,
                Conditions conditions, BikeAdditionalServices bikeServices){
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
        this.bikePricePerDay = bikePricePerDay;
        this.conditions = conditions;
        this.bikeServices = bikeServices;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public Double getBikePricePerDay() { return bikePricePerDay; }
    public void setBikePricePerDay(Double bikePricePerDay) { this.bikePricePerDay = bikePricePerDay; }

    public Conditions getConditions() { return conditions; }
    public void setConditions(Conditions conditions) { this.conditions = conditions; }

    public BikeAdditionalServices getBikeServices() { return bikeServices; }
    public void setBikeServices(BikeAdditionalServices bikeServices) { this.bikeServices = bikeServices; }

    public void printBikeInfo(){
        System.out.println("Bike " + getId() + ":\n" + getName() + " " + getDescription() +
                " " + getType() + "\nPrice per day: " + getBikePricePerDay());
        conditions.printConditions();
        bikeServices.printBikeServices();
    }

}
