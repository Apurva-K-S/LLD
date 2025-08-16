package com.example.DigitalWallet.Entity;

import com.example.DigitalWallet.Enum.ParticipantType;
import com.example.DigitalWallet.Enum.TransactionStatus;
import com.example.DigitalWallet.Enum.TransactionType;

import java.util.Map;
import java.util.UUID;
import java.time.Instant;

public class Transaction {

    private UUID transactionId;
    private UUID senderId;
    private UUID receiverId;
    private ParticipantType senderType;
    private ParticipantType receiverType;
    private TransactionType transactionType;
    private TransactionStatus transactionStatus;
    private UUID initiatedBy;
    private float amount;
    private String description;
    private Instant startedAt;
    private Instant completedAt;
    private boolean isReversed;
    private UUID reversalTransactionId;
    private Map<String, String> metadata;

    // Constructors
    public Transaction() {}

    public Transaction(UUID transactionId, UUID senderId, UUID receiverId,
                       ParticipantType senderType, ParticipantType receiverType,
                       TransactionType transactionType, TransactionStatus transactionStatus,
                       UUID initiatedBy, float amount, String description,
                       Instant startedAt, Instant completedAt,
                       boolean isReversed, UUID reversalTransactionId,
                       Map<String, String> metadata) {
        this.transactionId = transactionId;
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.senderType = senderType;
        this.receiverType = receiverType;
        this.transactionType = transactionType;
        this.transactionStatus = transactionStatus;
        this.initiatedBy = initiatedBy;
        this.amount = amount;
        this.description = description;
        this.startedAt = startedAt;
        this.completedAt = completedAt;
        this.isReversed = isReversed;
        this.reversalTransactionId = reversalTransactionId;
        this.metadata = metadata;
    }

    // Getters and Setters
    // (Can be generated or replaced with Lombok)

    // Optional: helper methods
    public boolean isSuccessful() {
        return this.transactionStatus == TransactionStatus.SUCCESS;
    }

    public boolean isPending() {
        return this.transactionStatus == TransactionStatus.PENDING;
    }

    public boolean isReversal() {
        return this.isReversed && this.reversalTransactionId != null;
    }

    public UUID getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }

    public UUID getSenderId() {
        return senderId;
    }

    public void setSenderId(UUID senderId) {
        this.senderId = senderId;
    }

    public UUID getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(UUID receiverId) {
        this.receiverId = receiverId;
    }

    public ParticipantType getSenderType() {
        return senderType;
    }

    public void setSenderType(ParticipantType senderType) {
        this.senderType = senderType;
    }

    public ParticipantType getReceiverType() {
        return receiverType;
    }

    public void setReceiverType(ParticipantType receiverType) {
        this.receiverType = receiverType;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public TransactionStatus getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(TransactionStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public UUID getInitiatedBy() {
        return initiatedBy;
    }

    public void setInitiatedBy(UUID initiatedBy) {
        this.initiatedBy = initiatedBy;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Instant getStartedAt() {
        return startedAt;
    }

    public void setStartedAt(Instant startedAt) {
        this.startedAt = startedAt;
    }

    public Instant getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(Instant completedAt) {
        this.completedAt = completedAt;
    }

    public boolean isReversed() {
        return isReversed;
    }

    public void setReversed(boolean reversed) {
        isReversed = reversed;
    }

    public UUID getReversalTransactionId() {
        return reversalTransactionId;
    }

    public void setReversalTransactionId(UUID reversalTransactionId) {
        this.reversalTransactionId = reversalTransactionId;
    }

    public Map<String, String> getMetadata() {
        return metadata;
    }

    public void setMetadata(Map<String, String> metadata) {
        this.metadata = metadata;
    }
}
