package com.example.DigitalWallet.Entity;

import com.example.DigitalWallet.Enum.KYCStatus;
import com.example.DigitalWallet.Enum.UserType;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class User {

    private UUID id;
    private String name;
    private String phone;
    private String email;
    private UserType userType;
    private boolean isActive;
    private Instant createdAt;
    private Instant lastLoginAt;
    private KYCStatus kycStatus;
    private UUID preferredWalletId;
    private List<BankAccount> bankAccounts;
    private List<Wallet> wallets;

    // Constructors
    public User() {}

    public User(UUID id, String name, String phone, String email, UserType userType,
                boolean isActive, Instant createdAt, Instant lastLoginAt,
                KYCStatus kycStatus, UUID preferredWalletId,
                List<BankAccount> bankAccounts, List<Wallet> wallets) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.userType = userType;
        this.isActive = isActive;
        this.createdAt = createdAt;
        this.lastLoginAt = lastLoginAt;
        this.kycStatus = kycStatus;
        this.preferredWalletId = preferredWalletId;
        this.bankAccounts = bankAccounts;
        this.wallets = wallets;
    }

    // Getters and Setters
    // (You can generate these using your IDE or use Lombok for brevity)

    // Optional: helper methods
    public boolean isVerified() {
        return this.kycStatus == KYCStatus.VERIFIED;
    }

    public Wallet getPreferredWallet() {
        if (wallets == null) return null;
        return wallets.stream()
                .filter(w -> w.getId().equals(preferredWalletId))
                .findFirst()
                .orElse(null);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getLastLoginAt() {
        return lastLoginAt;
    }

    public void setLastLoginAt(Instant lastLoginAt) {
        this.lastLoginAt = lastLoginAt;
    }

    public KYCStatus getKycStatus() {
        return kycStatus;
    }

    public void setKycStatus(KYCStatus kycStatus) {
        this.kycStatus = kycStatus;
    }

    public UUID getPreferredWalletId() {
        return preferredWalletId;
    }

    public void setPreferredWalletId(UUID preferredWalletId) {
        this.preferredWalletId = preferredWalletId;
    }

    public List<BankAccount> getBankAccounts() {
        return bankAccounts;
    }

    public void setBankAccounts(List<BankAccount> bankAccounts) {
        this.bankAccounts = bankAccounts;
    }

    public List<Wallet> getWallets() {
        return wallets;
    }

    public void setWallets(List<Wallet> wallets) {
        this.wallets = wallets;
    }
}
