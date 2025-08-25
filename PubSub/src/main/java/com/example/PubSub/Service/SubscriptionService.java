package com.example.PubSub.Service;
//
//import com.example.PubSub.Entities.Client;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//public class SubscriptionService {
//    Map<String, List<Client>> topicToSubscribers;
//    MessageDeliveryService messageDeliverService;
//    public SubscriptionService(MessageDeliveryService messageDeliverService)
//    {
//        this.topicToSubscribers = new HashMap<>();
//        this.messageDeliverService = messageDeliverService;
//    }
//
//    public void registerSubscriberToTopic(String topicId, Client subscriber)
//    {
//        if(topicToSubscribers.containsKey(topicId))
//        {
//            topicToSubscribers.get(topicId).add(subscriber);
//        }
//        else
//        {
//            List<Client> subscribers = new ArrayList<>();
//            subscribers.add(subscriber);
//            topicToSubscribers.put(topicId,subscribers);
//        }
//        System.out.println("Subscribed");
//    }
//
//    public void listOfSubscribersForATopic(String topicId)
//    {
//        if(topicToSubscribers.containsKey(topicId))
//        {
//            for(Client subscriber: topicToSubscribers.get(topicId))
//            {
//                System.out.println(subscriber);
//            }
//        }
//        else
//        {
//            System.out.println("No subscriber for topic");
//        }
//    }
//
//    public void publishMessage(String topicId, String message)
//    {
//        if(topicToSubscribers.containsKey(topicId))
//        {
//            for(Client subscriber: topicToSubscribers.get(topicId))
//            {
//                messageDeliverService.deliverMessage(subscriber.getId(), message);
//            }
//        }
//        else
//        {
//            System.out.println("No subscriber for topic");
//        }
//    }
//}

import com.example.PubSub.Entities.Client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SubscriptionService {
    private final Map<String, List<Client>> topicToSubscribers = new HashMap<>();
    private MessageDeliveryService messageDeliveryService;

    public void setMessageDeliveryService(MessageDeliveryService messageDeliveryService) {
        this.messageDeliveryService = messageDeliveryService;
    }

    public void registerSubscriberToTopic(String topicId, Client subscriber) {
        topicToSubscribers.computeIfAbsent(topicId, k -> new ArrayList<>())
                .add(subscriber);
        System.out.println("Subscribed");
    }

    public void listOfSubscribersForATopic(String topicId) {
        List<Client> subs = topicToSubscribers.get(topicId);
        if (subs != null) {
            subs.forEach(System.out::println);
        } else {
            System.out.println("No subscriber for topic");
        }
    }

    public void publishMessage(String topicId, String message) {
        List<Client> subs = topicToSubscribers.get(topicId);
        if (subs != null) {
            for (Client subscriber : subs) {
                messageDeliveryService.deliverMessage(subscriber.getId(), message);
            }
        } else {
            System.out.println("No subscriber for topic");
        }
    }
}
