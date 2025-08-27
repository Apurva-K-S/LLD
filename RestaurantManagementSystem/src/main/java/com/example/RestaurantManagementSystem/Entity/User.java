package com.example.RestaurantManagementSystem.Entity;

import com.example.RestaurantManagementSystem.Enum.UserRole;

public class User {
    private String id;
    private String name;
    private String phone;
    private String email;
    private UserRole userRole;

    public User(String id, String name, String phone, String email, UserRole userRole) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.userRole = userRole;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", userRole=" + userRole +
                '}';
    }
}
