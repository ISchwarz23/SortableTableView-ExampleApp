package com.sortabletableview.recyclerview.exampleapp.searchdata;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.*;
import android.widget.SearchView;
import com.sortabletableview.recyclerview.TableDataColumnAdapterDelegator;
import com.sortabletableview.recyclerview.TableView;
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
import com.sortabletableview.recyclerview.toolkit.TableDataRowBackgroundProviders;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SearchDataFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public final class SearchDataFragment extends Fragment {

    private final List<Flight> allFlights = FlightRepository.getAllFlights();
    private TableDataColumnAdapterDelegator<Flight> dataAdapter;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LoadDataFragment.
     */
    public static SearchDataFragment newInstance() {
        final SearchDataFragment fragment = new SearchDataFragment();
        final Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_with_table, container, false);

        // set up header adapter
        final SimpleTableHeaderAdapter headerAdapter = new SimpleTableHeaderAdapter(getContext(), "Time", "Airline", "Flight", "Destination", "Gate", "Status");
        headerAdapter.setTextColor(ContextCompat.getColor(getContext(), R.color.colorHeaderText));

        // set up data adapter
        dataAdapter = new TableDataColumnAdapterDelegator<>(getContext(), new ArrayList<>(allFlights));
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

        return view;
    }

    @Override
    public void onCreateOptionsMenu(final Menu menu, final MenuInflater inflater) {
        inflater.inflate(R.menu.fragment_search_datamain, menu);
        final MenuItem item = menu.findItem(R.id.menu_search);
        final SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(final String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(final String query) {
                showFilteredData(query);
                return false;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                showAllData();
                return false;
            }
        });
        super.onCreateOptionsMenu(menu, inflater);
    }

    private void showFilteredData(final String query) {
        // ******************** Interesting Code Section **********************************************************************
        dataAdapter.getData().clear();
        dataAdapter.getData().addAll(getFilteredData(query));
        dataAdapter.notifyDataSetChanged();
        // ******************** Interesting Code Section **********************************************************************
    }

    private void showAllData() {
        dataAdapter.getData().clear();
        dataAdapter.getData().addAll(allFlights);
        dataAdapter.notifyDataSetChanged();
    }

    private List<Flight> getFilteredData(final String query) {
        final FlightPredicate filter = new FlightPredicate(query);

        final List<Flight> filteredFlights = new ArrayList<>();
        for (final Flight flight : allFlights) {
            if (filter.matches(flight)) {
                filteredFlights.add(flight);
            }
        }
        return filteredFlights;
    }

}
