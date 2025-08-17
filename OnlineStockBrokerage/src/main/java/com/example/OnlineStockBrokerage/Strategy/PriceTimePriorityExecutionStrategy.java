package com.example.OnlineStockBrokerage.Strategy;

import com.example.OnlineStockBrokerage.Entities.Order;
import com.example.OnlineStockBrokerage.Entities.TradeExecutionLog;
import com.example.OnlineStockBrokerage.Enums.ExecutionStatus;

import java.time.LocalDateTime;
import java.util.*;

public class PriceTimePriorityExecutionStrategy implements OrderExecutionStrategy {

    @Override
    public List<TradeExecutionLog> matchOrders(List<Order> buys, List<Order> sells) {
        // Sort BUY orders: highest price first, earliest created first
        buys.sort(Comparator.comparing(Order::getAmount).reversed()
                .thenComparing(Order::getCreatedAt));

        // Sort SELL orders: lowest price first, earliest created first
        sells.sort(Comparator.comparing(Order::getAmount)
                .thenComparing(Order::getCreatedAt));

        List<TradeExecutionLog> logs = new ArrayList<>();

        for (Order buy : buys) {
            Iterator<Order> sellIter = sells.iterator();

            while (sellIter.hasNext() && buy.getShareUnits() > 0) {
                Order sell = sellIter.next();

                // Matching condition: sell price <= buy price
                if (sell.getAmount() <= buy.getAmount()) {
                    int qtyMatched = Math.min(buy.getShareUnits(), sell.getShareUnits());
                    double tradePrice = sell.getAmount();

                    // Create execution logs for each side
                    logs.add(buildExecutionLog(buy, qtyMatched, tradePrice));
                    logs.add(buildExecutionLog(sell, qtyMatched, tradePrice));

                    // Reduce quantities after the trade
                    buy.setShareUnits(buy.getShareUnits() - qtyMatched);
                    sell.setShareUnits(sell.getShareUnits() - qtyMatched);

                    // Update order statuses
                    updateOrderStatusAfterTrade(buy);
                    updateOrderStatusAfterTrade(sell);

                    // Remove sell order if fully executed
                    if (sell.getShareUnits() == 0) {
                        sellIter.remove();
                    }
                } else {
                    break; // No match possible due to sorted order
                }
            }
        }

        return logs;
    }

    private TradeExecutionLog buildExecutionLog(Order order, int qty, double price) {
        TradeExecutionLog log = new TradeExecutionLog();
        log.setId(UUID.randomUUID().toString());
        log.setOrderId(order.getId());
        log.setCompanyId(order.getCompanyId());
        log.setUserAccountId(order.getAccountId());
        log.setExecutedPrice(price);
        log.setExecutedUnits(qty);
        log.setCreatedAt(LocalDateTime.now());
        log.setExecutionStatus(ExecutionStatus.SUCCESS);
        return log;
    }

    private void updateOrderStatusAfterTrade(Order order) {
        if (order.getShareUnits() == 0) {
            order.setOrderStatus(com.example.OnlineStockBrokerage.Enums.OrderStatus.COMPLETED);
        } else {
            order.setOrderStatus(com.example.OnlineStockBrokerage.Enums.OrderStatus.PARTIAL);
        }
    }
}
