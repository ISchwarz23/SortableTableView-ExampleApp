package com.sortabletableview.recyclerview.exampleapp.customdata;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sortabletableview.recyclerview.TableDataColumnAdapterDelegator;
import com.sortabletableview.recyclerview.exampleapp.R;
import com.sortabletableview.recyclerview.exampleapp.data.Flight;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public final class FlightNumberColumnAdapter extends TableDataColumnAdapterDelegator.TableDataColumnAdapter<Flight, FlightNumberColumnAdapter.FlightNumberViewHolder> {

    @Override
    public FlightNumberViewHolder onCreateColumnCellViewHolder(final ViewGroup parent, final int viewType) {
        final View view = getLayoutInflater().inflate(R.layout.tablecell_flight_number, parent, false);
        return new FlightNumberViewHolder(view);
    }

    @Override
    public void onBindColumnCellViewHolder(final FlightNumberViewHolder flightNumberViewHolder, final int rowIndex) {
        final Flight flight = getRowData(rowIndex);
        flightNumberViewHolder.setFlightNumber(flight.getFlightNumber());
    }

    static final class FlightNumberViewHolder extends RecyclerView.ViewHolder {

        private static final NumberFormat FLIGHT_NUMBER_FORMATTER = new DecimalFormat("000");

        private final TextView airlineShortTermView;
        private final TextView flightNumberView;

        private FlightNumberViewHolder(final View itemView) {
            super(itemView);
            airlineShortTermView = itemView.findViewById(R.id.airline_short_term);
            flightNumberView = itemView.findViewById(R.id.flight_number);
        }

        private void setFlightNumber(final Flight.Number flightNumber) {
            airlineShortTermView.setText(flightNumber.getAirlineShortTerm());
            flightNumberView.setText(FLIGHT_NUMBER_FORMATTER.format(flightNumber.getNumber()));
        }
    }

}
