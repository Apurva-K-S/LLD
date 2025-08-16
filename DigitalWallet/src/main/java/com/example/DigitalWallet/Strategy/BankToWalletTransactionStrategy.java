package com.example.DigitalWallet.Strategy;

import com.example.DigitalWallet.Entity.Transaction;
import com.example.DigitalWallet.Enum.TransactionStatus;
import com.example.DigitalWallet.Enum.TransactionType;
import com.example.DigitalWallet.External.BankPort;
import com.example.DigitalWallet.External.WalletPort;

import java.time.Instant;

public class BankToWalletTransactionStrategy implements TransactionStrategy {
    private final BankPort bankPort;
    private final WalletPort walletPort;

    public BankToWalletTransactionStrategy(BankPort bankPort, WalletPort walletPort) {
        this.bankPort = bankPort;
        this.walletPort = walletPort;
    }

    @Override
    public TransactionType getType() {
        return TransactionType.BANK_TO_WALLET;
    }

    @Override
    public void validate(Transaction tx) {
        if (!bankPort.fraudCheck(tx.getSenderId(), tx.getReceiverId(), tx.getAmount()))
            throw new IllegalStateException("Fraud check failed");
        // Could also check per-transaction limits here
    }

    @Override
    public Transaction execute(Transaction tx) {
        if (!bankPort.debitAccount(tx.getSenderId(), tx.getAmount())) {
            tx.setTransactionStatus(TransactionStatus.FAILED);
            tx.setCompletedAt(Instant.now());
            return tx;
        }
        if (!walletPort.credit(tx.getReceiverId(), tx.getAmount())) {
            // Optionally refund bank account
            bankPort.creditAccount(tx.getSenderId(), tx.getAmount());
            tx.setTransactionStatus(TransactionStatus.FAILED);
            tx.setCompletedAt(Instant.now());
            return tx;
        }
        tx.setTransactionStatus(TransactionStatus.SUCCESS);
        tx.setCompletedAt(Instant.now());
        return tx;
    }
}
