package com.hochland386.luna.utils;

import android.location.Criteria;

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
public class LocationUtils {

    //    Make default constructor private
    private LocationUtils() {
    }

    //    Implements getInstance() method
    public static LocationUtils getInstance() {
        return Loader.instance;
    }

    /**
     * Returns a Criteria object with ACCURACY_COARSE and POWER_LOW requirements
     *
     * @return Criteria locationProviderCriteria
     */
    public Criteria getLocationProviderCriteria() {
        Criteria locationProviderCriteria = new Criteria();
        locationProviderCriteria.setAccuracy(Criteria.ACCURACY_COARSE);
        locationProviderCriteria.setPowerRequirement(Criteria.POWER_LOW);
        locationProviderCriteria.setAltitudeRequired(false);
        locationProviderCriteria.setBearingRequired(false);
        locationProviderCriteria.setSpeedRequired(false);
        locationProviderCriteria.setCostAllowed(true);
        return locationProviderCriteria;
    }

//    Public interface

    //    Singleton wrapper
    private static class Loader {
        static LocationUtils instance = new LocationUtils();
    }
}
