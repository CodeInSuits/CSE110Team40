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
 * RingToneManager.java
 *
 * Class for managing the ringTone information and playing ringTone.
 *
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
    private long ringDelay = 4000;

    /**
     * Constructor for creating the RingToneManager
     */
    public RingToneManager(Context context) {
        this.context = context;
    }

    /**
     * Method for playing the corresponding ring tone
     */
    public void playTone(FavoriteLocation loc){

        if(playMode()) {
            //String stringURI = getRingToneFromFirebase(loc.getName());

            String stringURI = loc.getRingTone();
            Uri uri = Uri.parse(stringURI);

            final Ringtone r = RingtoneManager.getRingtone(context, uri);
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

    /**
     * Method for checking if the current mode allows playing ring tone
     */
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

    /**
     * Method for playing the departure ring tone
     */
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

    /**
     * Method for playing the arrival ring tone
     */
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
            timer.schedule(task, 2500);

            try {
                Thread.sleep(3500);
            } catch(InterruptedException ex) {
                Thread.currentThread().interrupt();
            }

        }
    }
}
