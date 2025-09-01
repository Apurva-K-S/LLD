package com.example.MakeMyTrip.Service;

public class CreditCardPayment implements ProcessPayment{
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing credit card payment of amount: " + amount);
        // Integrate with credit card payment gateway here
        return true;
    }
}
