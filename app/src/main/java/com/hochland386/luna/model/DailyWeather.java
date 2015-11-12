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
public class DailyWeather extends Weather {

    //    DailyWeather-specific members
    private long mTimeStamp;
    private String mConditionGroup;

    //    Constructor
    public DailyWeather(int temperature, int humidity, int pressure, String summary, long timeStamp, String conditionGroup) {
        super(temperature, humidity, pressure, summary);
        mTimeStamp = timeStamp;
        mConditionGroup = conditionGroup;
    }

    //    Override default constructor
    public DailyWeather() {
        super();
        mTimeStamp = 0;
        mConditionGroup = "Clear";
    }

//    DailyWeather-specific getters

    /**
     * Returns timestamp value in seconds
     *
     * @return long timeStamp
     */
    public long getTimeStamp() {
        return mTimeStamp;
    }

    /**
     * Returns conditionGroup value
     *
     * @return String conditionGroup
     */
    public String getConditionGroup() {
        return mConditionGroup;
    }
}
