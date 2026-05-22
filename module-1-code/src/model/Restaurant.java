package model;

import java.io.Serializable;

public class Restaurant implements Serializable {
    private int id;
    private String restaurantName;
    private String address;
    private String description;

    public Restaurant() {
    }

    public Restaurant(int id, String restaurantName, String address, String description) {
        this.id = id;
        this.restaurantName = restaurantName;
        this.address = address;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
