package com.vapenaysh.jace.myapplication;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * MyApplication.java
 *
 * Class for storing customized application information
 *
 * Created by Matt on 5/26/16.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);
    }
}
