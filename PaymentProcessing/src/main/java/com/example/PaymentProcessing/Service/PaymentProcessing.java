package com.example.PaymentProcessing.Service;

import com.example.PaymentProcessing.Entity.PaymentRequest;
import com.example.PaymentProcessing.Entity.PaymentResponse;
import com.example.PaymentProcessing.Enum.ResponseStatus;

public interface PaymentProcessing {
    PaymentResponse processPayment(PaymentRequest paymentRequest);
    boolean validatePaymentRequest(PaymentRequest paymentRequest);
    PaymentResponse refundPayment(String transactionId);
    ResponseStatus getPaymentStatus(String transactionId);
}
