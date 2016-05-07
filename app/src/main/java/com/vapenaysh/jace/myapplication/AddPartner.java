
package com.vapenaysh.jace.myapplication;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import org.w3c.dom.Text;

import java.io.IOException;


public class AddPartner extends Activity implements View.OnClickListener {

    Button btnRegId;
    TextView tvRegId;
    GoogleCloudMessaging gcm;
    String regid;
    String id;
    String PROJECT_NUMBER = Constants.PROJECT_NUMBER;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_add_partner);

        btnRegId = (Button) findViewById(R.id.get_self_id);
        tvRegId = (TextView)findViewById(R.id.self_regId);

        btnRegId.setOnClickListener(this);
        //tvRegId.setText(id);

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
                    id = regid;

                } catch(IOException ex) {
                    msg = "Error: " + ex.getMessage();
                    id = msg;
                }

                return msg;
            }

            @Override
            protected void onPostExecute(String msg){
                tvRegId.setText(id);
            }

        }.execute(null, null, null);
    }

    @Override
    public void onClick(View v){
        getRegId();
    }
}