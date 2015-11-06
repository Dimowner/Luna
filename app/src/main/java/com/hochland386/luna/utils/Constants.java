package com.hochland386.luna.utils;

import android.Manifest;

/**
 * Created by vitaly on 10/17/15.
 * This class provides constants for whole application.
 */
public class Constants {

    //    Members
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

    //    Make default constructor private
    private Constants() {
        mLocationTimeout = 45000;
        mCountDownInterval = 1000;
        mLocationMinTime = 0;
        mLocationMinDistance = 0;
        mApiKey = "7afd086976468949384e4359c0409420";
        mForecastCount = "10";
    }

    //    Implements getInstance() method
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

//    Public interface

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

    //    Singleton wrapper
    private static class Loader {
        static Constants instance = new Constants();
    }
}
