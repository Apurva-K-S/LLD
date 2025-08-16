package com.example.DigitalWallet.Strategy;

import com.example.DigitalWallet.Entity.Transaction;
import com.example.DigitalWallet.Enum.TransactionType;

public interface TransactionStrategy {
    TransactionType getType();
    void validate(Transaction tx);
    Transaction execute(Transaction tx);
}
