package com.example.OnlineStockBrokerage.Command;

import com.example.OnlineStockBrokerage.Entities.StockAccount;

public class WithdrawCommand implements FundCommand{
    private final StockAccount account;
    private final double amount;

    public WithdrawCommand(StockAccount account, double amount) {
        this.account = account;
        this.amount = amount;
    }

    @Override
    public void execute() {
        if (account.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        account.setBalance(account.getBalance() - amount);
        // Optionally notify observers or log
    }
}
