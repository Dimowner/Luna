package com.hochland386.luna.model;

import android.os.Parcel;
import android.os.Parcelable;

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
public class DailyWeather extends Weather implements Parcelable {

    public static final Creator<DailyWeather> CREATOR = new Creator<DailyWeather>() {
        @Override
        public DailyWeather createFromParcel(Parcel source) {
            return new DailyWeather(source);
        }

        @Override
        public DailyWeather[] newArray(int size) {
            return new DailyWeather[size];
        }
    };

    private long mTimeStamp;
    private int mMinTemperature;
    private int mMaxTemperature;
    private String mConditionGroup;

    public DailyWeather(int temperature, int minTemperature, int maxTemperature, int humidity, int pressure, String summary, long timeStamp, String conditionGroup) {
        super(temperature, humidity, pressure, summary);
        mTimeStamp = timeStamp;
        mMinTemperature = minTemperature;
        mMaxTemperature = maxTemperature;
        mConditionGroup = conditionGroup;
    }

    public DailyWeather() {
        super();
        mTimeStamp = 0;
        mConditionGroup = "Clear";
    }

    private DailyWeather(Parcel in) {
        this(
                in.readInt(),
                in.readInt(),
                in.readInt(),
                in.readInt(),
                in.readInt(),
                in.readString(),
                in.readLong(),
                in.readString()
        );
    }

    /**
     * Returns timestamp value in seconds
     *
     * @return long timeStamp
     */
    public long getTimeStamp() {
        return mTimeStamp;
    }

    /**
     * Returns min temperature value
     *
     * @return int minTemperature
     */
    public int getMinTemperature() {
        return mMinTemperature;
    }

    /**
     * Returns max temperature value
     *
     * @return int maxTemperature
     */
    public int getMaxTemperature() {
        return mMaxTemperature;
    }

    /**
     * Returns conditionGroup value
     *
     * @return String conditionGroup
     */
    public String getConditionGroup() {
        return mConditionGroup;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getTemperature());
        dest.writeInt(mMinTemperature);
        dest.writeInt(mMaxTemperature);
        dest.writeInt(getHumidity());
        dest.writeInt(getPressure());
        dest.writeString(getSummary());
        dest.writeLong(mTimeStamp);
        dest.writeString(mConditionGroup);
    }
}
