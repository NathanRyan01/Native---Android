package com.example.virg.thesis;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import static android.support.constraint.Constraints.TAG;

public class GPSTracker implements LocationListener {

    public String myLocation;
    private TextView mTextView;
    String lat;
    String lon;
    private static final String TAG = "debugging ";

    GPSTracker(TextView tv) {
        this.mTextView = tv;

    }

    @Override
    public void onLocationChanged(Location location) {
        location.getLatitude();
        location.getLongitude();

        mTextView.setText("Latitude: " + location.getLatitude() + " Longitude:" + location.getLongitude());
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

}
