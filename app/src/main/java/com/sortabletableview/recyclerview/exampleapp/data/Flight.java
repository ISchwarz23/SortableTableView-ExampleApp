package com.sortabletableview.recyclerview.exampleapp.data;

import com.sortabletableview.recyclerview.toolkit.SelectionHelper;

import java.util.Calendar;

public final class Flight implements SelectionHelper.SelectableItem {

    public static final class Status {

        public enum Name {
            ON_TIME,
            DELAYED,
            CANCELED
        }

        private final Name name;
        private final int delayInMinutes;

        private Status(final Name name, final int delayInMinutes) {
            this.name = name;
            this.delayInMinutes = delayInMinutes;
        }

        public static Status onTime() {
            return new Status(Name.ON_TIME, 0);
        }

        public static Status delayed(final int delayInMinutes) {
            return new Status(Name.DELAYED, delayInMinutes);
        }

        public static Status canceled() {
            return new Status(Name.CANCELED, Integer.MAX_VALUE);
        }

        public Name getName() {
            return name;
        }

        public int getDelayInMinutes() {
            return delayInMinutes;
        }
    }

    public static final class Number {

        private final String airlineShortTerm;
        private final int number;

        private Number(final String airlineShortTerm, final int number) {
            this.airlineShortTerm = airlineShortTerm;
            this.number = number;
        }

        public String getAirlineShortTerm() {
            return airlineShortTerm;
        }

        public int getNumber() {
            return number;
        }

        static Number create(final Airline airline, final int number) {
            return new Number(airline.getFlightNumberShortTerm(), number);
        }
    }

    private final Calendar departure;
    private final Number flightNumber;
    private final String destination;
    private final Airline airline;
    private final int gate;
    private final Status status;

    private boolean isSelected;

    Flight(final Calendar departure, final Number flightNumber, final String destination, final Airline airline, final int gate, final Status status) {
        this.departure = departure;
        this.flightNumber = flightNumber;
        this.destination = destination;
        this.airline = airline;
        this.gate = gate;
        this.status = status;
    }

    public Calendar getDeparture() {
        return departure;
    }

    public Number getFlightNumber() {
        return flightNumber;
    }

    public String getDestination() {
        return destination;
    }

    public Airline getAirline() {
        return airline;
    }

    public int getGate() {
        return gate;
    }

    public Status getStatus() {
        return status;
    }

    @Override
    public void setSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }
}
