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
public class HumidityPressureFragment extends Fragment {

//    View's declaration
    private TextView fragmentHumidityValueTv;
    private TextView fragmentPressureValueTv;

    public HumidityPressureFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_humidity_pressure, container, false);
        // View's init
        fragmentHumidityValueTv = (TextView) view.findViewById(R.id.fragmentHumidityValueTv);
        fragmentPressureValueTv = (TextView) view.findViewById(R.id.fragmentPressureValueTv);
        return view;
    }

//    Public interface

    /**
     * Sets passed humidity value to humidity TextView and append % symbol at the end
     * (e.g. passing 86 as value sets 86% to humidity TextView)
     * @param humidity humidity value
     */
    public void setHumidityTvValue(int humidity) {
        String formattedHumidity = String.format("%s", humidity + "%");
        fragmentHumidityValueTv.setText(formattedHumidity);
    }

    /**
     * Sets passed pressure value to pressure TextView.
     * @param pressure pressure value
     */
    public void setPressureTvValue(int pressure) {
        fragmentPressureValueTv.setText(String.valueOf(pressure));
    }
}
