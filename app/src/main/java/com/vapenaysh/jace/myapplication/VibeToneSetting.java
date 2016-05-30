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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class VibeToneSetting extends Activity {

    private RadioGroup radioGroup;
    private static int VibeToneIndex;
    FirebaseDatabase locationsDB = FirebaseDatabase.getInstance();
    private static SharedPreferences sp;
    private String locName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vibe_tone_setting);

        Intent intent = getIntent();
        locName = intent.getStringExtra("locName");

        radioGroup = (RadioGroup) findViewById(R.id.myRadioGroup);
        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                // find which radio button is selected
                switch (checkedId) {
                    case R.id.radioButton:
                        Toast.makeText(getApplicationContext(),
                                "choice: Ring Tone 1", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioButton2:
                        Toast.makeText(getApplicationContext(),
                                "choice: Ring Tone 2", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioButton3:
                        Toast.makeText(getApplicationContext(),
                                "choice: Ring Tone 3", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioButton4:
                        Toast.makeText(getApplicationContext(),
                                "choice: Ring Tone 4", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioButton5:
                        Toast.makeText(getApplicationContext(),
                                "choice: Ring Tone 5", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioButton6:
                        Toast.makeText(getApplicationContext(),
                                "choice: Ring Tone 6", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioButton7:
                        Toast.makeText(getApplicationContext(),
                                "choice: Ring Tone 7", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioButton8:
                        Toast.makeText(getApplicationContext(),
                                "choice: Ring Tone 8", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioButton9:
                        Toast.makeText(getApplicationContext(),
                                "choice: Ring Tone 9", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radioButton10:
                        Toast.makeText(getApplicationContext(),
                                "choice: Ring Tone 10", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        Button button = (Button)findViewById(R.id.button3);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                int selectedId = radioGroup.getCheckedRadioButtonId();
                sp = getSharedPreferences("notif_mode", MODE_PRIVATE);
                switch (selectedId) {
                    case R.id.radioButton:
                        Toast.makeText(getApplicationContext(),
                                "Save Ring Tone 1", Toast.LENGTH_SHORT).show();
                        VibeToneIndex = 1;

                        break;
                    case R.id.radioButton2:
                        Toast.makeText(getApplicationContext(),
                                "Save Ring Tone 2", Toast.LENGTH_SHORT).show();
                        VibeToneIndex = 2;
                        break;
                    case R.id.radioButton3:
                        Toast.makeText(getApplicationContext(),
                                "Save Ring Tone 3", Toast.LENGTH_SHORT).show();
                        VibeToneIndex = 3;
                        break;
                    case R.id.radioButton4:
                        Toast.makeText(getApplicationContext(),
                                "Save Ring Tone 4", Toast.LENGTH_SHORT).show();
                        VibeToneIndex = 4;
                        break;
                    case R.id.radioButton5:
                        Toast.makeText(getApplicationContext(),
                                "Save Ring Tone 5", Toast.LENGTH_SHORT).show();
                        VibeToneIndex = 5;
                        break;
                    case R.id.radioButton6:
                        Toast.makeText(getApplicationContext(),
                                "Save Ring Tone 6", Toast.LENGTH_SHORT).show();
                        VibeToneIndex = 6;
                        break;
                    case R.id.radioButton7:
                        Toast.makeText(getApplicationContext(),
                                "Save Ring Tone 7", Toast.LENGTH_SHORT).show();
                        VibeToneIndex = 7;
                        break;
                    case R.id.radioButton8:
                        Toast.makeText(getApplicationContext(),
                                "Save Ring Tone 8", Toast.LENGTH_SHORT).show();
                        VibeToneIndex = 8;
                        break;
                    case R.id.radioButton9:
                        Toast.makeText(getApplicationContext(),
                                "Save Ring Tone 9", Toast.LENGTH_SHORT).show();
                        VibeToneIndex = 9;
                        break;
                    case R.id.radioButton10:
                        Toast.makeText(getApplicationContext(),
                                "Save Ring Tone 10", Toast.LENGTH_SHORT).show();
                        VibeToneIndex = 10;
                        break;
                }
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
        Vibrator e  = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        VibeToneManager vibe = new VibeToneManager(e);
        vibe.testPlayTone();
    }

    public static int getVibeToneIndex() {
        return VibeToneIndex;
    }

    public void setVibeToneIndex(int vibeToneIndex) {
        VibeToneIndex = vibeToneIndex;
    }

    public static SharedPreferences getSharedPreferences () {
        return sp;
    }

    public void save(){

        //DatabaseReference db = locationsDB.getReference(uid + Constants.LOC_URL);
        //fll = new ArrayList<>();

    }

}
