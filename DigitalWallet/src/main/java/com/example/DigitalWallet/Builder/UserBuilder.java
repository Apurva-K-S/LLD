package com.example.DigitalWallet.Builder;

import com.example.DigitalWallet.Entity.User;
import com.example.DigitalWallet.Enum.KYCStatus;
import com.example.DigitalWallet.Enum.UserType;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public class UserBuilder {
    private final User user;

    public UserBuilder(User user) {
        this.user = user;
    }

    public UserBuilder name(String name) { user.setName(name); return this; }
    public UserBuilder phone(String phone) { user.setPhone(phone); return this; }
    public UserBuilder email(String email) { user.setEmail(email); return this; }
    public UserBuilder userType(UserType type) { user.setUserType(type); return this; }
    public UserBuilder active(boolean active) { user.setActive(active); return this; }
    public UserBuilder createdAt(Instant createdAt) { user.setCreatedAt(createdAt); return this; }
    public UserBuilder lastLoginAt(Instant lastLoginAt) { user.setLastLoginAt(lastLoginAt); return this; }
    public UserBuilder kycStatus(KYCStatus status) { user.setKycStatus(status); return this; }
    public UserBuilder preferredWalletId(UUID walletId) { user.setPreferredWalletId(walletId); return this; }
    public UserBuilder bankAccounts(List bankAccounts) { user.setBankAccounts(bankAccounts); return this; }
    public UserBuilder wallets(List wallets) { user.setWallets(wallets); return this; }

    public User build() { return user; }
}
