package com.example.MakeMyTrip.Service;

public class UpiPayment implements ProcessPayment{
    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing UPI payment of amount: " + amount);
        // Integrate with UPI payment gateway here
        return true;
    }
}
