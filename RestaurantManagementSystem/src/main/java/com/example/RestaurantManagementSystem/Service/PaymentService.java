package com.example.RestaurantManagementSystem.Service;

import com.example.RestaurantManagementSystem.Entity.Payment;
import com.example.RestaurantManagementSystem.Enum.PaymentMethod;
import com.example.RestaurantManagementSystem.Enum.PaymentStatus;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.UUID;

public class PaymentService {

    private Map<String, Payment> paymentMap = new ConcurrentHashMap<>();

    // Process payment for an order
    public Payment processPayment(String orderId, double totalAmount, PaymentMethod paymentMethod) {
        if (orderId == null || totalAmount <= 0 || paymentMethod == null) {
            throw new IllegalArgumentException("Invalid payment parameters.");
        }
        String paymentId = UUID.randomUUID().toString();
        LocalDateTime paymentTime = LocalDateTime.now();

        // Simulate payment processing, assume success
        PaymentStatus paymentStatus = PaymentStatus.COMPLETED;

        Payment payment = new Payment(paymentId, orderId, totalAmount, paymentMethod, paymentStatus, paymentTime);
        paymentMap.put(paymentId, payment);
        return payment;
    }

    // Get payment by payment ID
    public Payment getPaymentById(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Payment ID cannot be null.");
        }
        return paymentMap.get(id);
    }

    // Update payment status if needed (e.g., for refunds)
    public boolean updatePaymentStatus(String paymentId, PaymentStatus status) {
        Payment payment = getPaymentById(paymentId);
        if (payment == null) {
            return false;
        }
        payment.setPaymentStatus(status);
        return true;
    }
}
