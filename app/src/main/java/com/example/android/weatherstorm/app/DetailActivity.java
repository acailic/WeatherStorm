package com.example.android.weatherstorm.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Aleksandar on 8/10/2016.
 */
public class DetailActivity extends ActionBarActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new DetailFragment ())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // dodaj items na akcioni bar ako postoji
        getMenuInflater().inflate(R.menu.detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*
         Action bar item click here
         home up button
         in AndroidManifest.xml
         */
        int id=item.getItemId();

        if(id==R.id.action_settings){
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    /**
     * Simple view.
     */
    public static class DetailFragment  extends Fragment {

        public DetailFragment () {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

            // Detail Activity prozvana kao intent.
            Intent intent=getActivity().getIntent();
            if(intent!= null && intent.hasExtra(Intent.EXTRA_TEXT)){
                String forecastStr=intent.getStringExtra(Intent.EXTRA_TEXT);
                ( (TextView) rootView.findViewById(R.id.detail_text)).setText(forecastStr);
            }
            return rootView;
        }
    }


}
