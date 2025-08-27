package com.example.OnlineAuctionSystem.Service;

import com.example.OnlineAuctionSystem.Entity.AuctionList;
import com.example.OnlineAuctionSystem.Entity.Item;
import com.example.OnlineAuctionSystem.Entity.SearchCriteria;
import com.example.OnlineAuctionSystem.Enum.AuctionListStatus;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class AuctionListingService {
    Map<String, AuctionList> auctionListMap;
    ItemService itemService;
    UserService userService;
    SearchInterface searchInterface;
    public AuctionListingService(ItemService itemService, UserService userService, SearchInterface searchInterface)
    {
        this.auctionListMap = new HashMap<>();
        this.itemService = itemService;
        this.userService = userService;
        this.searchInterface = searchInterface;
    }

    public void setSearchInterface(SearchInterface searchInterface) {
        this.searchInterface = searchInterface;
    }

    public AuctionList createAuctionList(String itemId, double startingPrice, int duration, String userId)
    {
        Item item = itemService.getItemById(itemId);
        if(item == null){
            System.out.println("Item doesnt exist");
            return null;
        }
        LocalDateTime start = LocalDateTime.now();
        Duration durationHours = Duration.ofHours(duration);

        LocalDateTime end = start.plus(durationHours);

        UUID uuid = UUID.randomUUID();
        AuctionList auctionList = new AuctionList(uuid.toString(),itemId, startingPrice, LocalDateTime.now(),duration, end, AuctionListStatus.SCHEDULED, startingPrice, userId);
        auctionListMap.put(uuid.toString(), auctionList);
        return auctionList;
    }

    public AuctionList getAuctionListById(String auctionId)
    {
        AuctionList auctionList = auctionListMap.get(auctionId);
        if(auctionList==null)
        {
            return null;
        }
        return auctionList;
    }
    public boolean updateHighestBidAmount(String auctionListId, double bidAmount)
    {
        AuctionList auctionList = auctionListMap.get(auctionListId);
        if(auctionList.getHighestBidValue() > bidAmount)
            return false;
        auctionList.setHighestBidValue(bidAmount);
        return true;
    }

    public void endExpiredAuctions() {
        LocalDateTime now = LocalDateTime.now();
        for (AuctionList auction : auctionListMap.values()) {
            if (auction.getAuctionListStatus() != AuctionListStatus.ENDED && auction.getEndTime().isBefore(now)) {
                auction.setAuctionListStatus(AuctionListStatus.ENDED);
                System.out.println("Auction ended: " + auction.getId());
                Item item = itemService.getItemById(auction.getItemId());
                String message = "You've won the Auction item " + item.getName() + " for " + auction.getHighestBidValue();
                userService.receiveNotification(auction.getUserIdOfHighestBidValue(), message);
            }
        }
    }

    public List<AuctionList> search(SearchCriteria searchCriteria)
    {
        return searchInterface.auctionSearch(auctionListMap, searchCriteria);
    }
}
