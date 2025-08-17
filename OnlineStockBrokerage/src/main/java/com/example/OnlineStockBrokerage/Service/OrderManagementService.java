package com.example.OnlineStockBrokerage.Service;

import com.example.OnlineStockBrokerage.Entities.Order;
import com.example.OnlineStockBrokerage.Entities.Portfolio;
import com.example.OnlineStockBrokerage.Entities.TradeExecutionLog;
import com.example.OnlineStockBrokerage.Enums.ExecutionStatus;
import com.example.OnlineStockBrokerage.Enums.OrderStatus;
import com.example.OnlineStockBrokerage.Enums.OrderType;
import com.example.OnlineStockBrokerage.Strategy.OrderExecutionStrategy;

import java.time.LocalDateTime;
import java.util.*;

public class OrderManagementService {

    private final Map<String, Order> orderStore = new HashMap<>();
    private final List<TradeExecutionLog> executionLogs = new ArrayList<>();
    private final OrderExecutionStrategy executionStrategy;

    public OrderManagementService(OrderExecutionStrategy executionStrategy) {
        this.executionStrategy = executionStrategy;
    }

    public String placeOrder(String userId, String accountId, String companyId,
                             OrderType type, int quantity, double price) {

        // (Optional) balance/holdings checks would go here
        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setUserId(userId);
        order.setAccountId(accountId);
        order.setCompanyId(companyId);
        order.setOrderType(type);
        order.setShareUnits(quantity);
        order.setAmount(price); // price per unit
        order.setOrderStatus(OrderStatus.PENDING);
        order.setCreatedAt(LocalDateTime.now());

        orderStore.put(order.getId(), order);
        return order.getId();
    }

    public boolean cancelOrder(String orderId) {
        Order order = orderStore.get(orderId);
        if (order == null) return false;
        if (order.getOrderStatus() != OrderStatus.PENDING) return false;

        order.setOrderStatus(OrderStatus.CANCELLED);
        return true;
    }

    public List<TradeExecutionLog> executeOrders(String companyId) {
        // Get pending BUY and SELL orders for the company
        List<Order> buys = new ArrayList<>();
        List<Order> sells = new ArrayList<>();

        for (Order o : orderStore.values()) {
            if (!o.getCompanyId().equals(companyId)) continue;
            if (o.getOrderStatus() == OrderStatus.PENDING || o.getOrderStatus() == OrderStatus.PARTIAL) {
                if (o.getOrderType() == OrderType.BUY) buys.add(o);
                else sells.add(o);
            }
        }

        // Use strategy to match orders
        List<TradeExecutionLog> logs = executionStrategy.matchOrders(buys, sells);

        // Apply effects for each log
        for (TradeExecutionLog log : logs) {
            // Save log
            executionLogs.add(log);

            // Update linked order
            Order order = orderStore.get(log.getOrderId());
            if (order != null) {
                if (log.getExecutionStatus() == ExecutionStatus.SUCCESS && order.getShareUnits() == 0) {
                    order.setOrderStatus(OrderStatus.COMPLETED);
                } else if (log.getExecutionStatus() == ExecutionStatus.SUCCESS) {
                    order.setOrderStatus(OrderStatus.PARTIAL);
                }
            }
        }

        return logs;
    }

    public List<Order> viewOrders(String userId) {
        List<Order> orders = new ArrayList<>();
        for (Order o : orderStore.values()) {
            if (o.getUserId().equals(userId)) {
                orders.add(o);
            }
        }
        return orders;
    }

    public List<TradeExecutionLog> getAllExecutionLogs() {
        return new ArrayList<>(executionLogs);
    }

    public List<Portfolio> viewPortfolio(String userId,
                                         Map<String, Double> latestPrices,
                                         Map<String, String> companyNames) {
        Map<String, Integer> qtyMap = new HashMap<>();
        Map<String, Double> costMap = new HashMap<>();

        for (Order o : orderStore.values()) {
            if (!o.getUserId().equals(userId)) continue;
            if (o.getOrderStatus() != OrderStatus.COMPLETED && o.getOrderStatus() != OrderStatus.PARTIAL) continue;

            int qty = o.getShareUnits();
            double pricePerUnit = o.getAmount();

            if (o.getOrderType() == OrderType.BUY) {
                qtyMap.merge(o.getCompanyId(), qty, Integer::sum);
                costMap.merge(o.getCompanyId(), qty * pricePerUnit, Double::sum);
            } else if (o.getOrderType() == OrderType.SELL) {
                qtyMap.merge(o.getCompanyId(), -qty, Integer::sum);
            }
        }

        List<Portfolio> result = new ArrayList<>();
        for (String companyId : qtyMap.keySet()) {
            int sharesOwned = qtyMap.getOrDefault(companyId, 0);
            if (sharesOwned <= 0) continue;

            double avgBuyPrice = costMap.getOrDefault(companyId, 0.0) / sharesOwned;
            double currentPrice = latestPrices.getOrDefault(companyId, avgBuyPrice);
            double unrealizedPL = (currentPrice - avgBuyPrice) * sharesOwned;

            Portfolio p = new Portfolio();
            p.setCompanyId(companyId);
            p.setCompanyName(companyNames.getOrDefault(companyId, "Unknown Co."));
            p.setSharesOwned(sharesOwned);
            p.setAvgBuyPrice(avgBuyPrice);
            p.setCurrentPrice(currentPrice);
            p.setUnrealizedPL(unrealizedPL);

            result.add(p);
        }

        return result;
    }

    public List<Order> viewOrderHistory(String userId) {
        List<Order> orders = new ArrayList<>();
        for (Order o : orderStore.values()) {
            if (o.getUserId().equals(userId)) {
                orders.add(o);
            }
        }
        orders.sort(Comparator.comparing(Order::getCreatedAt).reversed());
        return orders;
    }
}
