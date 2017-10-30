package com.sortabletableview.recyclerview.exampleapp.simpledata;

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
import com.sortabletableview.recyclerview.exampleapp.util.FlightStringValueExtractors;
import com.sortabletableview.recyclerview.model.TableColumnWeightModel;
import com.sortabletableview.recyclerview.toolkit.SimpleColumnAdapter;
import com.sortabletableview.recyclerview.toolkit.SimpleTableHeaderAdapter;
import com.sortabletableview.recyclerview.toolkit.TableDataRowBackgroundProviders;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SimpleDataExampleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SimpleDataExampleFragment extends Fragment {

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SimpleDataExampleFragment.
     */
    public static SimpleDataExampleFragment newInstance() {
        final SimpleDataExampleFragment fragment = new SimpleDataExampleFragment();
        final Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_simple_data_example, container, false);

        // ******************** Interesting Code Section ********************************************************************************************

        // set up header adapter
        final SimpleTableHeaderAdapter headerAdapter = new SimpleTableHeaderAdapter(getContext(), "Time", "Flight", "Destination", "Airline");
        headerAdapter.setTextColor(ContextCompat.getColor(getContext(), R.color.colorTextIcons));

        // set up data adapter
        final TableDataColumnAdapterDelegator<Flight> dataAdapter = new TableDataColumnAdapterDelegator<>(getContext(), FlightRepository.getAllFlights());
        dataAdapter.setColumnAdapter(0, new SimpleColumnAdapter<>(FlightStringValueExtractors.forDepartureTime()));
        dataAdapter.setColumnAdapter(1, new SimpleColumnAdapter<>(FlightStringValueExtractors.forFlightNumber()));
        dataAdapter.setColumnAdapter(2, new SimpleColumnAdapter<>(FlightStringValueExtractors.forDestination()));
        dataAdapter.setColumnAdapter(3, new SimpleColumnAdapter<>(FlightStringValueExtractors.forAirline()));

        // set up the table view
        final TableView<Flight> tableView = view.findViewById(R.id.table_view);
        tableView.setHeaderAdapter(headerAdapter);
        tableView.setDataAdapter(dataAdapter);

        // ******************** Interesting Code Section ********************************************************************************************

        // do some styling
        final int colorOddRows = ContextCompat.getColor(getContext(), R.color.colorOddRows);
        final int colorEvenRows = ContextCompat.getColor(getContext(), R.color.colorEvenRows);
        tableView.setDataRowBackgroundProvider(TableDataRowBackgroundProviders.alternatingRowColors(colorEvenRows, colorOddRows));

        // change column widths
        final TableColumnWeightModel tableColumnModel = new TableColumnWeightModel(4);
        tableColumnModel.setColumnWeight(0, 2);
        tableColumnModel.setColumnWeight(1, 2);
        tableColumnModel.setColumnWeight(2, 3);
        tableColumnModel.setColumnWeight(3, 3);
        tableView.setColumnModel(tableColumnModel);

        return view;
    }

}
