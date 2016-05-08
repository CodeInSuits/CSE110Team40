package com.vapenaysh.jace.myapplication.tests;

import android.content.Intent;
import android.os.IBinder;
import android.test.ServiceTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;

import com.google.android.gms.maps.model.LatLng;
import com.vapenaysh.jace.myapplication.GPSTrackerService;

/**
 * Created by Jace on 5/7/16.
 */
public class TestGPSTrackerService extends ServiceTestCase<GPSTrackerService>{

    GPSTrackerService gpsTrackerService;

    public TestGPSTrackerService() {
        super(GPSTrackerService.class);
    }

    public void test_onLocationChange() throws Exception {
        setUp();
    }

    @SmallTest
    public void testStartable() {
        Intent startIntent = new Intent();
        startIntent.setClass(getContext(), GPSTrackerService.class);
        startService(startIntent);
    }

    @MediumTest
    public void testBindable() {
        Intent startIntent = new Intent();
        startIntent.setClass(getContext(), GPSTrackerService.class);
        IBinder service = bindService(startIntent);
        assertNotNull(service);
        assertNotNull(((GPSTrackerService.LocalBinder)service).getService());
    }
    public void test_LocationInRange() throws Exception
    {
        LatLng one = new LatLng(1, 1);
        LatLng two = new LatLng(1.5, 1.5);
        boolean shouldbetrue = gpsTrackerService.calculateDistanceBetween(one, two) < .1;
        assertEquals(shouldbetrue, true);

        LatLng three = new LatLng(1, 1);
        LatLng four = new LatLng(3.5, 1.5);
        boolean shouldbefalse = gpsTrackerService.calculateDistanceBetween(three, four) < .1;
        assertEquals(shouldbefalse, false);
    }
}
