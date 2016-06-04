package com.vapenaysh.jace.myapplication.tests;

import android.content.Intent;
import android.os.CountDownTimer;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;
import com.vapenaysh.jace.myapplication.FavoriteLocation;
import com.vapenaysh.jace.myapplication.FavoriteLocationList;
import com.vapenaysh.jace.myapplication.R;
import com.vapenaysh.jace.myapplication.UserCenter;

import java.util.Date;

/**
 * Created by Jerry on 5/30/2016.
 */
public class TestNotEmptyList extends ActivityInstrumentationTestCase2<UserCenter>
{
    UserCenter userCenter;
    public TestNotEmptyList()
    {
        super(UserCenter.class);
    }
    @Override
    protected void setUp() throws Exception
    {
        Intent i = new Intent();
        i.putExtra("DisplayName", "TEST2");
        i.putExtra("DisplayEmail", "testolddata2");
        i.putExtra("BackendUID", "asdffdsa");
        i.putExtra("PartnerEmail", "testolddata");
        i.putExtra("PartnerName", "TEST");
        i.putExtra("ImageURL", "http://pixel.nymag.com/imgs/daily/following/2016/05/12/12376681_1063072477089016_3145722177588178860_n.nocrop.w536.h2147483647.2x.jpg");
        //DAT BOI
        setActivityIntent(i);
        FavoriteLocationList favoriteLocationList = new FavoriteLocationList("testolddata2");
        favoriteLocationList.removeAllLocations();
        favoriteLocationList.writeLocation(new FavoriteLocation(new LatLng(0, 0), "rand paul is a meme", new Date(System.currentTimeMillis())));
    }
    /*Test 3: (User Stories 1.1, 1.2) {(not pass 3am, visit some location), nonempty list}
        Click on app icon to launch the app
        Log into the app
        Assume the current time is 6 am
        When user click on the partner visited favorite location history button
        My partner haven’t visit any favorite location yet since 3 am at that day
        Then the partner then visits one of their favorite locations.
        It is then added to the user’s list of favorite locations history. [1.2]*/
    public void test_not_empty_list()
    {
        userCenter = getActivity();
        final ListView lv = (ListView) userCenter.findViewById(R.id.listView);
        assertNotNull(lv);
        new CountDownTimer(3500, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                assertNotNull(lv.getChildAt(lv.getFirstVisiblePosition()));

            }
        }.start();
    }
}
