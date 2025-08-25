package com.example.Splitwise.Entity;

import java.time.LocalDateTime;

public class Settlement {
    private String id;
    private User paidBy;
    private User paidTo;
    private LocalDateTime createdAt;
    private double totalAmount;
    private String groupId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getPaidBy() {
        return paidBy;
    }

    public void setPaidBy(User paidBy) {
        this.paidBy = paidBy;
    }

    public User getPaidTo() {
        return paidTo;
    }

    public void setPaidTo(User paidTo) {
        this.paidTo = paidTo;
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

    @Override
    public String toString() {
        return "id => "+this.id +
                " paidBy => " + this.getPaidBy().getName()+
                " paidTo => "+this.getPaidTo().getName() +
                " createdAt => "+this.createdAt+
                " totalAmount => "+this.totalAmount+
                " groupId => "+this.groupId;
    }
}
