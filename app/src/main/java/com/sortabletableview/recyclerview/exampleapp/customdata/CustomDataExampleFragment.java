package com.sortabletableview.recyclerview.exampleapp.customdata;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sortabletableview.recyclerview.TableDataColumnAdapterDelegator;
import com.sortabletableview.recyclerview.TableView;
import com.sortabletableview.recyclerview.exampleapp.R;
import com.sortabletableview.recyclerview.exampleapp.data.Flight;
import com.sortabletableview.recyclerview.exampleapp.data.FlightRepository;
import com.sortabletableview.recyclerview.exampleapp.simpledata.FlightStringValueExtractors;
import com.sortabletableview.recyclerview.model.TableColumnWeightModel;
import com.sortabletableview.recyclerview.toolkit.SimpleTableDataColumnAdapter;
import com.sortabletableview.recyclerview.toolkit.SimpleTableHeaderAdapter;
import com.sortabletableview.recyclerview.toolkit.TableDataRowBackgroundProviders;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CustomDataExampleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public final class CustomDataExampleFragment extends Fragment {

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SimpleDataExampleFragment.
     */
    public static CustomDataExampleFragment newInstance() {
        final CustomDataExampleFragment fragment = new CustomDataExampleFragment();
        final Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_full_size_table, container, false);

        // ******************** Interesting Code Section ********************************************************************************************

        // set up header adapter
        final SimpleTableHeaderAdapter headerAdapter = new SimpleTableHeaderAdapter(getContext(), "Time", "Airline", "Flight", "Destination", "Gate", "Status");
        headerAdapter.setTextColor(ContextCompat.getColor(getContext(), R.color.colorHeaderText));

        // set up data adapter
        final TableDataColumnAdapterDelegator<Flight> dataAdapter = new TableDataColumnAdapterDelegator<>(getContext(), FlightRepository.getAllFlights());
        dataAdapter.setColumnAdapter(0, new DepartureColumnAdapter());
        dataAdapter.setColumnAdapter(1, new AirlineColumnAdapter());
        dataAdapter.setColumnAdapter(2, new FlightNumberColumnAdapter());
        dataAdapter.setColumnAdapter(3, new SimpleTableDataColumnAdapter<>(FlightStringValueExtractors.forDestination()));
        dataAdapter.setColumnAdapter(4, new SimpleTableDataColumnAdapter<>(FlightStringValueExtractors.forGate()));
        dataAdapter.setColumnAdapter(5, new FlightStatusColumnAdapter());

        // set up the table view
        final TableView<Flight> tableView = view.findViewById(R.id.table_view);
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

        return view;
    }

}
