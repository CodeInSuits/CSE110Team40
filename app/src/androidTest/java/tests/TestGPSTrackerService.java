package tests;

import android.location.Location;
import android.test.ServiceTestCase;

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
    public void test_LocationInRange() throws Exception
    {
        LatLng one = new LatLng(1, 1);
        LatLng two = new LatLng(1.5, 1.5);
        boolean shouldbetrue = gpsTrackerService.LocationInRange(one, two);
        assertEquals(shouldbetrue, true);

        LatLng three = new LatLng(1, 1);
        LatLng four = new LatLng(3.5, 1.5);
        boolean shouldbefalse = gpsTrackerService.LocationInRange(three, four);
        assertEquals(shouldbefalse, false);
    }
}
