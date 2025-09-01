package com.example.MakeMyTrip.Service;

import com.example.MakeMyTrip.Entity.Flight;
import com.example.MakeMyTrip.Entity.FlightInstance;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class FlightService {

    private Map<String, Flight> flights = new HashMap<>();                         // flightNumber -> Flight
    private Map<String, List<FlightInstance>> flightInstances = new HashMap<>();   // flightNumber -> List of FlightInstances

    public Flight createFlight(String flightNumber, String airlineName, String source, String destination,
                                     int totalCapacity, int numberOfStops) {
        // Optionally, validate inputs here (e.g., non-null, unique flightNumber, etc.)
        Flight flight = new Flight(flightNumber, airlineName, source, destination, totalCapacity);
        flights.put(flightNumber, flight);
        return flight;
    }
    public void registerFlight(Flight flight) {
        flights.put(flight.getFlightNumber(), flight);
    }

    public void updateFlightDetails(String flightNumber, Flight updatedFlight) {
        flights.put(flightNumber, updatedFlight);
    }

    public void addFlightInstance(FlightInstance flightInstance) {
        flightInstances.computeIfAbsent(flightInstance.getFlightNumber(), k -> new ArrayList<>())
                .add(flightInstance);
    }

    // Search flight instances by source, destination, date/time range and optional number of passengers
    public List<FlightInstance> searchFlights(String source, String destination, LocalDateTime startDate, LocalDateTime endDate, Integer numberOfPassengers) {
        return flights.values().stream()
                .filter(f -> f.getSource().equalsIgnoreCase(source) && f.getDestination().equalsIgnoreCase(destination))
                .flatMap(f -> flightInstances.getOrDefault(f.getFlightNumber(), Collections.emptyList()).stream())
                .filter(fi -> !fi.getDepartureTime().isBefore(startDate) && !fi.getDepartureTime().isAfter(endDate))
                .filter(fi -> numberOfPassengers == null || fi.getAvailableCapacity() >= numberOfPassengers)
                .collect(Collectors.toList());
    }

    public Flight getFlightByNumber(String flightNumber) {
        return flights.get(flightNumber);
    }

    public FlightInstance getFlightInstanceById(String flightInstanceId) {
        return flightInstances.values().stream()
                .flatMap(List::stream)
                .filter(fi -> fi.getFlightInstanceId().equals(flightInstanceId))
                .findFirst()
                .orElse(null);
    }

    public FlightInstance createFlightInstance(String flightNumber, LocalDateTime departureTime, LocalDateTime arrivalTime,
                                               int availableCapacity, double priceOfSeat) {
        if (!flights.containsKey(flightNumber)) {
            throw new IllegalArgumentException("Flight number does not exist: " + flightNumber);
        }
        FlightInstance flightInstance = new FlightInstance(flightNumber, departureTime, arrivalTime, availableCapacity, priceOfSeat);
        addFlightInstance(flightInstance);
        return flightInstance;
    }

}
