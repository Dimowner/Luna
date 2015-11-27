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
public class TimestampFragment extends Fragment {

    private TextView fragmentTimestampTv;

    public TimestampFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_timestamp, container, false);
        fragmentTimestampTv = (TextView) view.findViewById(R.id.fragmentTimestampTv);
        return view;
    }

    /**
     * Sets passed date to timestamp TextView and format it.
     * E.g. if you passed "16 Nov" as a parameter the final result will be "At 16 Nov it will be"
     *
     * @param date date
     */
    public void setTimestampTvDate(String date) {
        String formattedDate = String.format("At %s it will be", date);
        fragmentTimestampTv.setText(formattedDate);
    }
}
