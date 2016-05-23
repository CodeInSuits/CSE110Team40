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

    AudioManager audioManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_global_settings);

        Button both = (Button) findViewById(R.id.both);
        Button soundOnly = (Button) findViewById(R.id.soundonly);
        Button vibeOnly = (Button) findViewById(R.id.vibeonly);
        Button mute = (Button) findViewById(R.id.mute);

        AudioManager audioManager =
                (AudioManager) getSystemService(getApplicationContext().AUDIO_SERVICE);


        both.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    setVibration(true);
                    setSound(true);
            }
        });

        soundOnly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setVibration(false);
                setSound(true);
            }
        });

        vibeOnly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setVibration(true);
                setSound(false);
            }
        });


        mute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                setVibration(false);
                setSound(false);
            }
        });
    }

    public void setVibration(boolean turnOn) {

        if(audioManager != null) {
            if (turnOn) {
                audioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
            } else {
                audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
            }
        }
    }

    public void setSound(boolean turnOn){

        if(audioManager != null) {
            if (turnOn) {
                audioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            } else {
                audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
            }
        }
    }

}
