package com.vapenaysh.jace.myapplication.tests;

import android.os.CountDownTimer;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;
import com.vapenaysh.jace.myapplication.FavoriteLocation;
import com.vapenaysh.jace.myapplication.R;

import com.vapenaysh.jace.myapplication.UserCenter;

import junit.framework.Test;

import java.util.Date;

/**
 * Created by Jerry on 5/30/2016.
 */
public class TestUserCenter extends ActivityInstrumentationTestCase2<UserCenter>
{
    UserCenter userCenter;
    public TestUserCenter()
    {
        super(UserCenter.class);
    }
    public void test_showsList()
    {
        userCenter = getActivity();
        final ListView lv = (ListView) userCenter.findViewById(R.id.listView);
        assertNotNull(lv);

        new CountDownTimer(3500, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                //userCenter.flls.add(new FavoriteLocation(new LatLng(0, 0), "test", new Date()));
                //userCenter.fla.notifyDataSetChanged();
                assertNotNull(lv.getChildAt(lv.getFirstVisiblePosition()));
            }
        }.start();
    }
}
