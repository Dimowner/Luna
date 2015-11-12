package com.hochland386.luna.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hochland386.luna.R;
import com.hochland386.luna.fragments.HumidityPressureFragment;
import com.hochland386.luna.fragments.TemperatureFragment;
import com.hochland386.luna.fragments.TimestampFragment;
import com.hochland386.luna.fragments.WeatherSummaryFragment;

public class DailyWeatherDetailsActivity extends AppCompatActivity {

//    Fragments declaration
    private TimestampFragment timestampFragment;
    private TemperatureFragment temperatureFragment;
    private HumidityPressureFragment humidityPressureFragment;
    private WeatherSummaryFragment weatherSummaryFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_weather_details);

//        Fragments init
        timestampFragment = (TimestampFragment)
                getFragmentManager().findFragmentById(R.id.detailsTimestampFragment);
        temperatureFragment = (TemperatureFragment)
                getFragmentManager().findFragmentById(R.id.detailsTemperatureFragment);
        humidityPressureFragment = (HumidityPressureFragment)
                getFragmentManager().findFragmentById(R.id.detailsHumidityPressureFragment);
        weatherSummaryFragment = (WeatherSummaryFragment)
                getFragmentManager().findFragmentById(R.id.detailsWeatherSummaryFragment);
    }
}
