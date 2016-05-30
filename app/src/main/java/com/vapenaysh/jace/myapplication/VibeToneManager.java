package com.vapenaysh.jace.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by XuanpeiEstherOuyang on 5/29/16.
 */
public class VibeToneManager extends AppCompatActivity {

    protected Vibrator vibrate;

    private long[] vibeTone1 = { 0, 100 };
    private long[] vibeTone2 = { 0, 100, 400, 100 };
    private long[] vibeTone3 = { 0, 100, 400, 100, 400, 100 };
    private long[] vibeTone4 = { 0, 100, 400, 100, 400, 100, 400, 100 };
    private long[] vibeTone5 = { 0, 100, 400, 100, 400, 100, 400, 100, 400, 100 };
    private long[] vibeTone6 = { 0, 100, 400, 100, 400, 100, 400, 100, 400, 100, 400, 100 };
    private long[] vibeTone7 = { 0, 100, 400, 100, 400, 100 ,400, 100, 400, 100, 400, 100, 400, 100 };
    private long[] vibeTone8 = { 0, 500, 500, 500, 500, 500 };
    private long[] vibeTone9 = { 0, 100, 100, 100, 100, 110 };
    private long[] vibeTone10 = { 0, 100, 500, 100, 500, 500, 500, 500, 500 };

    private long[][] vibeToneArray = {vibeTone1, vibeTone2, vibeTone3, vibeTone4, vibeTone5, vibeTone6,
            vibeTone7, vibeTone8, vibeTone9, vibeTone10};

    // default notification mode is both sound and vibration tone
    private int notificationMode = 1;

    public VibeToneManager(Vibrator vib) {

        vibrate = vib;
        SharedPreferences sharedPreferences = VibeToneSetting.getSharedPreferences();
        this.notificationMode = Integer.parseInt(sharedPreferences.getString("mode", "1"));
    }

    public void playTone(FavoriteLocation loc){

        if (vibrate != null) {

            //int vibeToneindex = loc.getVibeToneIndex();

            // hardcoded the vibeToneindex for now
            int vibeToneindex = 0;

            if (checkMode(this.notificationMode)) {
                vibrate.vibrate(vibeToneArray[vibeToneindex], -1);
            }
        }
    }


    public void testPlayTone(){

        if (vibrate != null) {

            //int vibeToneindex = loc.getVibeToneIndex();
            // for testing so far

            int vibeToneindex = 2;

            if (checkMode(this.notificationMode)) {
                vibrate.vibrate(vibeToneArray[vibeToneindex], -1);
            }
        }
    }


    private boolean checkMode(int mode){
        if (mode == 1 || mode == 3){
            return true;
        }
        else {
            return false;
        }
    }
}
