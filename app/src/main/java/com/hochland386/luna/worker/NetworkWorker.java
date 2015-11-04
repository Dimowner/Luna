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
 * Created by vitaly on 10/18/15.
 * This class provides public interface and callback to download data from URL in background thread
 */
public class NetworkWorker {

//    Members
    private final OkHttpClient mClient = new OkHttpClient();

//    Make default constructor private
    private NetworkWorker() {
    }

//    Singleton wrapper
    private static class Loader {
        static NetworkWorker instance = new NetworkWorker();
    }

//    Implements getInstance() method
    public static NetworkWorker getInstance() {
        return Loader.instance;
    }

//    Public interface
    /**
     * Downloads data from passed URL in background thread. CurrentWeatherResponseEvent will be
     * posted when data is ready or CurrentWeatherFailureEvent if any error occurs
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
}
