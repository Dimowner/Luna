package com.hochland386.luna.worker;

import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by vitaly on 10/18/15.
 * This class provides public interface and callback to download data from URL in background thread
 */
public class NetworkWorker {

//    NetworkWorkerHandler interface
    public interface NetworkWorkerHandler {
        void handleResponse(String response);
        void handleNetworkFailure();
    }

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
     * Downloads data from passed URL in background thread. handleResponse() interface method
     * will be called when data is ready or handleNetworkFailure() if error occurs
     * @param url URL
     * @param networkWorkerHandler class that implements an NetworkWorkerHandler interface
     */
    public void downloadDataFromUrl(String url, final NetworkWorkerHandler networkWorkerHandler) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        mClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                networkWorkerHandler.handleNetworkFailure();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                networkWorkerHandler.handleResponse(response.body().string());
            }
        });
    }
}
