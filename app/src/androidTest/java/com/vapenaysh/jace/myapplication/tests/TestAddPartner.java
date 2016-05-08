package com.vapenaysh.jace.myapplication.tests;

import android.test.suitebuilder.annotation.MediumTest;

import com.vapenaysh.jace.myapplication.AddPartner;
import com.vapenaysh.jace.myapplication.Constants;

import junit.framework.TestCase;

/**
 * Created by bijanfarahani on 5/7/16.
 */
public class TestAddPartner extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void testOnCreate() throws Exception {

    }
    @MediumTest
    public void testGetRegId() throws Exception {
        AddPartner a = new AddPartner();
        a.getRegId();
        assertNotNull(a.gcm);
        //suspicion that our code is wrong in its logic
        assertNotSame(a.gcm, Constants.PROJECT_NUMBER);

    }
    public void testOnClick() throws Exception {

    }
}