package com.hochland386.luna.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;

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
public class ProvidersChecker {

    private ProvidersChecker() {
    }

    public static ProvidersChecker getInstance() {
        return Loader.instance;
    }

    /**
     * Returns true if location services enabled and available
     *
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
     *
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
     *
     * @param context context
     * @return boolean isLocationAndNetworkAvailable
     */
    public boolean isLocationAndNetworkAvailable(Context context) {
        return isLocationEnabled(context) && isNetworkAvailable(context);
    }

    private static class Loader {
        static ProvidersChecker instance = new ProvidersChecker();
    }
}
