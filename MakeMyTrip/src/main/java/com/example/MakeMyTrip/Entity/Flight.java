package com.example.MakeMyTrip.Entity;

public class Flight {
    private String flightNumber;      // Unique flight number (e.g., AI101)
    private String airlineName;
    private String source;            // Source airport/ city code
    private String destination;       // Destination airport/ city code
    private int totalCapacity;        // Capacity per flight instance

    public Flight(String flightNumber, String airlineName, String source, String destination, int totalCapacity) {
        this.flightNumber = flightNumber;
        this.airlineName = airlineName;
        this.source = source;
        this.destination = destination;
        this.totalCapacity = totalCapacity;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getTotalCapacity() {
        return totalCapacity;
    }

    public void setTotalCapacity(int totalCapacity) {
        this.totalCapacity = totalCapacity;
    }
}
