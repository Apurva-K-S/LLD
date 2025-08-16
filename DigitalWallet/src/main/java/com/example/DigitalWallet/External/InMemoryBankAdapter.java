package com.example.DigitalWallet.External;

import com.example.DigitalWallet.Entity.Wallet;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryBankAdapter implements BankPort {
    private final Map<UUID, Float> balances = new ConcurrentHashMap<>();

    @Override
    public boolean debitAccount(UUID accountId, float amount) {
        return balances.computeIfPresent(accountId, (id, bal) -> {
            if (bal >= amount) return bal - amount;
            return bal;
        }) >= 0;
    }

    @Override
    public boolean creditAccount(UUID accountId, float amount) {
        balances.merge(accountId, amount, Float::sum);
        return true;
    }

    @Override
    public boolean fraudCheck(UUID senderId, UUID receiverId, float amount) {
        // trivial always-pass for demo
        return true;
    }

    public void createAccount(UUID accountId, float initialBalance) {
        balances.put(accountId, initialBalance);
    }
}
