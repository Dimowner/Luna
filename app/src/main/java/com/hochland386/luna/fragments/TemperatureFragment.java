package com.hochland386.luna.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hochland386.luna.R;

/**
 * Copyright 2015 Vitaly Sulimov <quarry386@fastmail.com>
 * <p/>
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 */
public class TemperatureFragment extends Fragment {

    private TextView fragmentMinusSymbolTv;
    private TextView fragmentTemperatureTv;

    public TemperatureFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_temperature, container, false);
        fragmentMinusSymbolTv = (TextView) view.findViewById(R.id.fragmentMinusSymbolTv);
        fragmentTemperatureTv = (TextView) view.findViewById(R.id.fragmentTemperatureTv);
        return view;
    }

    /**
     * Sets VISIBILITY state for minus TextView
     *
     * @param isMinusVisible true/false
     */
    public void setMinusVisibility(boolean isMinusVisible) {
        fragmentMinusSymbolTv.setVisibility(isMinusVisible ? View.VISIBLE : View.INVISIBLE);
    }

    /**
     * Sets passed temperature value to temperature TextView
     *
     * @param temperature temperature value
     */
    public void setTemperatureTvValue(int temperature) {
        fragmentTemperatureTv.setText(String.valueOf(temperature));
    }

    /**
     * Set passed onClickListener to temperature TextView
     *
     * @param temperatureTvOnClickListener View.OnClickListener
     */
    public void setTemperatureTvOnClickListener(View.OnClickListener temperatureTvOnClickListener) {
        fragmentTemperatureTv.setOnClickListener(temperatureTvOnClickListener);
    }
}
