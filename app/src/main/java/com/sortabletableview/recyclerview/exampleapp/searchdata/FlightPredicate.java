package com.sortabletableview.recyclerview.exampleapp.searchdata;

import com.sortabletableview.recyclerview.exampleapp.data.Flight;
import com.sortabletableview.recyclerview.exampleapp.simpledata.FlightStringValueExtractors;
import com.sortabletableview.recyclerview.toolkit.SimpleTableDataColumnAdapter;

final class FlightPredicate {

    private static final SimpleTableDataColumnAdapter.StringValueExtractor<Flight> FLIGHT_NUMBER_FORMATTER = FlightStringValueExtractors.forFlightNumber();

    private final String query;

    FlightPredicate(final String query) {
        this.query = query.toLowerCase();
    }

    boolean matches(final Flight flight) {
        // search for airline name
        if (flight.getAirline().getName().toLowerCase().contains(query)) {
            return true;
        }
        // search for flight number
        if (FLIGHT_NUMBER_FORMATTER.getStringValue(flight).toLowerCase().contains(query)) {
            return true;
        }
        // search for destination
        return flight.getDestination().toLowerCase().contains(query);
    }

}
