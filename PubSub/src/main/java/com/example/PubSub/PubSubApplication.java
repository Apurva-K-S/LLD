package com.example.PubSub;

import com.example.PubSub.Entities.Client;
import com.example.PubSub.Service.MessageDeliveryService;
import com.example.PubSub.Service.PublisherService;
import com.example.PubSub.Service.SubscriberService;
import com.example.PubSub.Service.SubscriptionService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PubSubApplication {

	public static void main(String[] args) {
		SubscriptionService subscriptionService = new SubscriptionService();
		SubscriberService subscriberService = new SubscriberService();

		// Wire cross‑dependencies
		subscriberService.setSubscriptionService(subscriptionService);
		subscriptionService.setMessageDeliveryService(subscriberService);

		PublisherService publisherService = new PublisherService(subscriptionService);

		// Demo flow
		Client sub = subscriberService.addSubscriber("Apurva");
		System.out.println("subcriber --> " + sub);
		subscriberService.registerSubscriberToTopic("topic1", sub.getId());

		Client pub = publisherService.registerPublisher("TestPublisher");
		System.out.println("publisher --> " + pub);
		publisherService.publishMessage("topic1", "Hello Subscribers!");
	}

}
