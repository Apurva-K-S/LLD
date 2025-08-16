package com.example.DigitalWallet.Strategy;

import com.example.DigitalWallet.Entity.Transaction;
import com.example.DigitalWallet.Enum.TransactionStatus;
import com.example.DigitalWallet.Enum.TransactionType;
import com.example.DigitalWallet.External.UPIPort;
import com.example.DigitalWallet.External.WalletPort;

import java.time.Instant;

public class WalletToUPITransactionStrategy implements TransactionStrategy {
    private final WalletPort walletPort;
    private final UPIPort upiPort;

    public WalletToUPITransactionStrategy(WalletPort walletPort, UPIPort upiPort) {
        this.walletPort = walletPort;
        this.upiPort = upiPort;
    }

    @Override
    public TransactionType getType() {
        return TransactionType.WALLET_TO_UPI;
    }

    @Override
    public void validate(Transaction tx) {
        if (!walletPort.hasSufficientBalance(tx.getSenderId(), tx.getAmount()))
            throw new IllegalStateException("Insufficient wallet balance");
        if (!upiPort.verifyUPIHandle(tx.getReceiverId()))
            throw new IllegalArgumentException("Invalid UPI handle/ID");
    }

    @Override
    public Transaction execute(Transaction tx) {
        if (!walletPort.debit(tx.getSenderId(), tx.getAmount())) {
            tx.setTransactionStatus(TransactionStatus.FAILED);
            tx.setCompletedAt(Instant.now());
            return tx;
        }
        if (!upiPort.send(tx.getReceiverId(), tx.getAmount())) {
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
