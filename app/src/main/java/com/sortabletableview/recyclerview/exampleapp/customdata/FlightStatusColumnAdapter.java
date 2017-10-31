package com.sortabletableview.recyclerview.exampleapp.customdata;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.sortabletableview.recyclerview.TableDataColumnAdapterDelegator;
import com.sortabletableview.recyclerview.exampleapp.R;
import com.sortabletableview.recyclerview.exampleapp.data.Flight;

public final class FlightStatusColumnAdapter extends TableDataColumnAdapterDelegator.TableDataColumnAdapter<Flight, FlightStatusColumnAdapter.FlightStatusViewHolder> {

    @Override
    public FlightStatusViewHolder onCreateColumnCellViewHolder(final ViewGroup parent, final int viewType) {
        final View view = getLayoutInflater().inflate(R.layout.tablecell_flight_status, parent, false);
        return new FlightStatusViewHolder(view);
    }

    @Override
    public void onBindColumnCellViewHolder(final FlightStatusViewHolder flightStatusViewHolder, final int columnIndex) {
        final Flight.Status flightStatus = getRowData(columnIndex).getStatus();
        flightStatusViewHolder.setFlightStatus(flightStatus);
    }

    static final class FlightStatusViewHolder extends RecyclerView.ViewHolder {

        private final TextView flightStatusView;

        private FlightStatusViewHolder(final View itemView) {
            super(itemView);
            flightStatusView = itemView.findViewById(R.id.flight_status_view);
        }

        private void setFlightStatus(final Flight.Status flightStatus) {
            flightStatusView.setText("");
            switch (flightStatus.getName()) {
                case ON_TIME:
                    flightStatusView.setBackgroundResource(R.drawable.circle_green);
                    break;
                case DELAYED:
                    flightStatusView.setBackgroundResource(R.drawable.circle_yellow);
                    flightStatusView.setText("" + flightStatus.getDelayInMinutes());
                    break;
                case CANCELED:
                    flightStatusView.setBackgroundResource(R.drawable.circle_red);
                    break;
            }
        }
    }

}
