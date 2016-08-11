package com.example.android.weatherstorm.app;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends ActionBarActivity {

    // LOGGER
    private final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
       // Log.v(LOG_TAG, "in onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new ForecastFragment())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; Dodaje iteme u action bar ako je tu
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Action Bar click handle
        //  Home/Up button
        // kao uAndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        if (id == R.id.action_map) {
            openPreferredLocationInMap();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void openPreferredLocationInMap() {
        SharedPreferences sharedPrefs =
                PreferenceManager.getDefaultSharedPreferences(this);
        String location = sharedPrefs.getString(
                getString(R.string.pref_location_key),
                getString(R.string.pref_location_default));

        // URI Scheme za locakaciju na mapi, intent iz Common intents na sajtu:
        // http://developer.android.com/guide/components/intents-common.html#Maps
        Uri geoLocation = Uri.parse("geo:0,0?").buildUpon()
                .appendQueryParameter("q", location)
                .build();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(geoLocation);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d(LOG_TAG, "Couldn't call " + location + ", no receiving apps installed!");
        }
    }

/*    @Override
    protected void onStart() {
        // bice vidljiva
        Log.v(LOG_TAG, "in onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        // nastavice se
        Log.v(LOG_TAG, "in onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        // ova pauzirana, druga aktivnost preuzima fokus
        Log.v(LOG_TAG, "in onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        // nje vise vidljiva, stopirano
        Log.v(LOG_TAG, "in onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        // destroy :D
        Log.v(LOG_TAG, "in onDestroy");
        super.onDestroy();
    }*/
}