package com.vapenaysh.jace.myapplication;
import android.app.IntentService;
import android.os.Handler;
import android.content.Intent;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by XuanpeiEstherOuyang on 5/6/16.
 */
public class GcmMessageHandler extends IntentService {

    String mes;
    private Handler handler;

    public GcmMessageHandler(){
        super("GcmMessageHandler");
    }

    @Override
    public void onCreate(){
        super.onCreate();
        handler = new Handler();
    }

    @Override
    protected void onHandleIntent(Intent intent){
        Bundle extras = intent.getExtras();

        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        String messageType = gcm.getMessageType(intent);
        mes = extras.getString("title");
        showToast();
        Log.i("GCM", "Received: (" + messageType + ") " + extras.getString("title"));

        GcmReceiver.completeWakefulIntent(intent);
    }

    public void showToast(){
        handler.post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(), mes, Toast.LENGTH_LONG).show();
            }
        });
    }

}
