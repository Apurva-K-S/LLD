package com.example.OnlineStockBrokerage.Factory;

import com.example.OnlineStockBrokerage.Entities.StockAccount;
import com.example.OnlineStockBrokerage.Enums.StockAccountStatus;
import com.example.OnlineStockBrokerage.Enums.StockAccountType;

import java.util.UUID;

public class StockAccountFactory {
    public StockAccount createAccount(StockAccountType type, String userId, double initialBalance) {
        UUID uuid = UUID.randomUUID();

        // Convert UUID to string
        String uuidString = uuid.toString();
        switch (type) {
            case STANDARD:
                return new StockAccount(uuidString,userId, initialBalance, 100000,20000, StockAccountStatus.UNDER_PROGRESS, StockAccountType.STANDARD);
            case MARGIN:
                return new StockAccount(uuidString,userId, initialBalance, 500000,50000, StockAccountStatus.UNDER_PROGRESS, StockAccountType.MARGIN);// example margin limit
            case RETIREMENT:
                return new StockAccount(uuidString,userId, initialBalance, 50000,1000, StockAccountStatus.UNDER_PROGRESS, StockAccountType.RETIREMENT);
            default:
                throw new IllegalArgumentException("Unsupported account type: " + type);
        }
    }
}
