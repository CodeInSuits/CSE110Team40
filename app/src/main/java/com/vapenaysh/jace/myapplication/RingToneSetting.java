package com.vapenaysh.jace.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class RingToneSetting extends AppCompatActivity {

    String tonePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_tone_setting);
        setRingTone();
    }

    public void setRingTone(){

        Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);

        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        //intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, currenturi);

        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "Select ringtone for Location");
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_SILENT, false);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT, false);
        //intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TYPE, RingtoneManager.TYPE_NOTIFICATION);
        startActivityForResult(intent, 3);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        Toast.makeText(getApplicationContext(), resultCode+"yes", Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), Activity.RESULT_OK+"yes", Toast.LENGTH_SHORT).show();
        Toast.makeText(getApplicationContext(), Activity.RESULT_CANCELED+"", Toast.LENGTH_SHORT).show();


        if(resultCode == Activity.RESULT_OK && requestCode == 3){
            Uri toneUri = data.getParcelableExtra(RingtoneManager.EXTRA_RINGTONE_PICKED_URI);
            //RingtoneManager.setActualDefaultRingtoneUri(this, RingtoneManager.TYPE_NOTIFICATION, toneUri);
            if(toneUri != null){
                tonePath = toneUri.toString();
                Log.i("tonepath", tonePath);
                Toast.makeText(getApplicationContext(), tonePath, Toast.LENGTH_SHORT).show();
            }
        }
    }

}
