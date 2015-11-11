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
 * A simple {@link Fragment} subclass.
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
    public void setFragmentRefreshIbOnClickListener(View.OnClickListener refreshIbOnClickListener) {
        fragmentRefreshIb.setOnClickListener(refreshIbOnClickListener);
    }
}
