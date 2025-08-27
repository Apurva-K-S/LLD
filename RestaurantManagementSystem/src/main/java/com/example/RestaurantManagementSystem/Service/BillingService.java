package com.example.RestaurantManagementSystem.Service;

import com.example.RestaurantManagementSystem.Entity.Order;
import com.example.RestaurantManagementSystem.Entity.Payment;

public class BillingService {

    private static final double TAX_RATE = 0.1; // 10% tax
    private static final double DISCOUNT_RATE = 0.05; // 5% discount, example

    // Calculate the total bill amount with tax and optional discount applied
    public double calculateTotalWithTaxAndDiscount(Order order, boolean applyDiscount) {
        double baseAmount = order.getTotalAmount();
        double taxAmount = baseAmount * TAX_RATE;
        double discount = applyDiscount ? baseAmount * DISCOUNT_RATE : 0;
        return baseAmount + taxAmount - discount;
    }

    // Generate receipt text combining order and payment details
    public String generateReceipt(Order order, Payment payment) {
        StringBuilder receipt = new StringBuilder();
        receipt.append("Receipt\n");
        receipt.append("Order ID: ").append(order.getId()).append("\n");
        receipt.append("Customer ID: ").append(order.getUserId()).append("\n");
        receipt.append("Order Date: ").append(order.getOrderedAt()).append("\n");
        receipt.append("Items:\n");
        order.getOrderedItems().forEach((itemId, quantity) -> {
            receipt.append("- Item: ").append(itemId).append(", Quantity: ").append(quantity).append("\n");
        });
        double totalBill = calculateTotalWithTaxAndDiscount(order, true);
        receipt.append("Total Amount (incl. tax & discount): ").append(String.format("%.2f", totalBill)).append("\n");
        if (payment != null) {
            receipt.append("Payment Method: ").append(payment.getPaymentMethod()).append("\n");
            receipt.append("Payment Status: ").append(payment.getPaymentStatus()).append("\n");
        }
        return receipt.toString();
    }
}
