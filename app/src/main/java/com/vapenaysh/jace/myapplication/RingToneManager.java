package com.vapenaysh.jace.myapplication;

import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.media.Ringtone;
import android.media.RingtoneManager;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;


/**
 * Created by XuanpeiEstherOuyang on 5/29/16.
 */
public class RingToneManager extends AppCompatActivity {


    // default notification mode is both sound and vibration tone
    private int notificationMode = 1;
    private Uri arrivalRingTone = Uri.parse("content://media/internal/audio/media/61");
    private Uri departureRingTone = Uri.parse("content://media/internal/audio/media/62");

    FirebaseDatabase locationsDB = FirebaseDatabase.getInstance();
    FavoriteLocationAdapter fla;
    private ArrayList<FavoriteLocation> flls = new ArrayList<FavoriteLocation>();


    public RingToneManager() {

        SharedPreferences sharedPreferences = getSharedPreferences("notif_mode", MODE_PRIVATE);
        this.notificationMode = Integer.parseInt(sharedPreferences.getString("mode", "1"));
    }

    public void playTone(FavoriteLocation loc){

        if(playMode(this.notificationMode)) {
            String stringURI = getRingToneFromFirebase(loc.getName());
            Uri uri = Uri.parse(stringURI);
            Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), uri);
            r.play();
        }
    }

    private boolean playMode(int mode){
        if (mode == 1 || mode == 2){
            return true;
        }
        else {
            return false;
        }
    }

    public void playDepartureTone(){
        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), this.departureRingTone);
        r.play();

    }

    public void playArrivalTone(){
        Ringtone r = RingtoneManager.getRingtone(getApplicationContext(), this.arrivalRingTone);
        r.play();
    }

    public String getRingToneFromFirebase(String locName) {
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
                    fla.notifyDataSetChanged();
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
            return currentLoc.getRingTone();
        }
        else {
            return "";
        }
    }

}
