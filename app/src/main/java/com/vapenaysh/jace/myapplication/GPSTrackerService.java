package com.vapenaysh.jace.myapplication;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.HashSet;

/*WARNING: THIS CLASS IS COMPLETELY UNTESTED*/
public class GPSTrackerService extends Service implements LocationListener
{

    private FavoriteLocationList favLocations;
    protected LocationManager locManager;
    private Context context;
    private static final long time = 12000;
    private static final float distance = 50;
    private HashSet<FavoriteLocation> visitedLocations = new HashSet<>();
    @Override
    public int onStartCommand(Intent i, int flags, int startID)
    {
        this.favLocations = i.getParcelableExtra("FavoriteLocations");
        this.context = getApplicationContext();
        getCurrentLocation();
        return 0;
    }

    public LatLng getCurrentLocation()
    {
        Log.d("NOTE", "ATTEMPTING TO GET LOCATION");
        LatLng latlng = null;
        try
        {
            boolean net;
            boolean gps;
            double latitude;
            double longitude;
            locManager = (LocationManager) context.getSystemService(LOCATION_SERVICE);
            gps = locManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            net = locManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

            if (!net  && !gps)
            {
                Log.d("ERROR", "ENABLE UR STUFF BUD");
            }
            else
            {

                if (ContextCompat.checkSelfPermission( context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                    Log.d("ERROR", "CHECK UR PERMS BRUH");
                if (net)
                {
                    locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, time, distance, this);
                    Log.d("Note", "Network Enabled");
                }
                if (ContextCompat.checkSelfPermission( context, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED)
                    Log.d("ERROR", "CHECK UR PERMS BRUH");
                if (gps)
                {
                    locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, time, distance, this);
                    Log.d("Note", "GPS Enabled");
                }
                if (locManager != null) {
                    Location location = null;
                    if (gps)
                        location = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    else if (net)
                        location = locManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                    if (location != null) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        Log.d("LOCATION FOUND", latitude + " " + longitude);
                        latlng = new LatLng(latitude, longitude);
                    }
                }
            }
        }
        catch (Exception e)
        {
            Log.d("YOU DIED", e.toString());
        }
        return latlng;
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onLocationChanged(Location location)
    {
        LatLng loc = getCurrentLocation();

        //check if user is close to a favorite location
        HashSet<FavoriteLocation> fll = favLocations.getLocations();
        for (FavoriteLocation fli: fll)
        {
            //within .1 miles
            if( calculateDistanceBetween(loc, fli.getCoord()) < .1 )  {
                Log.d("NOTIFICATION", "FOUND FAVORITE LOCATION AT" + loc.toString());
                //TODO: NOTIFICATION CODE
                //com.vapenaysh.jace.myapplication.SentSMS.sendSms(loc.toString());
                //not already in the list of visited locations
                visitedLocations.add(fli);
                Toast.makeText(getApplicationContext(), "Visited a favorite location", Toast.LENGTH_SHORT);
            }
            else{
                visitedLocations.remove(fli); //not within range anymore
            }
        }

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

    /**
     * http://andrew.hedges.name/experiments/haversine/ for formula
     * @param loc1
     * @param loc2
     * @return
     */
    private double calculateDistanceBetween(LatLng loc1, LatLng loc2){
        int EARTH_RADIUS = 3961;
        double dlon = loc2.latitude - loc1.latitude;
        double dlat = loc2.longitude - loc1.longitude;
        double a = Math.pow(Math.sin(dlat/2), 2);
        a += Math.cos(loc1.latitude) * Math.cos(loc2.latitude) * Math.pow(Math.sin(dlon/2), 2);
        double c = 2 * Math.atan2( Math.sqrt(a), Math.sqrt(1-a) );
        return EARTH_RADIUS * c;
    }
}


