package com.example.OnlineAuctionSystem.Service;

import com.example.OnlineAuctionSystem.Entity.AuctionList;
import com.example.OnlineAuctionSystem.Entity.Bid;
import com.example.OnlineAuctionSystem.Entity.User;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class BidService {
    Map<String, Bid> bidMap;
    UserService userService;
    AuctionListingService auctionListingService;
    public BidService(UserService userService, AuctionListingService auctionListingService){
        this.bidMap = new HashMap<>();
        this.userService = userService;
        this.auctionListingService = auctionListingService;
    }

    /*
     private String id;
    private String userId;
    private String auctionListId;
    private LocalDateTime createdAt;
    private double bidAmount;
     */

    public Bid createBid(String userId, String auctionListId, double bidAmount)
    {
        User user = userService.getUserById(userId);
        if(user==null)
        {
            System.out.println("User doesnt Exist. cant create bid");
            return null;
        }
        AuctionList auctionList = auctionListingService.getAuctionListById(auctionListId);
        if(auctionList == null)
        {
            System.out.println("auction list doesnt exist to put a bid");
        }
        UUID uuid = UUID.randomUUID();
        Bid bid = new Bid(uuid.toString(), userId, auctionListId, LocalDateTime.now(), bidAmount);
        boolean highestBid = auctionListingService.updateHighestBidAmount(auctionListId, bidAmount);
        if(highestBid)
        {
            userService.receiveNotification(userId, "You've made the Highest bid");
        }
        return bid;
    }
}
