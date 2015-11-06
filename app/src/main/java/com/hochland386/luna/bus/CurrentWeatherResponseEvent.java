package com.hochland386.luna.bus;

/**
 * Created by vitaly on 11/5/15.
 */
public class CurrentWeatherResponseEvent {

    //    Members
    private String mResponse;

    //    Constructor
    public CurrentWeatherResponseEvent(String response) {
        mResponse = response;
    }

//    Getters

    /**
     * Returns current weather JSON data as string
     *
     * @return String response
     */
    public String getResponse() {
        return mResponse;
    }
}
