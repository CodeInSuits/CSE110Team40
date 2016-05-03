package com.vapenaysh.jace.myapplication;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.io.FileInputStream;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by Matt on 5/3/16.
 */
public class SavedLocations {
    private static HashSet<FavoriteLocation> locations;
    public final static String LOC_FILE_NAME = "CoupleToneLocs.txt";

    public static HashSet<FavoriteLocation> getLocations(){
        if( locations == null ) {
            locations = new HashSet<>();
        }
        return locations;
    }

    public static int getSize(){
        if(locations == null)
            return 0;
        return locations.size();
    }

    public static boolean addLocation(FavoriteLocation loc){
        locations.add(loc);
        return true;
    }


    /** Every time the user starts up the app, populate the array of favorite locations */
    public static void loadLocations(Context c){
        FileInputStream fis;
        try {
            fis = c.openFileInput(LOC_FILE_NAME);
        }catch(Exception e){
            Log.e("SavedLocations", "loadLocations() had exception: " + e.toString());
            return; //return if no file found
        }

        Scanner input = new Scanner(fis);
        while( input.hasNextLine() ){
            translateFavoriteLocation( input.nextLine() );
        }

        try {
            fis.close();
        } catch( Exception e ){
            Log.e("SavedLocations", "loadLocations() had exception: " + e.toString());
        }
    }


    /** Take each string of form "name&latitude&longitude" and create a favorite location,
     *  adding it to the array
     */
    private static void translateFavoriteLocation( String line ){
        String[] parts = line.split("&");
        if( parts.length != 3 ){
            Log.v("SavedLocation", "translateFavoriteLocation() read a"
                    + "line of location with incorrect number of paramaters");
            return;
        }

        double lat = Double.parseDouble(parts[1]);
        double lon = Double.parseDouble(parts[2]);
        FavoriteLocation loc = new FavoriteLocation(new LatLng(lat, lon), parts[0]);
        locations.add(loc);
        Log.v("SavedLocation", "translateFavoriteLocation() read line successfully");
    }
}
