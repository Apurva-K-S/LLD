package com.example.OnlineStockBrokerage.Service;

import com.example.OnlineStockBrokerage.Entities.StockAccount;
import com.example.OnlineStockBrokerage.Enums.StockAccountType;
import com.example.OnlineStockBrokerage.Factory.StockAccountFactory;

import java.util.HashMap;
import java.util.Map;

public class StockAccountManagementService {
    private final StockAccountFactory accountFactory;
    private final Map<String, StockAccount> accountStore = new HashMap<>();

    public StockAccountManagementService(StockAccountFactory accountFactory) {
        this.accountFactory = accountFactory;
    }

    public StockAccount createAccount(StockAccountType type, String userId, double initialBalance) {
        StockAccount account = accountFactory.createAccount(type, userId, initialBalance);
        accountStore.put(account.getId(), account);
        emitEvent(account);
        return account;
    }

    public StockAccount viewAccount(String accountId) {
        emitEvent(accountStore.get(accountId));
        return accountStore.get(accountId);
    }

    public void emitEvent(StockAccount stkAccnt) {
        System.out.println("StockAccount Details: " + stkAccnt.getId() +
                "| status: "+stkAccnt.getStockAccountStatus() +
                "| userId: " + stkAccnt.getUserId() +
                "| balance: "+stkAccnt.getBalance() +
                "| daily limit: "+stkAccnt.getDailyLimit() +
                "| accountType: "+stkAccnt.getAccountType()+
                "| hourlyLimit: "+stkAccnt.getHourlyLimit());
    }

    public StockAccount getAccountById(String accountId) {
        return accountStore.get(accountId);
    }
}
