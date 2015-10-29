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
    private static SimpleDateFormat mFormatter = new SimpleDateFormat("dd LLL");

//    Make default constructor private
    private DateFormatUtils() {
    }

//    Public static interface
    /**
     * Format unix timestamp (in seconds) to human-friendly date with dd MMM pattern
     * @param timestamp unix timestamp in seconds
     * @return String formattedDate
     */
    public static String getFormattedDate(long timestamp) {
        mFormatter.setTimeZone(TimeZone.getDefault());
        Date date = new Date(timestamp * 1000);
        return mFormatter.format(date);
    }
}
