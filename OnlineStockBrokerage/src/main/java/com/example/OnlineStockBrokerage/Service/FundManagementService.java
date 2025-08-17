package com.example.OnlineStockBrokerage.Service;

import com.example.OnlineStockBrokerage.Command.DepositCommand;
import com.example.OnlineStockBrokerage.Command.FundCommand;
import com.example.OnlineStockBrokerage.Command.WithdrawCommand;
import com.example.OnlineStockBrokerage.Entities.StockAccount;

import java.util.HashMap;
import java.util.Map;

public class FundManagementService {
    StockAccountManagementService stockAccountManagementService;

    public FundManagementService(StockAccountManagementService stockAccountManagementService) {
        this.stockAccountManagementService = stockAccountManagementService;
    }

    public void depositFunds(String accountId, double amount) {
        StockAccount account = getAccount(accountId);
        FundCommand command = new DepositCommand(account, amount);
        command.execute();
    }

    public void withdrawFunds(String accountId, double amount) {
        StockAccount account = getAccount(accountId);
        FundCommand command = new WithdrawCommand(account, amount);
        command.execute();
    }

    private StockAccount getAccount(String accountId) {
        StockAccount account = stockAccountManagementService.getAccountById(accountId);
        if (account == null) throw new IllegalArgumentException("Account not found");
        return account;
    }

}
