package com.example.appu.location;

/**
 * Created by APPU on 21-03-2017.
 */

public class LocationDetails {

    Double latitude;
    Double longitude;

    public LocationDetails(){}

    public LocationDetails(Double latitude,Double longitude){this.latitude=latitude;this.longitude=longitude;}

    public Double getLatitude(){return this.latitude;}
    public void setLatitude(Double latitude){
        this.latitude=latitude;
    }
    public Double getLongitude(){return this.longitude;}
    public void setLongitude(Double longitude){
        this.longitude=longitude;
    }
}
