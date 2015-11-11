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
public class PlaceFragment extends Fragment {

//    View's declaration
    private TextView fragmentPlaceTv;

    public PlaceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_place, container, false);
        // View's init
        fragmentPlaceTv = (TextView) view.findViewById(R.id.fragmentPlaceTv);
        return view;
    }

//    Public interface

    /**
     * Sets passed data to place TextView
     * @param place place
     */
    public void setPlaceTvValue(String place) {
        fragmentPlaceTv.setText(place);
    }
}
