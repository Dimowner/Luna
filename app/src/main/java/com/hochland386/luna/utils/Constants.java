package com.hochland386.luna.utils;

import android.Manifest;

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
public class Constants {

    private int mLocationTimeout;
    private int mCountDownInterval;
    private long mLocationMinTime;
    private float mLocationMinDistance;
    private String mApiKey;
    private String mForecastCount;
    private String[] mLocationPermissionsArray = {
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
    };
    private String mDailyWeatherExtraKey;

    private Constants() {
        mLocationTimeout = 45000;
        mCountDownInterval = 1000;
        mLocationMinTime = 0;
        mLocationMinDistance = 0;
        mApiKey = "7afd086976468949384e4359c0409420";
        mForecastCount = "16";
        mDailyWeatherExtraKey = "INTENT_EXTRA_DAILY_WEATHER";
    }

    public static Constants getInstance() {
        return Loader.instance;
    }

    /**
     * Returns default location timeout in milliseconds
     *
     * @return int locationTimeout
     */
    public int getLocationTimeout() {
        return mLocationTimeout;
    }

    /**
     * Returns default count down interval in milliseconds
     *
     * @return int countDownInterval
     */
    public int getCountDownInterval() {
        return mCountDownInterval;
    }

    /**
     * Returns default location min time
     *
     * @return long locationMinTime
     */
    public long getLocationMinTime() {
        return mLocationMinTime;
    }

    /**
     * Returns default location min distance
     *
     * @return float locationMinDistance
     */
    public float getLocationMinDistance() {
        return mLocationMinDistance;
    }

    /**
     * Returns API key for signed request as string
     *
     * @return String apiKey
     */
    public String getApiKey() {
        return mApiKey;
    }

    /**
     * Returns default days count for forecast request
     *
     * @return String forecastCount
     */
    public String getForecastCount() {
        return mForecastCount;
    }

    /**
     * Returns array with location permissions which requires for application location features
     * functionality
     *
     * @return String[] locationPermissionsArray
     */
    public String[] getLocationPermissionsArray() {
        return mLocationPermissionsArray;
    }

    /**
     * Returns key to send / retrieve DailyWeather intent extra
     *
     * @return String mDailyWeatherExtraKey
     */
    public String getDailyWeatherExtraKey() {
        return mDailyWeatherExtraKey;
    }

    private static class Loader {
        static Constants instance = new Constants();
    }
}
