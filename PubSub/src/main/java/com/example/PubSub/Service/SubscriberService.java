package com.example.PubSub.Service;
//
//import com.example.PubSub.Entities.Client;
//import com.example.PubSub.Enums.ClientType;
//
//import java.util.HashMap;
//import java.util.Map;
//import java.util.UUID;
//
//public class SubscriberService implements MessageDeliveryService {
//    Map<String, Client> subscribers;
//    SubscriptionService subscriptionService;
//
//    public SubscriberService(SubscriptionService subscriptionService)
//    {
//        this.subscribers = new HashMap<>();
//        this.subscriptionService = subscriptionService;
//    }
//
//    public Client addSubscriber(String name)
//    {
//        UUID uuid = UUID.randomUUID();
//        Client subscriber = new Client(uuid.toString(), name, ClientType.SUBSCRIBER);
//        subscribers.put(uuid.toString(), subscriber);
//        return subscriber;
//    }
//
//    public void registerSubscriberToTopic(String topicId, String subscriberId)
//    {
//        Client subscriber = null;
//        if(!subscribers.containsKey(subscriberId))
//        {
//            System.out.println("Subscriber doesnt exist");
//            return;
//        }
//        subscriber = subscribers.get(subscriberId);
//        subscriptionService.registerSubscriberToTopic(topicId, subscriber);
//        System.out.println("subscribed");
//    }
//
//    @Override
//    public void deliverMessage(String subscriberId, String message) {
//        // call particular downstream service or send to another kafka queue etc.
//        System.out.println(subscriberId  + " " + message);
//    }
//}

import com.example.PubSub.Entities.Client;
import com.example.PubSub.Enums.ClientType;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SubscriberService implements MessageDeliveryService {
    private final Map<String, Client> subscribers = new HashMap<>();
    private SubscriptionService subscriptionService;

    public void setSubscriptionService(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    public Client addSubscriber(String name) {
        UUID uuid = UUID.randomUUID();
        Client subscriber = new Client(uuid.toString(), name, ClientType.SUBSCRIBER);
        subscribers.put(uuid.toString(), subscriber);
        return subscriber;
    }

    public void registerSubscriberToTopic(String topicId, String subscriberId) {
        Client subscriber = subscribers.get(subscriberId);
        if (subscriber == null) {
            System.out.println("Subscriber doesn't exist");
            return;
        }
        subscriptionService.registerSubscriberToTopic(topicId, subscriber);
        System.out.println("subscribed");
    }

    @Override
    public void deliverMessage(String subscriberId, String message) {
        // In a real system: call downstream service, send to queue, etc.
        System.out.println("\n\nYou got new message!!!");
        System.out.println(subscriberId + " received: " + message+"\n\n");
    }
}
