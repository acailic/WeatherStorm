package com.example.android.weatherstorm.app.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by Aleksandar on 8/21/2016.
 */
public class WeatherStormAuthenticatorService extends Service {
    // Instance field that stores the authenticator object
    private WeatherStormAuthenticator mAuthenticator;

    @Override
    public void onCreate() {
        // Create a new authenticator object
        mAuthenticator = new WeatherStormAuthenticator(this);
    }

    /*
     * When the system binds to this Service to make the RPC call
     * return the authenticator's IBinder.
     */
    @Override
    public IBinder onBind(Intent intent) {
        return mAuthenticator.getIBinder();
    }
}