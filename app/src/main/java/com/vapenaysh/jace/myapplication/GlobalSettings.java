package com.vapenaysh.jace.myapplication;

import android.content.SharedPreferences;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * GlobalSettings.java
 *
 * Class for storing and managing the global setting locally
 *
 * Created by Esther on 5/26/16.
 */
public class GlobalSettings extends AppCompatActivity {

    /*
     1 - Both Sound and Vibration
     2 - Sound only
     3 - Vibe only
     4 - Mute
     */
    private static int notificationMode;
    private String mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_settings);

        SharedPreferences sharedPreferences = getSharedPreferences("notif_mode", MODE_PRIVATE);
        mode = sharedPreferences.getString("mode", "");

        final Button both = (Button) findViewById(R.id.both);
        final Button soundOnly = (Button) findViewById(R.id.soundonly);
        final Button vibeOnly = (Button) findViewById(R.id.vibeonly);
        final Button mute = (Button) findViewById(R.id.mute);

        setColor(mode, both, soundOnly, vibeOnly, mute);

        // set the onClick method for the both notification button
        both.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setNotificationSetting(true, true);
                Toast.makeText(getApplicationContext(), "Sound & Vibration", Toast.LENGTH_SHORT).show();
                saveSettings();
                setColor(mode, both, soundOnly, vibeOnly, mute);


            }
        });

        // set the onClick method for the sound only button
        soundOnly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setNotificationSetting(true, false);
                Toast.makeText(getApplicationContext(), "Sound Only", Toast.LENGTH_SHORT).show();
                saveSettings();
                setColor(mode, both, soundOnly, vibeOnly, mute);

            }
        });

        // set the onClick method for the vibe only button
        vibeOnly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setNotificationSetting(false, true);
                Toast.makeText(getApplicationContext(), "Vibration Only", Toast.LENGTH_SHORT).show();
                saveSettings();
                setColor(mode, both, soundOnly, vibeOnly, mute);

            }
        });

        // set the onClick method for the mute button
        mute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setNotificationSetting(false, false);
                Toast.makeText(getApplicationContext(), "Mute", Toast.LENGTH_SHORT).show();
                saveSettings();
                setColor(mode, both, soundOnly, vibeOnly, mute);

            }
        });
    }

    /**
     * Setter method for notification mode
     * @param sound
     * @param vibe
     */
    public void setNotificationSetting(boolean sound, boolean vibe) {

        if (sound && vibe){
            notificationMode = 1;
        }
        else if (sound && !vibe){
            notificationMode = 2;
        }
        else if (!sound && vibe){
            notificationMode = 3;
        }
        else {
            notificationMode = 4;
        }
    }

    /**
     * method for saving the current notification mode to local
     */
    public void saveSettings(){
        SharedPreferences sharedPreferences = getSharedPreferences("notif_mode", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("mode", notificationMode+"");
        editor.apply();
        mode = notificationMode+"";
    }

    // getter method for the current notification mode
    public static int getNotificationMode(){
        return notificationMode;
    }

    // method for setting color of buttons
    private void setColor(String mode, Button both, Button soundOnly, Button vibeOnly, Button mute){
        final int newColor = 0xFF66ccff;
        final int startColor = 0xFFc2c2d6;

        both.setBackgroundColor(startColor);
        soundOnly.setBackgroundColor(startColor);
        vibeOnly.setBackgroundColor(startColor);
        mute.setBackgroundColor(startColor);

        switch(mode){
            case "1":
                both.setBackgroundColor(newColor);
                break;
            case "2":
                soundOnly.setBackgroundColor(newColor);
                break;
            case "3":
                vibeOnly.setBackgroundColor(newColor);
                break;
            case "4":
                mute.setBackgroundColor(newColor);
        }
    }

}
