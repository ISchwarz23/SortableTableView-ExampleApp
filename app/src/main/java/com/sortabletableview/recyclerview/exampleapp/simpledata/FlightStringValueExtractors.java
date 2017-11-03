package com.sortabletableview.recyclerview.exampleapp.simpledata;

import com.sortabletableview.recyclerview.exampleapp.data.Flight;
import com.sortabletableview.recyclerview.toolkit.SimpleTableDataColumnAdapter;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

public final class FlightStringValueExtractors {

    private static final NumberFormat FLIGHT_NUMBER_FORMATTER = new DecimalFormat("000");
    private static final DateFormat FORMATTER = new SimpleDateFormat("HH:mm");

    private FlightStringValueExtractors() {
        //no instance
    }

    public static SimpleTableDataColumnAdapter.StringValueExtractor<Flight> forDepartureTime() {
        return new SimpleTableDataColumnAdapter.StringValueExtractor<Flight>() {

            @Override
            public String getStringValue(final Flight flight) {
                return FORMATTER.format(flight.getDeparture().getTime());
            }
        };
    }

    public static SimpleTableDataColumnAdapter.StringValueExtractor<Flight> forFlightNumber() {
        return new SimpleTableDataColumnAdapter.StringValueExtractor<Flight>() {

            @Override
            public String getStringValue(final Flight flight) {
                return flight.getFlightNumber().getAirlineShortTerm() + FLIGHT_NUMBER_FORMATTER.format(flight.getFlightNumber().getNumber());
            }
        };
    }

    public static SimpleTableDataColumnAdapter.StringValueExtractor<Flight> forDestination() {
        return new SimpleTableDataColumnAdapter.StringValueExtractor<Flight>() {

            @Override
            public String getStringValue(final Flight flight) {
                return flight.getDestination();
            }
        };
    }

    public static SimpleTableDataColumnAdapter.StringValueExtractor<Flight> forAirline() {
        return new SimpleTableDataColumnAdapter.StringValueExtractor<Flight>() {

            @Override
            public String getStringValue(final Flight flight) {
                return flight.getAirline().getName();
            }
        };
    }

    public static SimpleTableDataColumnAdapter.StringValueExtractor<Flight> forGate() {
        return new SimpleTableDataColumnAdapter.StringValueExtractor<Flight>() {

            @Override
            public String getStringValue(final Flight flight) {
                return "" + flight.getGate();
            }
        };
    }

    public static SimpleTableDataColumnAdapter.StringValueExtractor<Flight> forStatus() {
        return new SimpleTableDataColumnAdapter.StringValueExtractor<Flight>() {

            @Override
            public String getStringValue(final Flight flight) {
                switch (flight.getStatus().getName()) {
                    case ON_TIME:
                        return "On Time";
                    case DELAYED:
                        return "Delayed";// + "(" + flight.getStatus().getDelayInMinutes() + "m)";
                    case CANCELED:
                        return "Canceled";
                    default:
                        return "";
                }
            }
        };
    }
}
