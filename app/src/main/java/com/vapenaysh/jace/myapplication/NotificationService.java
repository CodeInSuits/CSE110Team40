package com.vapenaysh.jace.myapplication;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;

import java.util.ArrayList;
import java.util.Date;

/**
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
        Log.v("NotificationService", "Service Started");
        visitedList = new ArrayList<>();

        //ring = new RingToneManager();
        //vibe = new VibeToneManager((Vibrator) getSystemService(VIBRATOR_SERVICE));

        if (intent != null) {
            synchronized (this) {
                String partner = intent.getStringExtra(Constants.PARTNER_EMAIL);
                FirebaseDatabase locationsDB = FirebaseDatabase.getInstance();
                partnerDb = locationsDB.getReference(partner + Constants.LOC_URL);


                partnerDb.addValueEventListener(new com.google.firebase.database.ValueEventListener() {

                    @Override
                    public void onDataChange(com.google.firebase.database.DataSnapshot dataSnapshot) {
                        GenericTypeIndicator<ArrayList<FavoriteLocation>> t = new GenericTypeIndicator<ArrayList<FavoriteLocation>>() {};
                        ArrayList<FavoriteLocation> data = dataSnapshot.getValue(t);

                        FavoriteLocation latest = getMostRecentlyVisited(data);
                        NotificationCompat.Builder mBuilder = null;

                        clearListIfAfterCutoff();

                        //ARRIVED AT A LOCATION
                        if (latest != null && !visitedList.contains(latest) && latest.afterCutoffTime() ) {
                            visitedList.add(latest);
                            mBuilder = new NotificationCompat.Builder(getApplicationContext())
                                            .setSmallIcon(R.drawable.heart2)
                                            .setContentTitle("Arrival")
                                            .setContentText("Partner arrived at " + latest.getName());


                            // code for calling arrival sound notification method (FavoriteLocation object)
                            // ring.playArrivalTone();

                            // code for calling arrival vibration notification method (FavoriteLocation obejct)
                            //vibe.playArrivalTone();

                            // code for calling location sound notification method (FavoriteLocation object)
                            // ring.playTone(latest);

                            // code for calling location vibration notification method (FavoriteLocation obejct)
                            //vibe.playTone(latest);
                        }
                        //DEPARTED FROM A LOCATION
                        else if(latest != null && visitedList.contains(latest) ){
                            mBuilder = new NotificationCompat.Builder(getApplicationContext())
                                    .setSmallIcon(R.drawable.heart2)
                                    .setContentTitle("Departure")
                                    .setContentText("Partner departed from " + latest.getName());
                            visitedList.remove(latest);

                            // code for calling departure sound notification method (FavoriteLocation object)
                            // ring.playDepartureTone();

                            // code for calling departure vibration notification method (FavoriteLocation obejct)
                            //vibe.playDepartureTone();

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
        if(set == null){
            return null;
        }

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

    /**
     * Go through visited list, removing locations if not after cutoff time
     */
    private void clearListIfAfterCutoff(){
        if(visitedList.size() == 0) return;

        for(FavoriteLocation favLoc : visitedList ){
            if(!favLoc.afterCutoffTime()) {
                visitedList.remove(favLoc);
            }
        }
    }
}

