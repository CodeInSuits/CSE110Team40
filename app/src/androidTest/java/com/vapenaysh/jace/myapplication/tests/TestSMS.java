package com.vapenaysh.jace.myapplication.tests;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.vapenaysh.jace.myapplication.FavoriteLocation;
import com.vapenaysh.jace.myapplication.SentSMS;

import junit.framework.TestCase;

import static junit.framework.Assert.assertEquals;

/**
 *
 * Works as of 5/8 4:56PM
 *
 * Created by XuanpeiEstherOuyang on 5/8/16.
 * Testing the functionality of SentSMS.java
 *
 * [Scenario 4.3]
 * I arrive at one of my favorite locations
 * Given that I have a partner
 * And have set the location as one of my favorites,
 * When I am less than 1/10 of a mile away from that location,
 * Then my partner will be notified with my visit by a text and a sound
 *
 */

public class TestSMS extends TestCase {

    SentSMS sentSMS_obj;

    // Constructor
    public TestSMS() throws Exception {

        super.setUp();

        // Instantiate new SMS object
        sentSMS_obj = new SentSMS();
    }

    // Test sendSMS formatting function - CSE Lab
    public void test_sendSms_withlocation() {

        FavoriteLocation favLoc = new FavoriteLocation(new LatLng(1,1), "CSE Lab");
        String msg = sentSMS_obj.sendSms(favLoc);
        Log.e("LOCA",msg);

        assertEquals("Your partner is nearby CSE Lab", msg);
    }

    // Test sendSMS formatting function - Geisel Library
    public void test_sendSms_withlocation2() {

        FavoriteLocation favLoc = new FavoriteLocation(new LatLng(1,1), "Geisel Library");
        String msg = sentSMS_obj.sendSms(favLoc);
        assertEquals("Your partner is nearby Geisel Library", msg);
    }

    // Test sendSMS formatting function - Price Center
    public void test_sendSms_withlocation3() {

        FavoriteLocation favLoc = new FavoriteLocation(new LatLng(1,1), "Price Center");
        String msg = sentSMS_obj.sendSms(favLoc);
        assertEquals("Your partner is nearby Price Center", msg);
    }

    // Test sendSMS formatting function - Empty String
    public void test_sendSms_withlocation4() {

        FavoriteLocation favLoc = new FavoriteLocation(new LatLng(1,1), "");
        String msg = sentSMS_obj.sendSms(favLoc);
        assertEquals("Your partner is nearby ", msg);
    }

    // Test sendSMS formatting function - String Object
    public void test_sendSms_withlocation5() {

        FavoriteLocation favLoc = new FavoriteLocation(new LatLng(1,1), "Library Walk");
        String msg = sentSMS_obj.sendSms(favLoc);
        assertEquals("Your partner is nearby Library Walk", msg);
    }

}