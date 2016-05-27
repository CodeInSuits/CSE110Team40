package com.vapenaysh.jace.myapplication;

/**
 * Created by Matt on 5/26/16.
 */
public class MyLatLng {
    private double lat;
    private double lon;

    public MyLatLng(){}

    public MyLatLng(double lat, double longi){
        this.lat = lat;
        this.lon = longi;
    }

    public double getLat(){
        return lat;
    }

    public double getLon(){
        return lon;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MyLatLng myLatLng = (MyLatLng) o;

        if (Double.compare(myLatLng.lat, lat) != 0) return false;
        return Double.compare(myLatLng.lon, lon) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(lat);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lon);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public void setLatLng(double lat, double lon){
        this.lat = lat;
        this.lon = lon;
    }

    public void setLat(double l){
        lat = l;
    }

    public void setLon(double l){
        lon = l;
    }

}
