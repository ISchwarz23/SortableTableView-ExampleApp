package com.sortabletableview.recyclerview.exampleapp.paging;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
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
import com.sortabletableview.recyclerview.toolkit.PagingHelper;
import com.sortabletableview.recyclerview.toolkit.SimpleTableDataColumnAdapter;
import com.sortabletableview.recyclerview.toolkit.SimpleTableHeaderAdapter;
import com.sortabletableview.recyclerview.toolkit.TableDataRowBackgroundProviders;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PagingExampleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public final class PagingExampleFragment extends Fragment {

    private Button goToFirstPageButton;
    private Button goToPreviousPageButton;
    private Button goToNextPageButton;
    private Button goToLastPageButton;
    private TextView pageIndicatorView;

    private PagingHelper<Flight> pagingHelper;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment SimpleDataExampleFragment.
     */
    public static PagingExampleFragment newInstance() {
        final PagingExampleFragment fragment = new PagingExampleFragment();
        final Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_with_table_and_paging_controls, container, false);

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

        pagingHelper = new PagingHelper<>(tableView);
        pagingHelper.setItemsPerPage(8);

        goToFirstPageButton = view.findViewById(R.id.button_first_page);
        goToFirstPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                pagingHelper.goToFistPage();
                updatePagingControlls();
            }
        });

        goToPreviousPageButton = view.findViewById(R.id.button_previous_page);
        goToPreviousPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                pagingHelper.goToPreviousPage();
                updatePagingControlls();
            }
        });

        goToNextPageButton = view.findViewById(R.id.button_next_page);
        goToNextPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                pagingHelper.goToNextPage();
                updatePagingControlls();
            }
        });

        goToLastPageButton = view.findViewById(R.id.button_last_page);
        goToLastPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                pagingHelper.goToLastPage();
                updatePagingControlls();
            }
        });

        pageIndicatorView = view.findViewById(R.id.page_indicator_view);
        updatePagingControlls();

        // ******************** Interesting Code Section ********************************************************************************************

        return view;
    }

    private void updatePagingControlls() {
        pageIndicatorView.setText((pagingHelper.getCurrentPageIndex() + 1) + " / " + pagingHelper.getNumberOfPages());

        if (pagingHelper.getCurrentPageIndex() == 0) {
            goToFirstPageButton.setEnabled(false);
        } else {
            goToFirstPageButton.setEnabled(true);
        }

        if (pagingHelper.getCurrentPageIndex() == pagingHelper.getNumberOfPages() - 1) {
            goToLastPageButton.setEnabled(false);
        } else {
            goToLastPageButton.setEnabled(true);
        }

        if (pagingHelper.isPreviousPageAvailable()) {
            goToPreviousPageButton.setEnabled(true);
        } else {
            goToPreviousPageButton.setEnabled(false);
        }

        if (pagingHelper.isNextPageAvailable()) {
            goToNextPageButton.setEnabled(true);
        } else {
            goToNextPageButton.setEnabled(false);
        }
    }

}
