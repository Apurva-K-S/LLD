package com.example.RestaurantManagementSystem.Service;

import com.example.RestaurantManagementSystem.Entity.Reservation;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.UUID;

public class ReservationService {

    private Map<String, Reservation> reservationMap = new ConcurrentHashMap<>();

    // Create a new reservation with generated ID
    public Reservation createReservation(String userId, int size, int tableId, LocalDateTime reservationDateTime) {
        if (userId == null || size <= 0 || reservationDateTime == null) {
            throw new IllegalArgumentException("Invalid reservation details");
        }
        String id = UUID.randomUUID().toString();
        LocalDateTime createdAt = LocalDateTime.now();

        Reservation reservation = new Reservation(userId, id, size, tableId, reservationDateTime, createdAt);
        reservationMap.put(id, reservation);
        return reservation;
    }

    // Get reservation by ID
    public Reservation getReservationById(String id) {
        if (id == null) {
            throw new IllegalArgumentException("Reservation id cannot be null");
        }
        return reservationMap.get(id);
    }

    // Update reservation details: size, table, time
    public boolean updateReservation(String id, Integer size, Integer tableId, LocalDateTime reservationDateTime) {
        Reservation reservation = getReservationById(id);
        if (reservation == null) {
            return false;
        }
        if (size != null && size > 0) {
            reservation.setSize(size);
        }
        if (tableId != null) {
            reservation.setTableId(tableId);
        }
        if (reservationDateTime != null) {
            reservation.setReservationDateTime(reservationDateTime);
        }
        return true;
    }

    // Cancel reservation by removing it
    public boolean cancelReservation(String id) {
        return reservationMap.remove(id) != null;
    }
}
