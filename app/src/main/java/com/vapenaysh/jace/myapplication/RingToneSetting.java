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

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;

public class RingToneSetting extends AppCompatActivity {

    private String tonePath;
    private String locName;
    FirebaseDatabase locationsDB = FirebaseDatabase.getInstance();
    FavoriteLocationAdapter fla;
    private ArrayList<FavoriteLocation> flls = new ArrayList<FavoriteLocation>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_tone_setting);
        Intent intent = getIntent();
        locName = intent.getStringExtra("locName");
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
                save();
            }
        }
    }


    public void save() {

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

            currentLoc.setRingTone(tonePath);
            tmp.writeLocation(currentLoc);
        }
    }

}
