package com.example.MakeMyTrip.Entity;

import com.example.MakeMyTrip.Enum.BookingStatus;

import java.time.LocalDateTime;

public class Booking {
    private String bookingId;
    private String userId;                  // User making the booking
    private String flightInstanceId;       // FlightInstance being booked
    private LocalDateTime bookingDate;
    private int numberOfSeats;
    private BookingStatus bookingStatus;
    private double totalAmount;
    private String paymentId;               // Associated payment transaction

    public Booking(String bookingId, String userId, String flightInstanceId, LocalDateTime bookingDate, int numberOfSeats, BookingStatus bookingStatus, double totalAmount, String paymentId) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.flightInstanceId = flightInstanceId;
        this.bookingDate = bookingDate;
        this.numberOfSeats = numberOfSeats;
        this.bookingStatus = bookingStatus;
        this.totalAmount = totalAmount;
        this.paymentId = paymentId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFlightInstanceId() {
        return flightInstanceId;
    }

    public void setFlightInstanceId(String flightInstanceId) {
        this.flightInstanceId = flightInstanceId;
    }

    public LocalDateTime getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDateTime bookingDate) {
        this.bookingDate = bookingDate;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }
}
