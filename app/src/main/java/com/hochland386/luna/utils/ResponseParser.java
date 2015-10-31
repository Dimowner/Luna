package com.hochland386.luna.utils;

import com.hochland386.luna.model.CurrentWeather;
import com.hochland386.luna.model.DailyWeather;
import com.hochland386.luna.model.Forecast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by vitaly on 10/17/15.
 * This class provides helper methods for server response parsing and build Weather objects.
 */
public class ResponseParser {

//    Make default constructor private
    private ResponseParser() {
    }

//    Public interface
    /**
     * Parses JSON response from server, build CurrentWeather object and return it
     * @param response JSON response from server
     * @return CurrentWeather currentWeather
     * @throws JSONException thrown if provided data is not valid JSON
     */
    public static CurrentWeather parseCurrentWeatherResponse(String response) throws JSONException {
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

    /**
     * Parses JSON response from server, build Forecast object and return it
     * @param response JSON response from server
     * @return Forecast forecast
     * @throws JSONException thrown if provided data is not valid JSON
     */
    public static Forecast parseForecastWeatherResponse(String response) throws JSONException {
        JSONObject responseAsJSON = new JSONObject(response);
        JSONArray dailiesJsonArray = responseAsJSON.getJSONArray("list");
        int dailiesCount = dailiesJsonArray.length();
        Forecast forecast = new Forecast(dailiesCount);
        for (int i = 0; i < dailiesCount; i++) {
            JSONObject dailyJson = dailiesJsonArray.getJSONObject(i);
            int temperature = dailyJson
                    .getJSONObject("temp")
                    .getInt("day");
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
            DailyWeather dailyWeather = new DailyWeather(temperature, humidity, pressure, summary, timeStamp, conditionGroup);
            forecast.setDailyAtIndex(i, dailyWeather);
        }
        return forecast;
    }
}
