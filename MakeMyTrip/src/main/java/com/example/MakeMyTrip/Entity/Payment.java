package com.example.MakeMyTrip.Entity;

import com.example.MakeMyTrip.Enum.PaymentMethod;
import com.example.MakeMyTrip.Enum.PaymentStatus;

import java.time.LocalDateTime;

public class Payment {
    private String paymentId;
    private String bookingId;       // Booking linked to payment
    private PaymentStatus status;
    private PaymentMethod paymentMethod;     // e.g., CARD, NET_BANKING, UPI
    private double amount;
    private LocalDateTime paymentDate;


    public Payment(String paymentId, String bookingId, PaymentStatus status, PaymentMethod paymentMethod, double amount, LocalDateTime paymentDate) {
        this.paymentId = paymentId;
        this.bookingId = bookingId;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void setStatus(PaymentStatus status) {
        this.status = status;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }
}
