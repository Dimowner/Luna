package com.hochland386.luna.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

/**
 * Created by vitaly on 10/17/15.
 * This class provides helper methods to check availability of system services such as
 * location, network, etc.
 */
public class ProvidersChecker {

//    Make default constructor private
    private ProvidersChecker() {
    }

//    Singleton wrapper
    private static class Loader {
        static ProvidersChecker instance = new ProvidersChecker();
    }

//    Implements getInstance() method
    public static ProvidersChecker getInstance() {
        return Loader.instance;
    }

//    Public interface
    /**
     * Returns true if location services enabled and available
     * @param context context
     * @return boolean isLocationEnabled
     */
    public boolean isLocationEnabled(Context context) {
        String locationProviders = Settings.Secure.getString(
                context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED
        );
        return !locationProviders.isEmpty();
    }

    /**
     * Returns true if any data network are available and connected
     * @param context context
     * @return boolean isOnline
     */
    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /**
     * Returns true if location and network enabled and available on device. Otherwise returns false
     * @param context context
     * @return boolean isLocationAndNetworkAvailable
     */
    public boolean isLocationAndNetworkAvailable(Context context) {
        return isLocationEnabled(context) && isNetworkAvailable(context);
    }
}
