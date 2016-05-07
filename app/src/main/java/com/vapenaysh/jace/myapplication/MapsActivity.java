package com.vapenaysh.jace.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleMap.OnMapClickListener, android.location.LocationListener

{

    private GoogleMap mMap;
    private Marker currentMarker;
    private ArrayList<Marker> searchMarkers = new ArrayList<Marker>();
    private TextView namePrompt;
    private RelativeLayout namePromptLayout;
    private String filename = FavoriteLocationList.LOC_FILE_NAME;
    private SearchView sV;
    private Location currentLocation;
    private LocationManager locationManager;
    private FavoriteLocationList favoriteLocationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
        namePrompt = (TextView) findViewById(R.id.custom_name_prompt);
        namePromptLayout = (RelativeLayout) findViewById(R.id.custom_name_layout);
        namePromptLayout.setVisibility(View.GONE);

        favoriteLocationList = getIntent().getParcelableExtra("FavoriteLocations");
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
        mMap.setOnMapClickListener(this);
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener()
        {
            @Override
            public boolean onMarkerClick(Marker marker)
            {
                currentMarker = marker;
                namePromptLayout.setVisibility(View.VISIBLE);
                namePrompt.setText(R.string.add_custom_name);
                return true;
            }
        });

        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    100);
            return;
        } else if (mMap != null) {
            Log.d("test2", "outs");
            mMap.setMyLocationEnabled(true);

        }

        /*
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, this);
        */

        moveMap(currentLocation);

    }

    @Override
    public void onMapClick(LatLng latLng) {
        setMarkerAt(latLng);
    }

    private void setMarkerAt(LatLng latLng){
        if (currentMarker != null)
            currentMarker.remove();
        if (searchMarkers.size() != 0)
        {
            for (Marker i : searchMarkers)
            {
                i.remove();
            }
        }

        currentMarker = mMap.addMarker(new MarkerOptions().position(latLng));
        namePromptLayout.setVisibility(View.VISIBLE);
        namePrompt.setText(R.string.add_custom_name);
    }

    public void saveCustomName(View view) {
        if (currentMarker == null) {
            namePrompt.setText(R.string.pick_loc_first);
            return;
        } else {
            TextView textView = (TextView) findViewById(R.id.custom_name);
            String name = textView.getText().toString();
            if (name.equals("")) {
                name = "Location" + favoriteLocationList.getSize();
            }

            FavoriteLocation fave = new FavoriteLocation(currentMarker.getPosition(), name);

            //add to local file for storage and current session's location set
            try {
                favoriteLocationList.addLocation(fave, this);
            } catch (Exception e) {
                namePrompt.setText(R.string.problem_saving);
                return;
            }

            //close name window
            namePrompt.setText(R.string.saved_successfully);
            namePromptLayout.setVisibility(View.GONE);
            currentMarker.remove();


        }
    }

    public void cancelCustomName(View view) {
        currentMarker.remove();
        namePromptLayout.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_maps, menu);
        sV = (SearchView) menu.findItem(R.id.action_search_map).getActionView();
        sV.setIconifiedByDefault(true);
        sV.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextChange(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextSubmit(String query)
            {
                Log.d("USER SEARCHED", query);
                Geocoder geoCoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                ArrayList<LatLng> points = new ArrayList<>();
                try
                {
                    List<Address> addresses = geoCoder.getFromLocationName(query, 5);
                    for (Address i : addresses)
                    {
                        points.add(new LatLng(i.getLatitude(), i.getLongitude()));
                    }

                    if(points.size() > 0){
                        LatLng latLng = points.get(0);
                        Location loc = new Location("name");
                        loc.setLatitude(latLng.latitude);
                        loc.setLongitude(latLng.longitude);
                        moveMap(loc);

                        //only one search result, add flag and go to custom name options
                        if(points.size() == 1){
                            setMarkerAt(latLng);
                        }
                        else {
                            namePromptLayout.setVisibility(View.GONE);
                            namePrompt.setText("Multiple search results");
                            for (LatLng l : points) {
                                searchMarkers.add(mMap.addMarker(new MarkerOptions().position(l)));
                            }
                        }
                    }


                } catch (IOException e)
                {
                    e.printStackTrace();
                }

                return true;
            }

        });
        sV.setQueryHint("Search");
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_close_map:
                finish();
                return true;
            case R.id.action_search_map:
                EditText editText = new EditText(this);
                getActionBar().setCustomView(editText);
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    /**
     * make san diego the starting location by default
     */
    private Location getDefaultStartingLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return null;
        }
        Location loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        Location networkLoc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        if(loc == null && networkLoc != null){
            loc = networkLoc;
        }
        else if( networkLoc != null && networkLoc.getTime() > loc.getTime() ){
            //network provider has a more recent location
            loc = networkLoc;
        }
        else {
            loc = new Location("default");
            loc.setLatitude(32.7157);
            loc.setLongitude(-117.1611);
        }
        return loc;
    }


    /**
     * move map's camera to current location
     */
    private void moveMap(Location loc){
        if(loc == null)
            loc = getDefaultStartingLocation();

        LatLng latLng = new LatLng(loc.getLatitude(), loc.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));
    }

    /**
     * for location listener interface
     * @param location
     */
    @Override
    public void onLocationChanged(Location location) {
        /*
        currentLocation = location;
        moveMap(currentLocation);
        */
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {
        /*
        new AlertDialog.Builder(getBaseContext())
                .setTitle("Error")
                .setMessage("GPS Must be enabled for this application to function.")
                .setPositiveButton("AIGHT FAM", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();*/
    }
}
