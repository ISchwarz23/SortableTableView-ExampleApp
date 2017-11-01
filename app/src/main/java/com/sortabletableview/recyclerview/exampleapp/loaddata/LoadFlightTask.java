package com.sortabletableview.recyclerview.exampleapp.loaddata;

import android.os.AsyncTask;
import com.sortabletableview.recyclerview.TableDataAdapter;
import com.sortabletableview.recyclerview.exampleapp.data.Flight;
import com.sortabletableview.recyclerview.exampleapp.data.FlightRepository;
import com.sortabletableview.recyclerview.listeners.SwipeToRefreshListener;

import java.util.List;

public final class LoadFlightTask extends AsyncTask<Void, Void, List<Flight>> {

    private final TableDataAdapter<Flight, ?> dataAdapter;
    private final SwipeToRefreshListener.RefreshIndicator refreshIndicator;

    LoadFlightTask(final TableDataAdapter<Flight, ?> dataAdapter, final SwipeToRefreshListener.RefreshIndicator refreshIndicator) {
        this.dataAdapter = dataAdapter;
        this.refreshIndicator = refreshIndicator;
    }

    @Override
    protected List<Flight> doInBackground(final Void... voids) {
        // simulate loading time
        try {
            Thread.sleep(2000);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
        return FlightRepository.getAllFlights();
    }

    @Override
    protected void onPostExecute(final List<Flight> flights) {
        super.onPostExecute(flights);

        // add the loaded data
        dataAdapter.getData().addAll(FlightRepository.getAllFlights());
        dataAdapter.notifyDataSetChanged();

        // hide the loading indicator
        refreshIndicator.hide();
    }
}
