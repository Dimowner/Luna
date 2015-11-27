package com.hochland386.luna.utils;

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
public class UrlBuilder {

    private UrlBuilder() {
    }

    public static UrlBuilder getInstance() {
        return Loader.instance;
    }

    /**
     * Returns the URL suitable for obtaining current weather data from the server.
     *
     * @param latitude  User latitude
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
                Constants.getInstance().getApiKey()
        );
    }

    /**
     * Returns the URL suitable for obtaining forecast weather data from server.
     *
     * @param latitude  User latitude
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
                Constants.getInstance().getForecastCount(),
                Constants.getInstance().getApiKey()
        );
    }

    private static class Loader {
        static UrlBuilder instance = new UrlBuilder();
    }
}
