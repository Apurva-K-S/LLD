package com.example.Splitwise.Entity;

import com.example.Splitwise.Enum.SplitType;

import java.time.LocalDateTime;
import java.util.List;

public class Expense {
    private String id;
    private User paidBy;
    private double totalAmount;
    private String groupId;
    private SplitType splitType;
    private LocalDateTime createdAt;
    private List<Split> splits;

    public Expense(List<Split> splits, SplitType splitType, String groupId, double totalAmount, User paidBy, String id) {
        this.splits = splits;
        this.splitType = splitType;
        this.groupId = groupId;
        this.totalAmount = totalAmount;
        this.paidBy = paidBy;
        this.createdAt = LocalDateTime.now();
        this.id = id;
    }

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

    public SplitType getSplitType() {
        return splitType;
    }

    public void setSplitType(SplitType splitType) {
        this.splitType = splitType;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<Split> getSplits() {
        return splits;
    }

    public void setSplits(List<Split> splits) {
        this.splits = splits;
    }

    @Override
    public String toString() {
        return "id => " + this.id +
                " paidBy => " + this.getPaidBy().getName()+
                " totalAmount => "+this.totalAmount+
                " groupId => " + this.getGroupId()+
                " splitType => " + this.splitType+
                " splits => "+this.getSplits();
    }
}
