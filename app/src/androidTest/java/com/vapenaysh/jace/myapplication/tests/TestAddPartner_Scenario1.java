package com.vapenaysh.jace.myapplication.tests;

import android.test.ActivityInstrumentationTestCase2;
import static junit.framework.Assert.*;
import com.vapenaysh.jace.myapplication.AddPartner;
import com.vapenaysh.jace.myapplication.PartnerSettings;
import com.vapenaysh.jace.myapplication.PartnershipTracker;

/**
 * Created by XuanpeiEstherOuyang on 5/7/16.
 *
 * Scenario 1: User does not have a partner but wants to add one
 * Given that I does not have a partner already,
 * When I wants to add a partner,
 * Then I can enter his/her name and phone number to add the person as my partner
 *
 */
public class TestAddPartner_Scenario1 extends ActivityInstrumentationTestCase2<AddPartner> {


    // Constructor
    public TestAddPartner_Scenario1() {

        // Call Superclass
        super(AddPartner.class);
    }

    // Setting up the given part
    public void setUp() {

    }

    public void testEditText_Name() {

    }




}