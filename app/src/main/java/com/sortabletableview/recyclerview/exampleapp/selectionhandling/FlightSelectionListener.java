package com.sortabletableview.recyclerview.exampleapp.selectionhandling;

import android.support.design.widget.Snackbar;
import com.sortabletableview.recyclerview.exampleapp.data.Flight;
import com.sortabletableview.recyclerview.exampleapp.simpledata.FlightStringValueExtractors;
import com.sortabletableview.recyclerview.toolkit.SelectionHelper;
import com.sortabletableview.recyclerview.toolkit.SimpleTableDataColumnAdapter;

public class FlightSelectionListener implements SelectionHelper.SelectionListener<Flight> {

    private static final SimpleTableDataColumnAdapter.StringValueExtractor<Flight> FLIGHT_STRING_VALUE_EXTRACTOR = FlightStringValueExtractors.forFlightNumber();

    private final Snackbar snackbar;

    public FlightSelectionListener(Snackbar snackbar) {
        this.snackbar = snackbar;
    }

    @Override
    public void onItemSelected(final Flight flight, final SelectionHelper<Flight> selectionHelper) {
        onSelectionChanged(selectionHelper);
    }

    @Override
    public void onItemDeselected(final Flight flight, final SelectionHelper<Flight> selectionHelper) {
        onSelectionChanged(selectionHelper);
    }

    private void onSelectionChanged(final SelectionHelper<Flight> selectionHelper) {
        String displayText = "";
        if (selectionHelper.getSelectedItems().size() == 1) {
            displayText += "You selected Flight '";
            displayText += FLIGHT_STRING_VALUE_EXTRACTOR.getStringValue(selectionHelper.getSelectedItems().get(0));
            displayText += "'";
        } else if (selectionHelper.getSelectedItems().size() == 2) {
            displayText += "You selected Flight ";
            displayText += FLIGHT_STRING_VALUE_EXTRACTOR.getStringValue(selectionHelper.getSelectedItems().get(0));
            displayText += "' and '";
            displayText += FLIGHT_STRING_VALUE_EXTRACTOR.getStringValue(selectionHelper.getSelectedItems().get(1));
            displayText += "'";
        } else {
            displayText += "You selected ";
            displayText += selectionHelper.getSelectedItems().size();
            displayText += " Flights.";
        }

        if (selectionHelper.getSelectedItems().size() == 0) {
            snackbar.dismiss();
        } else {
            snackbar.setText(displayText).show();
        }
    }

}
