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
import android.util.Log;
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
import java.util.Observable;
import java.util.Observer;

public class VibeToneSetting extends Activity implements Observer {

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

        String uid = intent.getExtras().getString("uid");

        FavoriteLocationList fl = new FavoriteLocationList(uid);
        RadioButton button1 = (RadioButton) findViewById(R.id.radioButton);
        button1.setChecked(true);

        fl.addObserver(this);


        radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                // find which radio button is selected
                switch (checkedId) {
                    case R.id.radioButton:
                        VibeToneIndex = 0;
                        break;
                    case R.id.radioButton2:
                        VibeToneIndex = 1;
                        break;
                    case R.id.radioButton3:
                        VibeToneIndex = 2;
                        break;
                    case R.id.radioButton4:
                        VibeToneIndex = 3;
                        break;
                    case R.id.radioButton5:
                        VibeToneIndex = 4;
                        break;
                    case R.id.radioButton6:
                        VibeToneIndex = 5;
                        break;
                    case R.id.radioButton7:
                        VibeToneIndex = 6;
                        break;
                    case R.id.radioButton8:
                        VibeToneIndex = 7;
                        break;
                    case R.id.radioButton9:
                        VibeToneIndex = 8;
                        break;
                    case R.id.radioButton10:
                        VibeToneIndex = 9;
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

    }

    public void update(Observable o, Object object){

        flls = ((FavoriteLocationList)o).getLocations();
        int currVibeTone = flls.get(position).getVibeTone();

        switch (currVibeTone) {
            case 0:
                RadioButton button1 = (RadioButton) findViewById(R.id.radioButton);
                button1.setChecked(true);
                break;
            case 1:
                RadioButton button2 = (RadioButton) findViewById(R.id.radioButton2);
                button2.setChecked(true);
                break;
            case 2:
                RadioButton button3 = (RadioButton) findViewById(R.id.radioButton3);
                button3.setChecked(true);
                break;
            case 3:
                RadioButton button4 = (RadioButton) findViewById(R.id.radioButton4);
                button4.setChecked(true);
                break;
            case 4:
                RadioButton button5 = (RadioButton) findViewById(R.id.radioButton5);
                button5.setChecked(true);
                break;
            case 5:
                RadioButton button6 = (RadioButton) findViewById(R.id.radioButton6);
                button6.setChecked(true);
                break;
            case 6:
                RadioButton button7 = (RadioButton) findViewById(R.id.radioButton7);
                button7.setChecked(true);
                break;
            case 7:
                RadioButton button8 = (RadioButton) findViewById(R.id.radioButton8);
                button8.setChecked(true);
                break;
            case 8:
                RadioButton button9 = (RadioButton) findViewById(R.id.radioButton9);
                button9.setChecked(true);
                break;
            case 9:
                RadioButton button10 = (RadioButton) findViewById(R.id.radioButton10);
                button10.setChecked(true);
                break;
        }
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
