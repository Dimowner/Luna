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
public class WeatherSummaryFragment extends Fragment {

//    View's declaration
    private TextView fragmentWeatherSummaryTv;

    public WeatherSummaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weather_summary, container, false);
        fragmentWeatherSummaryTv = (TextView) view.findViewById(R.id.fragmentWeatherSummaryTv);
        return view;
    }

//    Public interface

    /**
     * Sets passed data to weather summary TextView
     * @param weatherSummary weather summary
     */
    public void setWeatherSummaryTvValue(String weatherSummary) {
        fragmentWeatherSummaryTv.setText(weatherSummary);
    }
}
