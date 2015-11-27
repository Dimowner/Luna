package com.hochland386.luna.utils;

import android.os.CountDownTimer;

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
public class TimerUtils {

    private CountDownTimer mLocationTimer;

    private TimerUtils() {
    }

    public static TimerUtils getInstance() {
        return Loader.instance;
    }

    /**
     * Starts location timeout timer with default timeout and countdown interval values.
     * You can cancel this timer by calling cancelLocationTimeoutTimer();
     * handleTimeout() interface method will be called if timeout occurs
     *
     * @param timeoutHandler class which implements LocationTimerTimeout interface
     */
    public void startLocationTimeoutTimer(final LocationTimerTimeout timeoutHandler) {
        mLocationTimer = new CountDownTimer(
                Constants.getInstance().getLocationTimeout(),
                Constants.getInstance().getCountDownInterval()) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                timeoutHandler.handleTimeout();
            }
        };
        mLocationTimer.start();
    }

    /**
     * Cancel location timeout timer.
     */
    public void cancelLocationTimeoutTimer() {
        if (mLocationTimer != null) {
            mLocationTimer.cancel();
        }
    }

    public interface LocationTimerTimeout {
        void handleTimeout();
    }

    private static class Loader {
        static TimerUtils instance = new TimerUtils();
    }
}
