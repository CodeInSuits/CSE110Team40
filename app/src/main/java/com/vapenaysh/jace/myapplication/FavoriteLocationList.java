package com.vapenaysh.jace.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by Jerry on 5/5/16.
 * Parcelable class containing a list of favorite locations which can be passed around in Intents
 * as well as write out to a file.
 */
public class FavoriteLocationList extends Activity implements Parcelable
{
    private static HashSet<FavoriteLocation> locations;
    public final static String LOC_FILE_NAME = Constants.LOC_FILE_NAME;
    private final boolean NEW_FILE = true;

    public FavoriteLocationList(Parcel in)
    {
        locations = new HashSet<>();
        while (in.dataAvail() > 0)
        {
            //Data is stored latitude, longitude, and name
            locations.add(new FavoriteLocation(new LatLng(in.readDouble(), in.readDouble()), in.readString()));
        }
    }

    //default constructor create empty hashset
    public FavoriteLocationList()
    {
        locations = new HashSet<>();
    }

    //constructor that also loads previous locations
    public FavoriteLocationList(Context c){
        locations = FavoriteLocationList.loadLocations(c);
    }


    public HashSet<FavoriteLocation> getLocations()
    {
        return locations;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        if(locations != null){
            for (FavoriteLocation l : locations)
            {
                dest.writeDouble(l.getCoord().latitude);
                dest.writeDouble(l.getCoord().longitude);
                dest.writeString(l.getName());
            }
        }
    }

    public static final Parcelable.Creator<FavoriteLocationList> CREATOR
            = new Parcelable.Creator<FavoriteLocationList>() {
        public FavoriteLocationList createFromParcel(Parcel in) {
            return new FavoriteLocationList(in);
        }

        public FavoriteLocationList[] newArray(int size) {
            return new FavoriteLocationList[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public int getSize(){
        return locations.size();
    }

    //Wrapper for the HashSet to add a new location
    public boolean addLocation(FavoriteLocation loc, Context c){
        boolean added = locations.add(loc);
        if(added){
            locationToFile(loc, FavoriteLocationList.LOC_FILE_NAME, c, false);
        }
        return added;
    }

    public void removeAllLocations(Context c){
        c.deleteFile(LOC_FILE_NAME);
        locations = new HashSet<>();
        Log.v("FavoriteLocationList", "Deleted locations file");

    }

    public boolean removeLocation(FavoriteLocation loc, Context c){
        boolean removed = locations.remove(loc);
        if(removed){
            writeLocations(locations, c);
        }
        return removed;
    }

    //write all the locations to a new file i.e. rewriting the file after a remove
    private void writeLocations(Iterable<FavoriteLocation> list, Context c){
        for(FavoriteLocation loc : list){
            locationToFile(loc, FavoriteLocationList.LOC_FILE_NAME, c, NEW_FILE);
        }
    }

    private void locationToFile(FavoriteLocation loc, String filename, Context c, boolean newfile) {
        try {
            if(newfile){
                c.deleteFile(filename);
            }

            FileOutputStream fos = c.openFileOutput(filename, Context.MODE_APPEND);
            fos.write(loc.toString().getBytes());
            fos.write("\n".getBytes());
            Log.v("FavoriteLocationList", "locationToFile() saved " + loc.getName() + " successfully!");
            fos.close();
        }catch(Exception e){
            Toast.makeText(c, "Problems saving " + loc.getName(), Toast.LENGTH_SHORT).show();
            Log.e("FavoriteLocationList", "locationToFile() had errors saving.");

        }
    }

    /** Every time the user starts up the app, populate the array of favorite locations */
    public static HashSet<FavoriteLocation> loadLocations(Context c) {
        FileInputStream fis;
        HashSet<FavoriteLocation> set = new HashSet<>();

        try {
            fis = c.openFileInput(LOC_FILE_NAME);
        } catch (Exception e) {
            Log.d("FavoriteLocationList", "loadLocations() had opening exception: " + e.toString());
            return null; //return if no file found
        }

        Scanner input = new Scanner(fis);
        while (input.hasNextLine()) {
            translateFavoriteLocation(input.nextLine(), set);
        }

        try {
            fis.close();
        } catch (Exception e) {
            Log.d("FavoriteLocationList", "loadLocations() had closing exception: " + e.toString());
        }

        return set;
    }

    /** Take each string of form "name&latitude&longitude" and create a favorite location,
     *  adding it to the array
     */
    private static void translateFavoriteLocation( String line, HashSet<FavoriteLocation> set ){
        String[] parts = line.split("&");
        if( parts.length != 3 ){
            Log.v("FavoriteLocationList", "translateFavoriteLocation() read a "
                    + "line of location with incorrect number of parameters");
            return;
        }

        double lat = Double.parseDouble(parts[1]);
        double lon = Double.parseDouble(parts[2]);
        FavoriteLocation loc = new FavoriteLocation(new LatLng(lat, lon), parts[0]);
        set.add(loc);
        Log.v("FavoriteLocationList", "translateFavoriteLocation() read " + loc.getName() + " successfully");
    }
}


