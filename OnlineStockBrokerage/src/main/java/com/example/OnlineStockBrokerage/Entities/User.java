package com.example.OnlineStockBrokerage.Entities;

import com.example.OnlineStockBrokerage.Enums.UserStatus;

public class User {
    private String Id;
    private String name;
    private String email;
    private String phone;
    private UserStatus userStatus;

    public User(String id, String name, String email, String phone, UserStatus userStatus) {
        Id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.userStatus = userStatus;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }
}
