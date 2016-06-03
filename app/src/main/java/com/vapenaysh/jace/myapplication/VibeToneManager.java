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
 * Created by XuanpeiEstherOuyang on 5/29/16.
 */
public class VibeToneManager extends AppCompatActivity {

    protected Vibrator vibrate;

    private long[] vibeTone1 = { 0, 10000 };
    private long[] vibeTone2 = { 0, 100, 400, 100, 1000 };
    private long[] vibeTone3 = { 0, 100, 400, 100, 400, 100, 500 };
    private long[] vibeTone4 = { 0, 100, 400, 100, 400, 100, 400, 100, 1000};
    private long[] vibeTone5 = { 0, 100, 400, 100, 400, 100, 400, 100, 400, 100 };
    private long[] vibeTone6 = { 0, 100, 400, 100, 400, 100, 400, 100, 400, 100, 400, 100 };
    private long[] vibeTone7 = { 0, 100, 400, 100, 400, 100 ,400, 100, 400, 100, 400, 100, 400, 100 };
    private long[] vibeTone8 = { 0, 500, 500, 500, 500, 500 };
    private long[] vibeTone9 = { 0, 100, 100, 100, 100, 110 };
    private long[] vibeTone10 = { 0, 100, 500, 100, 500, 500, 500, 500, 500 };

    private long[] arrivalTone = {0, 1000, 1000, 1000, 1000, 1000};
    private long[] departureTone = {0, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100};

    private long[][] vibeToneArray = {vibeTone1, vibeTone2, vibeTone3, vibeTone4, vibeTone5, vibeTone6,
            vibeTone7, vibeTone8, vibeTone9, vibeTone10};

    // default notification mode is both sound and vibration tone
    private int notificationMode = 1;
    FirebaseDatabase locationsDB = FirebaseDatabase.getInstance();
    private ArrayList<FavoriteLocation> flls = new ArrayList<FavoriteLocation>();
    private Context context;

    public VibeToneManager(Vibrator vib) {

        vibrate = vib;
    }

    public VibeToneManager(Vibrator vib, Context context ){
        this.context = context;
        vibrate = vib;
    }

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

    public void playArrivalTone(){

        if (vibrate != null){
            if (playMode()) {

                vibrate.vibrate(arrivalTone, -1);
                Log.v("VibeToneManager", "Played Arrival VibeTone");
            }
        }
    }


    public void playDepartureTone(){

        if (vibrate != null){
            if (playMode()) {

                vibrate.vibrate(departureTone, -1);
                Log.v("VibeToneManager", "Played Departure VibeTone");

            }
        }
    }


    private boolean playMode(){

        SharedPreferences sharedPreferences = context.getSharedPreferences("notif_mode", MODE_PRIVATE);
        this.notificationMode = Integer.parseInt(sharedPreferences.getString("mode", "1"));

        if (notificationMode == 1 || notificationMode == 3){
            return true;
        }
        else {
            return false;
        }
    }

    /*
    private int getVibeToneFromFirebase(String locName ) {

        PartnerFavoriteLocation loc = new PartnerFavoriteLocation();
        String uid = loc.getPartnerEmail();
        DatabaseReference db = locationsDB.getReference(uid + Constants.LOC_URL);
        db.addListenerForSingleValueEvent(new com.google.firebase.database.ValueEventListener() {

            @Override
            public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                GenericTypeIndicator<ArrayList<FavoriteLocation>> t = new GenericTypeIndicator<ArrayList<FavoriteLocation>>() {
                };
                ArrayList<FavoriteLocation> data = dataSnapshot.getValue(t);

                flls.clear();
                if (data != null) {
                    for (FavoriteLocation i : data) {
                        flls.add(i);
                    }
                   // fla.notifyDataSetChanged();

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        FavoriteLocationList tmp = new FavoriteLocationList(uid);
        FavoriteLocation currentLoc = null;

        for (FavoriteLocation i : flls) {
            if (i.getName().equals(locName)) {
                currentLoc = i;
                break;
            }
        }

        if (currentLoc != null) {
            return currentLoc.getVibeTone();
        }
        else {
            return 0;
        }
    }
    */
}
