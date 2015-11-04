package com.hochland386.luna.bus;

/**
 * Created by vitaly on 11/5/15.
 */
public class LocationFailureEvent {

//    Members
    private String mFailureMessage;

//    Constructor
    public LocationFailureEvent(String failureMessage) {
        mFailureMessage = failureMessage;
    }

//    Getters
    /**
     * Returns location failure error message
     * @return String message
     */
    public String getFailureMessage() {
        return mFailureMessage;
    }
}
