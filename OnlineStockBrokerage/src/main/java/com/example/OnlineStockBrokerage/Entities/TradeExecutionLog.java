package com.example.OnlineStockBrokerage.Entities;

import com.example.OnlineStockBrokerage.Enums.ExecutionStatus;

import java.time.LocalDateTime;

public class TradeExecutionLog {
    private String id;
    private String orderId;
    private String companyId;
    private String userAccountId;
    private double executedPrice;
    private int executedUnits;
    private LocalDateTime createdAt;
    private ExecutionStatus executionStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getUserAccountId() {
        return userAccountId;
    }

    public void setUserAccountId(String userAccountId) {
        this.userAccountId = userAccountId;
    }

    public double getExecutedPrice() {
        return executedPrice;
    }

    public void setExecutedPrice(double executedPrice) {
        this.executedPrice = executedPrice;
    }

    public int getExecutedUnits() {
        return executedUnits;
    }

    public void setExecutedUnits(int executedUnits) {
        this.executedUnits = executedUnits;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ExecutionStatus getExecutionStatus() {
        return executionStatus;
    }

    public void setExecutionStatus(ExecutionStatus executionStatus) {
        this.executionStatus = executionStatus;
    }
}
