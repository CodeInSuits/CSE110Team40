package com.vapenaysh.jace.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class RemovePartner extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove);

        TextView name_partner = (TextView) findViewById(R.id.partner_email);
        TextView number_partner = (TextView)findViewById(R.id.partner_name);

        SharedPreferences share = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String name = share.getString("partner_name", "N/A");
        String number = share.getString("phone_number", "N/A");
        name_partner.setText(name);
        number_partner.setText(number);

    }


}
