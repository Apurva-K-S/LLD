package com.example.OnlineStockBrokerage.Factory;

import com.example.OnlineStockBrokerage.Entities.Order;
import com.example.OnlineStockBrokerage.Enums.OrderType;

public interface OrderFactory {
    Order createOrder(String userId, String symbol, OrderType type, int quantity, double price);
}
