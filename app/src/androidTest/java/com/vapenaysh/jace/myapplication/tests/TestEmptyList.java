package com.vapenaysh.jace.myapplication.tests;

import android.content.Intent;
import android.os.CountDownTimer;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;
import com.vapenaysh.jace.myapplication.FavoriteLocation;
import com.vapenaysh.jace.myapplication.FavoriteLocationList;
import com.vapenaysh.jace.myapplication.R;

import com.vapenaysh.jace.myapplication.UserCenter;

import junit.framework.Test;

import java.util.Date;

/**
 * Created by Jerry on 5/30/2016.
 */
public class TestEmptyList extends ActivityInstrumentationTestCase2<UserCenter>
{
    UserCenter userCenter;
    public TestEmptyList()
    {
        super(UserCenter.class);
    }
    @Override
    protected void setUp() throws Exception
    {
        Intent i = new Intent();
        i.putExtra("DisplayName", "TEST");
        i.putExtra("DisplayEmail", "testolddata");
        i.putExtra("BackendUID", "fdsaasdf");
        i.putExtra("PartnerEmail", "testolddata2");
        i.putExtra("PartnerName", "TEST2");
        i.putExtra("ImageURL", "http://pixel.nymag.com/imgs/daily/following/2016/05/12/12376681_1063072477089016_3145722177588178860_n.nocrop.w536.h2147483647.2x.jpg");
        setActivityIntent(i);
        FavoriteLocationList favoriteLocationList = new FavoriteLocationList("testolddata");
        favoriteLocationList.removeAllLocations();
        favoriteLocationList.writeLocation(new FavoriteLocation(new LatLng(0, 0), "rand paul is a meme", new Date(System.currentTimeMillis()-2*24*60*60*1000)));
    }
    /*Test 2: (User Stories 1.1, 1.2) {(not pass 3am, haven’t visit any location), empty list}
        Click on app icon to launch the app
        Log into the app
        Assume the current time is 6 am
        When user click on the partner visited favorite location history button
        My partner haven’t visit any favorite location yet since 3 am at that day
        The list will used to display an empty list in the UI. [1.1]
        Then the partner then visits one of their favorite locations.
        It is then added to the user’s list of favorite locations history. [1.2]*/
    public void test_empty_list()
    {
        userCenter = getActivity();
        final ListView lv = (ListView) userCenter.findViewById(R.id.listView);
        assertNotNull(lv);
        new CountDownTimer(3500, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                assertNull(lv.getChildAt(lv.getFirstVisiblePosition()));
            }
        }.start();
    }
}
