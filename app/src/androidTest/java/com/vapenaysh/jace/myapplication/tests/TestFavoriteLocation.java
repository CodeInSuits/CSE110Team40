package com.vapenaysh.jace.myapplication.tests;

import com.google.android.gms.maps.model.LatLng;
import com.vapenaysh.jace.myapplication.FavoriteLocation;

import junit.framework.TestCase;


/**
 *
 * Works as of 5/8 5:11pm
 *
 * Created by adamabadilla on 5/8/16.
 *
 * Story 2: User wants to set a favorite location
 *
 * Unit Testing the fuctionality of FavoriteLocation.java
 *
 * Scenario Testing is performed in TestGPDTrackerService
 */

public class TestFavoriteLocation extends TestCase {

    FavoriteLocation favoriteLocation;

    // Constructor
    public TestFavoriteLocation() throws Exception {

        // Set Up J-Unit Test Environment
        super.setUp();

    }

    // Test getName function - return stored name
    public void test_getName() {

        favoriteLocation = new FavoriteLocation(new LatLng(1, 1), "test");
        String name = favoriteLocation.getName();
        assertEquals("test", name);
    }

    // Test setName function - change name to new string
    public void test_setName() {

        favoriteLocation = new FavoriteLocation(new LatLng(1,1), "test");
        favoriteLocation.setName("test2");
        assertEquals("test2", favoriteLocation.getName());

    }


    // Test consecutive get/set Name function calls - return newly stored name
    public void test_getAndSetName() {

        favoriteLocation = new FavoriteLocation(new LatLng(1,1), "test");
        favoriteLocation.setName("test2");
        String name = favoriteLocation.getName();
        assertEquals(name, favoriteLocation.getName());
    }


    // Test getCoord function - return stored coordinate
    public void test_getCoord() {

        LatLng coord = new LatLng(1,1);
        favoriteLocation = new FavoriteLocation(coord, "test");
        assertEquals(coord, favoriteLocation.coordinate());

    }

}




