package com.vapenaysh.jace.myapplication;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Matt on 4/29/16.
 * FavoriteLocation class - stores a LatLng and a custom name for the location.
 */
public class FavoriteLocation {

    private LatLng coord;
    private String name;

    public FavoriteLocation(LatLng latLng, String name){
        coord = latLng;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LatLng getCoord() {
        return coord;
    }

    //Equals comparator for direct comparison.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FavoriteLocation that = (FavoriteLocation) o;
        Log.v("EQUALS NAME", "" + this.getName().equals(that.getName()));

        if (coord != null && !coord.equals(that.coord)) return false;

        return name != null && name.equals(that.name);

    }

    @Override
    public String toString() {
        return name + "&" + coord.latitude + "&" + coord.longitude;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}
