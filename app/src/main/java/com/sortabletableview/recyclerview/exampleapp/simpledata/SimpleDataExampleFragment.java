package com.sortabletableview.recyclerview.exampleapp.simpledata;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.sortabletableview.recyclerview.exampleapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SimpleDataExampleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SimpleDataExampleFragment extends Fragment {

    public SimpleDataExampleFragment() {
        // Required empty public constructor
    }

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
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_simple_data_example, container, false);
    }

}
