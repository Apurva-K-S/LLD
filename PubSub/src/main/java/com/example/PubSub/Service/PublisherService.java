package com.example.PubSub.Service;

import com.example.PubSub.Entities.Client;
import com.example.PubSub.Enums.ClientType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PublisherService {
    Map<String, Client> publisherMap;
    SubscriptionService subscriptionService;

    public PublisherService(SubscriptionService subscriptionService) {
        this.publisherMap = new HashMap<>();
        this.subscriptionService = subscriptionService;
    }

    public Client registerPublisher(String name)
    {
        UUID uuid = UUID.randomUUID();
        Client publisher = new Client(uuid.toString(), name, ClientType.PUBLISHER);
        publisherMap.put(uuid.toString(),publisher);
        return publisher;
    }

    public void publishMessage(String topicId, String message)
    {
        subscriptionService.publishMessage(topicId, message);
        System.out.println("published");
    }
}
