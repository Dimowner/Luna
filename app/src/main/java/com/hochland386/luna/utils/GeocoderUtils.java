package com.hochland386.luna.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Created by vitaly on 10/18/15.
 * This class provides helper methods for reverse geocoding.
 * Reverse geocoding means transforming a (latitude, longitude) coordinate into a (partial) address.
 */
public class GeocoderUtils {

//    Make default constructor private
    private GeocoderUtils() {
    }

//    Public interface
    /**
     * Transform latitude/longitude coordinate into a address.
     * @param context context
     * @param latitude latitude
     * @param longitude longitude
     * @return List of Address objects
     * @throws IOException thrown if the network is unavailable or any other I/O problem occurs
     */
    public static List<Address> getPlaceFromLocation(Context context, double latitude, double longitude) throws IOException {
        Geocoder geocoder = new Geocoder(context, Locale.ENGLISH);
        return geocoder.getFromLocation(latitude, longitude, 1);
    }
}
