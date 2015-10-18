package com.hochland386.luna.worker;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.hochland386.luna.utils.Constants;
import com.hochland386.luna.utils.LocationUtils;
import com.hochland386.luna.utils.TimerUtils;

/**
 * Created by vitaly on 10/18/15.
 * This class provides public interface and callback to determine user location
 */
@SuppressWarnings("ResourceType")
public class LocationWorker implements TimerUtils.TimerTimeout {

//    Handle Location Timer timeout
    @Override
    public void handleTimerTimeout() {
        /* Remove updates from listener and call handleLocationFailure() */
        mLocationManager.removeUpdates(mLocationListener);
        mLocationHandler.handleLocationFailure();
    }

    //    LocationHandler interface
    public interface LocationHandler {
        void handleUserLocation(Location location);
        void handleLocationFailure();
    }

//    Members
    private LocationHandler mLocationHandler;
    private LocationManager mLocationManager;
    private LocationListener mLocationListener;

//    Public interface
    /**
     * Trying to determine user location using suitable provider.
     * By default, the priority is given to POWER_LOW requirements which means in most cases
     * network location provider will be used. If for some reasons location not found within
     * 45 seconds then handleLocationFailure() interface method will be called
     * @param context context
     * @param locationHandler class that implements an LocationHandler interface
     */
    public void determineUserLocation(Context context, LocationHandler locationHandler) {
        /* Members init */
        mLocationHandler = locationHandler;
        mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        mLocationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                /* Cancel timer, remove updates from listener and call handleUserLocation() */
                TimerUtils.cancelLocationTimeoutTimer();
                mLocationManager.removeUpdates(mLocationListener);
                mLocationHandler.handleUserLocation(location);
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
                TimerUtils.cancelLocationTimeoutTimer();
                mLocationManager.removeUpdates(mLocationListener);
                mLocationHandler.handleLocationFailure();
            }
        };
        /* Obtain locationProviderCriteria and best location provider */
        Criteria locationProviderCriteria = LocationUtils.getLocationProviderCriteria();
        String locationProvider = mLocationManager.getBestProvider(locationProviderCriteria, true);
        /* Start Location Timeout timer and request location updates */
        TimerUtils.startLocationTimeoutTimer(this);
        mLocationManager.requestLocationUpdates(
                locationProvider,
                Constants.LOCATION_MIN_TIME,
                Constants.LOCATION_MIN_DISTANCE,
                mLocationListener
        );
    }
}
