package com.vapenaysh.jace.myapplication.tests;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;
import com.vapenaysh.jace.myapplication.CustomListViewAdapter;
import com.vapenaysh.jace.myapplication.FavoriteLocation;
import com.vapenaysh.jace.myapplication.PartnerFavoriteLocation;
import com.vapenaysh.jace.myapplication.R;
import com.vapenaysh.jace.myapplication.VibeToneSetting;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by bijancfarahani on 5/30/16.
 *
 * Story 2: Keep a list of the partner’s favorite locations: name, assigned location vibration
 * tones and location sound tones.
 *
 * Scenario 1:
 * Given that I have a partner
 * And my partner has set at least one favorite location
 * When I enter the corresponding screen for displaying my partner’s favorite location.
 * Then I can see the name, location, vibration pattern, and names of assigned location vibration
 * tones and location sound tones of each location shown up next to each location name on the app.
 *
 * Scenario 2:
 * Given that I have a partner
 * And my partner has never set a favorite location
 * When I enter the corresponding screen for displaying my partner’s favorite location.
 * Then I can see an empty list with no name or any extra information on the app.
 *
 *
 * BDD Tests: Given a users partner has no favorite locations, then the
 * user should have an empty list of the partners favorite locations.
 * When the partner adds a favorite location, then the users list should update to reflect this
 * and when the partner removes a favorite location, the users list should also reflect this
 *
 */
public class TestPartnerFavLocList extends ActivityInstrumentationTestCase2<PartnerFavoriteLocation> {

    PartnerFavoriteLocation partnerFavoriteLocation;

    public TestPartnerFavLocList(Class<PartnerFavoriteLocation> activityClass) {
        super(activityClass);
    }

    @SmallTest
    public void testEmptyList() {
        partnerFavoriteLocation = getActivity();
        partnerFavoriteLocation.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ListView listView = (ListView) partnerFavoriteLocation.findViewById(R.id.list);
                ArrayList<FavoriteLocation> favoriteLocationArrayList = new ArrayList<FavoriteLocation>();
                CustomListViewAdapter customListViewAdapter = new CustomListViewAdapter(partnerFavoriteLocation, favoriteLocationArrayList);
                listView.setAdapter(customListViewAdapter);
                assertTrue(customListViewAdapter.isEmpty());
            }
        });
    }

    @SmallTest
    public void testAddItem() {
        partnerFavoriteLocation = getActivity();
        partnerFavoriteLocation.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ListView listView = (ListView) partnerFavoriteLocation.findViewById(R.id.list);
                ArrayList<FavoriteLocation> favoriteLocationArrayList = new ArrayList<FavoriteLocation>();
                CustomListViewAdapter customListViewAdapter = new CustomListViewAdapter(partnerFavoriteLocation, favoriteLocationArrayList);
                listView.setAdapter(customListViewAdapter);
                LatLng coor = new LatLng(5.5, 3.2);
                FavoriteLocation loc = new FavoriteLocation(coor, "testLoc");
                favoriteLocationArrayList.add(loc);
                assertFalse(customListViewAdapter.isEmpty());
            }
        });
    }

    @SmallTest
    public void testRemoveItem() {
        partnerFavoriteLocation = getActivity();
        partnerFavoriteLocation.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ListView listView = (ListView) partnerFavoriteLocation.findViewById(R.id.list);
                ArrayList<FavoriteLocation> favoriteLocationArrayList = new ArrayList<FavoriteLocation>();
                CustomListViewAdapter customListViewAdapter = new CustomListViewAdapter(partnerFavoriteLocation, favoriteLocationArrayList);
                listView.setAdapter(customListViewAdapter);
                LatLng coor = new LatLng(5.5, 3.2);
                FavoriteLocation loc = new FavoriteLocation(coor, "testLoc");
                favoriteLocationArrayList.add(loc);
                favoriteLocationArrayList.remove(0);
                assertTrue(customListViewAdapter.isEmpty());
            }
        });
    }
}