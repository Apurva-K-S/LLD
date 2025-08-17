package com.example.OnlineStockBrokerage.Service;

import com.example.OnlineStockBrokerage.Entities.StockAccount;
import com.example.OnlineStockBrokerage.Entities.User;
import com.example.OnlineStockBrokerage.Enums.StockAccountType;
import com.example.OnlineStockBrokerage.Enums.UserStatus;
import com.example.OnlineStockBrokerage.Factory.StockAccountFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/*
private String Id;
    private String name;
    private String email;
    private String phone;
    private UserStatus userStatus;
 */
public class UserManagementService {
    private final Map<String, User> userStore = new HashMap<>();

    public User createUser(String name, String email, String phone) {
        UUID uuid = UUID.randomUUID();

        // Convert UUID to string
        String uuidString = uuid.toString();
        User user = new User(uuidString, name,email, phone, UserStatus.ACTIVE);
        userStore.put(user.getId(), user);
        emitEvent(user);
        return user;
    }

    public User viewUser(String userId) {
        emitEvent(userStore.get(userId));
        return userStore.get(userId);
    }

    public void emitEvent(User user) {
        System.out.println("User Details: " + user.getId() +
                "| status: "+user.getUserStatus() +
                "| name: " + user.getName() +
                "| email: " + user.getEmail() +
                "| phone: " + user.getPhone());
    }
}
