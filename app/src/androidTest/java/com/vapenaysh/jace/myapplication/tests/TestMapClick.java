package com.vapenaysh.jace.myapplication.tests;

import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;

import com.vapenaysh.jace.myapplication.MapsActivity;

/**
 * Created by Matt on 5/1/16.
 */
public class TestMapClick extends ActivityInstrumentationTestCase2<MapsActivity> {

    private MapsActivity mapsActivity;
    private int numLocs;

    public TestMapClick(){
        super(MapsActivity.class);
    }

    /*
    @Override
    protected void setUp(){
        mapsActivity = getActivity();
        SavedLocations.loadLocations(mapsActivity);

        numLocs = SavedLocations.getSize();

        mapsActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mapsActivity.onMapClick(new LatLng(32, -117));

            }
        });
    }
    */

    @UiThreadTest
    public void test_ClickOnMap(){
        /*

        TextView namePrompt = (TextView) mapsActivity.findViewById(R.id.custom_name_prompt);
        RelativeLayout namePromptLayout = (RelativeLayout) mapsActivity.findViewById(R.id.custom_name_layout);

        assertEquals( "(Optional) Add custom name!", namePrompt.getText().toString() );

        assertEquals(View.VISIBLE, namePromptLayout.getVisibility());
        */

    }

    @UiThreadTest
    public void test_SaveName(){
        /*
        final Button btn = (Button)mapsActivity.findViewById(R.id.cancel_btn);


        mapsActivity.saveCustomName(btn);

        assertEquals( numLocs+1, SavedLocations.getSize() );
        */

    }

    //@UiThreadTest
    //public void test_CancelName(){
        /*
        final Button btn = (Button)mapsActivity.findViewById(R.id.save_btn);


        mapsActivity.cancelCustomName(btn);

        assertEquals( numLocs, SavedLocations.getSize() );
        */
    //}

}
