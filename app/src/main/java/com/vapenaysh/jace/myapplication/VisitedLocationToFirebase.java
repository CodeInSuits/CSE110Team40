package com.vapenaysh.jace.myapplication;

import com.firebase.client.Firebase;

/**
 * Created by Matt on 5/24/16.
 */
public class VisitedLocationToFirebase {
    private Firebase firebase;
    private int numLocations;

    public VisitedLocationToFirebase(Firebase firebase){
        this.firebase = firebase;
        //TODO: initialize numLocations to number of locs
    }

    public boolean writeVisitedLocation(FavoriteLocation favoriteLocation){
        numLocations++;
        AddLocationToFirebase.writeLocation(firebase, favoriteLocation, ""+numLocations);
        return true;
    }
}
