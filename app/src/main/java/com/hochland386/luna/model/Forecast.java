package com.hochland386.luna.model;

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
