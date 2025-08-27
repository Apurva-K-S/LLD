package com.example.RestaurantManagementSystem.Service;

import com.example.RestaurantManagementSystem.Entity.User;
import com.example.RestaurantManagementSystem.Enum.UserRole;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.UUID;

public class UserService {

    private Map<String, User> userMap = new ConcurrentHashMap<>();

    // Creates a user with generated unique ID and stores in map
    public User createUser(String name, String phone, String email, UserRole userRole) {
        if (name == null || phone == null || email == null || userRole == null) {
            throw new IllegalArgumentException("User details must not be null");
        }
        String id = UUID.randomUUID().toString(); // Generate unique ID
        User user = new User(id, name, phone, email, userRole);
        userMap.put(id, user);
        return user;
    }

    // Retrieves a user by ID, or null if not found
    public User getUserById(String id) {
        if (id == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }
        return userMap.get(id);
    }
}

