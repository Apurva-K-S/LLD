package com.example.MakeMyTrip.Entity;

import java.time.LocalDateTime;
import java.util.UUID;

public class FlightInstance {
    private String flightInstanceId;
    private String flightNumber;           // Link to Flight.flightNumber
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;
    private int availableCapacity;         // Available seats for this instance
    private double priceOfSeat;            // Price for this instance

    public FlightInstance(String flightNumber, LocalDateTime departureTime, LocalDateTime arrivalTime, int availableCapacity, double priceOfSeat) {
        this.flightInstanceId = UUID.randomUUID().toString();
        this.flightNumber = flightNumber;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.availableCapacity = availableCapacity;
        this.priceOfSeat = priceOfSeat;
    }

    public String getFlightInstanceId() {
        return flightInstanceId;
    }

    public void setFlightInstanceId(String flightInstanceId) {
        this.flightInstanceId = flightInstanceId;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public LocalDateTime getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(LocalDateTime departureTime) {
        this.departureTime = departureTime;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getAvailableCapacity() {
        return availableCapacity;
    }

    public void setAvailableCapacity(int availableCapacity) {
        this.availableCapacity = availableCapacity;
    }

    public double getPriceOfSeat() {
        return priceOfSeat;
    }

    public void setPriceOfSeat(double priceOfSeat) {
        this.priceOfSeat = priceOfSeat;
    }

    @Override
    public String toString() {
        return "FlightInstance{" +
                "flightInstanceId='" + flightInstanceId + '\'' +
                ", flightNumber='" + flightNumber + '\'' +
                ", departureTime=" + departureTime +
                ", arrivalTime=" + arrivalTime +
                ", availableCapacity=" + availableCapacity +
                ", priceOfSeat=" + priceOfSeat +
                '}';
    }
}
