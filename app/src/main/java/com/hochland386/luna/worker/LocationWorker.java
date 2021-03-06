package com.hochland386.luna.worker;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.hochland386.luna.bus.LocationChangedEvent;
import com.hochland386.luna.bus.LocationFailureEvent;
import com.hochland386.luna.utils.Constants;
import com.hochland386.luna.utils.LocationUtils;
import com.hochland386.luna.utils.TimerUtils;

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
@SuppressWarnings("ResourceType")
public class LocationWorker implements TimerUtils.LocationTimerTimeout {

    private LocationManager mLocationManager;
    private LocationListener mLocationListener;
    private boolean mIsDetermineUserLocationTriggered;
    private boolean mIsListeningForUpdates;
    private double mLatitude;
    private double mLongitude;

    private LocationWorker() {
        mIsDetermineUserLocationTriggered = false;
        mIsListeningForUpdates = false;
        mLatitude = 0.0;
        mLongitude = 0.0;
    }

    public static LocationWorker getInstance() {
        return Loader.instance;
    }

    @Override
    public void handleTimeout() {
        /* Remove updates from listener and post LocationFailureEvent */
        mLocationManager.removeUpdates(mLocationListener);
        mIsListeningForUpdates = false;
        EventBus.getDefault().post(new LocationFailureEvent("Location timeout"));
    }

    /**
     * Trying to determine user location using suitable provider.
     * By default, the priority is given to POWER_LOW requirements which means in most cases
     * network location provider will be used. If for some reasons location not found within
     * 45 seconds then LocationFailureEvent will be posted. LocationChangedEvent will be posted
     * when location determined. You can access latest location data by calling getLatitude() and
     * getLongitude() methods
     *
     * @param context context
     */
    public void determineUserLocation(Context context) {
        /* Members init */
        mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                /* Cancel timer, remove updates from listener and call handleUserLocation() */
                TimerUtils.getInstance().cancelLocationTimeoutTimer();
                mLocationManager.removeUpdates(mLocationListener);
                mIsListeningForUpdates = false;
                mLatitude = location.getLatitude();
                mLongitude = location.getLongitude();
                EventBus.getDefault().post(new LocationChangedEvent());
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            @Override
            public void onProviderEnabled(String provider) {
            }

            @Override
            public void onProviderDisabled(String provider) {
                /* Cancel timer, remove updates from listener and call handleLocationFailure() */
                TimerUtils.getInstance().cancelLocationTimeoutTimer();
                mLocationManager.removeUpdates(mLocationListener);
                mIsListeningForUpdates = false;
                EventBus.getDefault().post(new LocationFailureEvent("Location provider disabled"));
            }
        };
        /* Obtain locationProviderCriteria and best location provider */
        Criteria locationProviderCriteria = LocationUtils
                .getInstance()
                .getLocationProviderCriteria();
        String locationProvider = mLocationManager.getBestProvider(locationProviderCriteria, true);
        /* Start Location Timeout timer and request location updates */
        TimerUtils.getInstance().startLocationTimeoutTimer(this);
        mLocationManager.requestLocationUpdates(
                locationProvider,
                Constants.getInstance().getLocationMinTime(),
                Constants.getInstance().getLocationMinDistance(),
                mLocationListener
        );
        mIsDetermineUserLocationTriggered = true;
        mIsListeningForUpdates = true;
    }

    /**
     * Remove location updates from LocationListener
     */
    public void removeLocationUpdates() {
        if (mLocationManager != null && mLocationListener != null) {
            mLocationManager.removeUpdates(mLocationListener);
            mIsListeningForUpdates = false;
        }
    }

    /**
     * Returns true if determineUserLocation() was be called at least once
     *
     * @return boolean isDetermineUserLocationTriggered
     */
    public boolean isDetermineUserLocationTriggered() {
        return mIsDetermineUserLocationTriggered;
    }

    /**
     * Returns true if LocationListener listening for updates from LocationManager
     *
     * @return boolean isListeningForUpdates
     */
    public boolean isListeningForUpdates() {
        return mIsListeningForUpdates;
    }

    /**
     * Returns last known user latitude
     *
     * @return double latitude
     */
    public double getLatitude() {
        return mLatitude;
    }

    /**
     * Returns last known user longitude
     *
     * @return double longitude
     */
    public double getLongitude() {
        return mLongitude;
    }

    private static class Loader {
        static LocationWorker instance = new LocationWorker();
    }
}
