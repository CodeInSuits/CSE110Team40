package com.vapenaysh.jace.myapplication;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.io.FileDescriptor;
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
    private ArrayList<FavoriteLocation> visitedLocations = new ArrayList<FavoriteLocation>();
    private final IBinder GPSBinder = new LocalBinder();

    public class LocalBinder extends Binder
    {
        GPSTrackerService getService()
        {
            return GPSTrackerService.this;
        }
    }

    @Override
    public int onStartCommand(Intent i, int flags, int startID)
    {
        this.favLocations = i.getParcelableExtra("FavoriteLocations");
        this.context = getApplicationContext();
        getCurrentLocation();
        return 0;
    }

    public void addFavLocation(FavoriteLocation newFavLocation)
    {
        favLocations.addLocation(newFavLocation, this);
    }

    public boolean removeFavLocation(FavoriteLocation remFavLocation)
    {
        for (FavoriteLocation i : favLocations.getLocations())
        {
            if (i.equals(remFavLocation))
            {
                favLocations.removeLocation(i, this);
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
        return GPSBinder;
        //throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onLocationChanged(Location location)
    {
        LatLng loc = getCurrentLocation();

        //check if user is close to a favorite location
        HashSet<FavoriteLocation> fll = favLocations.getLocations();
        for (FavoriteLocation fli: fll)
        {
            if (LocationInRange(loc, fli))
            {
                if (visitedLocations.indexOf(fli) == -1)
                {
                    visitedLocations.add(fli);
                    Log.d("NOTIFICATION", "FOUND FAVORITE LOCATION AT" + loc.toString());
                    //TODO: NOTIFICATION CODE
                    //SentSMS sentSMS = new SentSMS();
                    //String msg = sentSMS.sendSms(loc.toString());
                    //not already in the list of visited locations
                    if (visitedLocations.indexOf(fli) == -1)
                    {
                        visitedLocations.add(fli);
                    }
                }
            }
        }
    }

    public boolean LocationInRange(LatLng loc, FavoriteLocation fli)
    {
        if (loc.latitude > fli.getCoord().latitude - 0.01 || loc.latitude < fli.getCoord().latitude + 0.01)
        {
            if (loc.longitude > fli.getCoord().longitude - 0.01 || loc.longitude < fli.getCoord().longitude + 0.01)
            {
                return true;
            }
        }
        return false;
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


