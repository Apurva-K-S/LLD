package com.example.DigitalWallet.Strategy;

import com.example.DigitalWallet.Entity.Wallet;

public class PurchaseDebitStrategy implements WalletDebitStrategy {
    @Override
    public boolean debit(Wallet wallet, float amount) {
        if (wallet.canTransact(amount)) {
            wallet.setCurrentBalance(wallet.getCurrentBalance() - amount);
            return true;
        }
        return false;
    }
}