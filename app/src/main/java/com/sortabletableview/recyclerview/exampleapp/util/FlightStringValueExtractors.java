package com.sortabletableview.recyclerview.exampleapp.util;

import com.sortabletableview.recyclerview.exampleapp.data.Flight;
import com.sortabletableview.recyclerview.toolkit.SimpleColumnAdapter;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

public final class FlightStringValueExtractors {

    private static final NumberFormat FLIGHT_NUMBER_FORMATTER = new DecimalFormat("000");
    private static final DateFormat FORMATTER = SimpleDateFormat.getTimeInstance(DateFormat.SHORT);

    private FlightStringValueExtractors() {
        //no instance
    }

    public static SimpleColumnAdapter.StringValueExtractor<Flight> forDepartureTime() {
        return new SimpleColumnAdapter.StringValueExtractor<Flight>() {

            @Override
            public String getStringValue(final Flight flight) {
                return FORMATTER.format(flight.getDeparture().getTime());
            }
        };
    }

    public static SimpleColumnAdapter.StringValueExtractor<Flight> forFlightNumber() {
        return new SimpleColumnAdapter.StringValueExtractor<Flight>() {

            @Override
            public String getStringValue(final Flight flight) {
                return flight.getFlightNumber().getAirlineShortTerm() + FLIGHT_NUMBER_FORMATTER.format(flight.getFlightNumber().getNumber());
            }
        };
    }

    public static SimpleColumnAdapter.StringValueExtractor<Flight> forDestination() {
        return new SimpleColumnAdapter.StringValueExtractor<Flight>() {

            @Override
            public String getStringValue(final Flight flight) {
                return flight.getDestination();
            }
        };
    }

    public static SimpleColumnAdapter.StringValueExtractor<Flight> forAirline() {
        return new SimpleColumnAdapter.StringValueExtractor<Flight>() {

            @Override
            public String getStringValue(final Flight flight) {
                return flight.getAirline().getName();
            }
        };
    }
}
