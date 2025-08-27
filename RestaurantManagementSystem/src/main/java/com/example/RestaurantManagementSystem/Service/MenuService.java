package com.example.RestaurantManagementSystem.Service;


import com.example.RestaurantManagementSystem.Entity.Item;
import com.example.RestaurantManagementSystem.Entity.Menu;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.UUID;

public class MenuService {

    private Map<String, Menu> menuMap = new ConcurrentHashMap<>();
    private final ItemService itemService;

    public MenuService(ItemService itemService) {
        this.itemService = itemService;
    }

    // Create a new Menu with given name, description, and list of item IDs
    public Menu createMenu(String name, String description, List<String> itemIds) {
        if (name == null || itemIds == null) {
            throw new IllegalArgumentException("Menu name and itemIds cannot be null");
        }
        String id = UUID.randomUUID().toString();
        // Retrieve actual Item objects from itemService using itemIds
        List<Item> items = itemIds.stream()
                .map(itemService::getItemById)
                .filter(item -> item != null)
                .toList();

        Menu menu = new Menu(id, name, description, items);
        menuMap.put(id, menu);
        return menu;
    }

    // Get Menu by ID
    public Menu getMenuById(String menuId) {
        if (menuId == null) {
            throw new IllegalArgumentException("Menu ID cannot be null");
        }
        return menuMap.get(menuId);
    }

    // Update menu details (name, description, or items)
    public boolean updateMenu(String menuId, String name, String description, List<String> itemIds) {
        Menu menu = getMenuById(menuId);
        if (menu == null) {
            return false; // Menu not found
        }
        if (name != null) {
            menu.setName(name);
        }
        if (description != null) {
            menu.setDescription(description);
        }
        if (itemIds != null) {
            List<Item> items = itemIds.stream()
                    .map(itemService::getItemById)
                    .filter(item -> item != null)
                    .toList();
            menu.setItems(items);
        }
        return true;
    }

    // List all menus
    public List<Menu> getAllMenus() {
        return menuMap.values().stream().toList();
    }
}
