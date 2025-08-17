package com.example.OnlineStockBrokerage.Strategy;


import com.example.OnlineStockBrokerage.Entities.Order;
import com.example.OnlineStockBrokerage.Entities.TradeExecutionLog;

import java.util.List;

public interface OrderExecutionStrategy {
    List<TradeExecutionLog> matchOrders(List<Order> buyOrders, List<Order> sellOrders);
}
