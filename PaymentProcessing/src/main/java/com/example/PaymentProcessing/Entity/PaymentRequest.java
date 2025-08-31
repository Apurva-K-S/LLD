package com.example.PaymentProcessing.Entity;

import com.example.PaymentProcessing.Enum.Currency;
import com.example.PaymentProcessing.Enum.PaymentMethod;

import java.util.Map;

public class PaymentRequest {
    private double totalAmount;
    private String userId;
    private PaymentMethod paymentMethod;
    private Currency currency;
    private Map<String, String> paymentDetails;
    private String description;
    private String transactionId;

    public PaymentRequest(double totalAmount, String userId, PaymentMethod paymentMethod, Currency currency, Map<String, String> paymentDetails, String description, String transactionId) {
        this.totalAmount = totalAmount;
        this.userId = userId;
        this.paymentMethod = paymentMethod;
        this.currency = currency;
        this.paymentDetails = paymentDetails;
        this.description = description;
        this.transactionId = transactionId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Map<String, String> getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(Map<String, String> paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
