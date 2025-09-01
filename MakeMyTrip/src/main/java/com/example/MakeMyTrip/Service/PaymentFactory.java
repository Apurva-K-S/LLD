package com.example.MakeMyTrip.Service;

import com.example.MakeMyTrip.Enum.PaymentMethod;

public class PaymentFactory {
    public static ProcessPayment getPaymentMethod(PaymentMethod type) {
        switch (type) {
            case CREDIT_CARD:
                return new CreditCardPayment();
            case DEBIT_CARD:
                return new DebitCardPayment();
            case UPI:
                return new UpiPayment();
            default:
                throw new IllegalArgumentException("Unsupported payment type: " + type);
        }
    }
}
