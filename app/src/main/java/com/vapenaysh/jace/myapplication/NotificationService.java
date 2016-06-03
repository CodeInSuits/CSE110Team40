package com.vapenaysh.jace.myapplication;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Date;

/**
 * NotificationService.java
 *
 * Class for sending push notification, playing ringTone, vibeTone to user
 *
 * Created by Matt on 5/23/16.
 */
public class NotificationService extends IntentService {
    private DatabaseReference partnerDb;
    private ArrayList<FavoriteLocation> visitedList;
    private RingToneManager ring;
    private VibeToneManager vibe;

    public NotificationService() {
        super("notification service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        visitedList = new ArrayList<>();
        Vibrator e  = (Vibrator) getSystemService(VIBRATOR_SERVICE);


        if (intent != null) {
            synchronized (this) {
                String partner = intent.getStringExtra(Constants.PARTNER_EMAIL);

                vibe = new VibeToneManager(e, getApplicationContext());
                ring = new RingToneManager(getApplicationContext());

                FirebaseDatabase locationsDB = FirebaseDatabase.getInstance();
                partnerDb = locationsDB.getReference(partner + Constants.LOC_URL);
                Log.v("NotificationService", "Service Started - reading from " + partner);

                partnerDb.addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                    }

                    @Override
                    public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                        FavoriteLocation data = dataSnapshot.getValue(FavoriteLocation.class);
                        Log.v("NotifService", "Received data change: " + data);


                        NotificationCompat.Builder mBuilder = null;

                        clearListIfAfterCutoff();

                        //ARRIVED AT A LOCATION
                        if (data != null && !visitedList.contains(data) && data.isVisited() ) {
                            visitedList.add(data);
                            mBuilder = new NotificationCompat.Builder(getApplicationContext())
                                    .setSmallIcon(R.drawable.heart2)
                                    .setContentTitle("Arrival")
                                    .setContentText("Partner arrived at " + data.getName());


                            // code for calling arrival vibration notification method (FavoriteLocation obejct)
                            vibe.playArrivalTone();

                            // code for calling arrival sound notification method (FavoriteLocation object)
                            ring.playArrivalTone();


                            // code for calling location vibration notification method (FavoriteLocation obejct)
                            vibe.playTone(data);

                            // code for calling location sound notification method (FavoriteLocation object)
                            ring.playTone(data);

                        }

                        //DEPARTED FROM A LOCATION
                        else if(data != null && visitedList.contains(data) && !data.isVisited() ){
                            mBuilder = new NotificationCompat.Builder(getApplicationContext())
                                    .setSmallIcon(R.drawable.heart2)
                                    .setContentTitle("Departure")
                                    .setContentText("Partner departed from " + data.getName());

                            Log.e("departure","departure notification");
                            visitedList.remove(data);

                            // code for calling departure sound notification method (FavoriteLocation object)
                            ring.playDepartureTone();

                            // code for calling departure vibration notification method (FavoriteLocation obejct)
                            vibe.playDepartureTone();

                        }

                        //display the notification
                        if( mBuilder != null){
                            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                            manager.notify(1, mBuilder.build());
                        }
                    }

                    @Override
                    public void onChildRemoved(DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(DataSnapshot dataSnapshot, String s) {

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
        if(set == null){
            return null;
        }

        FavoriteLocation latest = null; //the location last visited
        Date mostRecent = new Date(Long.MAX_VALUE);
        for (FavoriteLocation fl : set) {
            if (fl.date().before(mostRecent)) {
                mostRecent = fl.date();
                latest = fl;
            }
        }

        return latest;
    }

    /**
     * Go through visited list, removing locations if not after cutoff time
     */
    private void clearListIfAfterCutoff(){
        if(visitedList.size() == 0) return;

        for(FavoriteLocation favLoc : visitedList ){
            if(!favLoc.afterCutoffTime()) {
                Log.v("NotificationService", favLoc.getName() + " removed");
                visitedList.remove(favLoc);
            }
        }
    }
}

