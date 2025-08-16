package com.example.DigitalWallet.External;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryWalletAdapter implements WalletPort {
    private final Map<UUID, Float> balances = new ConcurrentHashMap<>();
    private final float DAILY_LIMIT = 10000f;

    @Override
    public boolean debit(UUID walletId, float amount) {
        return balances.computeIfPresent(walletId, (id, bal) -> {
            if (bal >= amount) return bal - amount;
            return bal; // no change
        }) >= 0;
    }

    @Override
    public boolean credit(UUID walletId, float amount) {
        balances.merge(walletId, amount, Float::sum);
        return true;
    }

    @Override
    public boolean hasSufficientBalance(UUID walletId, float amount) {
        return balances.getOrDefault(walletId, 0f) >= amount;
    }

    @Override
    public boolean withinDailyLimit(UUID walletId, float amount) {
        return amount <= DAILY_LIMIT;
    }

    // helper for tests
    public void createWallet(UUID walletId, float initialBalance) {
        balances.put(walletId, initialBalance);
    }
}
