package com.vapenaysh.jace.myapplication;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by XuanpeiEstherOuyang on 5/29/16.
 */
public class VibeToneManager extends AppCompatActivity {

    // default notification mode is both sound and vibration tone
    private int notificationMode = 1;

    public VibeToneManager() {

        SharedPreferences sharedPreferences = getSharedPreferences("notif_mode", MODE_PRIVATE);
        this.notificationMode = Integer.parseInt(sharedPreferences.getString("mode", "1"));
    }

    public void playRingTone(FavoriteLocation loc){

        if(checkMode(this.notificationMode)) {


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
