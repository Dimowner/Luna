package com.hochland386.luna.worker;

import com.hochland386.luna.bus.CurrentWeatherFailureEvent;
import com.hochland386.luna.bus.CurrentWeatherResponseEvent;
import com.hochland386.luna.bus.ForecastWeatherFailureEvent;
import com.hochland386.luna.bus.ForecastWeatherResponseEvent;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

import de.greenrobot.event.EventBus;

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
public class NetworkWorker {

    private final OkHttpClient mClient = new OkHttpClient();

    private NetworkWorker() {
    }

    public static NetworkWorker getInstance() {
        return Loader.instance;
    }

    /**
     * Downloads data from passed URL in background thread. CurrentWeatherResponseEvent will be
     * posted when data is ready or CurrentWeatherFailureEvent if any error occurs
     *
     * @param url URL
     */
    public void downloadCurrentWeatherData(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                EventBus.getDefault().post(new CurrentWeatherFailureEvent());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String responseAsString = response.body().string();
                EventBus.getDefault().post(new CurrentWeatherResponseEvent(responseAsString));
            }
        });
    }

    /**
     * Downloads data from passed URL in background thread. ForecastWeatherResponseEvent will be
     * posted when data is ready or ForecastWeatherFailureEvent if any error occurs
     *
     * @param url URL
     */
    public void downloadForecastWeatherData(String url) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                EventBus.getDefault().post(new ForecastWeatherFailureEvent());
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String responseAsString = response.body().string();
                EventBus.getDefault().post(new ForecastWeatherResponseEvent(responseAsString));
            }
        });
    }

    private static class Loader {
        static NetworkWorker instance = new NetworkWorker();
    }
}
