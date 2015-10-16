package com.hochland386.luna.model;

/**
 * Created by vitaly on 10/17/15.
 */
public class CurrentWeather extends Weather {

//    CurrentWeather-specific members
    private String mPlace;

//    Constructor
    public CurrentWeather(int temperature, int humidity, int pressure, String summary, String place) {
        super(temperature, humidity, pressure, summary);
        mPlace = place;
    }

//    Override default constructor
    public CurrentWeather() {
        super();
        mPlace = "Unknown";
    }

//    CurrentWeather-specific getters
    /**
     * Returns place
     * @return String place
     */
    public String getPlace() {
        return mPlace;
    }

//    CurrentWeather-specific setters
    /**
     * Sets place with provided value
     * @param place New place value
     */
    public void setPlace(String place) {
        mPlace = place;
    }
}
