package com.example.OnlineAuctionSystem.Service;

import com.example.OnlineAuctionSystem.Entity.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserService {
    Map<String, User> userMap;
    public UserService(){
        this.userMap = new HashMap<>();
    }

    public User createUser(String name, String password)
    {
        UUID uuid = UUID.randomUUID();
        User user = new User(uuid.toString(), name, password);
        userMap.put(uuid.toString(), user);
        return user;
    }

    public User getUserById(String userId)
    {
        if(userMap.containsKey(userId))
        {
            return userMap.get(userId);
        }
        System.out.println("User doesnt exist");
        return null;
    }

    public boolean login(String userId, String password)
    {
        if(!userMap.containsKey(userId)) {
            System.out.println("User doesnt exist");
            return false;
        }
        if(userMap.containsKey(userId) && !userMap.get(userId).getPassword().equals(password))
        {
            System.out.println("password doesnt match");
            return false;
        }
        return true;
    }

    public void receiveNotification(String userId, String message)
    {
        User user = userMap.get(userId);
        System.out.println(user.getName() + " " +message);
    }
}
