package com.hochland386.luna.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by vitaly on 10/29/15.
 * This class provides helper methods for converting unix-like timestamp to human-friendly date.
 */
public class DateFormatUtils {

    //    Members
    private SimpleDateFormat mFormatter;

    //    Make default constructor private
    private DateFormatUtils() {
        mFormatter = new SimpleDateFormat("dd LLL");
    }

    //    Implements getInstance() method
    public static DateFormatUtils getInstance() {
        return Loader.instance;
    }

    /**
     * Format unix timestamp (in seconds) to human-friendly date with DD MMM pattern
     *
     * @param timestamp unix timestamp in seconds
     * @return String formattedDate
     */
    public String getFormattedDate(long timestamp) {
        mFormatter.setTimeZone(TimeZone.getDefault());
        Date date = new Date(timestamp * 1000);
        return mFormatter.format(date);
    }

//    Public interface

    //    Singleton wrapper
    private static class Loader {
        static DateFormatUtils instance = new DateFormatUtils();
    }
}
