package com.example.RestaurantManagementSystem.Entity;

import com.example.RestaurantManagementSystem.Enum.OrderStatus;

import java.time.LocalDateTime;
import java.util.Map;

public class Order {
    private String id;
    private String userId;
    private Map<String, Integer> orderedItems; // key: itemId, value: quantity
    private OrderStatus orderStatus;
    private LocalDateTime orderedAt;
    private double totalAmount;
    private String staffId; // staff who served/prepared order

    public Order(String id, String userId, Map<String, Integer> orderedItems, OrderStatus orderStatus, LocalDateTime orderedAt, double totalAmount, String staffId) {
        this.id = id;
        this.userId = userId;
        this.orderedItems = orderedItems;
        this.orderStatus = orderStatus;
        this.orderedAt = orderedAt;
        this.totalAmount = totalAmount;
        this.staffId = staffId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Map<String, Integer> getOrderedItems() {
        return orderedItems;
    }

    public void setOrderedItems(Map<String, Integer> orderedItems) {
        this.orderedItems = orderedItems;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public LocalDateTime getOrderedAt() {
        return orderedAt;
    }

    public void setOrderedAt(LocalDateTime orderedAt) {
        this.orderedAt = orderedAt;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", userId='" + userId + '\'' +
                ", orderedItems=" + orderedItems +
                ", orderStatus=" + orderStatus +
                ", orderedAt=" + orderedAt +
                ", totalAmount=" + totalAmount +
                ", staffId='" + staffId + '\'' +
                '}';
    }
}
