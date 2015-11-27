package com.hochland386.luna.model;

/**
 * Copyright 2015 Vitaly Sulimov <quarry386@fastmail.com>
 * <p/>
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 */
public abstract class Weather {

    private int mTemperature;
    private int mHumidity;
    private int mPressure;
    private String mSummary;

    public Weather(int temperature, int humidity, int pressure, String summary) {
        mTemperature = temperature;
        mHumidity = humidity;
        mPressure = pressure;
        mSummary = summary;
    }

    public Weather() {
        this(0, 0, 0, "No data");
    }

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
