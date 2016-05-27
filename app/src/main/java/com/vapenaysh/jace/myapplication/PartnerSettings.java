package com.vapenaysh.jace.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PartnerSettings extends Activity {

    private static String name;
    private static String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove);

        //Instantiate widgets
        TextView name_partner = (TextView) findViewById(R.id.show_name);
        TextView number_partner = (TextView)findViewById(R.id.show_phone);
        Button remove_partner = (Button) findViewById(R.id.remove_partner_button);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("user");
        name_partner.setText(name);
        number_partner.setText(number);

        remove_partner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO: on removePartner, stop the service
                SharedPreferences remove = getSharedPreferences("MyData", Context.MODE_PRIVATE);
                SharedPreferences.Editor removeEditor = remove.edit();
                removeEditor.remove("partner_name");
                removeEditor.remove("phone_number");
                name = "N/A";
                number = "N/A";
                removeEditor.commit();
                finish();
            }
        });



    }

    public static String getName(){
        return name;
    }

    public static String getNumber(){
        return number;
    }

    public static void setName(String name) {
        PartnerSettings.name = name;
    }

    public static void setNumber(String number) {
        PartnerSettings.number = number;
    }
}
