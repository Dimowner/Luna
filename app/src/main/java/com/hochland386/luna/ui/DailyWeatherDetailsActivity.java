package com.hochland386.luna.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hochland386.luna.R;
import com.hochland386.luna.fragments.HumidityPressureFragment;
import com.hochland386.luna.fragments.TemperatureFragment;
import com.hochland386.luna.fragments.TimestampFragment;
import com.hochland386.luna.fragments.WeatherSummaryFragment;
import com.hochland386.luna.model.DailyWeather;
import com.hochland386.luna.utils.Constants;
import com.hochland386.luna.utils.DateFormatUtils;

public class DailyWeatherDetailsActivity extends AppCompatActivity {

//    Fragments declaration
    private TimestampFragment timestampFragment;
    private TemperatureFragment temperatureFragment;
    private HumidityPressureFragment humidityPressureFragment;
    private WeatherSummaryFragment weatherSummaryFragment;

//    Members
    private DailyWeather mDailyWeather;

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

//        Retrieve extra data from intent that starts this activity
        Intent intent = getIntent();
        mDailyWeather = intent.getParcelableExtra(
                Constants.getInstance().getDailyWeatherExtraKey()
        );

//        Call updateUi when all necessary data is ready
        updateUi();
    }

//    Private interface

    /**
     * Updates UI with data from mDailyWeather object
     */
    private void updateUi() {
        String formattedDate = DateFormatUtils.getInstance().getFormattedDate(mDailyWeather.getTimeStamp());
        timestampFragment.setTimestampTvDate(formattedDate);
        if (mDailyWeather.getTemperature() < 0) {
            int reversedTemperature = mDailyWeather.getTemperature() * -1;
            temperatureFragment.setMinusVisibility(true);
            temperatureFragment.setTemperatureTvValue(reversedTemperature);
        } else {
            temperatureFragment.setMinusVisibility(false);
            temperatureFragment.setTemperatureTvValue(mDailyWeather.getTemperature());
        }
        humidityPressureFragment.setHumidityTvValue(mDailyWeather.getHumidity());
        humidityPressureFragment.setPressureTvValue((int) (mDailyWeather.getPressure() * 0.75));
        String uppercaseSummary = mDailyWeather.getSummary()
                .substring(0, 1)
                .toUpperCase()
                + mDailyWeather.getSummary()
                .substring(1);
        weatherSummaryFragment.setWeatherSummaryTvValue(uppercaseSummary);
    }
}
