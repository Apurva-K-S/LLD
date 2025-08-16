package com.example.DigitalWallet.External;

import java.util.UUID;

public interface WalletPort {
    boolean debit(UUID walletId, float amount);
    boolean credit(UUID walletId, float amount);
    boolean hasSufficientBalance(UUID walletId, float amount);
    boolean withinDailyLimit(UUID walletId, float amount);
}