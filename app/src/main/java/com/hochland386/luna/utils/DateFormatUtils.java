package com.hochland386.luna.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

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
