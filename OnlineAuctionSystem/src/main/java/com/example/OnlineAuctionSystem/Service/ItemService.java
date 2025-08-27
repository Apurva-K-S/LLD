package com.example.OnlineAuctionSystem.Service;

import com.example.OnlineAuctionSystem.Entity.Item;
import com.example.OnlineAuctionSystem.Enum.Category;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ItemService {
    Map<String, Item> itemMap;
    public ItemService() {
        this.itemMap = new HashMap<>();
    }

    public Item createItem(String name, Category category)
    {
        UUID uuid = UUID.randomUUID();
        Item item = new Item(uuid.toString(), name, category);
        itemMap.put(uuid.toString(), item);
        return item;
    }

    public Item getItemById(String itemId)
    {
        if(itemMap.containsKey(itemId))
            return itemMap.get(itemId);
        return null;
    }
}
