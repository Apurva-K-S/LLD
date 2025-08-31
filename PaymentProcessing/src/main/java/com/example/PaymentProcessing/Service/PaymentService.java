package com.example.PaymentProcessing.Service;

import com.example.PaymentProcessing.Entity.PaymentRequest;
import com.example.PaymentProcessing.Entity.PaymentResponse;
import com.example.PaymentProcessing.Enum.PaymentMethod;
import com.example.PaymentProcessing.Enum.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

public class PaymentService {
    private Map<PaymentMethod, PaymentProcessing> processors = new HashMap<>();

    public PaymentService() {
        processors.put(PaymentMethod.CREDIT_CARD, new CreditCardService());
        processors.put(PaymentMethod.WALLET, new WalletService());
    }

    public PaymentResponse processPayment(PaymentRequest paymentRequest) {
        PaymentProcessing paymentProcessing = processors.get(paymentRequest.getPaymentMethod());
        if(paymentProcessing == null)
        {
            return new PaymentResponse("", ResponseStatus.SUCCESS, "payment method is invalid");
        }
        if(!paymentProcessing.validatePaymentRequest(paymentRequest))
        {
            return new PaymentResponse("", ResponseStatus.SUCCESS, "payment request validation failed");
        }
        return paymentProcessing.processPayment(paymentRequest);
    }

    public PaymentResponse refundPayment(PaymentMethod paymentMethod, String transactionId) {
        PaymentProcessing processor = processors.get(paymentMethod);
        if (processor == null) {
            return new PaymentResponse("", ResponseStatus.SUCCESS, "Unsupported payment method");
        }
        return processor.refundPayment(transactionId);
    }

    public ResponseStatus getPaymentStatus(PaymentMethod paymentMethod, String transactionId) {
        PaymentProcessing processor = processors.get(paymentMethod);
        if (processor == null) {
            return null;
        }
        return processor.getPaymentStatus(transactionId);
    }
}
