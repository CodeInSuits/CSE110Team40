
package com.vapenaysh.jace.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;

import java.io.IOException;


public class AddPartner extends Activity implements View.OnClickListener {

    private Button btnRegId;
    private Button addPar;
    private TextView tvRegId;
    private GoogleCloudMessaging gcm;
    private String regid;
    private String id;
    private String PROJECT_NUMBER = Constants.PROJECT_NUMBER;
    private EditText uname;
    private EditText phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_add_partner);

        btnRegId = (Button) findViewById(R.id.get_self_id);
        addPar = (Button) findViewById(R.id.add_par);
        tvRegId = (TextView)findViewById(R.id.self_regId);

        btnRegId.setOnClickListener(this);
        addPar.setOnClickListener(this);
        //tvRegId.setText(id);

        uname = (EditText) findViewById(R.id.par_name);
        phone = (EditText) findViewById(R.id.par_phone);

        //EditText uid = (EditText) findViewById(R.id.par_id);

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

    public void addPar(){
        SharedPreferences share = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = share.edit();
        String name = uname.getText().toString();
        String phoneNumber = phone.getText().toString();
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

            editor.putString("phone_number",phoneNumber);
            PartnerSettings.setNumber(phoneNumber);

            editor.commit();
            Toast.makeText(getApplicationContext(), "Partner Added", Toast.LENGTH_SHORT).show();
            finish();
        }
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

        switch (v.getId()) {
            case R.id.get_self_id:
                getRegId();
                break;
            case R.id.add_par:
                addPar();
                break;
        }

    }

    public String getUname(){
        return uname.getText().toString();
    }

    public String getPhone(){
        return phone.getText().toString();
    }


}