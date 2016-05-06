package com.vapenaysh.jace.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dd.processbutton.iml.ActionProcessButton;

import Utility.ProgressGenerator;

public class HomePage extends Activity implements View.OnClickListener, ProgressGenerator.OnCompleteListener{

    private ProgressGenerator progressGenerator;
    private ActionProcessButton signout;
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

        progressGenerator = new ProgressGenerator(this);
        signout = (ActionProcessButton) findViewById(R.id.logout);

        signout.setMode(ActionProcessButton.Mode.PROGRESS);
        signout.setOnClickListener(this);

        //connect the register button
        //Button loc_history_view = (Button)findViewById(R.id.see_loc_history);
        //loc_history_view.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_fave_loc_btn:
                startActivity(new Intent(HomePage.this, MapsActivity.class));
                break;

            case R.id.partner_setting_btn:

                if (true) { // TO DO if user does not have a partner, then activity_add_partner
                    startActivity(new Intent(HomePage.this, activity_add_partner.class));
                } else {
                    startActivity(new Intent(HomePage.this, remove.class));
                }
                break;

            // case R.id.see_loc_history:
            // not for MS 1
            // break;

            case R.id.logout:
                signout();
        }
        //WARNING: UNTESTED CODE
        FavoriteLocationList locationsList = new FavoriteLocationList();
        Intent i = new Intent(this, GPSTrackerService.class);
        i.putExtra("FavoriteLocations", locationsList);
        startService(i);
        //WARNING: END UNTESTED CODE
    }

    public void signout(){

        progressGenerator.start(signout);
        signout.setEnabled(false);
    }

    /**
     * button onclick method
     * @param view
     */
    public void startMapActivity(View view){
        startActivity(new Intent(HomePage.this, MapsActivity.class));
    }

    /**
     * button onclick method
     * @param view
     */
    public void openPartnerSettings(View view){
        startActivity(new Intent(HomePage.this, activity_add_partner.class));
    }

    /**
     * button onclick method
     * @param view
     */
    public void seePartnerHistory(View view){
        //TODO after adding partner history part
        //startActivity(new Intent(HomePage.this, History.class));
    }

    @Override
    public void onComplete() {
        finishActivity(1);
        onBackPressed();

    }
}
