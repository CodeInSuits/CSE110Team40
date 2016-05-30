package com.vapenaysh.jace.myapplication;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by Jace on 5/26/16.
 */
public class FirebaseDataStorageManager {
    private static FirebaseDatabase database = FirebaseDatabase.getInstance();

    public static void storeData(String reference, String[] childPath, Object data){

        DatabaseReference myRef = database.getReference(reference);
        for(int i = 0; i < childPath.length; i++){
            myRef = myRef.child(childPath[i]);
        }
        myRef.setValue(data);

    }


}
