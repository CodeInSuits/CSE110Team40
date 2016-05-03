package com.vapenaysh.jace.myapplication;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;
import com.google.android.gms.common.server.FavaDiagnosticsEntity;
import com.google.android.gms.maps.model.LatLng;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class MainActivity extends Activity implements View.OnClickListener{

    //backend object
    private Firebase firebase;

    //login button
    private Button login;

    //register button
    private Button register;

    public final static String LOC_FILE_NAME = "CoupleToneLocs.txt";

    public static HashSet<FavoriteLocation> locations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //get rid of the hiderous action bar on the top
        ActionBar actionBar = getActionBar();
        actionBar.hide();

        //set up the view
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //connect the login button
        login = (Button)findViewById(R.id.login);
        login.setOnClickListener(this);


        //connect the register button
        register = (Button)findViewById(R.id.register);
        register.setOnClickListener(this);

        //initialize the firebase object
        Firebase.setAndroidContext(this);


        //connect to the server
        firebase = new Firebase("https://coupletone.firebaseio.com/");

        Firebase usernames = firebase.child("usernames");
        usernames.setValue("Test");

        locations = new HashSet<>();
        fillLocationsArray();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                EditText uname = (EditText) findViewById(R.id.email);
                EditText passw = (EditText) findViewById(R.id.password);
                //TODO: login logic
                startActivity(new Intent(MainActivity.this, HomePage.class));
                break;
            case R.id.register:
                startActivity(new Intent(MainActivity.this, CreateAccount.class));
                break;
        }
    }

    /** Every time the user starts up the app, populate the array of favorite locations */
    private void fillLocationsArray(){
        FileInputStream fis;
        try {
            fis = openFileInput(MainActivity.LOC_FILE_NAME);
        }catch(Exception e){
            Log.e("MainActivity", "fillLocationsArray() had exception: " + e.toString());
            return; //return if no file found
        }

        Scanner input = new Scanner(fis);
        while( input.hasNextLine() ){
            translateFavoriteLocation( input.nextLine() );
        }

        try {
            fis.close();
        } catch( Exception e ){
            Log.e("MainActivity", "fillLocationsArray() had exception: " + e.toString());
        }
    }

    /** Take each string of form "name&latitude&longitude" and create a favorite location,
     *  adding it to the array
     */
    private void translateFavoriteLocation( String line ){
        String[] parts = line.split("&");
        if( parts.length != 3 ){
            Log.v("MainActivity", "translateFavoriteLocation() read a"
                    + "line of location with incorrect number of paramaters");
            return;
        }

        double lat = Double.parseDouble(parts[1]);
        double lon = Double.parseDouble(parts[2]);
        FavoriteLocation loc = new FavoriteLocation(new LatLng(lat, lon), parts[0]);
        locations.add(loc);
        Log.v("MainActivity", "translateFavoriteLocation() read line successfully");
    }
}
