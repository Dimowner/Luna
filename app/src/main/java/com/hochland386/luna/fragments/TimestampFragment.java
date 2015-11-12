package com.hochland386.luna.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hochland386.luna.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TimestampFragment extends Fragment {

//    View's declaration
    private TextView fragmentTimestampTv;

    public TimestampFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_timestamp, container, false);
        // View's init
        fragmentTimestampTv = (TextView) view.findViewById(R.id.fragmentTimestampTv);
        return view;
    }

//    Public interface

    /**
     * Sets passed date to timestamp TextView and format it.
     * E.g. if you passed "16 Nov" as a parameter the final result will be "At 16 Nov it will be"
     * @param date date
     */
    public void setTimestampTvDate(String date) {
        String formattedDate = String.format("At %s it will be", date);
        fragmentTimestampTv.setText(formattedDate);
    }
}
