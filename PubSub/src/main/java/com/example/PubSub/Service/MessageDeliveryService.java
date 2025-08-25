package com.example.PubSub.Service;

public interface MessageDeliveryService {
    void deliverMessage(String subscriberId, String message);
}
