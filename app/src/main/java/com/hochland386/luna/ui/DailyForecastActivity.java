package com.hochland386.luna.ui;

import android.app.ListActivity;
import android.os.Bundle;

import com.hochland386.luna.R;

public class DailyForecastActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forecast);
    }
}
