package com.vapenaysh.jace.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;

public class VibeToneSetting extends Activity {

    private RadioGroup radioGroup;
    private static int VibeToneIndex;
    private String locName;
    FirebaseDatabase locationsDB = FirebaseDatabase.getInstance();
    FavoriteLocationAdapter fla;
    private ArrayList<FavoriteLocation> flls = new ArrayList<FavoriteLocation>();
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vibe_tone_setting);

        Intent intent = getIntent();
        locName = intent.getStringExtra("locName");
        position = intent.getIntExtra("position", 0);

        radioGroup = (RadioGroup) findViewById(R.id.myRadioGroup);
        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                // find which radio button is selected
                switch (checkedId) {
                    case R.id.radioButton:
                        VibeToneIndex = 1;
                        break;
                    case R.id.radioButton2:
                        VibeToneIndex = 2;
                        break;
                    case R.id.radioButton3:
                        VibeToneIndex = 3;
                        break;
                    case R.id.radioButton4:
                        VibeToneIndex = 4;
                        break;
                    case R.id.radioButton5:
                        VibeToneIndex = 5;
                        break;
                    case R.id.radioButton6:
                        VibeToneIndex = 6;
                        break;
                    case R.id.radioButton7:
                        VibeToneIndex = 7;
                        break;
                    case R.id.radioButton8:
                        VibeToneIndex = 8;
                        break;
                    case R.id.radioButton9:
                        VibeToneIndex = 9;
                        break;
                    case R.id.radioButton10:
                        VibeToneIndex = 10;
                        break;
                }
            }
        });

        Button button = (Button)findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });

        Button b = (Button) findViewById(R.id.button_test);
        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                testVibe();
            }
        });
    }



    // testing code // need to be removed later
    public void testVibe(){


        FavoriteLocation currentLoc = new FavoriteLocation();
        currentLoc.setVibeTone(4);

        Vibrator e  = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        VibeToneManager vibe = new VibeToneManager(e);
        vibe.playTone(currentLoc);
    }

    public static int getVibeToneIndex() {
        return VibeToneIndex;
    }

    public void setVibeToneIndex(int vibeToneIndex) {
        VibeToneIndex = vibeToneIndex;
    }

    public void save(){
        PartnerFavoriteLocation loc = new PartnerFavoriteLocation();
        String uid = loc.getPartnerEmail();
        DatabaseReference db = locationsDB.getReference(uid + Constants.LOC_URL);
        db.child(""+position).child("vibeTone").setValue(VibeToneIndex);

        Toast.makeText(getApplicationContext(), "VibeTone " + VibeToneIndex + " saved", Toast.LENGTH_SHORT).show();
    }

}
