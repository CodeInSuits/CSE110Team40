package com.vapenaysh.jace.myapplication;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Matt on 5/23/16.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
