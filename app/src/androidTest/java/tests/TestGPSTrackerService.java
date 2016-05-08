package tests;

import android.test.ServiceTestCase;

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
}
