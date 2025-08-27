package com.example.OnlineAuctionSystem.Service;

import com.example.OnlineAuctionSystem.Entity.AuctionList;
import com.example.OnlineAuctionSystem.Entity.Item;
import com.example.OnlineAuctionSystem.Entity.SearchCriteria;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PriceRangeSearch implements SearchInterface{
    ItemService itemService;
    public PriceRangeSearch(ItemService itemService)
    {
        this.itemService = itemService;
    }


    @Override
    public List<AuctionList> auctionSearch(Map<String, AuctionList> auctionListMap, SearchCriteria searchCriteria) {
        List<AuctionList> result = new ArrayList<>();
        for(AuctionList auctionList: auctionListMap.values())
        {
            if(auctionList.getBasePrice()>=searchCriteria.getMinPrice() && auctionList.getBasePrice()<=searchCriteria.getMaxPrice())
            {
                result.add(auctionList);
            }
        }
        return result;
    }
}
