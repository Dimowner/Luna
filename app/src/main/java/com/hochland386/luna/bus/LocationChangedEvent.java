package com.hochland386.luna.bus;

import android.location.Location;

/**
 * Created by vitaly on 11/5/15.
 */
public class LocationChangedEvent {

//    Members
    private Location mLocation;

//    Constructor
    public LocationChangedEvent(Location location) {
        mLocation = location;
    }

//    Getters
    /**
     * Returns Location object
     * @return Location location
     */
    public Location getLocation() {
        return mLocation;
    }
}
