package com.hochland386.luna.bus;

/**
 * Created by vitaly on 11/5/15.
 */
public class ForecastWeatherResponseEvent {

    //    Members
    private String mResponse;

    //    Constructor
    public ForecastWeatherResponseEvent(String response) {
        mResponse = response;
    }

//    Getters

    /**
     * Returns forecast weather JSON data as string
     *
     * @return String response
     */
    public String getResponse() {
        return mResponse;
    }
}
