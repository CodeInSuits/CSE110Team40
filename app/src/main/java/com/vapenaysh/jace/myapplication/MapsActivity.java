package com.vapenaysh.jace.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.FileOutputStream;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleMap.OnMapClickListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener

{

    private GoogleMap mMap;
    private Marker currentMarker;
    private TextView namePrompt;
    private RelativeLayout namePromptLayout;
    private String filename = MainActivity.LOC_FILE_NAME;
    private SearchView sV;
    private Location currentLocation;
    GoogleApiClient mGoogleApiClient;

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

        mGoogleApiClient = LocationAPIHelper.getApiClient(this, this, this);

    }

    /**
     * for the location tracker, from Google API
     */
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    /**
     * for the location tracker, from Google API
     */
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    /**
     * for the location tracker, from Google API
     */
    @Override
    public void onConnected(@Nullable Bundle bundle) {

        //get rid of a security check compiler warning
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }

        //start location updates, uses locationListener
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, LocationRequest.create(),this);

        currentLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if( currentLocation == null ){
            //ucsd for default
            Log.v("MapsActivity", "onConnected() using default starting location");
            setDefaultStartingLocation();
        }

        moveMap();

    }

    /**
     * for the location tracker, from Google API
     */
    @Override
    public void onConnectionSuspended(int i) {
        //fill in
    }

    /**
     * for the location tracker, from Google API
     */
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        //fill in
        Log.e("MapsActivity", "onConnectedFailed()");
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
        Log.v("MapsActivity", "onMapReady()");
        mMap = googleMap;
        mMap.setOnMapClickListener(this);

        /*
        // Add a marker in sd and move the camera
        if(currentLocation == null)
            setDefaultStartingLocation();

        LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));
        mMap.setOnMapClickListener(this);
        */


    }

    @Override
    public void onMapClick(LatLng latLng) {
        if(currentMarker != null)
            currentMarker.remove();
        currentMarker = mMap.addMarker(new MarkerOptions().position(latLng));
        namePromptLayout.setVisibility(View.VISIBLE);
        namePrompt.setText(R.string.add_custom_name);
    }

    public void saveCustomName(View view){
        if(currentMarker == null){
            namePrompt.setText(R.string.pick_loc_first);
        }
        else{
            TextView textView = (TextView)findViewById(R.id.custom_name);
            String name = textView.getText().toString();
            if( name.equals("") ){
                name = "Location" + MainActivity.locations.size();
            }
            namePrompt.setText(name);

            FavoriteLocation fave = new FavoriteLocation(currentMarker.getPosition(), name);
            //add to local file for storage
            try {
                toFile(fave);
            } catch( Exception e ){
                namePrompt.setText(R.string.problem_saving);
            }

            //add to arraylist for current session
            MainActivity.locations.add(fave);

        }
    }

    public void cancelCustomName(View view){
        currentMarker.remove();
        namePromptLayout.setVisibility(View.GONE);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_maps, menu);
        sV = (SearchView) menu.findItem(R.id.action_search_map).getActionView();
        sV.setIconifiedByDefault(true);
        sV.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextChange(String query) {

                //DO WHATEVS
                return true;

            }
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                return true;
            }

        });
        sV.setQueryHint("Search");
        return true;
    }
    private void setupSearchView()
    {
        sV.setIconifiedByDefault(true);
        sV.setOnQueryTextListener(new SearchView.OnQueryTextListener(){
            @Override
            public boolean onQueryTextChange(String query) {

                //DO WHATEVS
                return true;

            }
            @Override
            public boolean onQueryTextSubmit(String query)
            {
                return true;
            }

        });
        sV.setQueryHint("Search");
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


    private void toFile( FavoriteLocation loc ) throws Exception {
        FileOutputStream fos = openFileOutput(filename, Context.MODE_APPEND);
        fos.write(loc.toString().getBytes());
        fos.write("\n".getBytes());
        Log.v("MapsActivity", "toFile() saved a location successfully!");
        fos.close();
    }

    /**
     * make san diego the starting location by default
     */
    private void setDefaultStartingLocation(){
        currentLocation = new Location("default");
        currentLocation.setLatitude(32.7157);
        currentLocation.setLongitude(-117.1611);
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.v("MapsActivity", "onLocationChanged");
        currentLocation = location;
        moveMap();
    }

    /**
     * move map's camera to current location
     */
    private void moveMap(){
        if(currentLocation == null)
            setDefaultStartingLocation();

        LatLng latLng = new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude());
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));
    }
}
