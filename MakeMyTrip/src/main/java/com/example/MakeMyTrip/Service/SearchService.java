package com.example.MakeMyTrip.Service;

import com.example.MakeMyTrip.Entity.FlightInstance;

import java.time.LocalDateTime;
import java.util.List;

public class SearchService {
    private FlightService flightService;

    public SearchService(FlightService flightService) {
        this.flightService = flightService;
    }

    // Search flights by source, destination and date range and optional passengers
    public List<FlightInstance> searchFlights(String source, String destination,
                                              LocalDateTime startDate, LocalDateTime endDate,
                                              Integer numberOfPassengers) {
        // Delegate to FlightService's existing searchFlights method
        return flightService.searchFlights(source, destination, startDate, endDate, numberOfPassengers);
    }
}
