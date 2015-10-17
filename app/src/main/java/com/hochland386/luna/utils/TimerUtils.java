package com.hochland386.luna.utils;

import android.os.CountDownTimer;

/**
 * Created by vitaly on 10/17/15.
 * This class provides timers with callback and control interfaces
 */
public class TimerUtils {

//    Timer timeout callback interface
    public interface TimerTimeout {
        void handleTimerTimeout();
    }

//    Members
    private static CountDownTimer mLocationTimer;

//    Make default constructor private
    private TimerUtils() {
    }

//    Public interface
    /**
     * Starts location timeout timer with default timeout and countdown interval values.
     * You can cancel this timer by calling cancelLocationTimeoutTimer();
     * If a timeout occurs handleTimerTimeout() interface method will be called.
     * @param timeoutHandler class which implements TimerTimeout interface
     */
    public static void startLocationTimeoutTimer(final TimerTimeout timeoutHandler) {
        mLocationTimer = new CountDownTimer(Constants.LOCATION_TIMEOUT, Constants.COUNT_DOWN_INTERVAL) {
            @Override
            public void onTick(long millisUntilFinished) {
//                Do nothing :)
            }

            @Override
            public void onFinish() {
                timeoutHandler.handleTimerTimeout();
            }
        };
        mLocationTimer.start();
    }

    /**
     * Cancel location timeout timer.
     */
    public static void cancelLocationTimeoutTimer() {
        mLocationTimer.cancel();
    }
}
