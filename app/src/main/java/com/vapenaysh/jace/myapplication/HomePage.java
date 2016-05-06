package com.vapenaysh.jace.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;

public class HomePage extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        //WARNING: UNTESTED CODE
        FavoriteLocationList locationsList = new FavoriteLocationList();
        Intent i = new Intent(this, GPSTrackerService.class);
        i.putExtra("FavoriteLocations", locationsList);
        startService(i);
        //WARNING: END UNTESTED CODE
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

}
