package com.example.RestaurantManagementSystem.Entity;

public class Ingredient {
    private String id;
    private String name;
    private double quantityAvailable;

    public Ingredient(String id, String name, double quantityAvailable) {
        this.id = id;
        this.name = name;
        this.quantityAvailable = quantityAvailable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(double quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    @Override
    public String toString() {
        return "Ingredient{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", quantityAvailable=" + quantityAvailable +
                '}';
    }
}
