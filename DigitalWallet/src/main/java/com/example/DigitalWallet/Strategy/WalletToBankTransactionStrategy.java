package com.example.DigitalWallet.Strategy;

import com.example.DigitalWallet.Entity.Transaction;
import com.example.DigitalWallet.Enum.TransactionStatus;
import com.example.DigitalWallet.Enum.TransactionType;
import com.example.DigitalWallet.External.BankPort;
import com.example.DigitalWallet.External.WalletPort;

import java.time.Instant;

public class WalletToBankTransactionStrategy implements TransactionStrategy {
    private final WalletPort walletPort;
    private final BankPort bankPort;

    public WalletToBankTransactionStrategy(WalletPort walletPort, BankPort bankPort) {
        this.walletPort = walletPort;
        this.bankPort = bankPort;
    }

    @Override
    public TransactionType getType() {
        return TransactionType.WALLET_TO_BANK;
    }

    @Override
    public void validate(Transaction tx) {
        if (!walletPort.hasSufficientBalance(tx.getSenderId(), tx.getAmount()))
            throw new IllegalStateException("Insufficient wallet balance");
    }

    @Override
    public Transaction execute(Transaction tx) {
        if (!walletPort.debit(tx.getSenderId(), tx.getAmount())) {
            tx.setTransactionStatus(TransactionStatus.FAILED);
            tx.setCompletedAt(Instant.now());
            return tx;
        }
        if (!bankPort.creditAccount(tx.getReceiverId(), tx.getAmount())) {
            walletPort.credit(tx.getSenderId(), tx.getAmount()); // rollback
            tx.setTransactionStatus(TransactionStatus.FAILED);
            tx.setCompletedAt(Instant.now());
            return tx;
        }
        tx.setTransactionStatus(TransactionStatus.SUCCESS);
        tx.setCompletedAt(Instant.now());
        return tx;
    }
}
