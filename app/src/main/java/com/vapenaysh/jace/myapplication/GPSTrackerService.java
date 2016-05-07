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

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.HashSet;

/*WARNING: THIS CLASS IS COMPLETELY UNTESTED*/
public class GPSTrackerService extends Service implements LocationListener
{

    private ArrayList<FavoriteLocation> favLocations;
    protected LocationManager locManager;
    private Context context;
    private static final long time = 12000;
    private static final float distance = 50;
    private ArrayList<FavoriteLocation> visitedLocations = new ArrayList<FavoriteLocation>();
    @Override
    public int onStartCommand(Intent i, int flags, int startID)
    {
        this.favLocations = ((FavoriteLocationList) i.getParcelableExtra("FavoriteLocations")).getList();
        this.context = getApplicationContext();
        getCurrentLocation();
        return 0;
    }

    public void addFavLocation(FavoriteLocation newFavLocation)
    {
        favLocations.add(newFavLocation);
    }

    public boolean removeFavLocation(FavoriteLocation remFavLocation)
    {
        for (FavoriteLocation i : favLocations)
        {
            if (i.getCoord().equals(remFavLocation.getCoord()))
            {
                favLocations.remove(i);
                return true;
            }
        }
        return false;
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
        LatLng currentLocation = getCurrentLocation();
        for (FavoriteLocation fl : favLocations)
        {
            HashSet<FavoriteLocation> fll = SavedLocations.getLocations();
            for (FavoriteLocation fli: fll)
            {
                if (visitedLocations.indexOf(fli) != -1)
                {
                    if (loc.latitude > fl.getCoord().latitude - 0.01 || loc.latitude < fl.getCoord().latitude + 0.01)
                    {
                        if (loc.longitude > fl.getCoord().longitude - 0.01 || loc.longitude < fl.getCoord().longitude + 0.01)
                        {
                            Log.d("NOTIFICATION", "FOUND FAVORITE LOCATION AT" + loc.toString());
                            //TODO: NOTIFICATION CODE
                            visitedLocations.add(fli);
                        }
                    }
                }
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
}
