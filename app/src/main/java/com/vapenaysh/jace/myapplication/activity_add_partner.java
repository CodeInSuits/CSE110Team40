package com.vapenaysh.jace.myapplication;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import android.os.AsyncTask;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.w3c.dom.Text;

import java.io.IOException;


public class activity_add_partner extends Activity {

    GoogleCloudMessaging gcm;
    String regid;
    String id;
    String PROJECT_NUMBER = "123";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_add_partner);

        getRegId();
        TextView self_reg_id = (TextView)findViewById(R.id.self_regId);
        self_reg_id.setText(id);

        EditText uname = (EditText) findViewById(R.id.par_name);
        EditText uemail = (EditText) findViewById(R.id.par_email);
        EditText uid = (EditText) findViewById(R.id.par_id);

        // register everything and update partnership
        /*

        success -> if the entered email does not a partner yet

        if (success){
            set the button to show partner added and redirect user to homepage
        } else {
            set the button to show failure and redirect user to homepage
        }
        */
    }

    public void getRegId() {
        new AsyncTask<Void, Void, String>() {

            @Override
            protected String doInBackground(Void... params) {
                String msg = "";
                try {
                    if(gcm == null) {
                        gcm = GoogleCloudMessaging.getInstance(getApplicationContext());
                    }

                    regid = gcm.register(PROJECT_NUMBER);
                    msg = "Device registered, registration ID=" + regid;
                    Log.i("GCM", "!!!!! " + regid);

                } catch(IOException ex) {
                    msg = "Error: " + ex.getMessage();
                }
                id = msg;
                return msg;
            }

        }.execute(null, null, null);
    }




}
