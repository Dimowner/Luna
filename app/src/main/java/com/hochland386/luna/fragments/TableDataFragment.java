package com.hochland386.luna.fragments;


import android.app.Fragment;
import android.os.Bundle;
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
public class TableDataFragment extends Fragment {

//    View's declaration
    private TextView tableFragmentLeftHeader;
    private TextView tableFragmentLeftValue;
    private TextView tableFragmentRightHeader;
    private TextView tableFragmentRightValue;

    public TableDataFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_table_data, container, false);
        // View's init
        tableFragmentLeftHeader = (TextView) view.findViewById(R.id.tableFragmentLeftHeader);
        tableFragmentLeftValue = (TextView) view.findViewById(R.id.tableFragmentLeftValue);
        tableFragmentRightHeader = (TextView) view.findViewById(R.id.tableFragmentRightHeader);
        tableFragmentRightValue = (TextView) view.findViewById(R.id.tableFragmentRightValue);
        return view;
    }

//    Public interface

    /**
     * Sets passed string to left header TextView
     * @param text String leftHeaderText
     */
    public void setLeftHeaderText(String text) {
        tableFragmentLeftHeader.setText(text);
    }

    /**
     * Sets passed string to left value TextView
     * @param text String leftValueText
     */
    public void setLeftValueText(String text) {
        tableFragmentLeftValue.setText(text);
    }

    /**
     * Sets passed string to right header TextView
     * @param text String rightHeaderText
     */
    public void setRightHeaderText(String text) {
        tableFragmentRightHeader.setText(text);
    }

    /**
     * Sets passed string to right value TextView
     * @param text String rightValueText
     */
    public void setRightValueText(String text) {
        tableFragmentRightValue.setText(text);
    }
}
