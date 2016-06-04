package com.vapenaysh.jace.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

/**
 * RingToneSetting.java
 *
 * Class for managing the ringTone information and UI setting for ringTone activity.
 *
 * Created by XuanpeiEstherOuyang on 5/29/16.
 */
public class RingToneSetting extends AppCompatActivity implements Observer{

    // the tonePath
    private String tonePath;

    // the current index of current location
    private int locIndex;
    FirebaseDatabase locationsDB = FirebaseDatabase.getInstance();
    FavoriteLocationAdapter fla;

    // the favorite location list
    private ArrayList<FavoriteLocation> flls = new ArrayList<FavoriteLocation>();

    // the current location ring tone uri
    private String currenturi = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound_tone_setting);
        Intent intent = getIntent();
        locIndex = intent.getExtras().getInt("locIndex");
        String uid = intent.getExtras().getString("uid");

        FavoriteLocationList fl = new FavoriteLocationList(uid);

        fl.addObserver(this);


        Toast.makeText(getBaseContext(), "What partner email " + locIndex, Toast.LENGTH_SHORT).show();
        setRingTone();
    }

    /**
     * Method for setting the ring tone
     */
    public void setRingTone(){

        Intent intent = new Intent(RingtoneManager.ACTION_RINGTONE_PICKER);

        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_TITLE, "Select ringtone for Location");
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_SILENT, false);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT, true);
        intent.putExtra(RingtoneManager.EXTRA_RINGTONE_EXISTING_URI, Uri.parse(this.currenturi));
        startActivityForResult(intent, 3);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
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
        finish();

    }

    /**
     * Method for saving the ring tone
     */
    public void save() {

        PartnerFavoriteLocation loc = new PartnerFavoriteLocation();
        String uid = loc.getPartnerEmail();
        DatabaseReference db = locationsDB.getReference(uid + Constants.LOC_URL);
        db.child(""+locIndex).child("ringTone").setValue(tonePath);
    }

    /**
     * Method for getting the updated ring tone
     */
    public void update(Observable o, Object object){

        flls = ((FavoriteLocationList)o).getLocations();
        currenturi = flls.get(locIndex).getRingTone();
    }

}
