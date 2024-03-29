package com.vapenaysh.jace.myapplication.tests;

import android.content.Intent;
import android.os.IBinder;
import android.test.ServiceTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;

import com.google.android.gms.maps.model.LatLng;
import com.vapenaysh.jace.myapplication.GPSTrackerService;

/**
 * [Story 4]: Get notification when the partner gets close to one of their favorite locations
 *
 * Scenario 1: I am notified about my partner
 * Given that I have a partner
 * And my partner has added his/her favorite location,
 * When my partner is less than 1/10 of a mile away from that location,
 * Then I will be notified of the visit by a text and a sound
 *
 *
 * Scenario 2: I am not notified about my partner
 * Given that I have a partner
 * And my partner has not added a specific location
 * Then I will not be notified when they visit different locations.
 *
 * Created by Jace on 5/7/16.
 */
public class TestGPSTrackerService extends ServiceTestCase<GPSTrackerService>{

    GPSTrackerService gpsTrackerService;

    public TestGPSTrackerService() {
        super(GPSTrackerService.class);
        gpsTrackerService = new GPSTrackerService();
    }

    //Scenario: Get notification when the partner gets close
    //Testing starting a GPSTrackerService
    @SmallTest
    public void test_Startable() {
        Intent startIntent = new Intent();
        startIntent.setClass(getSystemContext(), GPSTrackerService.class);
        startService(startIntent);
    }

    //Scenario: Get notification when the partner gets close
    //Testing Binding for GPSTrackerService
    @MediumTest
    public void test_Bindable() {
        Intent startIntent = new Intent();
        startIntent.setClass(getContext(), GPSTrackerService.class);
        IBinder service = bindService(startIntent);
        assertNotNull(service);
        assertNotNull(((GPSTrackerService.LocalBinder)service).getService());
    }

    //Scenario: Get notification when the partner gets close
    //Testing math function which calculates distances between points.
    public void test_calculateDistanceBetween() throws Exception
    {

        LatLng one = new LatLng(1, 1);
        LatLng two = new LatLng(1, 1);
        boolean shouldbetrue = gpsTrackerService.calculateDistanceBetween(one, two) < .1;
        assertEquals(true, shouldbetrue);

        LatLng three = new LatLng(1, 1);
        LatLng four = new LatLng(3.5, 1.5);
        boolean shouldbefalse = gpsTrackerService.calculateDistanceBetween(three, four) < .1;
        assertEquals(shouldbefalse, false);
    }
}
