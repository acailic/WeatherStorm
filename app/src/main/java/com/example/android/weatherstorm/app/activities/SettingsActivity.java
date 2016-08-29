package com.example.android.weatherstorm.app.activities;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

import com.example.android.weatherstorm.app.R;

/**
 * Created by Aleksandar on 8/10/2016.
 */

/*
            developing a Settings UI.
 */
public class SettingsActivity extends PreferenceActivity implements Preference.OnPreferenceChangeListener {
    @Override
    public boolean onPreferenceChange(Preference preference, Object value) {
        String stringValue = value.toString();

       if (preference instanceof ListPreference) {
            // Lista preferences
            // entry lista label : value
            ListPreference listPreference = (ListPreference) preference;
            int prefIndex = listPreference.findIndexOfValue(stringValue);
            if (prefIndex >= 0) {
                preference.setSummary(listPreference.getEntries()[prefIndex]);
            }
        } else {
            // ostali jednostavan string
            preference.setSummary(stringValue);
        }
        return true;
    }
    /*
        Inicialno se opali jednom, listiner se azurira na preference value
     */

    private void bindPreferenceSummaryToValue(Preference preference) {
        // listener slusa prommene vrednosti.
        preference.setOnPreferenceChangeListener(this);

        // pri izmeni se opali.

        onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // General u preferences iz xml
        addPreferencesFromResource(R.xml.pref_general);
        //  za sve preferences, na listener, kad se promeni vrednost
        // da ui moze da se azurira
        bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_location_key)));
        bindPreferenceSummaryToValue(findPreference(getString(R.string.pref_units_key)));
    }


    // TODO:
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public Intent getParentActivityIntent() {
        return super.getParentActivityIntent().addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    }
}
