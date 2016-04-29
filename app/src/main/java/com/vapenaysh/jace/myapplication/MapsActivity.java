package com.vapenaysh.jace.myapplication;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
                                                    GoogleMap.OnMapClickListener {

    private GoogleMap mMap;
    private Marker currentMarker;
    private TextView namePrompt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        namePrompt = (TextView) findViewById(R.id.custom_name_prompt);

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

        // Add a marker in sd and move the camera
        LatLng ucsd = new LatLng(32.7157, -117.1611);
        //mMap.addMarker(new MarkerOptions().position(ucsd).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ucsd, 13));
        mMap.setOnMapClickListener(this);


    }

    @Override
    public void onMapClick(LatLng latLng) {
        if(currentMarker != null)
            currentMarker.remove();
        currentMarker = mMap.addMarker(new MarkerOptions().position(latLng));
        namePrompt.setText("(Optional)Add a custom name!");
    }

    public void saveCustomName(View view){
        if(currentMarker == null){
            namePrompt.setText("Must pick a location first");
        }
        else{
            TextView textView = (TextView)findViewById(R.id.custom_name);
            String name = textView.getText().toString();
            if( name.equals("") ){
                name = "Location1";
            }
            namePrompt.setText(name);

        }
    }
}
