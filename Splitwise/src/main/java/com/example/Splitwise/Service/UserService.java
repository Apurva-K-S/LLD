package com.example.Splitwise.Service;

import com.example.Splitwise.Entity.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserService {
    private Map<String, User> users;

    public UserService() {
        this.users = new HashMap<>();
    }

    public User CreateUser(String name, String email)
    {
        UUID uuid =UUID.randomUUID();
        User user = new User(uuid.toString(), name, email);
        users.put(uuid.toString(), user);
        return user;
    }

    public User getUserById(String id)
    {
        if(users.containsKey(id))
        {
            return users.get(id);
        }
        return null;
    }
}
