package com.example.OnlineStockBrokerage.Command;

import com.example.OnlineStockBrokerage.Entities.StockAccount;

public class DepositCommand implements FundCommand {
    private final StockAccount account;
    private final double amount;

    public DepositCommand(StockAccount account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void execute() {
        account.setBalance(account.getBalance() + amount);
        // Optionally notify observers or log
    }
}
