package com.vapenaysh.jace.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;

/**
 * VibeToneManager.java
 *
 * Class for managing the vibeTone information and playing vibeTone.
 *
 * Created by XuanpeiEstherOuyang on 5/29/16.
 */
public class VibeToneManager extends AppCompatActivity {

    protected Vibrator vibrate;

    // vibeTone Database
    private long[] vibeTone1 = { 0, 300, 300 , 1000, 1000, 1000, 300, 300};
    private long[] vibeTone2 = { 0, 100, 400, 100, 1000 };
    private long[] vibeTone3 = { 0, 100, 400, 100, 400, 100, 500 };
    private long[] vibeTone4 = { 0, 100, 400, 100, 400, 100, 400, 100, 1000};
    private long[] vibeTone5 = { 0, 100, 400, 100, 400, 100, 400, 100, 400, 100 };
    private long[] vibeTone6 = { 0, 100, 400, 100, 400, 100, 400, 100, 400, 100, 400, 100 };
    private long[] vibeTone7 = { 0, 100, 400, 100, 400, 100 ,400, 100, 400, 100, 400, 100, 400, 100 };
    private long[] vibeTone8 = { 0, 500, 500, 500, 500, 500 };
    private long[] vibeTone9 = { 0, 100, 100, 100, 100, 110 };
    private long[] vibeTone10 = { 0, 100, 500, 100, 500, 500, 500, 500, 500 };
    private long[] arrivalTone = {0, 1000,};
    private long[] departureTone = {0, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100};
    private long[][] vibeToneArray = {vibeTone1, vibeTone2, vibeTone3, vibeTone4, vibeTone5, vibeTone6,
            vibeTone7, vibeTone8, vibeTone9, vibeTone10};


    // default notification mode is both sound and vibration tone
    private int notificationMode = 1;
    FirebaseDatabase locationsDB = FirebaseDatabase.getInstance();
    private ArrayList<FavoriteLocation> flls = new ArrayList<FavoriteLocation>();
    private Context context;

    /**
     * Constructor for creating VibeToneManager
     */
    public VibeToneManager(Vibrator vib) {
        vibrate = vib;
    }

    /**
     * Constructor for creating VibeToneManager
     */
    public VibeToneManager(Vibrator vib, Context context ){
        this.context = context;
        vibrate = vib;
    }

    /**
     * Method for playing the corresponding vibe tone
     */
    public void playTone(FavoriteLocation loc){

        if (vibrate != null) {
            if (playMode()) {

                int vibeToneindex = loc.getVibeTone();

                //vibeToneindex = getVibeToneFromFirebase(loc.getName());
                vibrate.vibrate(vibeToneArray[vibeToneindex], -1);
                Log.v("VibeToneManager", "Played location VibeTone");

            }
        }
    }

    /**
     * Method for playing the arrival vibe tone
     */
    public void playArrivalTone(){

        if (vibrate != null){
            if (playMode()) {

                vibrate.vibrate(arrivalTone, -1);
                Log.v("VibeToneManager", "Played Arrival VibeTone");
            }
        }
    }

    /**
     * Method for playing the departure vibe tone
     */
    public void playDepartureTone(){

        if (vibrate != null){
            if (playMode()) {

                vibrate.vibrate(departureTone, -1);
                Log.v("VibeToneManager", "Played Departure VibeTone");

            }
        }
    }

    /**
     * Method for checking if the current mode allows playing vibe tone
     */
    private boolean playMode() {

        SharedPreferences sharedPreferences = context.getSharedPreferences("notif_mode", MODE_PRIVATE);
        this.notificationMode = Integer.parseInt(sharedPreferences.getString("mode", "1"));

        if (notificationMode == 1 || notificationMode == 3) {
            return true;
        } else {
            return false;
        }
    }
}
