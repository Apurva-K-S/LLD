package com.example.MakeMyTrip.Service;

public class DebitCardPayment implements ProcessPayment{
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing debit card payment of amount: " + amount);
        // Integrate with debit card payment gateway here
        return true;
    }
}
