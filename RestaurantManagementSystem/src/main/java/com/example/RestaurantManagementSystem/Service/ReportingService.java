package com.example.RestaurantManagementSystem.Service;

import com.example.RestaurantManagementSystem.Entity.Order;
import com.example.RestaurantManagementSystem.Enum.OrderStatus;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class ReportingService {

    private final OrderService orderService;
    private final ItemService itemService;
    private final StaffService staffService;

    public ReportingService(OrderService orderService, ItemService itemService, StaffService staffService) {
        this.orderService = orderService;
        this.itemService = itemService;
        this.staffService = staffService;
    }

    // Sales report: total sales amount across all orders or within date range
    public double getTotalSales(LocalDateTime from, LocalDateTime to) {
        Collection<Order> orders = orderService.getAllOrders();
        return orders.stream()
                .filter(order -> order.getOrderStatus() == OrderStatus.COMPLETED)
                .filter(order -> (from == null || !order.getOrderedAt().isBefore(from)))
                .filter(order -> (to == null || !order.getOrderedAt().isAfter(to)))
                .mapToDouble(Order::getTotalAmount)
                .sum();
    }

    // Inventory usage report: total quantity ordered for each item
    public Map<String, Integer> getInventoryUsageReport(LocalDateTime from, LocalDateTime to) {
        Collection<Order> orders = orderService.getAllOrders();
        Map<String, Integer> itemUsage = new HashMap<>();
        orders.stream()
                .filter(order -> order.getOrderStatus() == OrderStatus.COMPLETED)
                .filter(order -> (from == null || !order.getOrderedAt().isBefore(from)))
                .filter(order -> (to == null || !order.getOrderedAt().isAfter(to)))
                .flatMap(order -> order.getOrderedItems().entrySet().stream())
                .forEach(entry -> {
                    itemUsage.put(entry.getKey(),
                            itemUsage.getOrDefault(entry.getKey(), 0) + entry.getValue());
                });
        return itemUsage;
    }

    // Staff performance report: number of completed orders per staff (userId)
    public Map<String, Long> getStaffPerformanceReport(LocalDateTime from, LocalDateTime to) {
        Collection<Order> orders = orderService.getAllOrders();
        return orders.stream()
                .filter(order -> order.getOrderStatus() == OrderStatus.COMPLETED)
                .filter(order -> order.getStaffId() != null) // only orders assigned to staff
                .filter(order -> (from == null || !order.getOrderedAt().isBefore(from)))
                .filter(order -> (to == null || !order.getOrderedAt().isAfter(to)))
                .collect(Collectors.groupingBy(Order::getStaffId, Collectors.counting()));
    }
}
