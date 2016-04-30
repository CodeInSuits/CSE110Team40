package com.vapenaysh.jace.myapplication;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by Matt on 4/29/16.
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

    @Override
    public String toString() {
        return name + "&" + coord.latitude + "&" + coord.longitude;
    }
}
