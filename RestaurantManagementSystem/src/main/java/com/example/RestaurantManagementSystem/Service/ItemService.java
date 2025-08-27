package com.example.RestaurantManagementSystem.Service;

import com.example.RestaurantManagementSystem.Entity.Item;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.UUID;

public class ItemService {

    private Map<String, Item> itemMap = new ConcurrentHashMap<>();

    // Creates an item with generated unique ID and stores in map
    public Item createItem(String name, double price, boolean available) {
        if (name == null || price < 0) {
            throw new IllegalArgumentException("Item name should not be null and price must be non-negative");
        }
        String id = UUID.randomUUID().toString(); // Generate unique ID
        Item item = new Item(id, name, price, available);
        itemMap.put(id, item);
        return item;
    }

    // Retrieves an item by ID, or null if not found
    public Item getItemById(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Item ID must not be null");
        }
        return itemMap.get(id);
    }
}
