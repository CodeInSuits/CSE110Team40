
package com.vapenaysh.jace.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.android.gms.gcm.GoogleCloudMessaging;

public class AddPartner extends Activity implements View.OnClickListener {
    private Button btnRegId;
    private Button addPar;
    private TextView tvRegId;
    //private GoogleCloudMessaging gcm;
    private String regid;
    private String id;
    private String PROJECT_NUMBER = Constants.PROJECT_NUMBER;
    private EditText uname;
    private EditText phone;
    private static String username;
    private static String userphone;
    private FavoriteLocationList partnerLocationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_add_partner);

        //Instantiate UI widgets
        btnRegId = (Button) findViewById(R.id.get_self_id);
        addPar = (Button) findViewById(R.id.add_par);
        tvRegId = (TextView)findViewById(R.id.self_regId);

        btnRegId.setOnClickListener(this);
        addPar.setOnClickListener(this);
        //tvRegId.setText(id);

        uname = (EditText) findViewById(R.id.par_name);
        phone = (EditText) findViewById(R.id.par_phone);


        partnerLocationList = null;
    }

    //Put partner name and phone number into Android's shared preferences for the application.
    public void addPar(){
        SharedPreferences share = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = share.edit();
        String name = uname.getText().toString();
        String phoneNumber = phone.getText().toString();

        // empty suit checking
        // if the text are not empty, then add the partner
        if(name.equals("") || phoneNumber.equals("")){
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
            editor.putString("partner_name",name);
            PartnerSettings.setName(name);
            username = name;

            editor.putString("phone_number",phoneNumber);
            PartnerSettings.setNumber(phoneNumber);
            userphone = phoneNumber;

            startTracking();
            startNotificationService();

            editor.commit();
            Toast.makeText(getApplicationContext(), "Partner Added", Toast.LENGTH_SHORT).show();
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

    // getter method for user name
    public static String getUname(){
        return username;
    }

    // getter method for user phone number
    public static String getPhone(){
        return userphone;
    }

    // method for starting the server for tracking
    private void startTracking(){
        Intent i = new Intent(this, GPSTrackerService.class);
        //i.putExtra("FavoriteLocations", favoriteLocationList);
        startService(i);
    }

    private void startNotificationService(){
        Intent notifsIntent = new Intent(this, NotificationService.class);
        notifsIntent.putExtra(Constants.PARTNER_KEY, PartnerSettings.getNumber());
        startService(notifsIntent);
    }


}