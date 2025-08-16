package com.example.DigitalWallet.Service;

import com.example.DigitalWallet.Entity.Transaction;
import com.example.DigitalWallet.Enum.TransactionStatus;
import com.example.DigitalWallet.Enum.TransactionType;

import java.util.List;
import java.util.UUID;

public interface TransactionService {

    // StartTransaction: create and register a PENDING transaction
    Transaction startTransaction(Transaction tx);

    // ValidateTransaction: run limits/balance/fraud checks via the appropriate strategy
    void validateTransaction(UUID transactionId);

    // ExecuteTransaction: perform debit/credit via dependent services and update status
    Transaction executeTransaction(UUID transactionId);

    // GetTransactionById: fetch transaction
    Transaction getTransactionById(UUID transactionId);

    // ListTransactionsByUser: both as sender or receiver
    List<Transaction> listTransactionsByUser(UUID userId);

    // TrackStatus: current lifecycle status
    TransactionStatus getTransactionStatus(UUID transactionId);
}
