package com.example.OnlineAuctionSystem.Service;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AuctionScheduler {
    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
    private final AuctionListingService auctionListingService;

    public AuctionScheduler(AuctionListingService auctionListingService) {
        this.auctionListingService = auctionListingService;
    }

    public void start() {
        Runnable task = auctionListingService::endExpiredAuctions;

        // Schedule the task to run every 1 minute (adjust as needed)
        scheduler.scheduleAtFixedRate(task, 0, 1, TimeUnit.MINUTES);
    }

    public void stop() {
        scheduler.shutdown();
    }
}
