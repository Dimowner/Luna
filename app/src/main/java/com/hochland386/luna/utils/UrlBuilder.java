package com.hochland386.luna.utils;

/**
 * Created by vitaly on 10/17/15.
 * This class provides helper methods for building URLs with certain parameters
 */
public class UrlBuilder {

//    Make default constructor private
    private UrlBuilder() {
    }

//    Singleton wrapper
    private static class Loader {
        static UrlBuilder instance = new UrlBuilder();
    }

//    Implements getInstance() method
    public static UrlBuilder getInstance() {
        return Loader.instance;
    }

//    Public interface
    /**
     * Returns the URL suitable for obtaining current weather data from the server.
     * @param latitude User latitude
     * @param longitude User longitude
     * @return String currentWeatherUrl
     */
    public String buildCurrentWeatherUrl(String latitude, String longitude) {
        return String.format(
                "http://api.openweathermap.org/data/2.5/weather" +
                        "?lat=%s" +
                        "&lon=%s" +
                        "&units=metric" +
                        "&appid=%s",
                latitude,
                longitude,
                Constants.API_KEY
        );
    }

    /**
     * Returns the URL suitable for obtaining forecast weather data from server.
     * @param latitude User latitude
     * @param longitude User longitude
     * @return String forecastWeatherUrl
     */
    public String buildForecastWeatherUrl(String latitude, String longitude) {
        return String.format(
                "http://api.openweathermap.org/data/2.5/forecast/daily" +
                        "?lat=%s" +
                        "&lon=%s" +
                        "&units=metric" +
                        "&cnt=%s" +
                        "&appid=%s",
                latitude,
                longitude,
                Constants.FORECAST_COUNT,
                Constants.API_KEY
        );
    }
}
