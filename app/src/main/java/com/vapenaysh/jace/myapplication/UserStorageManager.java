package com.vapenaysh.jace.myapplication;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Map;

/**
 * Created by Jace on 5/23/16.
 */
public class UserStorageManager {

    // Write a message to the database
    public FirebaseDatabase database = FirebaseDatabase.getInstance();
    public DatabaseReference myRef = database.getReference("location");
    public void store(Map<String, String> info, String user){
        myRef.child("users").child(user).setValue(info);
    }



}
