package com.example.ihelpproject;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class GoogleMapActivity extends AppCompatActivity {
    private static String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static String COARSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private boolean perrGranted = false;
    private final static int requestCode1 = 1;
    private final static float DEFAULT_ZOOM = 15f;
    double x, y;
    Button btn_getLocation;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_map);
        getLocationPermission();
        btn_getLocation = findViewById(R.id.btn_getLocation);
        btn_getLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra("x", x);
                resultIntent.putExtra("y", y);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });


    }


    private void moveCamera(LatLng latLng, Float zoom) {
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

    }


    private void getDeviceLocation() {

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(GoogleMapActivity.this);
        try {
            if (perrGranted) {
                Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(GoogleMapActivity.this, "found location", Toast.LENGTH_LONG).show();
                            Location currentLocation = (Location) task.getResult();
                            assert currentLocation != null;
                            x = currentLocation.getLatitude();
                            y = currentLocation.getLongitude();

                            moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()),
                                    DEFAULT_ZOOM);


                        } else {
                            Toast.makeText(GoogleMapActivity.this, "didint found location", Toast.LENGTH_LONG).show();
                        }
                    }
                });

            }
        } catch (SecurityException e) {
            Toast.makeText(GoogleMapActivity.this, "error", Toast.LENGTH_LONG).show();
        }

    }

    private void getLocationPermission() {
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if (ContextCompat.checkSelfPermission(this.getApplicationContext(), FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            if (ContextCompat.checkSelfPermission(this.getApplicationContext(), COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                perrGranted = true;
                initMap();

            }
        } else {
            ActivityCompat.requestPermissions(this, permissions, requestCode1);

        }
    }//end method

    private void initMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                Toast.makeText(GoogleMapActivity.this, "ready", Toast.LENGTH_LONG).show();
                mMap = googleMap;

                if (perrGranted) {
                    getDeviceLocation();
                    if (ActivityCompat.checkSelfPermission(GoogleMapActivity.this,
                            Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                            && ActivityCompat.checkSelfPermission(GoogleMapActivity.this,
                            Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                    mMap.setMyLocationEnabled(true);


                }
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        perrGranted = false;

        switch (requestCode) {
            case requestCode1:
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                            perrGranted = false;
                            return;
                        }

                    }
                    perrGranted = true;
                    initMap();

                }

        }
    }


}
