package com.codekul.weatherapp.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.codekul.weatherapp.App;
import com.codekul.weatherapp.R;
import com.codekul.weatherapp.domain.Main;
import com.codekul.weatherapp.domain.WeatherRes;
import com.codekul.weatherapp.util.Urls;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private static final String TAG = MapsActivity.class.getCanonicalName();
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 1234;
    private static final int MY_PERMISSIONS_REQUEST_LOCATION_UPDATES = 4568;
    private GoogleMap mMap;

    private ArrayList<LatLng> line = new ArrayList<>();

    private LocationManager manager;

    private Location loc;

    private LocationListener listener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            loc = location;
            Log.i(TAG, "Lat - "+loc.getLatitude());
            Log.i(TAG, "Lon - "+loc.getLongitude());

            LatLng coordinate = new LatLng(loc.getLatitude(), loc.getLongitude()); //Store these lat lng values somewhere. These should be constant.
            CameraUpdate locationMap = CameraUpdateFactory.newLatLngZoom(
                    coordinate, 15);
            mMap.addMarker(new MarkerOptions().position(coordinate));
            mMap.animateCamera(locationMap);
        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {

        }

        @Override
        public void onProviderEnabled(String provider) {

        }

        @Override
        public void onProviderDisabled(String provider) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Log.i("@codekul", Urls.BASE + Urls.LAT + 18.72 + Urls.LON + 72.68);

        manager = (LocationManager) getSystemService(LOCATION_SERVICE);

        locationRequests();

        /*Location loc = getMyLocation();
        if(loc != null){
            Log.i(TAG, "Lat - "+loc.getLatitude());
            Log.i(TAG, "Lon - "+loc.getLongitude());
            LatLng coordinate = new LatLng(loc.getLatitude(), loc.getLongitude()); //Store these lat lng values somewhere. These should be constant.
            CameraUpdate location = CameraUpdateFactory.newLatLngZoom(
                    coordinate, 15);
            mMap.animateCamera(location);
        }*/


    }


    private Location getMyLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                AlertDialog.Builder builder =
                        new AlertDialog.Builder(this)
                                .setTitle("Permission")
                                .setMessage("We need to access your location for getting your current location.")
                                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        ActivityCompat.requestPermissions(MapsActivity.this,
                                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                                                MY_PERMISSIONS_REQUEST_LOCATION);
                                    }
                                });

                builder.create().show();

            }
            else {
                ActivityCompat.requestPermissions(MapsActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return null;
        }
        return manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    }

    private void locationRequests() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                AlertDialog.Builder builder =
                        new AlertDialog.Builder(this)
                                .setTitle("Permission")
                                .setMessage("We need to access your location for getting your current location.")
                                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        ActivityCompat.requestPermissions(MapsActivity.this,
                                                new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                                                MY_PERMISSIONS_REQUEST_LOCATION);
                                    }
                                });

                builder.create().show();

            }
            else {
                ActivityCompat.requestPermissions(MapsActivity.this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
            return;
        }
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER,1000l,0.5f, listener);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == MY_PERMISSIONS_REQUEST_LOCATION){
            if(grantResults.length > 0){
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                    Location loc = getMyLocation();
                    Log.i(TAG, "Lat - "+loc.getLatitude());
                    Log.i(TAG, "Lon - "+loc.getLongitude());
                    LatLng coordinate = new LatLng(loc.getLatitude(), loc.getLongitude()); //Store these lat lng values somewhere. These should be constant.
                    CameraUpdate location = CameraUpdateFactory.newLatLngZoom(
                            coordinate, 15);
                    mMap.animateCamera(location);
                }
            }
        }

        if(requestCode == MY_PERMISSIONS_REQUEST_LOCATION_UPDATES){
            if(grantResults.length > 0){
                if(grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {

                    Log.i(TAG, "Lat - "+loc.getLatitude());
                    Log.i(TAG, "Lon - "+loc.getLongitude());
                    LatLng coordinate = new LatLng(loc.getLatitude(), loc.getLongitude()); //Store these lat lng values somewhere. These should be constant.
                    CameraUpdate location = CameraUpdateFactory.newLatLngZoom(
                            coordinate, 15);
                    mMap.animateCamera(location);
                }
            }
        }
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
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                mMap.addMarker(new MarkerOptions().position(latLng));

                line.add(latLng);
                ((App) getApplication()).q()
                        .add(new StringRequest(Urls.BASE + Urls.LAT + latLng.latitude + Urls.LON + latLng.longitude + Urls.UNITS + "metric", new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.i(TAG, "Response is " + response);

                                WeatherRes res = ((App) getApplication()).gson().fromJson(response, WeatherRes.class);
                                ((TextView) findViewById(R.id.textWeather)).setText("Temp is " + res.getMain().getTemp());
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {

                            }
                        }));
            }
        });

        findViewById(R.id.textWeather).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.addPolyline(new PolylineOptions()
                        .width(5)
                        .color(Color.RED)
                        .addAll(line));

                Geocoder coder = new Geocoder(MapsActivity.this);
                try {
                    List<Address> addreses = coder.getFromLocationName("MeLayer Software Solutions LLP", 5);
                    for (Address address : addreses) {
                        Log.i(TAG, "Country - "+address.getCountryName());
                        Log.i(TAG, "Lat - "+address.getLatitude());
                        Log.i(TAG, "Lon - "+address.getLongitude());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
