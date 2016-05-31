package com.vapenaysh.jace.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

public class PartnerSettings extends Activity {

    private static String name;
    private static String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove);

        //Instantiate widgets
        final TextView name_partner = (TextView) findViewById(R.id.show_name);
        final TextView number_partner = (TextView)findViewById(R.id.show_phone);
        Button remove_partner = (Button) findViewById(R.id.remove_partner_button);

        final String userEmail = getIntent().getStringExtra(Constants.DISPLAY_EMAIL);

        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        final DatabaseReference myRef = database.getReference("users");
        if(userEmail != null) {
            myRef.child(userEmail).addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    GenericTypeIndicator<Map<String, String>> t = new GenericTypeIndicator<Map<String, String>>() {
                    };
                    Map<String, String> map = dataSnapshot.getValue(t);
                    String name = map.get(Constants.DATABASE_PARTNER_NAME);
                    String email = map.get(Constants.DATABASE_PARTNER_KEY);
                    name_partner.setText(name);
                    number_partner.setText(email);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        remove_partner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef.child(userEmail).child(Constants.DATABASE_PARTNER_NAME).setValue("");
                myRef.child(userEmail).child(Constants.DATABASE_PARTNER_KEY).setValue("");
                Log.v("PartnerSettings", "Removed partner");
                Toast.makeText(getApplicationContext(), "Partner removed", Toast.LENGTH_LONG).show();
                stopService(new Intent(PartnerSettings.this, NotificationService.class));
                stopService(new Intent(PartnerSettings.this, GPSTrackerService.class));
                finish();

                onBackPressed();
            }
        });



    }

    public static String getName(){
        return name;
    }

    public static String getNumber(){
        return number;
    }

    public static void setName(String name) {
        PartnerSettings.name = name;
    }

    public static void setNumber(String number) {
        PartnerSettings.number = number;
    }
}
