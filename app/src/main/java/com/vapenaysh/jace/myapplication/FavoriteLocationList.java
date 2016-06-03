package com.vapenaysh.jace.myapplication;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by Matt on 5/24/16.
 */
public class FavoriteLocationList extends Observable{
    private DatabaseReference db;
    private ArrayList<FavoriteLocation> fll;
    private static final String TAG= "FavoriteLocationList";


    /**
     *
     * @param uid or identifier of the partner to get the correct url path in firebase
     */
    public FavoriteLocationList(final String uid){
        FirebaseDatabase locationsDB = FirebaseDatabase.getInstance();
        db = locationsDB.getReference(uid + Constants.LOC_URL);
        //database looking at the url for a given user's locations

        fll = new ArrayList<>();

        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Object o = dataSnapshot.getValue();
                if(o instanceof ArrayList) { //error checking
                    GenericTypeIndicator<ArrayList<FavoriteLocation>> t = new GenericTypeIndicator<ArrayList<FavoriteLocation>>() {};
                    fll = dataSnapshot.getValue(t);
                    setChanged();
                    notifyObservers();
                }
                else{
                    fll = new ArrayList<>();
                }
                Log.d("NOTE", "GOT UPDATED LOCATIONS FROM FIREBASE FOR USER " + uid);
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {
                // Failed to read value
                Log.w("ERROR:", "Failed to read value.", firebaseError.toException());
            }
        });

    }

    public boolean writeLocation(FavoriteLocation favoriteLocation){
        int i = fll.indexOf(favoriteLocation);
        if(i != -1){
            fll.set(i, favoriteLocation); //overwrite the previous one with an updated version
        }
        else {
            fll.add(favoriteLocation);
        }

        db.setValue(fll);
        Log.v(TAG, "Added location " + favoriteLocation + " to firebase");
        return true;
    }

    public ArrayList<FavoriteLocation> getLocations(){
        return fll;
    }


    public void removeAllLocations(){
        fll = new ArrayList<>();
        db.setValue(fll);
        Log.v(TAG, "Deleted all locations");
    }

    public boolean removeLocation(FavoriteLocation loc){
        fll.remove(loc);
        db.setValue(fll);
        Log.v(TAG, "Removed location " + loc);
        return true;
    }

    public int getSize(){
        return fll.size();
    }
}
