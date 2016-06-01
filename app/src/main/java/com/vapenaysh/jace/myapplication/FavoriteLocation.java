package com.vapenaysh.jace.myapplication;

import com.google.android.gms.maps.model.LatLng;

import java.util.Date;

/**
 * Created by Matt on 4/29/16.
 * FavoriteLocation class - stores a MyLatLng and a custom name for the location.
 */
public class FavoriteLocation implements Comparable<FavoriteLocation>{

    private MyLatLng myLatLng;
    private String name;
    private MyDate myDate;
    private int vibeTone = 0;
    private String ringTone = "content://media/internal/audio/media/63";
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
        this.myDate = new MyDate(new Date());
    }

    public FavoriteLocation(LatLng coord, String name, Date timeStamp){
        this.myLatLng = new MyLatLng(coord.latitude, coord.longitude);
        this.name = name;
        this.myDate = new MyDate(timeStamp);
    }


    public void setMyLatLng(MyLatLng myLatLng) {
        this.myLatLng = myLatLng;
    }

    public MyDate getMyDate() {
        return myDate;
    }

    public void setMyDate(MyDate myDate) {
        this.myDate = myDate;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Date date()
    {
        return myDate.getDate();
    }

    public void date (Date timeStamp)
    {
        this.myDate = new MyDate(timeStamp);
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
        Date cutoff;
        if(now.getHours() < 3) { //cutoff is day before
            cutoff = new Date(now.getYear(), now.getMonth(), now.getDate() - 1, 3, now.getMinutes());
        }
        else{ //cutoff is today
            cutoff = new Date(now.getYear(), now.getMonth(), now.getDate(), 3, now.getMinutes());
        }

        return date().after(cutoff);

    }

    @Override
    public int compareTo(FavoriteLocation another) {
        return date().compareTo(another.date());
    }
}
