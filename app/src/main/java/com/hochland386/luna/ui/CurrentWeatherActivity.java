package com.hochland386.luna.ui;

import android.location.Address;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.hochland386.luna.R;
import com.hochland386.luna.model.CurrentWeather;
import com.hochland386.luna.utils.Constants;
import com.hochland386.luna.utils.GeocoderUtils;
import com.hochland386.luna.utils.ProvidersChecker;
import com.hochland386.luna.utils.ResponseParser;
import com.hochland386.luna.utils.UrlBuilder;
import com.hochland386.luna.worker.LocationWorker;
import com.hochland386.luna.worker.NetworkWorker;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class CurrentWeatherActivity extends AppCompatActivity implements LocationWorker.LocationHandler, NetworkWorker.NetworkWorkerHandler {

//    View's declaration
    private ImageButton refreshIb;
    private ProgressBar refreshPb;
    private TextView placeTv, minusSymbolTv, temperatureTv,
            humidityValueTv, pressureValueTv, weatherSummaryTv;

//    Members
    private LocationWorker mLocationWorker;
    private NetworkWorker mNetworkWorker;
    private String mLatitudeAsString = String.valueOf(Constants.DEFAULT_LATITUDE);
    private String mLongitudeAsString = String.valueOf(Constants.DEFAULT_LONGITUDE);
    private CurrentWeather mCurrentWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_weather);

//        View's init
        refreshIb = (ImageButton) findViewById(R.id.refreshIb);
        refreshPb = (ProgressBar) findViewById(R.id.refreshPb);
        placeTv = (TextView) findViewById(R.id.placeTv);
        minusSymbolTv = (TextView) findViewById(R.id.minusSymbolTv);
        temperatureTv = (TextView) findViewById(R.id.temperatureTv);
        humidityValueTv = (TextView) findViewById(R.id.humidityValueTv);
        pressureValueTv = (TextView) findViewById(R.id.pressureValueTv);
        weatherSummaryTv = (TextView) findViewById(R.id.weatherSummaryTv);

//        Members init
        mLocationWorker = new LocationWorker();
        mNetworkWorker = new NetworkWorker();
    }

//    Private interface
    /**
     * Updates UI with data from mCurrentWeather object
     */
    private void updateUi() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                toggleRefreshAnimationOff();
                placeTv.setText(mCurrentWeather.getPlace());
                if (mCurrentWeather.isTemperatureNegative()) {
                    minusSymbolTv.setVisibility(View.VISIBLE);
                    temperatureTv.setText(mCurrentWeather.getReversedTemperatureAsString());
                } else {
                    minusSymbolTv.setVisibility(View.INVISIBLE);
                    temperatureTv.setText(mCurrentWeather.getTemperatureAsString());
                }
                humidityValueTv.setText(mCurrentWeather.getHumidityAsString());
                pressureValueTv.setText(mCurrentWeather.getPressureInMmhgAsString());
                weatherSummaryTv.setText(mCurrentWeather.getSummary());
            }
        });
    }

    /**
     * Hide refresh ImageButton and shows ProgressBar instead
     */
    private void toggleRefreshAnimationOn() {
        refreshIb.setVisibility(View.INVISIBLE);
        refreshPb.setVisibility(View.VISIBLE);
    }

    /**
     * Hide refresh ProgressBar and shows ImageButton instead
     */
    private void toggleRefreshAnimationOff() {
        refreshPb.setVisibility(View.INVISIBLE);
        refreshIb.setVisibility(View.VISIBLE);
    }

//    OnClick methods
    public void onClickRefreshIb(View view) {
        /* Checks availability of location and network and request location from LocationWorker */
        if (ProvidersChecker.isLocationAndNetworkAvailable(this)) {
            toggleRefreshAnimationOn();
            mLocationWorker.determineUserLocation(this, this);
        } else {
            Toast.makeText(
                    this,
                    "Location or network are unavailable",
                    Toast.LENGTH_LONG
            ).show();
        }
    }

//    Implements LocationHandler interface
    @Override
    public void handleUserLocation(Location location) {
        /* Override member variables with new LAT & LONG values */
        mLatitudeAsString = String.valueOf(location.getLatitude());
        mLongitudeAsString = String.valueOf(location.getLongitude());
        /* Build currentWeatherUrl and download data from server */
        String currentWeatherUrl = UrlBuilder.buildCurrentWeatherUrl(mLatitudeAsString, mLongitudeAsString);
        mNetworkWorker.downloadDataFromUrl(currentWeatherUrl, this);
    }

    @Override
    public void handleLocationFailure() {
        /* Shows Toast with error */
        Toast.makeText(
                this,
                "Can't determine your location. Timeout :(",
                Toast.LENGTH_LONG
        ).show();
        /* Build currentWeatherUrl and download data from server */
        String currentWeatherUrl = UrlBuilder.buildCurrentWeatherUrl(mLatitudeAsString, mLongitudeAsString);
        mNetworkWorker.downloadDataFromUrl(currentWeatherUrl, this);
    }

//    Implements NetworkWorkerHandler interface
    @Override
    public void handleResponse(String response) {
        /* Trying to parse response and build CurrentWeather object */
        try {
            mCurrentWeather = ResponseParser.parseCurrentWeatherResponse(response);
        } catch (JSONException e) {
            e.printStackTrace();
            /* This will create a mock CurrentWeather object */
            mCurrentWeather = new CurrentWeather();
        }
        /* Trying to reverse LAT & LONG into a place and set it as CurrentWeather place */
        double latitudeAsDouble = Double.parseDouble(mLatitudeAsString);
        double longitudeAsDouble = Double.parseDouble(mLongitudeAsString);
        try {
            List<Address> addresses = GeocoderUtils.getPlaceFromLocation(this, latitudeAsDouble, longitudeAsDouble);
            if (addresses.size() > 0) {
                if (addresses.get(0).getLocality() != null) {
                    mCurrentWeather.setPlace(addresses.get(0).getLocality());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        /* Hooray! CurrentWeather object is ready. Call updateUi(). */
        updateUi();
    }

    @Override
    public void handleNetworkFailure() {
        /* Create a mock CurrentWeather object and call updateUi() */
        mCurrentWeather = new CurrentWeather();
        updateUi();
    }
}
