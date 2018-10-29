package com.example.virg.thesis;
// https://www.techotopia.com/index.php/Video_Recording_and_Image_Capture_on_Android_using_Camera_Intents

import android.Manifest.permission.*;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.File;
import java.io.FileNotFoundException;

public class HardwareActivity extends MainActivity {

    TextView location;
    DisplayMetrics displayMetrics;
    VideoView videoView;
    public static int VIDEO_CAPTURED = 1;
    ImageView targetImage;
    boolean isGPSEnabled;
    boolean isNetworkEnabled;

    long startTime1;
    long startTime2;
    long startTime3;
    long startTime4;
    long startTime5;
    long startTime6;
    long endTime1;
    long endTime2;
    long endTime4;
    long endTime5;
    long endTime6;
    long MethodDuration1;
    long MethodDuration2;
    long MethodDuration4;
    long MethodDuration5;
    long MethodDuration6;
    double lat = 0.0;
    double lon = 0.0;
    Location loc; // location
    private static final String TAG = "debugging ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hardware);
        videoView = this.findViewById(R.id.videoView);
    }

    public void getLocation(View view) {
        location = findViewById(R.id.locationtext);
        startTime1 = System.currentTimeMillis();
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        GPSTracker locationListener = new GPSTracker(location);
            if (ActivityCompat.checkSelfPermission(HardwareActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(HardwareActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    requestPermissions(new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.INTERNET
                    },10);
                }
                return;
            } else {
                // getting GPS status
                if (locationManager != null) {
                    isGPSEnabled = locationManager
                            .isProviderEnabled(LocationManager.GPS_PROVIDER);
                }
                // getting network status
                if (locationManager != null) {
                    isNetworkEnabled = locationManager
                            .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
                }
                if (!isGPSEnabled && !isNetworkEnabled) {
                    Toast.makeText(this, "No network provider is enabled", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(isGPSEnabled){
                        Toast.makeText(this, "Enabled", Toast.LENGTH_SHORT).show();
                        if (locationManager != null) {
                            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, locationListener);
                        }
                        if (locationManager != null) {
                            locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        }
                    }
                }
            }
            endTime1 = System.currentTimeMillis();
            MethodDuration1 = (endTime1 - startTime1);
            String total = String.valueOf(MethodDuration1);

    }

    public void selectImage(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == VIDEO_CAPTURED) {
            if (resultCode == RESULT_OK) {
                Toast.makeText(this, "Video saved to:\n" +
                        data.getData(), Toast.LENGTH_LONG).show();
                Uri uri = Uri.parse(String.valueOf(data.getData()));
                videoView.setVideoURI(uri);
                videoView.start();
            } else if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Video recording cancelled.",
                        Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(this, "Failed to record video",
                        Toast.LENGTH_LONG).show();
            }
        }
        else {
            if (resultCode == RESULT_OK) {
                Uri targetUri = data.getData();
                Bitmap bitmap;
                try {
                    bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(targetUri));
                    targetImage.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public void recordingVideo(View view) {
        Intent captureVideoIntent = new Intent(android.provider.MediaStore.ACTION_VIDEO_CAPTURE);
        startActivityForResult(captureVideoIntent, VIDEO_CAPTURED);
    }

    public void startRecording(View view) {
        videoView.start();
    }

    public void stopRecording(View view) {
        videoView.stopPlayback();
    }
}
