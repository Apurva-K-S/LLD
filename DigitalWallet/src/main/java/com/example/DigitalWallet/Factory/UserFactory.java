package com.example.DigitalWallet.Factory;

import com.example.DigitalWallet.Entity.User;
import com.example.DigitalWallet.Enum.KYCStatus;
import com.example.DigitalWallet.Enum.UserType;

import java.time.Instant;
import java.util.ArrayList;
import java.util.UUID;

public class UserFactory {

    public User createUser(UserType type, String name, String phone, String email) {
        User user = new User();
        user.setId(UUID.randomUUID());
        user.setName(name);
        user.setPhone(phone);
        user.setEmail(email);
        user.setUserType(type);
        user.setActive(true);
        user.setCreatedAt(Instant.now());
        user.setLastLoginAt(null);
        user.setKycStatus(KYCStatus.PENDING);
        user.setPreferredWalletId(null);
        user.setBankAccounts(new ArrayList<>());
        user.setWallets(new ArrayList<>());

        // Type-specific defaults
        if (type == UserType.MERCHANT) {
            // Merchant-specific setup if needed
        } else if (type == UserType.INDIVIDUAL) {
            // Individual-specific setup if needed
        }

        return user;
    }
}
