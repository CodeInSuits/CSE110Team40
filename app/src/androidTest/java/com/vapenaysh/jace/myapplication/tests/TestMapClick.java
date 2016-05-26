package com.vapenaysh.jace.myapplication.tests;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;
import com.vapenaysh.jace.myapplication.FavoriteLocation;
import com.vapenaysh.jace.myapplication.FavoriteLocationList;
import com.vapenaysh.jace.myapplication.MapsActivity;

import java.util.ArrayList;

/**
 * Tests scenarios involving map clicking and added a location with a custom/default name
 * Created by Matt on 5/1/16.
 *
 * Scneario 1 The location is given a customized name when it is set via tapping on the map
 *
 * Given that the user taps a coordinate/address on the map
 * When the user selects the coordinate,
 * And the user enters a name for the favorite location
 * Then the location should be added to the list of favorites with the name the user entered
 * and a default notification sound associated with the location.
 * And the location on the map will be marked with a red pin and location name
 *
 * Scenario 2 The location is given a default name when it is set via tapping on the map
 *
 * Given that the user taps a coordinate/address on the map
 * When the user selects the coordinate,
 * And the user doesn’t enter a name for the favorite location
 * Then the location should be added to the list of favorites with an enumerated default name
 * (such as “Location 1”) and a default notification sound associated with the location.
 * And the location on the map will be marked with a red pin and location name
 *
 */

public class TestMapClick extends ActivityInstrumentationTestCase2<MapsActivity> {

    private MapsActivity mapsActivity;
    private int numLocs;
    FavoriteLocationList favoriteLocationList = null;
    EditText customNameEditText;
    Button saveBtn, cancelBtn;

    public TestMapClick(){
        super(MapsActivity.class);
    }

    @Override
    protected void setUp(){
       /* favoriteLocationList = new FavoriteLocationList();

        Intent i = new Intent(getInstrumentation().getTargetContext(), MapsActivity.class);
        i.putExtra("FavoriteLocations", favoriteLocationList);
        setActivityIntent(i);
        mapsActivity = getActivity();

        numLocs = favoriteLocationList.getSize();
        customNameEditText = (EditText) mapsActivity.findViewById(R.id.custom_name);

        saveBtn = (Button) mapsActivity.findViewById(R.id.save_btn);
        cancelBtn = (Button) mapsActivity.findViewById(R.id.cancel_btn);*/
    }

    /**
     *   The location is given a customized name when it is set via tapping on the map
         Given that the user taps a coordinate/address on the map
         When the user selects the coordinate,
         And the user enters a name for the favorite location
         Then the location should be added to the list of favorites with the name the user entered
         and a default notification sound associated with the location.
         And the location on the map will be marked with a red pin and location name
     */
    /*
    @UiThreadTest
    public void test_CustomNameOnMapClick(){
        LatLng latLng = new LatLng(32, -117);
        clickMap(latLng);
        enterName("TEST");
        saveBtn.performClick();

        //Favorite locaiton list should increase by one
        assertEquals( numLocs+1, favoriteLocationList.getSize() );

        assertTrue( setContainsLocation(new FavoriteLocation(latLng, "TEST")) );

    }
*/
    /**
     * The location is given a default name when it is set via tapping on the map
     Given that the user taps a coordinate/address on the map
     When the user selects the coordinate,
     And the user doesn’t enter a name for the favorite location
     Then the location should be added to the list of favorites with an enumerated default name
     (such as “Location 1”) and a default notification sound associated with the location.
     And the location on the map will be marked with a red pin and location name
     */
   /*
    @UiThreadTest
    public void test_DefaultNameOnMapClick(){
        LatLng latLng = new LatLng(32,-117);
        clickMap(latLng);
        enterName("");
        saveBtn.performClick();

        //Favorite locaiton list should increase by one
        assertTrue( setContainsLocation(new FavoriteLocation(latLng, "Location" + numLocs )) );
        assertEquals( numLocs+1, favoriteLocationList.getSize() );
    }
*/
    private void enterName(String name){
        customNameEditText.setText(name);
    }


    private void clickMap(final LatLng latLng){
        mapsActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mapsActivity.onMapClick(latLng);
            }
        });
    }

    private boolean setContainsLocation(FavoriteLocation loc){
        ArrayList<FavoriteLocation> set = favoriteLocationList.getLocations();
        return set.contains(loc);
    }


}
