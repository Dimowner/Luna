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
public class CurrentWeather extends Weather {

    private String mPlace;

    public CurrentWeather(int temperature, int humidity, int pressure, String summary, String place) {
        super(temperature, humidity, pressure, summary);
        mPlace = place;
    }

    public CurrentWeather() {
        super();
        mPlace = "Unknown";
    }

    /**
     * Returns place
     *
     * @return String place
     */
    public String getPlace() {
        return mPlace;
    }

    /**
     * Sets place with provided value
     *
     * @param place New place value
     */
    public void setPlace(String place) {
        mPlace = place;
    }
}
