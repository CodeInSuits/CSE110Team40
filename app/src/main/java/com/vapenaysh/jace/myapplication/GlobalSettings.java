package com.vapenaysh.jace.myapplication;

import android.media.AudioManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class GlobalSettings extends AppCompatActivity {

    /*
     1 - Both Sound and Vibration
     2 - Sound only
     3 - Vibe only
     4 - Mute
     */
    private static int notificationMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_settings);

        Button both = (Button) findViewById(R.id.both);
        Button soundOnly = (Button) findViewById(R.id.soundonly);
        Button vibeOnly = (Button) findViewById(R.id.vibeonly);
        Button mute = (Button) findViewById(R.id.mute);


        both.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setNotificationSetting(true, true);
            }
        });

        soundOnly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setNotificationSetting(true, false);
            }
        });

        vibeOnly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setNotificationSetting(false, true);
            }
        });


        mute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setNotificationSetting(false, false);
            }
        });
    }

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

    public static int getNotificationMode(){
        return notificationMode;
    }

}
