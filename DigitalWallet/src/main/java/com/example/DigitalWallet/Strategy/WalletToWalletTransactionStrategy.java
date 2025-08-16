package com.example.DigitalWallet.Strategy;

import com.example.DigitalWallet.Entity.Transaction;
import com.example.DigitalWallet.Enum.TransactionStatus;
import com.example.DigitalWallet.Enum.TransactionType;
import com.example.DigitalWallet.External.WalletPort;

import java.time.Instant;

public class WalletToWalletTransactionStrategy implements TransactionStrategy {

    private final WalletPort walletPort;

    public WalletToWalletTransactionStrategy(WalletPort walletPort) {
        this.walletPort = walletPort;
    }

    @Override
    public TransactionType getType() {
        return TransactionType.WALLET_TO_WALLET;
    }

    @Override
    public void validate(Transaction tx) {
        if (tx.getSenderId() == null || tx.getReceiverId() == null) {
            throw new IllegalArgumentException("Sender and Receiver must be provided");
        }
        if (tx.getSenderId().equals(tx.getReceiverId())) {
            throw new IllegalArgumentException("Sender and Receiver cannot be the same");
        }
        if (tx.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (!walletPort.hasSufficientBalance(tx.getSenderId(), tx.getAmount())) {
            throw new IllegalStateException("Insufficient balance");
        }
        if (!walletPort.withinDailyLimit(tx.getSenderId(), tx.getAmount())) {
            throw new IllegalStateException("Daily limit exceeded");
        }
        // Additional fraud checks can live here
    }

    @Override
    public Transaction execute(Transaction tx) {
        boolean debited = walletPort.debit(tx.getSenderId(), tx.getAmount());
        if (!debited) {
            tx.setTransactionStatus(TransactionStatus.FAILED);
            tx.setCompletedAt(Instant.now());
            return tx;
        }
        boolean credited = walletPort.credit(tx.getReceiverId(), tx.getAmount());
        if (!credited) {
            // Compensate debit if needed (best-effort)
            walletPort.credit(tx.getSenderId(), tx.getAmount());
            tx.setTransactionStatus(TransactionStatus.FAILED);
            tx.setCompletedAt(Instant.now());
            return tx;
        }
        tx.setTransactionStatus(TransactionStatus.SUCCESS);
        tx.setCompletedAt(Instant.now());
        return tx;
    }
}
