package com.example.DigitalWallet.Service;

import com.example.DigitalWallet.Builder.UserBuilder;
import com.example.DigitalWallet.Entity.User;
import com.example.DigitalWallet.Enum.KYCStatus;
import com.example.DigitalWallet.Enum.Status;
import com.example.DigitalWallet.Enum.UserType;
import com.example.DigitalWallet.Factory.UserFactory;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class UserService {

    private final Map<UUID, User> userStore = new ConcurrentHashMap<>();
    private final UserFactory userFactory = new UserFactory();

    // CreateUser
    public User createUser(UserType type, String name, String phone, String email) {
        User user = userFactory.createUser(type, name, phone, email);
        userStore.put(user.getId(), user);
        emitUserEvent("USER_CREATED", user);
        return user;
    }

    // UpdateUser (via builder)
    public User updateUser(UUID userId, UserBuilder updater) {
        User existing = getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        User updated = updater.build();
        userStore.put(userId, updated);
        return updated;
    }

    // ActivateUser / DeactivateUser
    public void activateUser(UUID userId) { changeActiveStatus(userId, true); }
    public void deactivateUser(UUID userId) { changeActiveStatus(userId, false); }

    // SetPreferredWallet
    public void setPreferredWallet(UUID userId, UUID walletId) {
        getUserById(userId).ifPresent(user -> user.setPreferredWalletId(walletId));
    }

    // SetKYCStatus
    public void setKYCStatus(UUID userId, KYCStatus status) {
        getUserById(userId).ifPresent(user -> {
            user.setKycStatus(status);
            if (status == KYCStatus.VERIFIED) {
                emitUserEvent("KYC_VERIFIED", user);
            }
        });
    }

    // EmitUserEvents
    private void emitUserEvent(String eventType, User user) {
        System.out.printf("Event: %s -> User ID: %s%n", eventType, user.getId());
    }

    // GetUserById
    public Optional<User> getUserById(UUID userId) {
        return Optional.ofNullable(userStore.get(userId));
    }

    // ListUsers
    public List<User> listUsers() {
        return new ArrayList<>(userStore.values());
    }

    // DeleteUser (soft delete)
    public void deleteUser(UUID userId) {
        deactivateUser(userId);
    }

    // Helper
    private void changeActiveStatus(UUID userId, boolean isActive) {
        getUserById(userId).ifPresent(user -> user.setActive(isActive));
    }
}
