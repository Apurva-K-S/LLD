package com.example.OnlineAuctionSystem.Service;

import com.example.OnlineAuctionSystem.Entity.AuctionList;
import com.example.OnlineAuctionSystem.Entity.SearchCriteria;

import java.util.List;
import java.util.Map;

public interface SearchInterface {
    List<AuctionList> auctionSearch(Map<String, AuctionList> auctionListMap, SearchCriteria searchCriteria);
}
