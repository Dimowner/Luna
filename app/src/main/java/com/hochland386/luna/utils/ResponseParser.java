package com.hochland386.luna.utils;

import com.hochland386.luna.model.CurrentWeather;

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
}
