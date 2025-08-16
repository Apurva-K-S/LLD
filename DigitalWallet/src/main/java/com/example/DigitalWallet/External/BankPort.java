package com.example.DigitalWallet.External;

import java.util.UUID;

public interface BankPort {
    boolean debitAccount(UUID accountId, float amount);
    boolean creditAccount(UUID accountId, float amount);
    boolean fraudCheck(UUID senderId, UUID receiverId, float amount);
    void createAccount(UUID accountId, float initialBalance);
}