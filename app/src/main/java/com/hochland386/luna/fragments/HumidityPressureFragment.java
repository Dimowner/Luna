package com.hochland386.luna.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hochland386.luna.R;

/**
 *
 * Copyright 2015 Vitaly Sulimov <quarry386@fastmail.com>
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 *
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
