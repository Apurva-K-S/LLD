package com.example.PaymentProcessing.Entity;

import com.example.PaymentProcessing.Enum.ResponseStatus;

public class PaymentResponse {
    private String transactionId;
    private ResponseStatus responseStatus;
    private String message;

    public PaymentResponse(String transactionId, ResponseStatus responseStatus, String message) {
        this.transactionId = transactionId;
        this.responseStatus = responseStatus;
        this.message = message;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
