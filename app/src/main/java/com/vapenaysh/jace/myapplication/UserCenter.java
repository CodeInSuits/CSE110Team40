package com.vapenaysh.jace.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;


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
    private CircleImageView profile;
    private TextView displayName;
    private TextView displayEmail;
    private static final String TAG = "UserCenter";
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private String userEmail;
    private String partnerEmail;
    private String backendUID;
    private String userName;
    private String partnerName;
    private String imageUri;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_center);
        // Initializing Toolbar and setting it as the actionbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //get content from login page
        userName = getIntent().getStringExtra("DisplayName");
        userEmail = getIntent().getStringExtra("DisplayEmail");
        backendUID = getIntent().getStringExtra("BackendUID");
        partnerEmail = getIntent().getStringExtra("PartnerEmail");
        partnerName = getIntent().getStringExtra("PartnerName");
        imageUri = getIntent().getStringExtra("ImageURL");
        Log.d(TAG, "ImageUrl" + imageUri);


        //Initializing NavigationView
        navigationView = (NavigationView) findViewById(R.id.navigation_view);

        View v = navigationView.getHeaderView(0); // 0-index header
        profile = (CircleImageView) v.findViewById(R.id.profile_image);
        displayName = (TextView) v.findViewById(R.id.username);
        displayEmail = (TextView) v.findViewById(R.id.email);




        displayEmail.setText(userEmail + "@gmail.com");
        displayName.setText(userName);
        Picasso.with(getApplicationContext()).load(imageUri).into(profile);

        //new ImageLoadTask(imageUri, profile).execute();

        if(!isSingle(userEmail)){
            setUpPartnerSettings();
            //WARNING: UNTESTED CODE
            Intent i = new Intent(this, GPSTrackerService.class);
            i.putExtra(Constants.DISPLAY_EMAIL, userEmail);
            startService(i);

            startNotificationService();
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
                        i.putExtra(Constants.DISPLAY_EMAIL, userEmail);
                        startActivity(i);
                        //Toast.makeText(getApplicationContext(), "Map Selected", Toast.LENGTH_SHORT).show();
                        //ContentFragment fragment = new ContentFragment();
                        //android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        //fragmentTransaction.replace(R.id.frame, fragment);
                        //fragmentTransaction.commit();
                        break;

                    // For rest of the options we just show a toast on click

                    case R.id.partnersetting:
                        if (isSingle(userEmail)) {
                            Intent i2 = new Intent(UserCenter.this, AddPartner.class);
                            i2.putExtra(Constants.DISPLAY_EMAIL, userEmail);
                            startActivity(i2);
                        } else {
                            Intent i2 = new Intent(UserCenter.this, PartnerSettings.class);
                            i2.putExtra(Constants.DISPLAY_EMAIL, userEmail);
                            startActivity(i2);
                        }
                        break;

                    case R.id.globalsetting:
                        Intent i_setting = new Intent(UserCenter.this, GlobalSettings.class);
                        startActivity(i_setting);
                        break;

                    case R.id.partnerfavlocationdispaly:

                        if(isSingle(userEmail)){
                            //partner does not exist in database yet
                            AlertDialog.Builder dialog = new AlertDialog.Builder(UserCenter.this);
                            dialog.setTitle("Partner Does Not Exist");
                            dialog.setMessage("No match for " + userName);
                            dialog.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();

                                }
                            });
                            dialog.show();

                        }
                        else{
                            Intent i_partnerFavLoc = new Intent(UserCenter.this, PartnerFavoriteLocation.class);
                            i_partnerFavLoc.putExtra("PartnerEmail", partnerEmail);
                            startActivity(i_partnerFavLoc);

                        }

                        break;

                    case R.id.removeFavLoc:
                        removeAllLocations();
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


    // Checks if current user is single, and reports information to UI and return value.
    public boolean isSingle(String userPath){
        DatabaseReference myRef = database.getReference("users");
        myRef.child(userPath).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User storedUser = dataSnapshot.getValue(User.class);
                partnerEmail = storedUser.getPartnerEmail();
                Toast.makeText(getBaseContext(), "Partner email is " + partnerEmail, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        if(partnerEmail.equals("")){
            return true;
        }
        else{
            return false;
        }
    }

    /**
     * delete the locations file of all saved locations
     */
    public void removeAllLocations(){
        FavoriteLocationList locationsList = new FavoriteLocationList(userEmail);
        locationsList.removeAllLocations();
        Toast.makeText(getApplicationContext(), "Removed all saved favorite locations.", Toast.LENGTH_SHORT).show();
    }

    //Creates sharedpreferences from app data
    private void setUpPartnerSettings(){
        PartnerSettings.setNumber(partnerEmail);
        PartnerSettings.setName(partnerName);

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
        if (id == R.id.log_out) {
            FirebaseAuth.getInstance().signOut();
            finishActivity(1);
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void startNotificationService(){
        Intent notifsIntent = new Intent(this, NotificationService.class);
        notifsIntent.putExtra(Constants.PARTNER_EMAIL, partnerEmail );

        startService(notifsIntent);
    }
}
