package com.example.DigitalWallet.Strategy;

import com.example.DigitalWallet.Entity.Wallet;
import com.example.DigitalWallet.Strategy.WalletCreditStrategy;

public class BankTransferCreditStrategy implements WalletCreditStrategy {
    @Override
    public boolean credit(Wallet wallet, float amount) {
        wallet.setCurrentBalance(wallet.getCurrentBalance() + amount);
        wallet.setTotalBalance(wallet.getTotalBalance() + amount);
        return true;
    }
}