package com.example.OnlineStockBrokerage.Factory;

import com.example.OnlineStockBrokerage.Entities.Order;
import com.example.OnlineStockBrokerage.Enums.OrderType;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.UUID;

public class DefaultOrderFactory implements OrderFactory {

    @Override
    public Order createOrder(String userId, String symbol, OrderType type, int quantity, double price) {
        Order order = new Order();
        order.setId(UUID.randomUUID().toString());
        order.setUserId(userId);
        order.setOrderType(type);
        order.setShareUnits(quantity);
        order.setAmount(price);
        order.setCreatedAt(LocalDateTime.now());
        return order;
    }
}
