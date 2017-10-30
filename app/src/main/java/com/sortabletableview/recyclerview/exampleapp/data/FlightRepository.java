package com.sortabletableview.recyclerview.exampleapp.data;

import com.sortabletableview.recyclerview.exampleapp.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public final class FlightRepository {

    private static final Airline AIRLINE_SWISS_INTERNATIONAL = new Airline("Swiss International", "LX", R.mipmap.ic_airline_swiss);
    private static final Airline AIRLINE_BRITISH_AIRWAYS = new Airline("British Airways", "BA", R.mipmap.ic_airline_british_airways);
    private static final Airline AIRLINE_AIR_FRANCE = new Airline("Air France", "AF", R.mipmap.ic_airline_airfrance);
    private static final Airline AIRLINE_IBERIA = new Airline("Iberia", "IBE", R.mipmap.ic_airline_iberia);
    private static final Airline AIRLINE_UNITED_AIRLINES = new Airline("United Airlines", "UA", R.mipmap.ic_airlines_united);
    private static final Airline AIRLINE_AMERICAN_AIRLINES = new Airline("American Airlines", "AA", R.mipmap.ic_airline_american);

    private FlightRepository() {
        //no instance
    }

    public static List<Flight> getAllFlights() {
        final List<Flight> flights = new ArrayList<>();
        flights.add(createFlight(13, 0, "Barcelona", AIRLINE_IBERIA, 844, 33, Flight.Status.onTime()));
        flights.add(createFlight(13, 5, "Zürich", AIRLINE_SWISS_INTERNATIONAL, 355, 55, Flight.Status.delayed(50)));
        flights.add(createFlight(14, 25, "Paris", AIRLINE_AIR_FRANCE, 477, 12, Flight.Status.onTime()));
        flights.add(createFlight(14, 29, "London", AIRLINE_BRITISH_AIRWAYS, 55, 22, Flight.Status.onTime()));
        flights.add(createFlight(15, 12, "Tokyo", AIRLINE_SWISS_INTERNATIONAL, 947, 39, Flight.Status.delayed(48)));
        flights.add(createFlight(15, 24, "New York", AIRLINE_UNITED_AIRLINES, 544, 44, Flight.Status.onTime()));
        flights.add(createFlight(15, 37, "San Francisco", AIRLINE_AMERICAN_AIRLINES, 43, 32, Flight.Status.onTime()));
        flights.add(createFlight(15, 45, "Buenos Aires", AIRLINE_IBERIA, 544, 33, Flight.Status.onTime()));
        flights.add(createFlight(15, 53, "Peru", AIRLINE_IBERIA, 10, 76, Flight.Status.canceled()));
        flights.add(createFlight(16, 5, "Washington", AIRLINE_AMERICAN_AIRLINES, 67, 111, Flight.Status.onTime()));
        flights.add(createFlight(16, 12, "Tolouse", AIRLINE_AIR_FRANCE, 77, 23, Flight.Status.delayed(12)));
        flights.add(createFlight(16, 20, "Dublin", AIRLINE_BRITISH_AIRWAYS, 351, 154, Flight.Status.onTime()));
        flights.add(createFlight(16, 35, "Frankfurt", AIRLINE_IBERIA, 654, 65, Flight.Status.onTime()));
        flights.add(createFlight(16, 55, "Berlin", AIRLINE_IBERIA, 45, 35, Flight.Status.delayed(30)));
        flights.add(createFlight(17, 10, "Paris", AIRLINE_AIR_FRANCE, 68, 64, Flight.Status.onTime()));
        flights.add(createFlight(17, 12, "Tokyo", AIRLINE_SWISS_INTERNATIONAL, 56, 39, Flight.Status.delayed(48)));
        flights.add(createFlight(17, 24, "New York", AIRLINE_UNITED_AIRLINES, 654, 44, Flight.Status.onTime()));
        flights.add(createFlight(17, 25, "Paris", AIRLINE_AIR_FRANCE, 477, 456, Flight.Status.onTime()));
        flights.add(createFlight(18, 5, "Washington", AIRLINE_AMERICAN_AIRLINES, 56, 111, Flight.Status.onTime()));
        flights.add(createFlight(18, 20, "Dublin", AIRLINE_BRITISH_AIRWAYS, 36, 154, Flight.Status.onTime()));
        flights.add(createFlight(19, 12, "Tolouse", AIRLINE_AIR_FRANCE, 876, 23, Flight.Status.delayed(12)));
        flights.add(createFlight(20, 0, "Barcelona", AIRLINE_IBERIA, 543, 14, Flight.Status.onTime()));
        flights.add(createFlight(20, 5, "Zürich", AIRLINE_SWISS_INTERNATIONAL, 25, 36, Flight.Status.delayed(50)));
        flights.add(createFlight(20, 37, "San Francisco", AIRLINE_AMERICAN_AIRLINES, 45, 54, Flight.Status.onTime()));
        flights.add(createFlight(20, 45, "Buenos Aires", AIRLINE_IBERIA, 35, 36, Flight.Status.onTime()));
        flights.add(createFlight(20, 53, "Peru", AIRLINE_IBERIA, 875, 78, Flight.Status.canceled()));
        flights.add(createFlight(20, 55, "Berlin", AIRLINE_IBERIA, 932, 6, Flight.Status.delayed(30)));
        flights.add(createFlight(21, 10, "Paris", AIRLINE_AIR_FRANCE, 1412, 80, Flight.Status.onTime()));
        flights.add(createFlight(21, 29, "London", AIRLINE_BRITISH_AIRWAYS, 415, 87, Flight.Status.onTime()));
        flights.add(createFlight(21, 35, "Frankfurt", AIRLINE_IBERIA, 41, 2, Flight.Status.onTime()));
        return flights;
    }

    private static Flight createFlight(final int departureHour, final int departureMinute, final String destination, final Airline airline,
                                       final int flightNumber, final int gate, final Flight.Status status) {

        return new Flight(createDepartureCalendar(departureHour, departureMinute), Flight.Number.create(airline, flightNumber),
                destination, airline, gate, status);
    }

    private static Calendar createDepartureCalendar(final int hour, final int minutes) {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minutes);
        return calendar;
    }

}
