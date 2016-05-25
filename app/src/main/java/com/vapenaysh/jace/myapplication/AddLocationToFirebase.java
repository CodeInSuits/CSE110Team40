package com.vapenaysh.jace.myapplication;

import com.firebase.client.Firebase;

/**
 * Created by Matt on 5/24/16.
 */
public class AddLocationToFirebase {

    /**
     *
     * @param firebase the database url to write to
     * @param favoriteLocation the location to save
     * @param id the id of the child i.e. the number of the location in the list
     * @return
     */
    public static boolean writeLocation(Firebase firebase, FavoriteLocation favoriteLocation, String id){
        firebase.child(id).setValue(favoriteLocation);
        return true;
    }
}
