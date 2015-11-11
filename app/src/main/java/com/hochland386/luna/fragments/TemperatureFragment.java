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
public class TemperatureFragment extends Fragment {

//    View's declaration
    private TextView fragmentMinusSymbolTv;
    private TextView fragmentTemperatureTv;

    public TemperatureFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_temperature, container, false);
        // View's init
        fragmentMinusSymbolTv = (TextView) view.findViewById(R.id.fragmentMinusSymbolTv);
        fragmentTemperatureTv = (TextView) view.findViewById(R.id.fragmentTemperatureTv);
        return view;
    }

//    Public interface
    /**
     * Sets VISIBILITY state for minus TextView
     * @param isMinusVisible true/false
     */
    public void setMinusVisibility(boolean isMinusVisible) {
        fragmentMinusSymbolTv.setVisibility(isMinusVisible ? View.VISIBLE : View.INVISIBLE);
    }

    /**
     * Sets passed temperature value to temperature TextView
     * @param temperature temperature value
     */
    public void setTemperatureTvValue(int temperature) {
        fragmentTemperatureTv.setText(String.valueOf(temperature));
    }

    /**
     * Set passed onClickListener to temperature TextView
     * @param fragmentTemperatureTvOnClickListener View.OnClickListener
     */
    public void setFragmentTemperatureTvOnClickListener(View.OnClickListener fragmentTemperatureTvOnClickListener) {
        fragmentTemperatureTv.setOnClickListener(fragmentTemperatureTvOnClickListener);
    }
}
