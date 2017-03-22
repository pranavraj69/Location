package com.example.appu.location;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.R.attr.onClick;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

     private GoogleMap mMap;
    Marker marker;
    MarkerOptions mo ;
    private FirebaseDatabase database;
    private DatabaseReference Lat;
    private DatabaseReference Lang;
    private DatabaseReference location;
    private String Lati;
    private String longi;
    private Double lat;
    private Double lng;
    final static int PERMISSION_ALL=1;
    final static String[] PERMISSIONS = {android.Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        database = FirebaseDatabase.getInstance();
        Lat = database.getReference("Latitude");
        Lang = database.getReference("Longitude");
        location = database.getReference("loc/Locationdetails");
        //LocationDetails loc = new LocationDetails(10.0016256,76.36);
        //location.setValue(loc);


        // Read from the database
        /*Lat.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Lati = dataSnapshot.getValue(String.class);
                //double lat=Double.valueOf(Lati.toString());
                lat= Double.parseDouble(Lati);
                lng= Double.parseDouble(longi);
                LatLng myCoordinates = new LatLng(lat,lng);
                marker.setPosition(myCoordinates);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(myCoordinates));

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("BUGGGG", "Failed to read value.", error.toException());
            }
        });
        // Read from the database
        Lang.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                longi = dataSnapshot.getValue(String.class);
                //double lng=Double.valueOf(longi.toString());
                lng=Double.parseDouble(longi);
                lat=Double.parseDouble(Lati);
                LatLng myCoordinates = new LatLng(lat,lng);
                marker.setPosition(myCoordinates);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(myCoordinates));


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("BUGGGG", "Failed to read value.", error.toException());
            }
        });*/

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        //mMap.clear();
        mMap = googleMap;
        mMap.clear();

        location.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.v("main","");
                LocationDetails Locationdetails = dataSnapshot.getValue(LocationDetails.class);
                Log.v("main","sdfdsfs"+Locationdetails.getLatitude());
                Log.v("main","sdfdsfs"+Locationdetails.getLongitude());
                if(mMap != null) {
                    LatLng myCoordinates = new LatLng(Locationdetails.getLatitude(),Locationdetails.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(myCoordinates));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(myCoordinates));
                } }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    private void showAlert(final int status) {
        String message, title, btnText;
        if (status == 1) {
            message = "LocationDetails settings is set to '0ff'. \n Please Enable location to use this App. ";
            title = "Enable location";
            btnText = "LocationDetails Settings";

        } else
        {
            message= "Please allow the App to acess LocationDetails.";
            title = "Permission Access";
            btnText= "Grant";

        }
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setCancelable(false);
        dialog.setTitle(title)
        .setMessage(message)
        .setPositiveButton(btnText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(status == 1) {
                    Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(i);
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(PERMISSIONS,PERMISSION_ALL);
                    }
                }
            }
        })
        .setNegativeButton(btnText, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

    }
}
