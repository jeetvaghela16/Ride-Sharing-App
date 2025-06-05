package com.example.rideshareprototype;

public class RideRequest {
    public double latitude;
    public double longitude;

    public RideRequest() {} // Needed for Firebase

    public RideRequest(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
