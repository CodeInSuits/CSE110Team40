package com.vapenaysh.jace.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//import com.google.android.gms.gcm.GoogleCloudMessaging;

public class AddPartner extends Activity implements View.OnClickListener {
    private Button btnRegId;
    private Button addPar;
    private TextView tvRegId;
    //private GoogleCloudMessaging gcm;
    private String regid;
    private String id;
    private String PROJECT_NUMBER = Constants.PROJECT_NUMBER;
    private EditText pname;
    private EditText pemail;
    private String userEmail;
    private String partnerName;
    private String partnerEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_add_partner);

        //Instantiate UI widgets
        //btnRegId = (Button) findViewById(R.id.get_sel  f_id);
        addPar = (Button) findViewById(R.id.add_par);
        //tvRegId = (TextView)findViewById(R.id.self_regId);

        //btnRegId.setOnClickListener(this);
        addPar.setOnClickListener(this);
        //tvRegId.setText(id);

        pname = (EditText) findViewById(R.id.par_name);
        pemail = (EditText) findViewById(R.id.par_phone);

        userEmail = getIntent().getStringExtra(Constants.DISPLAY_EMAIL);

    }

    //Put partner name and phone number into Android's shared preferences for the application.
    public void addPar(){
        partnerName = pname.getText().toString();
        partnerEmail = pemail.getText().toString().split("@")[0];

        // empty suit checking
        // if the text are not empty, then add the partner
        if(partnerEmail.equals("") || partnerEmail.equals("")){
            AlertDialog.Builder dialog = new AlertDialog.Builder(AddPartner.this);
            dialog.setTitle("Empty Fields");
            dialog.setMessage("Please complete the form");
            dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();

                }
            });
            dialog.show();
        }
        else{
            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            DatabaseReference partnerNameDatabase = firebaseDatabase.getReference("users").child(userEmail).child("partnerName");
            DatabaseReference partnerEmailDatabase = firebaseDatabase.getReference("users").child(userEmail).child("partnerEmail");


            partnerEmailDatabase.setValue(partnerEmail);
            partnerNameDatabase.setValue(partnerName);

            startTracking();
            startNotificationService();
            finish();
        }
    }

    @Override
    // defing the onClick behavior
    public void onClick(View v){

        switch (v.getId()) {
            case R.id.add_par:
                addPar();
                break;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "Partner Added", Toast.LENGTH_SHORT).show();
    }

    // method for starting the server for tracking
    private void startTracking(){
        Intent i = new Intent(this, GPSTrackerService.class);
        i.putExtra(Constants.DISPLAY_EMAIL, userEmail);
        startService(i);
    }

    private void startNotificationService(){
        Intent notifsIntent = new Intent(this, NotificationService.class);
        notifsIntent.putExtra(Constants.PARTNER_EMAIL, PartnerSettings.getNumber());
        startService(notifsIntent);
    }


}