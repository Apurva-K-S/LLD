package com.example.PaymentProcessing.Service;

import com.example.PaymentProcessing.Entity.PaymentRequest;
import com.example.PaymentProcessing.Entity.PaymentResponse;
import com.example.PaymentProcessing.Enum.ResponseStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CreditCardService implements PaymentProcessing{

    Map<String, ResponseStatus> transactionStatusMap = new HashMap<>();

    @Override
    public PaymentResponse processPayment(PaymentRequest paymentRequest) {
        String transactionID = paymentRequest.getTransactionId();
        if(transactionID.isEmpty())
        {
            UUID uuid = UUID.randomUUID();
            transactionID = uuid.toString();
        }
        // call payment gateway for payment processing.
        PaymentResponse paymentResponse = new PaymentResponse(transactionID, ResponseStatus.SUCCESS, "transaction completed");
        transactionStatusMap.put(transactionID, ResponseStatus.SUCCESS);
        return paymentResponse;
    }

    @Override
    public boolean validatePaymentRequest(PaymentRequest paymentRequest) {
        return paymentRequest.getTotalAmount() > 0 && paymentRequest.getPaymentDetails().containsKey("cardNumber")
                && paymentRequest.getPaymentDetails().containsKey("cvv");
    }

    @Override
    public PaymentResponse refundPayment(String transactionId) {
        ResponseStatus responseStatus = transactionStatusMap.get(transactionId);
        if (responseStatus == null || !responseStatus.equals(ResponseStatus.SUCCESS))
        {
            return new PaymentResponse(transactionId, ResponseStatus.FAILED,"Refund cannot be processed");
        }
        transactionStatusMap.put(transactionId, ResponseStatus.REFUNDED);
        // call the respective BANK/UPI to do refund
        return new PaymentResponse(transactionId, ResponseStatus.REFUNDED, "Refund completed");
    }

    @Override
    public ResponseStatus getPaymentStatus(String transactionId) {
        return transactionStatusMap.get(transactionId);
    }
}
