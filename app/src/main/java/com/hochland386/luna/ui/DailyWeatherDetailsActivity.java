package com.hochland386.luna.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hochland386.luna.R;
import com.hochland386.luna.fragments.TableDataFragment;
import com.hochland386.luna.fragments.TemperatureFragment;
import com.hochland386.luna.fragments.TimestampFragment;
import com.hochland386.luna.fragments.WeatherSummaryFragment;
import com.hochland386.luna.model.DailyWeather;
import com.hochland386.luna.utils.Constants;
import com.hochland386.luna.utils.DateFormatUtils;

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
public class DailyWeatherDetailsActivity extends AppCompatActivity {

//    Fragments declaration
    private TimestampFragment timestampFragment;
    private TemperatureFragment temperatureFragment;
    private TableDataFragment mTableDataFragment;
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
        mTableDataFragment = (TableDataFragment)
                getFragmentManager().findFragmentById(R.id.detailsTableDataFragment);
        weatherSummaryFragment = (WeatherSummaryFragment)
                getFragmentManager().findFragmentById(R.id.detailsWeatherSummaryFragment);

//        Sets TableDataFragment headers
        mTableDataFragment
                .setLeftHeaderText(getString(R.string.tableFragmentMinTempHeader));
        mTableDataFragment
                .setRightHeaderText(getString(R.string.tableFragmentMaxTempHeader));

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
        mTableDataFragment.setLeftValueText(
                mDailyWeather.getMinTemperature() + "\u00b0"
        );
        mTableDataFragment.setRightValueText(
                mDailyWeather.getMaxTemperature() + "\u00b0"
        );
        String uppercaseSummary = mDailyWeather.getSummary()
                .substring(0, 1)
                .toUpperCase()
                + mDailyWeather.getSummary()
                .substring(1);
        weatherSummaryFragment.setWeatherSummaryTvValue(uppercaseSummary);
    }
}
