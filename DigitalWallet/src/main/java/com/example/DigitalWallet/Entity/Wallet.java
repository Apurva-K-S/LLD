package com.example.DigitalWallet.Entity;

import com.example.DigitalWallet.Enum.WalletStatus;

import java.util.UUID;

public class Wallet {

    private UUID id;
    private UUID userId;
    private String pin; // Assume encrypted or hashed
    private float currentBalance;
    private float totalBalance;
    private WalletStatus walletStatus;
    private String currency; // e.g., "INR", "USD"
    private float dailyTransactionLimit;
    private float monthlyTransactionLimit;
    private boolean isDefault;

    // Constructors
    public Wallet() {}

    public Wallet(UUID id, UUID userId, String pin, float currentBalance, float totalBalance,
                  WalletStatus walletStatus, String currency,
                  float dailyTransactionLimit, float monthlyTransactionLimit, boolean isDefault) {
        this.id = id;
        this.userId = userId;
        this.pin = pin;
        this.currentBalance = currentBalance;
        this.totalBalance = totalBalance;
        this.walletStatus = walletStatus;
        this.currency = currency;
        this.dailyTransactionLimit = dailyTransactionLimit;
        this.monthlyTransactionLimit = monthlyTransactionLimit;
        this.isDefault = isDefault;
    }

    // Getters and Setters
    // (Can be generated via IDE or replaced with Lombok)

    // Optional: helper methods
    public boolean isActive() {
        return this.walletStatus == WalletStatus.ACTIVE;
    }

    public boolean canTransact(float amount) {
        return amount <= currentBalance && amount <= dailyTransactionLimit;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public float getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(float currentBalance) {
        this.currentBalance = currentBalance;
    }

    public float getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(float totalBalance) {
        this.totalBalance = totalBalance;
    }

    public WalletStatus getWalletStatus() {
        return walletStatus;
    }

    public void setWalletStatus(WalletStatus walletStatus) {
        this.walletStatus = walletStatus;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public float getDailyTransactionLimit() {
        return dailyTransactionLimit;
    }

    public void setDailyTransactionLimit(float dailyTransactionLimit) {
        this.dailyTransactionLimit = dailyTransactionLimit;
    }

    public float getMonthlyTransactionLimit() {
        return monthlyTransactionLimit;
    }

    public void setMonthlyTransactionLimit(float monthlyTransactionLimit) {
        this.monthlyTransactionLimit = monthlyTransactionLimit;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }
}
