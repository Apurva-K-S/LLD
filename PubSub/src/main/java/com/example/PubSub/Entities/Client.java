package com.example.PubSub.Entities;

import com.example.PubSub.Enums.ClientType;

public class Client {
    private String id;
    private String name;
    private ClientType clientType;

    public Client(String id, String name, ClientType clientType) {
        this.id = id;
        this.name = name;
        this.clientType = clientType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }

    @Override
    public String toString() {
        return "id - " + this.id + " name - " + this.name + " type - "+this.clientType;
    }
}
