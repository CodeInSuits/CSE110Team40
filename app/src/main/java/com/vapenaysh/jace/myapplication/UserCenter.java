package com.vapenaysh.jace.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


/*
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;*/

public class UserCenter extends AppCompatActivity {
    //Defining Variables
    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private FavoriteLocationList locationsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_center);
        // Initializing Toolbar and setting it as the actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);
        locationsList = new FavoriteLocationList(this);

        if(!isSingle()){
            setUpPartnerSettings();
            //WARNING: UNTESTED CODE
            Intent i = new Intent(this, GPSTrackerService.class);
            i.putExtra("FavoriteLocations", locationsList);
            startService(i);
        }
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {


                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);

                //Closing drawer on item click
                drawerLayout.closeDrawers();

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {


                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.addmap:
                        Intent i = new Intent(UserCenter.this, MapsActivity.class);
                        i.putExtra("FavoriteLocations", locationsList);
                        startActivity(i);
                        //Toast.makeText(getApplicationContext(), "Map Selected", Toast.LENGTH_SHORT).show();
                        //ContentFragment fragment = new ContentFragment();
                        //android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        //fragmentTransaction.replace(R.id.frame, fragment);
                        //fragmentTransaction.commit();
                        break;

                    // For rest of the options we just show a toast on click

                    case R.id.partnersetting:
                        Toast.makeText(getApplicationContext(), "Send Selected", Toast.LENGTH_SHORT).show();
                        if (isSingle()) {
                            Intent i2 = new Intent(UserCenter.this, AddPartner.class);
                            i2.putExtra("FavoriteLocations", locationsList);
                            startActivity(i2);
                        } else {
                            startActivity(new Intent(UserCenter.this, PartnerSettings.class));
                        }
                        break;

                    case R.id.globalsetting:
                        Intent i_setting = new Intent(UserCenter.this, GlobalSettings.class);
                        startActivity(i_setting);
                        break;

                    case R.id.partnerfavlocationdispaly:
                        Intent i_partnerFavLoc = new Intent(UserCenter.this, PartnerFavoriteLocation.class);
                        startActivity(i_partnerFavLoc);
                        break;

                    default:
                        Toast.makeText(getApplicationContext(), "Somethings Wrong", Toast.LENGTH_SHORT).show();
                        break;
                }
                return true;
            }
        });

        // Initializing Drawer Layout and ActionBarToggle
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.openDrawer, R.string.closeDrawer){

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank

                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawerLayout.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessay or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();

    }


    // isSingle method without making toast
    public boolean isSingleForTest(String parName, String parNumber){

        if(parName.equals("N/A") || parNumber.equals("N/A")) {
            return true;
        }
        else {
            return false;
        }
    }


    // Checks if current user is single, and reports information to UI and return value.
    public boolean isSingle(){

        SharedPreferences share = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String name = share.getString("partner_name", "N/A");
        String number = share.getString("phone_number", "N/A");

        if(name.equals("N/A") || number.equals("N/A")) {
            Toast.makeText(this,"No Partner Found", Toast.LENGTH_LONG).show();
            return true;
        }
        else {
            Toast.makeText(this,"Partner Found", Toast.LENGTH_LONG).show();
            return false;
        }
    }

    /**
     * delete the locations file of all saved locations
     * @param view
     */
    public void removeAllLocations(View view){
        locationsList.removeAllLocations(this);
        Toast.makeText(getApplicationContext(), "Removed all saved favorite locations.", Toast.LENGTH_SHORT).show();
    }

    //Creates sharedpreferences from app data
    private void setUpPartnerSettings(){
        SharedPreferences share = getSharedPreferences("MyData", Context.MODE_PRIVATE);
        String name = share.getString("partner_name", "N/A");
        String number = share.getString("phone_number", "N/A");
        PartnerSettings.setNumber(number);
        PartnerSettings.setName(name);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_user_center, menu);
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
}
