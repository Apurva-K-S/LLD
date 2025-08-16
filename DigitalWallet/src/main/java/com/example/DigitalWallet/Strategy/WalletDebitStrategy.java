package com.example.DigitalWallet.Strategy;

import com.example.DigitalWallet.Entity.Wallet;

public interface WalletDebitStrategy {
    boolean debit(Wallet wallet, float amount);
}