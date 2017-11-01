package com.sortabletableview.recyclerview.exampleapp.sortdata;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sortabletableview.recyclerview.SortableTableView;
import com.sortabletableview.recyclerview.TableDataColumnAdapterDelegator;
import com.sortabletableview.recyclerview.exampleapp.R;
import com.sortabletableview.recyclerview.exampleapp.customdata.AirlineColumnAdapter;
import com.sortabletableview.recyclerview.exampleapp.customdata.DepartureColumnAdapter;
import com.sortabletableview.recyclerview.exampleapp.customdata.FlightNumberColumnAdapter;
import com.sortabletableview.recyclerview.exampleapp.customdata.FlightStatusColumnAdapter;
import com.sortabletableview.recyclerview.exampleapp.data.Flight;
import com.sortabletableview.recyclerview.exampleapp.data.FlightRepository;
import com.sortabletableview.recyclerview.exampleapp.simpledata.FlightStringValueExtractors;
import com.sortabletableview.recyclerview.model.TableColumnWeightModel;
import com.sortabletableview.recyclerview.toolkit.SimpleTableDataColumnAdapter;
import com.sortabletableview.recyclerview.toolkit.SimpleTableHeaderAdapter;
import com.sortabletableview.recyclerview.toolkit.SortStateViewProviders;
import com.sortabletableview.recyclerview.toolkit.TableDataRowBackgroundProviders;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SortDataExampleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public final class SortDataExampleFragment extends Fragment {

    private static final List<Flight> ALL_FLIGHTS = new ArrayList<>(FlightRepository.getAllFlights());

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SimpleDataExampleFragment.
     */
    public static SortDataExampleFragment newInstance() {
        final SortDataExampleFragment fragment = new SortDataExampleFragment();
        final Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {

        // ******************** Interesting Code Section ********************************************************************************************
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_with_sortable_table, container, false);
        // ******************** Interesting Code Section ********************************************************************************************

        // set up header adapter
        final SimpleTableHeaderAdapter headerAdapter = new SimpleTableHeaderAdapter(getContext(), "Time", "Airline", "Flight", "Destination", "Gate", "Status");
        headerAdapter.setTextColor(ContextCompat.getColor(getContext(), R.color.colorHeaderText));

        // set up data adapter
        final TableDataColumnAdapterDelegator<Flight> dataAdapter = new TableDataColumnAdapterDelegator<>(getContext(), ALL_FLIGHTS);
        dataAdapter.setColumnAdapter(0, new DepartureColumnAdapter());
        dataAdapter.setColumnAdapter(1, new AirlineColumnAdapter());
        dataAdapter.setColumnAdapter(2, new FlightNumberColumnAdapter());
        dataAdapter.setColumnAdapter(3, new SimpleTableDataColumnAdapter<>(FlightStringValueExtractors.forDestination()));
        dataAdapter.setColumnAdapter(4, new SimpleTableDataColumnAdapter<>(FlightStringValueExtractors.forGate()));
        dataAdapter.setColumnAdapter(5, new FlightStatusColumnAdapter());

        // set up the table view
        final SortableTableView<Flight> tableView = view.findViewById(R.id.table_view);
        tableView.setHeaderAdapter(headerAdapter);
        tableView.setDataAdapter(dataAdapter);

        // do some styling
        final int colorOddRows = ContextCompat.getColor(getContext(), R.color.colorOddRows);
        final int colorEvenRows = ContextCompat.getColor(getContext(), R.color.colorEvenRows);
        tableView.setDataRowBackgroundProvider(TableDataRowBackgroundProviders.alternatingRowColors(colorEvenRows, colorOddRows));

        // change column widths
        final TableColumnWeightModel tableColumnModel;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            // show 6 columns in landscape mode
            tableColumnModel = new TableColumnWeightModel(6);
            tableColumnModel.setColumnWeight(4, 2);
            tableColumnModel.setColumnWeight(5, 3);
        } else {
            // show 4 columns in portrait mode
            tableColumnModel = new TableColumnWeightModel(4);
        }
        tableColumnModel.setColumnWeight(0, 3);
        tableColumnModel.setColumnWeight(1, 3);
        tableColumnModel.setColumnWeight(2, 3);
        tableColumnModel.setColumnWeight(3, 5);
        tableView.setColumnModel(tableColumnModel);

        // ******************** Interesting Code Section ********************************************************************************************
        tableView.setHeaderSortStateViewProvider(SortStateViewProviders.brightArrows());

        tableView.setColumnComparator(0, FlightComparators.forDepartureTime());
        tableView.setColumnComparator(1, FlightComparators.forAirline());
        tableView.setColumnComparator(2, FlightComparators.forFlightNumber());
        tableView.setColumnComparator(3, FlightComparators.forDestination());
        tableView.setColumnComparator(4, FlightComparators.forGate());
        tableView.setColumnComparator(5, FlightComparators.forState());
        // ******************** Interesting Code Section ********************************************************************************************

        return view;
    }

}
