package com.hochland386.luna.utils;

import android.location.Criteria;

/**
 * Created by vitaly on 10/17/15.
 * This class provides various location utils
 */
public class LocationUtils {

//    Make default constructor private
    private LocationUtils() {
    }

//    Public interface
    /**
     * Returns a Criteria object with ACCURACY_COARSE and POWER_LOW requirements
     * @return Criteria locationProviderCriteria
     */
    public static Criteria getLocationProviderCriteria() {
        Criteria locationProviderCriteria = new Criteria();
        locationProviderCriteria.setAccuracy(Criteria.ACCURACY_COARSE);
        locationProviderCriteria.setPowerRequirement(Criteria.POWER_LOW);
        locationProviderCriteria.setAltitudeRequired(false);
        locationProviderCriteria.setBearingRequired(false);
        locationProviderCriteria.setSpeedRequired(false);
        locationProviderCriteria.setCostAllowed(true);
        return locationProviderCriteria;
    }
}
