package com.vapenaysh.jace.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PartnerSettings extends Activity {

    private static String name;
    private static String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove);

        TextView name_partner = (TextView) findViewById(R.id.show_name);
        TextView number_partner = (TextView)findViewById(R.id.show_phone);
        Button remove_partner = (Button) findViewById(R.id.remove_partner_button);

        remove_partner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences remove = getSharedPreferences("MyData", Context.MODE_PRIVATE);
                SharedPreferences.Editor removeEditor = remove.edit();
                removeEditor.remove("partner_name");
                removeEditor.remove("phone_number");
                removeEditor.commit();
                finish();
            }
        });

        SharedPreferences share = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        name = share.getString("partner_name", "N/A");
        number = share.getString("phone_number", "N/A");
        name_partner.setText(name);
        number_partner.setText(number);

    }

    public static String getName(){
        return name;
    }

    public static String getNumber(){
        return number;
    }


}
