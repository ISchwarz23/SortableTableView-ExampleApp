package com.sortabletableview.recyclerview.exampleapp.sortdata;

import com.sortabletableview.recyclerview.exampleapp.data.Flight;
import com.sortabletableview.recyclerview.exampleapp.simpledata.FlightStringValueExtractors;
import com.sortabletableview.recyclerview.toolkit.SimpleTableDataColumnAdapter;

import java.util.Comparator;

final class FlightComparators {

    private static final SimpleTableDataColumnAdapter.StringValueExtractor<Flight> FLIGHT_NUMBER_FORMATTER = FlightStringValueExtractors.forFlightNumber();

    private FlightComparators() {
        //no instance
    }

    static Comparator<Flight> forDepartureTime() {
        return new Comparator<Flight>() {
            @Override
            public int compare(final Flight flight1, final Flight flight2) {
                return (int) (flight1.getDeparture().getTime().getTime() - flight2.getDeparture().getTime().getTime());
            }
        };
    }

    static Comparator<Flight> forAirline() {
        return new Comparator<Flight>() {
            @Override
            public int compare(final Flight flight1, final Flight flight2) {
                return flight1.getAirline().getName().compareTo(flight2.getAirline().getName());
            }
        };
    }

    static Comparator<Flight> forFlightNumber() {
        return new Comparator<Flight>() {
            @Override
            public int compare(final Flight flight1, final Flight flight2) {
                return FLIGHT_NUMBER_FORMATTER.getStringValue(flight1).compareTo(FLIGHT_NUMBER_FORMATTER.getStringValue(flight2));
            }
        };
    }

    static Comparator<Flight> forDestination() {
        return new Comparator<Flight>() {
            @Override
            public int compare(final Flight flight1, final Flight flight2) {
                return flight1.getDestination().compareTo(flight2.getDestination());
            }
        };
    }

    static Comparator<Flight> forGate() {
        return new Comparator<Flight>() {
            @Override
            public int compare(final Flight flight1, final Flight flight2) {
                return flight1.getGate() - flight2.getGate();
            }
        };
    }

    static Comparator<Flight> forState() {
        return new Comparator<Flight>() {
            @Override
            public int compare(final Flight flight1, final Flight flight2) {
                return flight1.getStatus().getDelayInMinutes() - flight2.getStatus().getDelayInMinutes();
            }
        };
    }
}
