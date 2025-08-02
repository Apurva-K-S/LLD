package com.example.StackOverflow_advanced.Service;

import com.example.StackOverflow_advanced.Entity.User;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserService {
    Map<String, User> users;

    public UserService() {
        users = new HashMap<>();
    }

    public void registerUser(String email, String password)
    {
        UUID uuid = UUID.randomUUID();
        users.put(email, new User(email, uuid, password));
    }
    public boolean login(String email, String password)
    {
        if(users.containsKey(email)){
            if(users.get(email).getPassword().equals(password))
            {
                System.out.println("login successful");
                return true;
            }
            else {
                System.out.println("entered details are wrong!");
                return false;
            }
        }
        else {
            System.out.println("please register befor logging in");
            return false;
        }
    }

    public User getByEmail(String email)
    {
        if(users.containsKey(email))
            return users.get(email);
        else
        {
            System.out.println("user doesnt exist");
            return null;
        }
    }
}
