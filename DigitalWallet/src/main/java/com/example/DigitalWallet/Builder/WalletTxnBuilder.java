package com.example.DigitalWallet.Builder;

import com.example.DigitalWallet.Entity.Transaction;
import com.example.DigitalWallet.Enum.ParticipantType;
import com.example.DigitalWallet.Enum.TransactionStatus;
import com.example.DigitalWallet.Enum.TransactionType;

import java.time.Instant;
import java.util.Map;
import java.util.UUID;

public class WalletTxnBuilder {
    private final Transaction txn;

    public WalletTxnBuilder() {
        txn = new Transaction();
        txn.setTransactionId(UUID.randomUUID());
        txn.setStartedAt(Instant.now());
    }

    public WalletTxnBuilder sender(UUID senderId, ParticipantType type) {
        txn.setSenderId(senderId);
        txn.setSenderType(type);
        return this;
    }

    public WalletTxnBuilder receiver(UUID receiverId, ParticipantType type) {
        txn.setReceiverId(receiverId);
        txn.setReceiverType(type);
        return this;
    }

    public WalletTxnBuilder type(TransactionType transactionType) {
        txn.setTransactionType(transactionType);
        return this;
    }

    public WalletTxnBuilder amount(float amount) {
        txn.setAmount(amount);
        return this;
    }

    public WalletTxnBuilder description(String description) {
        txn.setDescription(description);
        return this;
    }

    public WalletTxnBuilder status(TransactionStatus status) {
        txn.setTransactionStatus(status);
        if (status == TransactionStatus.SUCCESS || status == TransactionStatus.FAILED) {
            txn.setCompletedAt(Instant.now());
        }
        return this;
    }

    public WalletTxnBuilder metadata(Map<String, String> metadata) {
        txn.setMetadata(metadata);
        return this;
    }

    public Transaction build() {
        return txn;
    }
}