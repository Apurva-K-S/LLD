package com.example.OnlineAuctionSystem.Service;

import com.example.OnlineAuctionSystem.Entity.AuctionList;
import com.example.OnlineAuctionSystem.Entity.Item;
import com.example.OnlineAuctionSystem.Entity.SearchCriteria;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemCategoryAuctionSearch implements SearchInterface{

    ItemService itemService;
    public ItemCategoryAuctionSearch(ItemService itemService)
    {
        this.itemService = itemService;
    }

    @Override
    public List<AuctionList> auctionSearch(Map<String, AuctionList> auctionListMap, SearchCriteria searchCriteria) {
        List<AuctionList> result = new ArrayList<>();
        for(AuctionList auctionList: auctionListMap.values())
        {
            String itemId = auctionList.getItemId();
            Item item = itemService.getItemById(itemId);
            if(item.getCategory().equals(searchCriteria.getCategory()))
            {
                result.add(auctionList);
            }
        }
        return result;
    }
}
