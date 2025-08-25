package com.example.Splitwise.Entity;

import com.example.Splitwise.Enum.CurrencyType;

import java.util.*;

public class Group {
    private String id;
    private String name;
    private List<User> users;
    private CurrencyType currencyType;

    public Group(String id, String name, List<User> users, CurrencyType currencyType) {
        this.id = id;
        this.name = name;
        this.users = users;
        this.currencyType = currencyType;
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    @Override
    public String toString() {
        return "id => " + this.id +
                " name => " + this.name +
                " users => "+this.getUsers()+
                " currencyType => "+this.getCurrencyType();
    }
}
