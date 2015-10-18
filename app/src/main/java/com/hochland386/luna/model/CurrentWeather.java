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
     * Returns true if temperature is less than 0 degree
     * @return boolean isTemperatureNegative
     */
    public boolean isTemperatureNegative() {
        return getTemperature() < 0;
    }

    /**
     * Returns reversed temperature value (e.g. -16 degree will be returned as 16 degree)
     * @return int reversedTemperature
     */
    public int getReversedTemperature() {
        return getTemperature() * -1;
    }

    /**
     * Returns temperature value as String
     * @return String temperature
     */
    public String getTemperatureAsString() {
        return String.valueOf(getTemperature());
    }

    /**
     * Returns reversedTemperature value as String
     * @return String reversedTemperature
     */
    public String getReversedTemperatureAsString() {
        return String.valueOf(getReversedTemperature());
    }

    /**
     * Returns humidity value as String
     * @return String humidity
     */
    public String getHumidityAsString() {
        return String.format("%s",getHumidity() + "%");
    }

    /**
     * Returns pressureInHpa value as String
     * @return String pressureInHpa
     */
    public String getPressureInHpaAsString() {
        return String.valueOf(getPressureInHpa());
    }

    /**
     * Returns pressureInMmhg value as String
     * @return String pressureInMmhg
     */
    public String getPressureInMmhgAsString() {
        return String.valueOf(getPressureInMmhg());
    }

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
