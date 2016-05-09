package com.vapenaysh.jace.myapplication.tests;

import com.google.android.gms.maps.model.LatLng;
import com.vapenaysh.jace.myapplication.FavoriteLocation;
import com.vapenaysh.jace.myapplication.SentSMS;

import static junit.framework.Assert.assertEquals;


/**
 * Created by XuanpeiEstherOuyang on 5/8/16.
 *
 * JUnit testing the functionality of SentSMS.java
 *
 */


public class TestSentSMS {

    SentSMS sentSMS_obj;
    FavoriteLocation fav_loc;

    // Constructor
    public void setUp() {

        LatLng location = new LatLng(-34.8799074, 174.7565664);

        // Call the constructor in SentSMS
        sentSMS_obj = new SentSMS();
        fav_loc = new FavoriteLocation(location, "CSE Lab");
    }

    // test on method sendSms
    public void test_sendSms_withlocation(){

        String msg = sentSMS_obj.sendSms(fav_loc);
        assertEquals("Your partner is nearby CSE Lab", msg);
    }

}
