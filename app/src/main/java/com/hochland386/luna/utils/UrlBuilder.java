package com.hochland386.luna.utils;

/**
 * Created by vitaly on 10/17/15.
 * This class provides helper methods for building URLs with certain parameters
 */
public class UrlBuilder {

//    Make default constructor private
    private UrlBuilder() {
    }

//    Public interface
    /**
     * Returns the URL suitable for obtaining current weather data from the server.
     * @param latitude User latitude
     * @param longitude User longitude
     * @return String currentWeatherUrl
     */
    public static String buildCurrentWeatherUrl(String latitude, String longitude) {
        return String.format(
                "http://api.openweathermap.org/data/2.5/weather" +
                        "?lat=%s" +
                        "&lon=%s" +
                        "&units=metric",
                latitude,
                longitude
        );
    }
}
