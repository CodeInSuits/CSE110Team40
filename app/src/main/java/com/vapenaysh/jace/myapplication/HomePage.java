package com.vapenaysh.jace.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dd.processbutton.iml.ActionProcessButton;
import com.google.android.gms.maps.model.LatLng;

import Utility.ProgressGenerator;

public class HomePage extends Activity implements View.OnClickListener, ProgressGenerator.OnCompleteListener{

    private ProgressGenerator progressGenerator;
    private ActionProcessButton signout;
    private FavoriteLocationList locationsList;
    private String name;
    private String number;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //connect the register button
        Button map_view = (Button)findViewById(R.id.add_fave_loc_btn);
        map_view.setOnClickListener(this);

        //connect the register button
        Button partner_setting_view = (Button)findViewById(R.id.partner_setting_btn);
        partner_setting_view.setOnClickListener(this);


        //connect the sms button
        Button sms_button = (Button) findViewById(R.id.button_SMS);
        sms_button.setOnClickListener(this);

        //sign out button
        progressGenerator = new ProgressGenerator(this);
        signout = (ActionProcessButton) findViewById(R.id.logout);

        signout.setMode(ActionProcessButton.Mode.PROGRESS);
        signout.setOnClickListener(this);

        //get the saved locations from the file and store locally
        locationsList = new FavoriteLocationList(this);

        if(!isSingle(this.name, this.number)){
            setUpPartnerSettings();
            //WARNING: UNTESTED CODE
            Intent i = new Intent(this, GPSTrackerService.class);
            i.putExtra("FavoriteLocations", locationsList);
            startService(i);
        }


        //connect the register button
        //Button loc_history_view = (Button)findViewById(R.id.see_loc_history);
        //loc_history_view.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        //loads all the saved locations

        switch (v.getId()){
            case R.id.add_fave_loc_btn:
                Intent i = new Intent(this, MapsActivity.class);
                i.putExtra("FavoriteLocations", locationsList);
                startActivity(i);
                break;

            case R.id.partner_setting_btn:
                getPartnerInformation();
                if (isSingle(this.name, this.number)) {
                    startActivity(new Intent(HomePage.this, AddPartner.class));
                } else {
                    startActivity(new Intent(HomePage.this, PartnerSettings.class));
                }
                break;
            case R.id.button_SMS:

                SentSMS sentSMS = new SentSMS();
                String msg = sentSMS.sendSms(new FavoriteLocation(new LatLng(1,1), "test"));
                Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

                break;
            // case R.id.see_loc_history:
            // not for MS 1
            // break;

            case R.id.logout:
                signout();
                break;
        }


    }

    public void getPartnerInformation(){

        SharedPreferences share = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        this.name = share.getString("partner_name", "N/A");
        this.number = share.getString("phone_number", "N/A");
    }

    public boolean isSingle(String parName, String parNumber){

        if(parName.equals("N/A") || parNumber.equals("N/A")) {
            Toast.makeText(this,"No Partner Found", Toast.LENGTH_LONG).show();
            return true;
        }
        else {
            Toast.makeText(this,"Partner Found", Toast.LENGTH_LONG).show();
            return false;
        }

    }

    public void signout(){

        progressGenerator.start(signout);
        Toast.makeText(getApplicationContext(), "Save location and partner information", Toast.LENGTH_SHORT).show();
        signout.setEnabled(false);
    }

    @Override
    public void onComplete() {
        finishActivity(1);
        onBackPressed();

    }

    /**
     * delete the locations file of all saved locations
     * @param view
     */
    public void removeAllLocations(View view){
        locationsList.removeAllLocations(this);
        Toast.makeText(getApplicationContext(), "Removed all saved favorite locations.", Toast.LENGTH_SHORT).show();
    }

    private void setUpPartnerSettings(){
        SharedPreferences share = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String name = share.getString("partner_name", "N/A");
        String number = share.getString("phone_number", "N/A");
        PartnerSettings.setNumber(number);
        PartnerSettings.setName(name);

    }
}
