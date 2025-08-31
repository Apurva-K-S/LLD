package com.example.PaymentProcessing.Service;

import com.example.PaymentProcessing.Entity.PaymentRequest;
import com.example.PaymentProcessing.Entity.PaymentResponse;
import com.example.PaymentProcessing.Enum.ResponseStatus;

public class WalletService implements PaymentProcessing{
    @Override
    public PaymentResponse processPayment(PaymentRequest paymentRequest) {
        return null;
    }

    @Override
    public boolean validatePaymentRequest(PaymentRequest paymentRequest) {
        return false;
    }

    @Override
    public PaymentResponse refundPayment(String transactionId) {
        return null;
    }

    @Override
    public ResponseStatus getPaymentStatus(String transactionId) {
        return null;
    }
}
