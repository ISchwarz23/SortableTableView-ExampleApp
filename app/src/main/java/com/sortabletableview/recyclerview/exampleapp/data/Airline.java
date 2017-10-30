package com.sortabletableview.recyclerview.exampleapp.data;

public final class Airline {

    private final String name;
    private final int logoDrawableRes;
    private final String flightNumberShortTerm;

    Airline(final String name, final String flightNumberShortTerm, final int logoDrawableRes) {
        this.name = name;
        this.flightNumberShortTerm = flightNumberShortTerm;
        this.logoDrawableRes = logoDrawableRes;
    }

    public String getName() {
        return name;
    }

    public int getLogoImageRes() {
        return logoDrawableRes;
    }

    String getFlightNumberShortTerm() {
        return flightNumberShortTerm;
    }
}
