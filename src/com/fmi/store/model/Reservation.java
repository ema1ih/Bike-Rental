package com.fmi.store.model;

public class Reservation {

    Integer id;
    User user;
    Location pickUpInfo;
    Location dropOffInfo;
    Bike bike;
    UserPreferences userPreferences;
    Payment card;
    Double totalPrice;

    public Reservation(Integer id, User user, Location pickUpInfo, Location dropOffInfo, Bike bike,
                       UserPreferences userPreferences, Payment card){
            this.id = id;
            this.user = user;
            this.pickUpInfo = pickUpInfo;
            this.dropOffInfo = dropOffInfo;
            this.bike = bike;
            this.userPreferences = userPreferences;
            this.card = card;
            this.totalPrice = 0d;
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Location getPickUpInfo() { return pickUpInfo; }
    public void setPickUpInfo(Location pickUpInfo) { this.pickUpInfo = pickUpInfo; }

    public Location getDropOffInfo() { return dropOffInfo; }
    public void setDropOffInfo(Location dropOffInfo) { this.dropOffInfo = dropOffInfo; }

    public Bike getBike() { return bike; }
    public void setBike(Bike bike) { this.bike = bike; }

    public UserPreferences getUserPreferences() { return userPreferences; }
    public void setUserPreferences(UserPreferences userPreferences) { this.userPreferences = userPreferences; }

    public Payment getCard() { return card; }
    public void setCard(Payment card) { this.card = card; }

    public Double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(Double totalPrice) { this.totalPrice = totalPrice; }

    public void printReservation(){
        System.out.println("Reservation " + getId() + ":");
        user.printUser();
        pickUpInfo.printLocationInfo();
        dropOffInfo.printLocationInfo();
        bike.printBikeInfo();
        userPreferences.printUserPreferences();
        card.printCard();
        System.out.println("Total price: " + getTotalPrice());
    }

}
