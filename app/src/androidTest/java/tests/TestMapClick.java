package tests;

import android.test.ActivityInstrumentationTestCase2;

import com.vapenaysh.jace.myapplication.MapsActivity;

/**
 * Created by Matt on 5/1/16.
 */
public class TestMapClick extends ActivityInstrumentationTestCase2 {

    MapsActivity mapsActivity;

    public TestMapClick(){
        super(MapsActivity.class);
        mapsActivity = (MapsActivity) getActivity();
    }


}
