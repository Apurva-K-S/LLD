package com.example.DigitalWallet.Strategy;

import com.example.DigitalWallet.Entity.Wallet;

public interface WalletCreditStrategy {
    boolean credit(Wallet wallet, float amount);
}