package com.example.RestaurantManagementSystem.Entity;

import java.util.List;

public class Restaurant {
    private String name;
    private String id;
    private List<Table> tables;
    private String address;
    private String phoneNumber;

    public Restaurant(String name, String id, List<Table> tables, String address, String phoneNumber) {
        this.name = name;
        this.id = id;
        this.tables = tables;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Table> getTables() {
        return tables;
    }

    public void setTables(List<Table> tables) {
        this.tables = tables;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", tables=" + tables +
                ", address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
