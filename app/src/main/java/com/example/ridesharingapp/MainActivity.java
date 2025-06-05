package com.example.ridesharingapp;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.*;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;
import com.google.firebase.database.*;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private FusedLocationProviderClient fusedLocationProviderClient;
    private Location userLocation;

    private Button btnRequestRide;
    private TextView tvDriverDetails;

    private DatabaseReference databaseReference;

    private final int LOCATION_REQUEST_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnRequestRide = findViewById(R.id.btnRequestRide);
        tvDriverDetails = findViewById(R.id.tvDriverDetails);

        // Firebase DB reference
        databaseReference = FirebaseDatabase.getInstance().getReference("rideRequests");

        // Google Maps setup
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        // Initialize fused location provider
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        // Button listener
        btnRequestRide.setOnClickListener(v -> {
            if (userLocation != null) {
                requestRide(userLocation);
            } else {
                Toast.makeText(this, "Getting location... Please wait.", Toast.LENGTH_SHORT).show();
                getFreshLocation();  // Try again
            }
        });
    }

    private void requestRide(Location location) {
        String requestId = databaseReference.push().getKey();

        // Save ride request to Firebase
        databaseReference.child(requestId).setValue(
                new com.example.rideshareprototype.RideRequest(location.getLatitude(), location.getLongitude())
        );

        // Simulate assigning a driver
        tvDriverDetails.setText("Driver Assigned:\nName: John Doe\nVehicle: Toyota Prius\nPhone: 123-456-7890");
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        getLocationPermission();
    }

    private void getLocationPermission() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_REQUEST_CODE);
        } else {
            enableUserLocation();
        }
    }

    private void enableUserLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        mMap.setMyLocationEnabled(true);

        // Try to get last known location
        fusedLocationProviderClient.getLastLocation().addOnSuccessListener(location -> {
            if (location != null) {
                updateLocation(location);
            } else {
                getFreshLocation();  // Fallback
            }
        });
    }

    private void getFreshLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        LocationRequest request = LocationRequest.create()
                .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
                .setInterval(10000)
                .setFastestInterval(5000)
                .setNumUpdates(1);

        fusedLocationProviderClient.requestLocationUpdates(
                request,
                new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        if (locationResult != null && locationResult.getLastLocation() != null) {
                            updateLocation(locationResult.getLastLocation());
                        } else {
                            Toast.makeText(MainActivity.this, "Unable to retrieve current location.", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                null
        );
    }

    private void updateLocation(Location location) {
        userLocation = location;
        LatLng userLatLng = new LatLng(location.getLatitude(), location.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(userLatLng, 15));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_REQUEST_CODE && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            enableUserLocation();
        } else {
            Toast.makeText(this, "Location permission is required to use this feature.", Toast.LENGTH_LONG).show();
        }
    }
}
