package com.example.android.weatherstorm.app.sync;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

/**
 * Created by Aleksandar on 8/21/2016.
 */
public class WeatherStormSyncService extends Service {
    private static final Object sSyncAdapterLock = new Object();
    private static WeatherStormSyncAdapter sWeatherStormSyncService = null;

    @Override
    public void onCreate() {
        Log.d("WeatherStormSyncService", "onCreate - WeatherStormSyncService");
        synchronized (sSyncAdapterLock) {
            if (sWeatherStormSyncService == null) {
                sWeatherStormSyncService = new WeatherStormSyncAdapter(getApplicationContext(), true);
            }
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return sWeatherStormSyncService.getSyncAdapterBinder();
    }
}