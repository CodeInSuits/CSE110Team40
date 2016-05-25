package com.vapenaysh.jace.myapplication;

import android.app.IntentService;
import android.app.NotificationManager;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

/**
 * Created by Matt on 5/23/16.
 */
public class NotificationService extends IntentService {
    private Firebase firebase;
    private static final String URL = Constants.FIREBASE_URL;
    private static final String LOC_URL = Constants.LOC_URL;

    public NotificationService(){
        super("notification service");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.v("NotificationService", "Service Started");

        if(intent != null){
            synchronized (this)
            {
                String partner = intent.getStringExtra(Constants.PARTNER_KEY);
                firebase = new Firebase(URL + partner + LOC_URL);
                firebase.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        String data = dataSnapshot.getValue(String.class);

                        NotificationCompat.Builder mBuilder =
                                new NotificationCompat.Builder(getApplicationContext())
                                        .setSmallIcon(R.drawable.heart2)
                                        .setContentTitle("Notification")
                                        .setContentText(data);

                        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                        manager.notify(1, mBuilder.build());
                        Log.e("HELLO", mBuilder.build().toString());



                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
            }
        }
    }
}
