package com.vapenaysh.jace.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class HomePage extends Activity implements View.OnClickListener{

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
        }
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
