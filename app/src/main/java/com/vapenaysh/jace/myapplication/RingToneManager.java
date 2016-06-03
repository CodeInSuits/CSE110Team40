package com.vapenaysh.jace.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.util.Log;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;

import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by XuanpeiEstherOuyang on 5/29/16.
 */
public class RingToneManager extends AppCompatActivity {


    // default notification mode is both sound and vibration tone
    private int notificationMode = 1;
    private Uri arrivalRingTone = Uri.parse("content://media/internal/audio/media/61");
    private Uri departureRingTone = Uri.parse("content://media/internal/audio/media/62");

    FirebaseDatabase locationsDB = FirebaseDatabase.getInstance();
    private ArrayList<FavoriteLocation> flls = new ArrayList<FavoriteLocation>();
    private Context context;
    private long ringDelay = 3000;

    public RingToneManager(Context context) {
        this.context = context;
    }

    public void playTone(FavoriteLocation loc){

        Log.e("play.in", "inside the method playTone");
        Log.e("play.mode", playMode()+"");

        if(playMode()) {
            //String stringURI = getRingToneFromFirebase(loc.getName());

            String stringURI = loc.getRingTone();

            if (!stringURI.equals("N/A")) {

                Log.e("play.uri", stringURI);
            }

            Uri uri = Uri.parse(stringURI);
            Log.e("play.in", "inside the if statement");
            Log.e("play.uri", stringURI);

            final Ringtone r = RingtoneManager.getRingtone(context, uri);
            r.play();

            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    r.stop();
                }
            };

            Thread thread = new Thread(new MyThread());
            thread.start();

            Timer timer = new Timer();
            timer.schedule(task, ringDelay);
        }
    }

    private boolean playMode(){

        SharedPreferences sharedPreferences = context.getSharedPreferences("notif_mode", MODE_PRIVATE);
        this.notificationMode = Integer.parseInt(sharedPreferences.getString("mode", "1"));

        if (notificationMode == 1 || notificationMode == 2){
            return true;
        }
        else {
            return false;
        }
    }

    public void playDepartureTone() {

        if (playMode()) {
            final Ringtone r = RingtoneManager.getRingtone(context, this.departureRingTone);
            r.play();

            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    r.stop();
                }
            };
            Timer timer = new Timer();
            timer.schedule(task, ringDelay);
        }
    }

    public void playArrivalTone() {

        if (playMode()) {
            final Ringtone r = RingtoneManager.getRingtone(context, this.arrivalRingTone);
            r.play();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    r.stop();
                }
            };
            Timer timer = new Timer();
            timer.schedule(task, ringDelay);
        }
    }

    /*
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
            return "N/A";
        }
    }
    */


    final class MyThread implements Runnable
    {

        public MyThread(){ }

        @Override
        public void run() {

            synchronized (this) {

                try{
                    wait(8000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
