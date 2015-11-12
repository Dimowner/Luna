package com.hochland386.luna.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

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
public class GeocoderUtils {

    //    Make default constructor private
    private GeocoderUtils() {
    }

    //    Implements getInstance() method
    public static GeocoderUtils getInstance() {
        return Loader.instance;
    }

    /**
     * Transform latitude/longitude coordinate into a address.
     *
     * @param context   context
     * @param latitude  latitude
     * @param longitude longitude
     * @return List of Address objects
     * @throws IOException thrown if the network is unavailable or any other I/O problem occurs
     */
    public List<Address> getPlaceFromLocation(Context context, double latitude, double longitude) throws IOException {
        Geocoder geocoder = new Geocoder(context, Locale.ENGLISH);
        return geocoder.getFromLocation(latitude, longitude, 1);
    }

//    Public interface

    //    Singleton wrapper
    private static class Loader {
        static GeocoderUtils instance = new GeocoderUtils();
    }
}
