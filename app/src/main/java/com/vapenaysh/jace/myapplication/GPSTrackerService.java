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
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.HashSet;

/*WARNING: THIS CLASS IS COMPLETELY UNTESTED*/
public class GPSTrackerService extends Service implements LocationListener
{

    private FavoriteLocationList favLocations;
    protected LocationManager locManager;
    private Context context;
    private static final long TIME = 5*60*1000;
    private static final float DISTANCE = 50;
    private HashSet<FavoriteLocation> visitedLocations = new HashSet<>();

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
        setupLocationTracking();
        return 0;
    }

    /**
     *
     * @return location representing the starting location
     */
    public Location setupLocationTracking()
    {
        Location location = null;
        Log.d("NOTE", "ATTEMPTING TO SETUP");
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
                Log.d("ERROR", "ENABLE UR GPS AND NETWORK LOCATION PROVIDERS BUD");
            }
            else
            {

                if (ContextCompat.checkSelfPermission( context, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
                    Log.d("ERROR", "CHECK UR COURSE LOCATION PERMS BRUH");

                if (net)
                {
                    locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, TIME, DISTANCE, this);
                    Log.d("Note", "Network Enabled");
                }

                if (ContextCompat.checkSelfPermission( context, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED)
                    Log.d("ERROR", "CHECK UR FINE LOCATION PERMS BRUH");

                if (gps)
                {
                    locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, TIME, DISTANCE, this);
                    Log.d("Note", "GPS Enabled");
                }

                if (locManager != null) {
                    if (gps)
                        location = locManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                    else if (net)
                        location = locManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                }
            }
        }
        catch (Exception e)
        {
            Log.d("YOU DIED", e.toString());
        }
        return location;
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
        Log.v("GPSTrackerService", "Location changed");
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        //check if user is close to a favorite location
        HashSet<FavoriteLocation> fll = favLocations.getLocations();
        for (FavoriteLocation fli: fll)
        {
            //within .1 miles
            double distanceBetween = calculateDistanceBetween(latLng, fli.getCoord());
            Log.v("TESTING LOCATION" + fli.toString(), "distance between: " + distanceBetween);
            if( distanceBetween < .1 )  {

                Log.d("NOTIFICATION", "FOUND FAVORITE LOCATION AT" + latLng.toString());
                //TODO: NOTIFICATION CODE

                SentSMS msg = new SentSMS();
                msg.sendSms(latLng.toString());

                //not already in the list of visited locations



                visitedLocations.add(fli);
                Toast.makeText(getApplicationContext(), "Visited a favorite location", Toast.LENGTH_SHORT).show();
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
     * https://www.geodatasource.com/developers/java for formula
     * @param loc1
     * @param loc2
     * @return
     */
    public double calculateDistanceBetween(LatLng loc1, LatLng loc2){
        double theta = loc1.longitude - loc2.longitude;
        double dist = Math.sin(deg2rad(loc1.latitude)) * Math.sin(deg2rad(loc2.latitude))
                + Math.cos(deg2rad(loc1.latitude)) * Math.cos(deg2rad(loc2.latitude)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;

        return (dist);
    }


    private static double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }


    private static double rad2deg(double rad) {
        return (rad * 180 / Math.PI);
    }
}


