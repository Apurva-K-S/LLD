package com.example.OnlineStockBrokerage.Entities;

import com.example.OnlineStockBrokerage.Enums.OrderStatus;
import com.example.OnlineStockBrokerage.Enums.OrderType;

import java.time.LocalDateTime;

public class Order {
    private String id;
    private String userId;
    private String accountId;
    private String companyId;
    private double amount;
    private int shareUnits;
    private OrderType orderType;
    private OrderStatus orderStatus;
    private LocalDateTime createdAt;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getShareUnits() {
        return shareUnits;
    }

    public void setShareUnits(int shareUnits) {
        this.shareUnits = shareUnits;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void reduceQuantity(int executedUnits) {
        if (executedUnits < 0) {
            throw new IllegalArgumentException("Executed units cannot be negative");
        }
        if (executedUnits > this.shareUnits) {
            throw new IllegalArgumentException("Executed units cannot exceed current quantity");
        }
        this.shareUnits -= executedUnits;

        // Optionally update status
        if (this.shareUnits == 0) {
            this.orderStatus = OrderStatus.COMPLETED;
        } else {
            this.orderStatus = OrderStatus.PARTIAL;
        }
    }

    public String toString() {
        return this.id + " " + this.orderType + " " + this.orderStatus + " " + this.accountId + " " +this.userId + " " + this.companyId;
    }
}
