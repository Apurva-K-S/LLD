package com.example.MakeMyTrip.Service;

import com.example.MakeMyTrip.Entity.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserService {
    private Map<String, User> usersById = new HashMap<>();
    private Map<String, User> usersByEmail = new HashMap<>();

    public User registerUser(String name, String email, String phone, String passwordHash) {
        if (usersByEmail.containsKey(email)) {
            throw new IllegalArgumentException("Email already registered");
        }
        UUID uuid = UUID.randomUUID();
        User user = new User(uuid.toString(), name, email, phone, passwordHash);
        usersById.put(user.getUserId(), user);
        usersByEmail.put(email, user);
        return user;
    }

    public User loginUser(String email, String passwordHash) {
        User user = usersByEmail.get(email);
        if (user == null || !user.getPasswordHash().equals(passwordHash)) {
            throw new IllegalArgumentException("Invalid email or password");
        }
        return user;
    }
}
