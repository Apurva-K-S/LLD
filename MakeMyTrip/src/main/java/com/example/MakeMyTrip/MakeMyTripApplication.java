package com.example.MakeMyTrip;

import com.example.MakeMyTrip.Entity.Booking;
import com.example.MakeMyTrip.Entity.Flight;
import com.example.MakeMyTrip.Entity.FlightInstance;
import com.example.MakeMyTrip.Entity.Payment;
import com.example.MakeMyTrip.Enum.BookingStatus;
import com.example.MakeMyTrip.Enum.PaymentMethod;
import com.example.MakeMyTrip.Service.*;

import java.time.LocalDateTime;
import java.util.List;

public class MakeMyTripApplication {

	public static void main(String[] args) {
		FlightService flightService = new FlightService();
		BookingService bookingService = new BookingService(flightService);
		PaymentService paymentService = new PaymentService(bookingService);
		SearchService searchService = new SearchService(flightService);

		seedFlightsAndInstances(flightService);

		List<FlightInstance> flightsOnSep10 = searchFlights(searchService, "DEL", "BOM",
				LocalDateTime.of(2025, 9, 10, 0, 0),
				LocalDateTime.of(2025, 9, 10, 23, 59), 1);

		if (flightsOnSep10.isEmpty()) {
			System.out.println("No flights available on Sep 10.");
			return;
		}

		Booking booking = bookFlight(bookingService, flightsOnSep10.get(0), "user123", 1);

		Payment payment = processPayment(paymentService, booking, PaymentMethod.CREDIT_CARD);

		linkPaymentAndConfirmBooking(bookingService, booking, payment);

		System.out.println("Booking confirmed with ID: " + booking.getBookingId());

		System.out.println("===========================================================");

		createMultipleFlightInstances(flightService);

		searchAndDisplayFlights(searchService, "DEL", "BOM", LocalDateTime.of(2025, 10, 3, 0, 0), LocalDateTime.of(2025, 10, 3, 23, 59));

		searchAndDisplayFlights(searchService, "DEL", "BOM", LocalDateTime.of(2025, 10, 4, 0, 0), LocalDateTime.of(2025, 10, 4, 23, 59));

		List<Booking> userBookings = bookFlightsForUser(bookingService, searchService, "DEL", "BOM",
				new String[]{"2025-10-03", "2025-10-04"}, "user123");

		displayUserBookings(userBookings, flightService);
	}

	private static void seedFlightsAndInstances(FlightService flightService) {
		flightService.createFlight("AI101", "AirIndia", "DEL", "BOM", 180, 0);
		flightService.createFlightInstance("AI101", LocalDateTime.of(2025, 9, 10, 10, 0),
				LocalDateTime.of(2025, 9, 10, 12, 30), 180, 5000.0);
	}

	private static List<FlightInstance> searchFlights(SearchService searchService, String source, String destination,
													  LocalDateTime startDate, LocalDateTime endDate, Integer passengers) {
		return searchService.searchFlights(source, destination, startDate, endDate, passengers);
	}

	private static Booking bookFlight(BookingService bookingService, FlightInstance flightInstance, String userId, int seats) {
		return bookingService.bookFlight(userId, flightInstance.getFlightInstanceId(), seats);
	}

	private static Payment processPayment(PaymentService paymentService, Booking booking, PaymentMethod method) {
		return paymentService.processPayment(booking.getBookingId(), method, booking.getTotalAmount());
	}

	private static void linkPaymentAndConfirmBooking(BookingService bookingService, Booking booking, Payment payment) {
		bookingService.setPaymentIdForBooking(booking.getBookingId(), payment.getPaymentId());
		bookingService.updateBookingStatus(booking.getBookingId(), BookingStatus.CONFIRMED);
	}

	private static void createMultipleFlightInstances(FlightService flightService) {
		flightService.createFlightInstance("AI101", LocalDateTime.of(2025, 10, 3, 10, 0),
				LocalDateTime.of(2025, 10, 3, 12, 30), 180, 5000.0);

		flightService.createFlightInstance("AI101", LocalDateTime.of(2025, 10, 4, 10, 0),
				LocalDateTime.of(2025, 10, 4, 12, 30), 180, 5200.0);
	}

	private static void searchAndDisplayFlights(SearchService searchService, String source, String destination,
												LocalDateTime startDate, LocalDateTime endDate) {
		List<FlightInstance> flights = searchService.searchFlights(source, destination, startDate, endDate, null);
		System.out.println("Flights from " + startDate.toLocalDate() + ":");
		flights.forEach(System.out::println);
	}

	private static List<Booking> bookFlightsForUser(BookingService bookingService, SearchService searchService,
													String source, String destination, String[] dates, String userId) {
		List<Booking> bookings = new java.util.ArrayList<>();
		for (String dateStr : dates) {
			LocalDateTime start = LocalDateTime.parse(dateStr + "T00:00:00");
			LocalDateTime end = LocalDateTime.parse(dateStr + "T23:59:59");
			List<FlightInstance> flights = searchService.searchFlights(source, destination, start, end, 1);
			if (!flights.isEmpty()) {
				bookings.add(bookingService.bookFlight(userId, flights.get(0).getFlightInstanceId(), 1));
			}
		}
		return bookings;
	}

	private static void displayUserBookings(List<Booking> bookings, FlightService flightService) {
		System.out.println("\nUser's bookings:");
		for (Booking b : bookings) {
			FlightInstance fi = flightService.getFlightInstanceById(b.getFlightInstanceId());
			System.out.println("BookingId: " + b.getBookingId() +
					", Flight: " + fi.getFlightNumber() +
					", Departure: " + fi.getDepartureTime() +
					", Status: " + b.getBookingStatus());
		}
	}
}
