package com.hochland386.luna.ui;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.hochland386.luna.R;
import com.hochland386.luna.bus.CurrentWeatherFailureEvent;
import com.hochland386.luna.bus.CurrentWeatherResponseEvent;
import com.hochland386.luna.bus.LocationChangedEvent;
import com.hochland386.luna.bus.LocationFailureEvent;
import com.hochland386.luna.fragments.PlaceFragment;
import com.hochland386.luna.fragments.RefreshFragment;
import com.hochland386.luna.fragments.TableDataFragment;
import com.hochland386.luna.fragments.TemperatureFragment;
import com.hochland386.luna.fragments.WeatherSummaryFragment;
import com.hochland386.luna.model.CurrentWeather;
import com.hochland386.luna.utils.Constants;
import com.hochland386.luna.utils.GeocoderUtils;
import com.hochland386.luna.utils.ProvidersChecker;
import com.hochland386.luna.utils.ResponseParser;
import com.hochland386.luna.utils.TimerUtils;
import com.hochland386.luna.utils.UrlBuilder;
import com.hochland386.luna.worker.LocationWorker;
import com.hochland386.luna.worker.NetworkWorker;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

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
public class CurrentWeatherActivity extends AppCompatActivity {

    private final int LOCATION_PERMISSIONS_REQUEST_CODE = 0;
    private RefreshFragment refreshFragment;
    private PlaceFragment placeFragment;
    private TemperatureFragment temperatureFragment;
    private TableDataFragment mTableDataFragment;
    private WeatherSummaryFragment weatherSummaryFragment;
    private CurrentWeather mCurrentWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_weather);

        refreshFragment = (RefreshFragment)
                getFragmentManager().findFragmentById(R.id.currentRefreshFragment);
        placeFragment = (PlaceFragment)
                getFragmentManager().findFragmentById(R.id.currentPlaceFragment);
        temperatureFragment = (TemperatureFragment)
                getFragmentManager().findFragmentById(R.id.currentTemperatureFragment);
        mTableDataFragment = (TableDataFragment)
                getFragmentManager().findFragmentById(R.id.currentTableDataFragment);
        weatherSummaryFragment = (WeatherSummaryFragment)
                getFragmentManager().findFragmentById(R.id.currentWeatherSummaryFragment);

        mCurrentWeather = new CurrentWeather();

        refreshFragment.setRefreshIbOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refreshWeather();
            }
        });

        temperatureFragment.setTemperatureTvOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent showForecastActivityIntent = new Intent(
                        CurrentWeatherActivity.this,
                        DailyForecastListActivity.class
                );
                startActivity(showForecastActivityIntent);
            }
        });

        mTableDataFragment
                .setLeftHeaderText(getString(R.string.tableFragmentHumidityHeader));
        mTableDataFragment
                .setRightHeaderText(getString(R.string.tableFragmentPressureHeader));

        EventBus.getDefault().register(this);

        refreshWeather();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (LocationWorker.getInstance().isListeningForUpdates()) {
            refreshFragment.toggleRefreshAnimationOn();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        TimerUtils.getInstance().cancelLocationTimeoutTimer(); /* Cancel Location timeout timer */
        LocationWorker.getInstance().removeLocationUpdates(); /* Remove updates from LocationListener */
        EventBus.getDefault().unregister(this); /* Unsubscribe from all events */
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
     * Checks for runtime permissions and call getUserLocation() if it's granted
     * or request permissions if it's not yet granted.
     */
    private void checkRuntimePermissions() {
    /* Checks runtime permissions. Request permissions if it's not already granted */
        if (ContextCompat.checkSelfPermission(
                CurrentWeatherActivity.this,
                Constants.getInstance().getLocationPermissionsArray()[0])
                != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(
                CurrentWeatherActivity.this,
                Constants.getInstance().getLocationPermissionsArray()[1])
                != PackageManager.PERMISSION_GRANTED) {
                /* Warning! No permissions at this point of the time. Requesting ... */
            ActivityCompat.requestPermissions(
                    CurrentWeatherActivity.this,
                    Constants.getInstance().getLocationPermissionsArray(),
                    LOCATION_PERMISSIONS_REQUEST_CODE
            );
        } else {
        /* Yay! Required Permissions available. We can proceed */
            getUserLocation();
        }
    }

    /**
     * Checks availability of location services and request location updates from LocationWorker
     * if everything is OK, otherwise shows toast with error
     */
    private void getUserLocation() {
        if (ProvidersChecker.getInstance().isLocationEnabled(this)) {
            refreshFragment.toggleRefreshAnimationOn();
            LocationWorker.getInstance().determineUserLocation(this);
        } else {
            refreshFragment.toggleRefreshAnimationOff();
            Toast.makeText(
                    this,
                    getString(R.string.noLocationOrNetworkErrorMessage),
                    Toast.LENGTH_LONG
            ).show();
        }
    }

    /**
     * Checks availability of network, build current weather URL with data from LocationWorker
     * and download current weather data from server. Toast with error message will be shown
     * if network are unavailable
     */
    private void getCurrentWeatherData() {
        if (ProvidersChecker.getInstance().isNetworkAvailable(this)) {
            refreshFragment.toggleRefreshAnimationOn();
            String currentWeatherUrl = UrlBuilder.getInstance().buildCurrentWeatherUrl(
                    String.valueOf(LocationWorker.getInstance().getLatitude()),
                    String.valueOf(LocationWorker.getInstance().getLongitude())
            );
            NetworkWorker.getInstance().downloadCurrentWeatherData(currentWeatherUrl);
        } else {
            refreshFragment.toggleRefreshAnimationOff();
            Toast.makeText(
                    this,
                    getString(R.string.noLocationOrNetworkErrorMessage),
                    Toast.LENGTH_LONG
            ).show();
        }
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
                    /* Permissions denied! Shows toast with error */
                    Toast.makeText(
                            CurrentWeatherActivity.this,
                            getString(R.string.permissionsDeniedErrorMessage),
                            Toast.LENGTH_LONG
                    ).show();
                }
                break;
        }
    }

    /**
     * Call getCurrentWeatherData() when location determined
     */
    public void onEvent(LocationChangedEvent ev) {
        getCurrentWeatherData();
    }

    /**
     * Call getCurrentWeatherData() even if location won't determined. This will get weather
     * data for last known location or for default location if there is no last known location at all
     */
    public void onEvent(LocationFailureEvent ev) {
        /* Shows toast with error and download weather data for default location */
        Toast.makeText(
                this,
                ev.getFailureMessage(),
                Toast.LENGTH_LONG
        ).show();
        getCurrentWeatherData();
    }

    /**
     * Trying to parse JSON data and build CurrentWeather object. When object is ready trying to
     * reverse latitude and longitude into a place using geocoder and set it as CurrentWeather place.
     * Finally call updateUi() to shows data from mCurrentWeather on the screen.
     */
    public void onEvent(CurrentWeatherResponseEvent ev) {
        String response = ev.getResponse();
        try {
            mCurrentWeather = ResponseParser.getInstance().parseCurrentWeatherResponse(response);
            /* Trying to get place from LAT & LONG and set it to mCurrentWeather object */
            List<Address> addresses = GeocoderUtils.getInstance().getPlaceFromLocation(
                    this,
                    LocationWorker.getInstance().getLatitude(),
                    LocationWorker.getInstance().getLongitude()
            );
            if (addresses.size() > 0) {
                if (addresses.get(0).getLocality() != null) {
                    String geocoderPlace = addresses.get(0).getLocality();
                    mCurrentWeather.setPlace(geocoderPlace);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(
                    this,
                    R.string.jsonExceptionErrorMessage,
                    Toast.LENGTH_LONG
            ).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(
                    this,
                    R.string.geocoderFailureErrorMessage,
                    Toast.LENGTH_LONG
            ).show();
        } finally {
            updateUi();
        }
    }

    /**
     * Call updateUi() if network failure occurs. This will shows data from mock CurrentWeather
     * object
     */
    public void onEvent(CurrentWeatherFailureEvent ev) {
        Toast.makeText(
                this,
                R.string.networkFailureErrorMessage,
                Toast.LENGTH_LONG
        ).show();
        updateUi();
    }

    /**
     * Toggle refresh animation off and update View's with data from mCurrentWeather object.
     */
    private void updateUi() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                refreshFragment.toggleRefreshAnimationOff();
                placeFragment.setPlaceTvValue(mCurrentWeather.getPlace());
                if (mCurrentWeather.getTemperature() < 0) {
                    int reversedTemperature = mCurrentWeather.getTemperature() * -1;
                    temperatureFragment.setMinusVisibility(true);
                    temperatureFragment.setTemperatureTvValue(reversedTemperature);
                } else {
                    temperatureFragment.setMinusVisibility(false);
                    temperatureFragment.setTemperatureTvValue(mCurrentWeather.getTemperature());
                }
                String formattedHumidity = String.format("%s", mCurrentWeather.getHumidity() + "%");
                mTableDataFragment
                        .setLeftValueText(formattedHumidity);
                mTableDataFragment
                        .setRightValueText(String.valueOf(mCurrentWeather.getPressure() * 0.75));
                String uppercaseSummary = mCurrentWeather.getSummary()
                        .substring(0, 1)
                        .toUpperCase()
                        + mCurrentWeather.getSummary()
                        .substring(1);
                weatherSummaryFragment.setWeatherSummaryTvValue(uppercaseSummary);
            }
        });
    }
}
