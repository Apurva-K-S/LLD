package com.example.Splitwise.Entity;

import com.example.Splitwise.Enum.LedgerType;

import java.time.LocalDateTime;

public class LedgerEntry {
    private String id;
    private String groupId;
    private double amount;
    private User fromUser;
    private User toUser;
    private LocalDateTime createdAt;
    private LedgerType ledgerType;

    public LedgerEntry(String id, String groupId, double amount, User fromUser, User toUser, LedgerType ledgerType) {
        this.id = id;
        this.groupId = groupId;
        this.amount = amount;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.createdAt = LocalDateTime.now();
        this.ledgerType = ledgerType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

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

    public LedgerType getLedgerType() {
        return ledgerType;
    }

    public void setLedgerType(LedgerType ledgerType) {
        this.ledgerType = ledgerType;
    }

    @Override
    public String toString(){
        return "id => "+ this.id+
                " groupId => " + this.groupId +
                " amount => " + this.amount +
                " fromUser => "+this.getFromUser().getName()+
                " toUser => "+this.getToUser().getName()+
                " createdAt => "+this.getCreatedAt() +
                " ledgerType => "+this.getLedgerType();
    }
}
