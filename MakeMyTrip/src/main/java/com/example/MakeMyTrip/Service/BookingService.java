package com.example.MakeMyTrip.Service;

import com.example.MakeMyTrip.Entity.Booking;
import com.example.MakeMyTrip.Entity.FlightInstance;
import com.example.MakeMyTrip.Enum.BookingStatus;

import java.time.LocalDateTime;
import java.util.*;

public class BookingService {
    private Map<String, Booking> bookings = new HashMap<>();
    private FlightService flightService;

    public BookingService(FlightService flightService) {
        this.flightService = flightService;
    }

    public Booking bookFlight(String userId, String flightInstanceId, int numberOfSeats) {
        FlightInstance flightInstance = flightService.getFlightInstanceById(flightInstanceId);
        if (flightInstance == null) {
            throw new IllegalArgumentException("Flight instance not found");
        }
        synchronized (flightInstance) {
            if (flightInstance.getAvailableCapacity() < numberOfSeats) {
                throw new IllegalArgumentException("Not enough seats available");
            }
            flightInstance.setAvailableCapacity(flightInstance.getAvailableCapacity() - numberOfSeats);
        }

        double totalAmount = flightInstance.getPriceOfSeat() * numberOfSeats;
        UUID bookingId = UUID.randomUUID();
        Booking booking = new Booking(bookingId.toString(), userId, flightInstanceId, LocalDateTime.now(), numberOfSeats, BookingStatus.PENDING, totalAmount, null);
        bookings.put(booking.getBookingId(), booking);
        return booking;
    }

    public Booking getBookingById(String bookingId) {
        return bookings.get(bookingId);
    }

    // To update booking status after payment success/failure
    public void updateBookingStatus(String bookingId, BookingStatus status) {
        Booking booking = bookings.get(bookingId);
        if (booking != null) {
            booking.setBookingStatus(status);
        }
    }

    public void setPaymentIdForBooking(String bookingId, String paymentId) {
        Booking booking = bookings.get(bookingId);
        if (booking != null) {
            booking.setPaymentId(paymentId);
        }
    }

    public List<Booking> getBookingsForUser(String userId)
    {
        List<Booking> result = new ArrayList<>();
        for(Booking booking: bookings.values())
        {
            if(booking.getUserId().equals(userId))
            {
                result.add(booking);
            }
        }
        return result;
    }
}
