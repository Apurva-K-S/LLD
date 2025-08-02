package com.example.StackOverflow_advanced.Entity;

import java.util.UUID;

public class User {
    String email;
    UUID id;
    String password;

    public User(String email, UUID id, String password) {
        this.email = email;
        this.id = id;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return id + "_" + email + " _ " + password+"\n";
    }
}
