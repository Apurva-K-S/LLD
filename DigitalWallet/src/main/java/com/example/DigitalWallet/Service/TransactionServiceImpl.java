package com.example.DigitalWallet.Service;

import com.example.DigitalWallet.Entity.Transaction;
import com.example.DigitalWallet.Entity.Wallet;
import com.example.DigitalWallet.Enum.TransactionStatus;
import com.example.DigitalWallet.Factory.TransactionStrategyFactory;

import java.time.Instant;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class TransactionServiceImpl implements TransactionService {

    private final TransactionStrategyFactory strategyFactory;

    // In-memory ledger; replace with repository when needed
    private final Map<UUID, Transaction> transactions = new ConcurrentHashMap<>();

    public TransactionServiceImpl(TransactionStrategyFactory strategyFactory) {
        this.strategyFactory = strategyFactory;
    }

    @Override
    public Transaction startTransaction(Transaction tx) {
        UUID id = UUID.randomUUID();
        tx.setTransactionId(id);
        tx.setStartedAt(Instant.now());
        tx.setTransactionStatus(TransactionStatus.PENDING);
        transactions.put(id, tx);
        //  publish event
        emitEvent(tx);
        return tx;
    }

    @Override
    public void validateTransaction(UUID transactionId) {
        Transaction tx = getOrThrow(transactionId);
        strategyFactory.getStrategy(tx.getTransactionType()).validate(tx);
        // You can optionally mark status as VALIDATED if you want a distinct state
        // tx.setTransactionStatus(TransactionStatus.VALIDATED);
        // statusPublisher.publish(tx);
    }

    @Override
    public Transaction executeTransaction(UUID transactionId) {
        Transaction tx = getOrThrow(transactionId);
        try {
            Transaction executed = strategyFactory.getStrategy(tx.getTransactionType()).execute(tx);
            // Ensure terminal status and timestamps are set by either strategy or here
            if (executed.getTransactionStatus() == TransactionStatus.SUCCESS ||
                    executed.getTransactionStatus() == TransactionStatus.FAILED) {
                if (executed.getCompletedAt() == null) {
                    executed.setCompletedAt(Instant.now());
                }
            }
            transactions.put(executed.getTransactionId(), executed);
            //statusPublisher.publish(executed);
            emitEvent(tx);
            return executed;
        } catch (RuntimeException e) {
            tx.setTransactionStatus(TransactionStatus.FAILED);
            tx.setCompletedAt(Instant.now());
            transactions.put(tx.getTransactionId(), tx);
            //statusPublisher.publish(tx);
            throw e;
        }
    }

    @Override
    public Transaction getTransactionById(UUID transactionId) {
        return transactions.get(transactionId);
    }

    @Override
    public List<Transaction> listTransactionsByUser(UUID userId) {
        return transactions.values().stream()
                .filter(t -> userId.equals(t.getSenderId()) || userId.equals(t.getReceiverId()))
                .sorted(Comparator.comparing(Transaction::getStartedAt).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public TransactionStatus getTransactionStatus(UUID transactionId) {
        Transaction tx = getOrThrow(transactionId);
        return tx.getTransactionStatus();
    }

    private Transaction getOrThrow(UUID id) {
        Transaction tx = transactions.get(id);
        if (tx == null) throw new NoSuchElementException("Transaction not found: " + id);
        return tx;
    }

    public void emitEvent(Transaction txn) {
        System.out.println();
        System.out.println("Transaction details => "+
                "id: "+ txn.getTransactionId() +
                "| senderId: "+txn.getSenderId()+
                "| senderType: "+ txn.getSenderType()+
                "| receiverId: "+txn.getReceiverId() +
                "| receiverType: "+txn.getReceiverType()+
                "| transactionType: "+txn.getTransactionType() +
                "| txn status: "+txn.getTransactionStatus()+
                "| txn initiatedBy: "+txn.getInitiatedBy() +
                "| amount: "+txn.getAmount()+
                "| description: "+txn.getDescription()+
                "| metadata: "+txn.getMetadata());
        System.out.println();
    }
}
