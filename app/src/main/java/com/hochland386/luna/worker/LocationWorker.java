package com.hochland386.luna.worker;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.hochland386.luna.bus.LocationChangedEvent;
import com.hochland386.luna.bus.LocationFailureEvent;
import com.hochland386.luna.bus.TimerTimeoutEvent;
import com.hochland386.luna.utils.Constants;
import com.hochland386.luna.utils.LocationUtils;
import com.hochland386.luna.utils.TimerUtils;

import de.greenrobot.event.EventBus;

/**
 * Created by vitaly on 10/18/15.
 * This class provides public interface and events to determine user location
 */
@SuppressWarnings("ResourceType")
public class LocationWorker {

    //    Members
    private LocationManager mLocationManager;
    private LocationListener mLocationListener;
    private boolean mIsDetermineUserLocationTriggered;
    private boolean mIsListeningForUpdates;
    private double mLatitude;
    private double mLongitude;

    //    Make default constructor private
    private LocationWorker() {
        EventBus.getDefault().register(this);
        mIsDetermineUserLocationTriggered = false;
        mIsListeningForUpdates = false;
        mLatitude = 0.0;
        mLongitude = 0.0;
    }

    //    Implements getInstance() method
    public static LocationWorker getInstance() {
        return Loader.instance;
    }

    //    Handle TimerTimeoutEvent
    public void onEvent(TimerTimeoutEvent ev) {
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
                TimerUtils timerUtils = TimerUtils.getInstance();
                timerUtils.cancelLocationTimeoutTimer();
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
                TimerUtils timerUtils = TimerUtils.getInstance();
                timerUtils.cancelLocationTimeoutTimer();
                mLocationManager.removeUpdates(mLocationListener);
                mIsListeningForUpdates = false;
                EventBus.getDefault().post(new LocationFailureEvent("Location provider disabled"));
            }
        };
        /* Obtain locationProviderCriteria and best location provider */
        LocationUtils locationUtils = LocationUtils.getInstance();
        Criteria locationProviderCriteria = locationUtils.getLocationProviderCriteria();
        String locationProvider = mLocationManager.getBestProvider(locationProviderCriteria, true);
        /* Start Location Timeout timer and request location updates */
        TimerUtils timerUtils = TimerUtils.getInstance();
        timerUtils.startLocationTimeoutTimer();
        mLocationManager.requestLocationUpdates(
                locationProvider,
                Constants.getInstance().getLocationMinTime(),
                Constants.getInstance().getLocationMinDistance(),
                mLocationListener
        );
        mIsDetermineUserLocationTriggered = true;
        mIsListeningForUpdates = true;
    }

//    Public interface

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

    //    Singleton wrapper
    private static class Loader {
        static LocationWorker instance = new LocationWorker();
    }
}
