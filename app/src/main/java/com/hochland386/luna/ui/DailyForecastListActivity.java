package com.hochland386.luna.ui;

import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.hochland386.luna.R;
import com.hochland386.luna.adapters.DailyForecastAdapter;
import com.hochland386.luna.bus.ForecastWeatherResponseEvent;
import com.hochland386.luna.bus.LocationChangedEvent;
import com.hochland386.luna.bus.LocationFailureEvent;
import com.hochland386.luna.model.DailyWeather;
import com.hochland386.luna.model.Forecast;
import com.hochland386.luna.utils.Constants;
import com.hochland386.luna.utils.ProvidersChecker;
import com.hochland386.luna.utils.ResponseParser;
import com.hochland386.luna.utils.UrlBuilder;
import com.hochland386.luna.worker.LocationWorker;
import com.hochland386.luna.worker.NetworkWorker;

import org.json.JSONException;

import de.greenrobot.event.EventBus;

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
public class DailyForecastListActivity extends ListActivity implements NetworkWorker.NetworkFailure {

    @Override
    public void handleNetworkFailure() {
        Toast.makeText(
                this,
                getString(R.string.networkFailureErrorMessage),
                Toast.LENGTH_LONG
        ).show();
        updateUi();
    }

    private final int LOCATION_PERMISSIONS_REQUEST_CODE = 1;
    private Forecast mForecast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_forecast);

        mForecast = new Forecast();

        EventBus.getDefault().register(this);

        refreshWeather();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        DailyWeather dailyWeather = mForecast.getDailyWithIndex(position);
        Intent intent = new Intent(
                DailyForecastListActivity.this,
                DailyWeatherDetailsActivity.class
        );
        intent.putExtra(
                Constants.getInstance().getDailyWeatherExtraKey(),
                dailyWeather
        );
        startActivity(intent);
    }

    /**
     * Checks for location and network availability and call checkRuntimePermissions() if everything is OK
     */
    private void refreshWeather() {
        if (ProvidersChecker.getInstance().isLocationAndNetworkAvailable(this)) {
            checkRuntimePermissions();
        } else {
            Toast.makeText(
                    this,
                    getString(R.string.noLocationOrNetworkErrorMessage),
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    /**
     * Checks Location status. If LocationWorker.determineUserLocation() never be called and
     * device don't listening for location updates then call getUserLocation().
     * <p/>
     * If LocationWorker.determineUserLocation() was be called at least once and device don't
     * listening for location updates then assuming that location already available and we can
     * use it.
     * <p/>
     * Finally. If device is listening for location updates but there is no location available
     * then do nothing, just waiting for Location Event.
     */
    private void checkRuntimePermissions() {
        if (!LocationWorker.getInstance().isDetermineUserLocationTriggered()
                && !LocationWorker.getInstance().isListeningForUpdates()) {
        /* Checks runtime permissions. Request permissions if it's not already granted */
            if (ContextCompat.checkSelfPermission(
                    DailyForecastListActivity.this,
                    Constants.getInstance().getLocationPermissionsArray()[0])
                    != PackageManager.PERMISSION_GRANTED
                    && ContextCompat.checkSelfPermission(
                    DailyForecastListActivity.this,
                    Constants.getInstance().getLocationPermissionsArray()[1])
                    != PackageManager.PERMISSION_GRANTED) {
                /* Warning! No permissions at this point of the time. Requesting ... */
                ActivityCompat.requestPermissions(
                        DailyForecastListActivity.this,
                        Constants.getInstance().getLocationPermissionsArray(),
                        LOCATION_PERMISSIONS_REQUEST_CODE
                );
            } else {
            /* Yay! Required Permissions available. We can proceed */
                getUserLocation();
            }
        } else if (LocationWorker.getInstance().isDetermineUserLocationTriggered()
                && !LocationWorker.getInstance().isListeningForUpdates()) {
            getForecastWeatherData();
        } else {
            Toast.makeText(
                    this,
                    R.string.waitingForLocationMessage,
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    /**
     * Checks availability of location services and request location updates from LocationWorker
     * if everything is OK, otherwise shows toast with error
     */
    private void getUserLocation() {
        if (ProvidersChecker.getInstance().isLocationEnabled(this)) {
            LocationWorker.getInstance().determineUserLocation(this);
        } else {
            Toast.makeText(
                    this,
                    getString(R.string.noLocationOrNetworkErrorMessage),
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    /**
     * Checks availability of network, build forecast weather URL with data from LocationWorker
     * and download forecast weather data from server. Toast with error message will be shown
     * if network are unavailable
     */
    private void getForecastWeatherData() {
        if (ProvidersChecker.getInstance().isNetworkAvailable(this)) {
            String forecastWeatherUrl = UrlBuilder.getInstance().buildForecastWeatherUrl(
                    String.valueOf(LocationWorker.getInstance().getLatitude()),
                    String.valueOf(LocationWorker.getInstance().getLongitude())
            );
            NetworkWorker.getInstance().downloadForecastWeatherData(forecastWeatherUrl, this);
        } else {
            Toast.makeText(
                    this,
                    getString(R.string.noLocationOrNetworkErrorMessage),
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    /**
     * Fills list with data from mForecast object
     */
    private void updateUi() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                DailyForecastAdapter forecastAdapter = new DailyForecastAdapter(
                        DailyForecastListActivity.this,
                        mForecast
                );
                setListAdapter(forecastAdapter);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case LOCATION_PERMISSIONS_REQUEST_CODE:
                /* If request is cancelled, the result arrays are empty. */
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    /* Permissions was granted, yay! */
                    getUserLocation();
                } else {
                    /* Permissions denied! Finishing activity */
                    finish();
                }
                break;
        }
    }

    /**
     * Call downloadCurrentWeatherData() when location determined
     */
    public void onEvent(LocationChangedEvent ev) {
        getForecastWeatherData();
    }

    /**
     * Call downloadCurrentWeatherData() even if location won't determined. This will get weather
     * data for last known location or for default location if there is no last known location at all
     */
    public void onEvent(LocationFailureEvent ev) {
        /* Shows toast with error and download weather data for default location */
        Toast.makeText(
                this,
                ev.getFailureMessage(),
                Toast.LENGTH_LONG
        ).show();
        getForecastWeatherData();
    }

    /**
     * Trying to parse JSON data and build Forecast object. Call updateUi() when Forecast
     * object is ready.
     */
    public void onEvent(ForecastWeatherResponseEvent ev) {
        String response = ev.getResponse();
        try {
            mForecast = ResponseParser.getInstance().parseForecastWeatherResponse(response);
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(
                    this,
                    getString(R.string.jsonExceptionErrorMessage),
                    Toast.LENGTH_LONG
            ).show();
        } finally {
            updateUi();
        }
    }
}
