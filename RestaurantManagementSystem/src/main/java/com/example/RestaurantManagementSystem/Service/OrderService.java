package com.example.RestaurantManagementSystem.Service;

import com.example.RestaurantManagementSystem.Entity.Item;
import com.example.RestaurantManagementSystem.Entity.Order;
import com.example.RestaurantManagementSystem.Enum.OrderStatus;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.UUID;

public class OrderService {

    private Map<String, Order> orderMap = new ConcurrentHashMap<>();
    private ItemService itemService;
    public OrderService(ItemService itemService)
    {
        this.itemService = itemService;
    }

    // Create a new order for a user with ordered items (itemId -> quantity)
    public Order createOrder(String userId, Map<String, Integer> orderedItems, String staffId) {
        if (userId == null || orderedItems == null || orderedItems.isEmpty()) {
            throw new IllegalArgumentException("User ID and ordered items must not be null or empty.");
        }
        String id = UUID.randomUUID().toString();
        LocalDateTime orderedAt = LocalDateTime.now();

        // Calculate total amount by summing items * quantity using an ItemService (assumed available)
        double totalAmount = calculateTotalAmount(orderedItems);

        Order order = new Order(id, userId, orderedItems, OrderStatus.PENDING, orderedAt, totalAmount, staffId);
        orderMap.put(id, order);
        return order;
    }

    // Helper to calculate total order amount based on items and quantities
    private double calculateTotalAmount(Map<String, Integer> orderedItems) {
        double total = 0.0;
        // Example:
         for (Map.Entry<String, Integer> entry : orderedItems.entrySet()) {
             Item item = itemService.getItemById(entry.getKey());
             if (item != null) {
                 total += item.getPrice() * entry.getValue();
             }
         }
        return total;
    }

    // Get order by order ID
    public Order getOrderById(String orderId) {
        if (orderId == null) {
            throw new IllegalArgumentException("Order id cannot be null.");
        }
        return orderMap.get(orderId);
    }

    // Update order status: PENDING, PREPARING, COMPLETED, CANCELLED
    public boolean updateOrderStatus(String orderId, OrderStatus status) {
        Order order = getOrderById(orderId);
        if (order == null) {
            return false;
        }
        order.setOrderStatus(status);
        return true;
    }
    public Collection<Order> getAllOrders() {
        return orderMap.values();
    }
}
