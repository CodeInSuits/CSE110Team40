package com.vapenaysh.jace.myapplication;

import com.google.android.gms.maps.model.LatLng;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Matt on 4/29/16.
 * FavoriteLocation class - stores a MyLatLng and a custom name for the location.
 */
public class FavoriteLocation {

    private MyLatLng myLatLng;
    private String name;
    private Date date;
    private int vibeTone = 0;
    private String ringTone;
    private boolean visited = false;

    public FavoriteLocation(){}

    public int getVibeTone() {
        return vibeTone;
    }

    public void setVibeTone(int vibeTone) {
        this.vibeTone = vibeTone;
    }

    public String getRingTone() {
        return ringTone;
    }

    public void setRingTone(String ringTone) {
        this.ringTone = ringTone;
    }

    public FavoriteLocation(LatLng coord, String name){
        this.myLatLng = new MyLatLng(coord.latitude, coord.longitude);
        this.name = name;
    }

    public FavoriteLocation(LatLng coord, String name, Date timeStamp){
        this.myLatLng = new MyLatLng(coord.latitude, coord.longitude);
        this.name = name;
        this.date = timeStamp;
    }


    public Date getDate()
    {
        return date;
    }

    public void setDate (Date timeStamp)
    {
        this.date = timeStamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LatLng coordinate() {
        return new LatLng(myLatLng.getLat(), myLatLng.getLon());
    }

    public MyLatLng getMyLatLng(){
        return myLatLng;
    }

    public void setLat(double l){
        myLatLng.setLat(l);
    }

    public void setLon(double l){
        myLatLng.setLon(l);
    }

    public void setVisited(){
        visited = true;
    }

    public void clearVisited(){
        visited = false;
    }

    public boolean isVisited(){
        return visited;
    }

    //Equals comparator for direct comparison.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FavoriteLocation that = (FavoriteLocation) o;
        if (myLatLng != null && !myLatLng.equals(that.myLatLng)) return false;

        return name != null && name.equals(that.name);

    }

    @Override
    public String toString() {
        return name + "&" + myLatLng.getLat() + "&" + myLatLng.getLon();
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    /**
     *     @return true if Date represents time after the 3am cutoff time
     */
    public boolean afterCutoffTime() {
        Date now = new Date();
        Calendar cal = new Calendar() {
            @Override
            public void add(int i, int i1) {

            }

            @Override
            protected void computeFields() {

            }

            @Override
            protected void computeTime() {

            }

            @Override
            public int getGreatestMinimum(int i) {
                return 0;
            }

            @Override
            public int getLeastMaximum(int i) {
                return 0;
            }

            @Override
            public int getMaximum(int i) {
                return 0;
            }

            @Override
            public int getMinimum(int i) {
                return 0;
            }

            @Override
            public void roll(int i, boolean b) {

            }
        };
        cal.setTime(now);
        if(cal.get(Calendar.HOUR_OF_DAY) < 3) { //between 12am and 3am so should not reset
            return false;
        }

        cal.set(Calendar.HOUR_OF_DAY, 3);
        Date cutoff = cal.getTime();

        return getDate().after(cutoff);


    }
}
