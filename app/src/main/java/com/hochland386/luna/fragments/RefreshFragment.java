package com.hochland386.luna.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;

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
public class RefreshFragment extends Fragment {

//    View's declaration
    private ImageButton fragmentRefreshIb;
    private ProgressBar fragmentRefreshPb;

    public RefreshFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_refresh, container, false);
        // View's init
        fragmentRefreshIb = (ImageButton) view.findViewById(R.id.fragmentRefreshIb);
        fragmentRefreshPb = (ProgressBar) view.findViewById(R.id.fragmentRefreshPb);
        return view;
    }

//    Public interface

    /**
     * Hides refresh ImageButton and shows ProgressBar instead
     */
    public void toggleRefreshAnimationOn() {
        fragmentRefreshIb.setVisibility(View.INVISIBLE);
        fragmentRefreshPb.setVisibility(View.VISIBLE);
    }

    /**
     * Hides refresh ProgressBar and shows ImageButton instead
     */
    public void toggleRefreshAnimationOff() {
        fragmentRefreshPb.setVisibility(View.INVISIBLE);
        fragmentRefreshIb.setVisibility(View.VISIBLE);
    }

    /**
     * Set passed onClickListener to refresh ImageButton
     * @param refreshIbOnClickListener View.OnClickListener
     */
    public void setRefreshIbOnClickListener(View.OnClickListener refreshIbOnClickListener) {
        fragmentRefreshIb.setOnClickListener(refreshIbOnClickListener);
    }
}
