package com.example.RestaurantManagementSystem.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Reservation {
    private String userId;
    private String id;
    private int size;
    private int tableId;
    private LocalDateTime reservationDateTime;
    private LocalDateTime createdAt;

    public Reservation(String userId, String id, int size, int tableId, LocalDateTime reservationDateTime, LocalDateTime createdAt) {
        this.userId = userId;
        this.id = id;
        this.size = size;
        this.tableId = tableId;
        this.reservationDateTime = reservationDateTime;
        this.createdAt = createdAt;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public LocalDateTime getReservationDateTime() {
        return reservationDateTime;
    }

    public void setReservationDateTime(LocalDateTime reservationDateTime) {
        this.reservationDateTime = reservationDateTime;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "userId='" + userId + '\'' +
                ", id='" + id + '\'' +
                ", size=" + size +
                ", tableId=" + tableId +
                ", reservationDateTime=" + reservationDateTime +
                ", createdAt=" + createdAt +
                '}';
    }
}
