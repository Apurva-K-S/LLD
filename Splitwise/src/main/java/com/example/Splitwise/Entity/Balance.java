package com.example.Splitwise.Entity;

import com.example.Splitwise.Enum.CurrencyType;

import java.time.LocalDateTime;

public class Balance {
    private User fromUser;
    private User toUser;
    private LocalDateTime createdAt;
    private double totalAmount;
    private String groupId;
    private CurrencyType currencyType;

    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    public User getToUser() {
        return toUser;
    }

    public void setToUser(User toUser) {
        this.toUser = toUser;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public CurrencyType getCurrencyType() {
        return currencyType;
    }

    public void setCurrencyType(CurrencyType currencyType) {
        this.currencyType = currencyType;
    }

    @Override
    public String toString() {
        return "Group => " +this.groupId + " ToUser => " + this.toUser.getName() + " FromUser => " + this.fromUser.getName() + " amount => "+this.totalAmount + " currencyType => " + this.currencyType + " createdAt => "+ this.createdAt;
    }
}
