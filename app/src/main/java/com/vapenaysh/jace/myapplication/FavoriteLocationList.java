package com.vapenaysh.jace.myapplication;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by Jerry on 5/5/16.
 * WARNING: THIS CLASS IS COMPLETELY UNTESTED
 */
public class FavoriteLocationList implements Parcelable
{
    ArrayList<FavoriteLocation> locations;
    @Override
    public int describeContents() {
        return 0;
    }
    public FavoriteLocationList(Parcel in)
    {
        locations = new ArrayList<FavoriteLocation>();
        while (in.dataAvail() > 0)
        {
            locations.add(new FavoriteLocation(new LatLng(in.readDouble(), in.readDouble()), in.readString()));
        }
    }
    public FavoriteLocationList(ArrayList<FavoriteLocation> in)
    {
        for (FavoriteLocation fl : in)
            locations.add(fl);
    }
    public FavoriteLocationList()
    {
        locations = new ArrayList<FavoriteLocation>();
    }
    public ArrayList<FavoriteLocation> getList()
    {
        return locations;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags)
    {
        for (FavoriteLocation l : locations)
        {
            dest.writeDouble(l.getCoord().latitude);
            dest.writeDouble(l.getCoord().longitude);
            dest.writeString(l.getName());
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

}
