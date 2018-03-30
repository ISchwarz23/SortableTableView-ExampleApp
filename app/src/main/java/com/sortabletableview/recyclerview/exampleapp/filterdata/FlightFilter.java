package com.sortabletableview.recyclerview.exampleapp.filterdata;

import com.sortabletableview.recyclerview.exampleapp.data.Flight;
import com.sortabletableview.recyclerview.exampleapp.simpledata.FlightStringValueExtractors;
import com.sortabletableview.recyclerview.toolkit.FilterHelper;
import com.sortabletableview.recyclerview.toolkit.SimpleTableDataColumnAdapter;

final class FlightFilter implements FilterHelper.Filter<Flight> {

    private static final SimpleTableDataColumnAdapter.StringValueExtractor<Flight> FLIGHT_NUMBER_FORMATTER = FlightStringValueExtractors.forFlightNumber();

    private final String query;

    FlightFilter(final String query) {
        this.query = query.toLowerCase();
    }

    @Override
    public boolean apply(final Flight flight) {
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
