package com.sortabletableview.recyclerview.exampleapp.customdata;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sortabletableview.recyclerview.exampleapp.R;
import com.sortabletableview.recyclerview.exampleapp.data.Flight;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DepartureColumnAdapter extends com.sortabletableview.recyclerview.TableDataColumnAdapterDelegator.TableDataColumnAdapter<Flight, DepartureColumnAdapter.DepartureViewHolder> {

    @Override
    public DepartureViewHolder onCreateColumnCellViewHolder(final ViewGroup parent, final int viewType) {
        final View view = getLayoutInflater().inflate(R.layout.tablecell_departure_time, parent, false);
        return new DepartureViewHolder(view);
    }

    @Override
    public void onBindColumnCellViewHolder(final DepartureViewHolder viewHolder, final int rowIndex) {
        final Flight flight = getRowData(rowIndex);
        viewHolder.setDepartureTime(flight.getDeparture().getTime());
    }

    static final class DepartureViewHolder extends RecyclerView.ViewHolder {

        private static final DateFormat HOUR_FORMATTER = new SimpleDateFormat("HH");
        private static final DateFormat MINUTE_FORMATTER = new SimpleDateFormat("mm");

        private final TextView hourView;
        private final TextView minuteView;

        private DepartureViewHolder(final View itemView) {
            super(itemView);
            hourView = itemView.findViewById(R.id.hour_view);
            minuteView = itemView.findViewById(R.id.minute_view);
        }

        private void setDepartureTime(final Date departureTime) {
            hourView.setText(HOUR_FORMATTER.format(departureTime));
            minuteView.setText(MINUTE_FORMATTER.format(departureTime));
        }
    }
}
