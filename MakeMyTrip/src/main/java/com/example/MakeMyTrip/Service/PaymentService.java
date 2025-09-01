package com.example.MakeMyTrip.Service;

import com.example.MakeMyTrip.Entity.Booking;
import com.example.MakeMyTrip.Entity.Payment;
import com.example.MakeMyTrip.Enum.BookingStatus;
import com.example.MakeMyTrip.Enum.PaymentMethod;
import com.example.MakeMyTrip.Enum.PaymentStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PaymentService {
    private Map<String, Payment> payments = new HashMap<>();
    private BookingService bookingService;

    public PaymentService(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public Payment processPayment(String bookingId, PaymentMethod paymentMethod, double amount) {
        Booking booking = bookingService.getBookingById(bookingId);
        if (booking == null) {
            throw new IllegalArgumentException("Booking not found");
        }
        if (booking.getBookingStatus() == BookingStatus.CANCELLED) {
            throw new IllegalStateException("Booking is cancelled");
        }

        UUID uuid = UUID.randomUUID();

        Payment payment = new Payment(uuid.toString(), bookingId, PaymentStatus.INITIATED, paymentMethod, amount, LocalDateTime.now());

        ProcessPayment processPayment = PaymentFactory.getPaymentMethod(paymentMethod);
        boolean status = processPayment.processPayment(amount);
        if(status) {
            payment.setStatus(PaymentStatus.SUCCESS);
        } else {
            payment.setStatus(PaymentStatus.FAILED);
        }
        payments.put(payment.getPaymentId(), payment);

        bookingService.updateBookingStatus(bookingId, BookingStatus.CONFIRMED);
        booking.setPaymentId(payment.getPaymentId());

        return payment;
    }

    public boolean refundPayment(String paymentId) {
        Payment payment = payments.get(paymentId);
        if (payment == null || payment.getStatus() != PaymentStatus.SUCCESS) {
            return false;
        }
        payment.setStatus(PaymentStatus.REFUNDED);
        // Also update booking if necessary
        Booking booking = bookingService.getBookingById(payment.getBookingId());
        if (booking != null) {
            booking.setBookingStatus(BookingStatus.CANCELLED);
        }
        return true;
    }
}
