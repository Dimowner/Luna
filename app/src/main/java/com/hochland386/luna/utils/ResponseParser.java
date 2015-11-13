package com.hochland386.luna.utils;

import com.hochland386.luna.model.CurrentWeather;
import com.hochland386.luna.model.DailyWeather;
import com.hochland386.luna.model.Forecast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
public class ResponseParser {

    //    Make default constructor private
    private ResponseParser() {
    }

    //    Implements getInstance() method
    public static ResponseParser getInstance() {
        return Loader.instance;
    }

    /**
     * Parses JSON response from server, build CurrentWeather object and return it
     *
     * @param response JSON response from server
     * @return CurrentWeather currentWeather
     * @throws JSONException thrown if provided data is not valid JSON
     */
    public CurrentWeather parseCurrentWeatherResponse(String response) throws JSONException {
        JSONObject responseAsJSON = new JSONObject(response);
        int temperature = responseAsJSON
                .getJSONObject("main")
                .getInt("temp");
        int humidity = responseAsJSON
                .getJSONObject("main")
                .getInt("humidity");
        int pressure = responseAsJSON
                .getJSONObject("main")
                .getInt("pressure");
        String summary = responseAsJSON
                .getJSONArray("weather")
                .getJSONObject(0)
                .getString("description");
        String place = responseAsJSON
                .getString("name");
        return new CurrentWeather(temperature, humidity, pressure, summary, place);
    }

//    Public interface

    /**
     * Parses JSON response from server, build Forecast object and return it
     *
     * @param response JSON response from server
     * @return Forecast forecast
     * @throws JSONException thrown if provided data is not valid JSON
     */
    public Forecast parseForecastWeatherResponse(String response) throws JSONException {
        JSONObject responseAsJSON = new JSONObject(response);
        JSONArray dailiesJsonArray = responseAsJSON.getJSONArray("list");
        int dailiesCount = dailiesJsonArray.length();
        Forecast forecast = new Forecast(dailiesCount);
        for (int i = 0; i < dailiesCount; i++) {
            JSONObject dailyJson = dailiesJsonArray.getJSONObject(i);
            int temperature = dailyJson
                    .getJSONObject("temp")
                    .getInt("day");
            int minTemperature = dailyJson
                    .getJSONObject("temp")
                    .getInt("min");
            int maxTemperature = dailyJson
                    .getJSONObject("temp")
                    .getInt("max");
            int humidity = dailyJson
                    .getInt("humidity");
            int pressure = dailyJson
                    .getInt("pressure");
            String summary = dailyJson
                    .getJSONArray("weather")
                    .getJSONObject(0)
                    .getString("description");
            long timeStamp = dailyJson
                    .getLong("dt");
            String conditionGroup = dailyJson
                    .getJSONArray("weather")
                    .getJSONObject(0)
                    .getString("main");
            DailyWeather dailyWeather = new DailyWeather(
                    temperature, minTemperature, maxTemperature,
                    humidity, pressure, summary, timeStamp, conditionGroup);
            forecast.setDailyAtIndex(i, dailyWeather);
        }
        return forecast;
    }

    //    Singleton wrapper
    private static class Loader {
        static ResponseParser instance = new ResponseParser();
    }
}
