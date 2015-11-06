package com.hochland386.luna.model;

/**
 * Created by vitaly on 10/28/15.
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
