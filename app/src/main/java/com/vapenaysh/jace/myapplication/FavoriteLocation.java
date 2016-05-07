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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FavoriteLocation that = (FavoriteLocation) o;

        if (coord != null ? !coord.equals(that.coord) : that.coord != null) return false;
        return name != null ? name.equals(that.name) : that.name == null;

    }

    @Override
    public String toString() {
        return name + "&" + coord.latitude + "&" + coord.longitude;
    }
}
