package com.vapenaysh.jace.myapplication;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashSet;

/**
 * Created by Matt on 5/24/16.
 */
public class FavoriteLocationList {
    private DatabaseReference db;
    private HashSet<FavoriteLocation> fll;
    private static final String TAG= "LocationStorageMgr";

    /**
     *
     * @param uid or identifier of the partner to get the correct url path in firebase
     */
    public FavoriteLocationList(final String uid){
        FirebaseDatabase locationsDB = FirebaseDatabase.getInstance();
        db = locationsDB.getReference(uid + Constants.LOC_URL);
        //database looking at the url for a given user's locations

        fll = new HashSet<>();

        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                fll = (HashSet<FavoriteLocation>) dataSnapshot.getValue();
                Log.d("NOTE", "GOT LOCATIONS FROM FIREBASE FOR USER" + uid);
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {
                // Failed to read value
                Log.w("ERROR:", "Failed to read value.", firebaseError.toException());
            }
        });

    }

    public boolean writeLocation(FavoriteLocation favoriteLocation){
        fll.add(favoriteLocation);
        db.setValue(fll);
        Log.v(TAG, "Added location " + favoriteLocation + " to firebase");
        return true;
    }

    public HashSet<FavoriteLocation> getLocations(){
        return fll;
    }


    public void removeAllLocations(){
        fll = new HashSet<>();
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
