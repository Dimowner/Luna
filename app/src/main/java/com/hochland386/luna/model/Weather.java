package com.hochland386.luna.model;

/**
 * Created by vitaly on 10/17/15.
 */
public abstract class Weather {

    //    Members
    private int mTemperature;
    private int mHumidity;
    private int mPressure;
    private String mSummary;

    //    Constructor
    public Weather(int temperature, int humidity, int pressure, String summary) {
        mTemperature = temperature;
        mHumidity = humidity;
        mPressure = pressure;
        mSummary = summary;
    }

    //    Override default constructor
    public Weather() {
        this(0, 0, 0, "No data");
    }

//    Final getters

    /**
     * Returns temperature
     *
     * @return int temperature
     */
    public final int getTemperature() {
        return mTemperature;
    }

    /**
     * Returns humidity
     *
     * @return int humidity
     */
    public final int getHumidity() {
        return mHumidity;
    }

    /**
     * Returns pressure in hectopascals
     *
     * @return int pressure
     */
    public final int getPressure() {
        return mPressure;
    }

    /**
     * Returns weather summary
     *
     * @return String summary
     */
    public final String getSummary() {
        return mSummary;
    }
}
