package com.example.OnlineStockBrokerage.Entities;

import com.example.OnlineStockBrokerage.Enums.StockAccountStatus;
import com.example.OnlineStockBrokerage.Enums.StockAccountType;

public class StockAccount {
    private String id;
    private String userId;
    private double balance;
    private double dailyLimit;
    private double hourlyLimit;
    private StockAccountStatus stockAccountStatus;
    private StockAccountType accountType;

    public StockAccount(String id, String userId, double balance, double dailyLimit, double hourlyLimit, StockAccountStatus stockAccountStatus, StockAccountType accountType) {
        this.id = id;
        this.userId = userId;
        this.balance = balance;
        this.dailyLimit = dailyLimit;
        this.hourlyLimit = hourlyLimit;
        this.stockAccountStatus = stockAccountStatus;
        this.accountType = accountType;
    }

    public StockAccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(StockAccountType accountType) {
        this.accountType = accountType;
    }

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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getDailyLimit() {
        return dailyLimit;
    }

    public void setDailyLimit(double dailyLimit) {
        this.dailyLimit = dailyLimit;
    }

    public double getHourlyLimit() {
        return hourlyLimit;
    }

    public void setHourlyLimit(double hourlyLimit) {
        this.hourlyLimit = hourlyLimit;
    }

    public StockAccountStatus getStockAccountStatus() {
        return stockAccountStatus;
    }

    public void setStockAccountStatus(StockAccountStatus stockAccountStatus) {
        this.stockAccountStatus = stockAccountStatus;
    }
}
