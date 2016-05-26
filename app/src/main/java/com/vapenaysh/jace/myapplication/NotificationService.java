package com.vapenaysh.jace.myapplication;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;
import java.util.ArrayList;

/**
 * Created by Matt on 5/23/16.
 */
public class NotificationService extends IntentService {
    private DatabaseReference partnerDb;

    public NotificationService() {
        super("notification service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.v("NotificationService", "Service Started");

        if (intent != null) {
            synchronized (this) {
                String partner = intent.getStringExtra(Constants.PARTNER_KEY);
                FirebaseDatabase locationsDB = FirebaseDatabase.getInstance();
                partnerDb = locationsDB.getReference(partner + Constants.LOC_URL);


                partnerDb.addValueEventListener(new com.google.firebase.database.ValueEventListener() {
                    @Override
                    public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                        ArrayList<FavoriteLocation> data = (ArrayList<FavoriteLocation>) dataSnapshot.getValue();

                        FavoriteLocation latest = getMostRecentlyVisited(data);
                        NotificationCompat.Builder mBuilder = null;

                        //ARRIVED AT A LOCATION
                        if (latest != null && latest.isVisited() && latest.afterCutoffTime() ) {
                            mBuilder = new NotificationCompat.Builder(getApplicationContext())
                                            .setSmallIcon(R.drawable.heart2)
                                            .setContentTitle("Arrival")
                                            .setContentText("Partner arrived at " + latest.getName());
                        }
                        //DEPARTED FROM A LOCATION
                        else if(latest != null && !latest.isVisited() && latest.afterCutoffTime() ){
                            mBuilder = new NotificationCompat.Builder(getApplicationContext())
                                    .setSmallIcon(R.drawable.heart2)
                                    .setContentTitle("Departure")
                                    .setContentText("Partner departed from " + latest.getName());
                        }

                        //display the notification
                        if( mBuilder != null){
                            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                            manager.notify(1, mBuilder.build());
                            Log.e("HELLO", mBuilder.build().toString());
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        }
    }

    /**
     * When calling this method, the GPS Tracker will have updated the Date if the location was
     * just arrived at or departed from
     * @param set
     * @return
     */
    private FavoriteLocation getMostRecentlyVisited(ArrayList<FavoriteLocation> set) {
        FavoriteLocation latest = null; //the location last visited
        Date mostRecent = new Date(Long.MAX_VALUE);
        for (FavoriteLocation fl : set) {
            if (fl.getDate().before(mostRecent)) {
                mostRecent = fl.getDate();
                latest = fl;
            }
        }

        return latest;
    }
}

