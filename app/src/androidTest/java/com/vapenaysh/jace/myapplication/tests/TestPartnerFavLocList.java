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
 *
 * [Story 1]: Display a list of my partner’s daily location history
 *
 * Scenario 1: My partner doesn’t visit any one of the favorite locations
 * Given that I have a partner
 * And my partner has added a favorite location,
 * when my partner haven’t visited any of his/her favorite locations since 3:00am at that day
 * Then the list of my partner’s visited history will be an empty list.
 *
 * Scenario 2: My partner visits one of the favorite locations
 * Given that I have a partner
 * And my partner has added a favorite location,
 * when my partner visits the given location
 * Then the name of the my partner’s favorite location will appear in my list ordered by
 * the most recent visits.
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
                CustomListViewAdapter customListViewAdapter =
                        new CustomListViewAdapter(partnerFavoriteLocation, favoriteLocationArrayList);
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
                CustomListViewAdapter customListViewAdapter =
                        new CustomListViewAdapter(partnerFavoriteLocation, favoriteLocationArrayList);
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
                CustomListViewAdapter customListViewAdapter =
                        new CustomListViewAdapter(partnerFavoriteLocation, favoriteLocationArrayList);
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