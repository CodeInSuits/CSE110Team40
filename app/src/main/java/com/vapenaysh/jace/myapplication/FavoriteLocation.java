package com.vapenaysh.jace.myapplication;

import com.google.android.gms.maps.model.LatLng;

import java.util.Date;

/**
 * FavoriteLocation.java
 *
 * Class for storing the favorite location information, including LatLng, name
 * Data, vibeTone and ringTone
 *
 * Created by Matt on 4/29/16.
 */
public class FavoriteLocation implements Comparable<FavoriteLocation>{

    private MyLatLng myLatLng;
    private String name;
    private MyDate myDate;
    private int vibeTone = 0;
    private String ringTone = "content://media/internal/audio/media/63";
    private boolean visited = false;

    public FavoriteLocation(){}

    public void setRingTone(String ringTone) {
        this.ringTone = ringTone;
    }

    /**
     * Constructor for FavoriteLocation with parameter LatLng
     * @param coord
     * @param name
     */
    public FavoriteLocation(LatLng coord, String name){
        this.myLatLng = new MyLatLng(coord.latitude, coord.longitude);
        this.name = name;
        this.myDate = new MyDate(new Date(0));
    }

    /**
     * Constructor for FavoriteLocation with parameter date
     * @param coord
     * @param name
     * @param timeStamp
     */
    public FavoriteLocation(LatLng coord, String name, Date timeStamp){
        this.myLatLng = new MyLatLng(coord.latitude, coord.longitude);
        this.name = name;
        this.myDate = new MyDate(timeStamp);
    }


    // getter method for vibe tone
    public int getVibeTone() {
        return vibeTone;
    }

    // setter method for vibe tone
    public void setVibeTone(int vibeTone) {
        this.vibeTone = vibeTone;
    }

    // getter method for ring tone
    public String getRingTone() {
        return ringTone;
    }

    // setter method for LatLng
    public void setMyLatLng(MyLatLng myLatLng) {
        this.myLatLng = myLatLng;
    }

    // getter method for data
    public MyDate getMyDate() {
        return myDate;
    }

    // setter method for data
    public void setMyDate(MyDate myDate) {
        this.myDate = myDate;
    }

    // setter method for vistied
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

    // getter method for name
    public String getName() {
        return name;
    }

    // setter method for name
    public void setName(String name) {
        this.name = name;
    }

    public LatLng coordinate() {
        return new LatLng(myLatLng.getLat(), myLatLng.getLon());
    }

    public MyLatLng getMyLatLng(){
        return myLatLng;
    }

    // methods for updating visited instance variable

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

    /**
     * Method for comparing locations
     * @param another
     * @return
     */
    @Override
    public int compareTo(FavoriteLocation another) {
        return date().compareTo(another.date());
    }
}
