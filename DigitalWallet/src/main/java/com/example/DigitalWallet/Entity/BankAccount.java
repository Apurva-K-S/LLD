package com.example.DigitalWallet.Entity;

import com.example.DigitalWallet.Enum.AccountStatus;
import com.example.DigitalWallet.Enum.AccountType;

import java.time.Instant;
import java.util.UUID;

public class BankAccount {

    private String accountNumber;
    private UUID userId;
    private AccountType accountType;
    private float balance;
    private AccountStatus accountStatus;
    private Instant createdAt;
    private String bankName;
    private String ifscCode;
    private UUID linkedWalletId;
    private boolean isPrimary;

    // Constructors
    public BankAccount() {}

    public BankAccount(String accountNumber, UUID userId, AccountType accountType,
                       float balance, AccountStatus accountStatus, Instant createdAt,
                       String bankName, String ifscCode, UUID linkedWalletId, boolean isPrimary) {
        this.accountNumber = accountNumber;
        this.userId = userId;
        this.accountType = accountType;
        this.balance = balance;
        this.accountStatus = accountStatus;
        this.createdAt = createdAt;
        this.bankName = bankName;
        this.ifscCode = ifscCode;
        this.linkedWalletId = linkedWalletId;
        this.isPrimary = isPrimary;
    }

    // Getters and Setters
    // (Can be generated via IDE or replaced with Lombok annotations)

    // Optional: helper methods
    public boolean isActive() {
        return this.accountStatus == AccountStatus.ACTIVE;
    }

    public boolean isLinkedToWallet(UUID walletId) {
        return linkedWalletId != null && linkedWalletId.equals(walletId);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public AccountStatus getAccountStatus() {
        return accountStatus;
    }

    public void setAccountStatus(AccountStatus accountStatus) {
        this.accountStatus = accountStatus;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getIfscCode() {
        return ifscCode;
    }

    public void setIfscCode(String ifscCode) {
        this.ifscCode = ifscCode;
    }

    public UUID getLinkedWalletId() {
        return linkedWalletId;
    }

    public void setLinkedWalletId(UUID linkedWalletId) {
        this.linkedWalletId = linkedWalletId;
    }

    public boolean isPrimary() {
        return isPrimary;
    }

    public void setPrimary(boolean primary) {
        isPrimary = primary;
    }
}
