package com.hochland386.luna.model;

/**
 * Created by vitaly on 10/28/15.
 */
public class Forecast {

    //    Members
    private DailyWeather[] mDailyWeathers;

    //    Constructors
    public Forecast(DailyWeather[] dailyWeathers) {
        mDailyWeathers = dailyWeathers;
    }

    public Forecast(int forecastCount) {
        mDailyWeathers = new DailyWeather[forecastCount];
        for (int i = 0; i < forecastCount; i++) {
            mDailyWeathers[i] = new DailyWeather();
        }
    }

    //    Override default constructor
    public Forecast() {
        this(10);
    }

//    Public interface

    /**
     * Returns the number of DailyWeather objects in collection
     *
     * @return int dailiesCount
     */
    public int getForecastCount() {
        return mDailyWeathers.length;
    }

    /**
     * Returns a DailyWeather object with passed index
     *
     * @param index DailyWeather object index
     * @return DailyWeather dailyWeather
     */
    public DailyWeather getDailyWithIndex(int index) {
        return mDailyWeathers[index];
    }

    /**
     * Sets new DailyWeather object at passed index
     *
     * @param index        DailyWeather object index
     * @param dailyWeather new DailyWeather object
     */
    public void setDailyAtIndex(int index, DailyWeather dailyWeather) {
        mDailyWeathers[index] = dailyWeather;
    }
}
