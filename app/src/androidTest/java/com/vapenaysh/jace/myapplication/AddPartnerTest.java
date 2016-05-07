package com.vapenaysh.jace.myapplication;

import junit.framework.TestCase;

/**
 * Created by bijanfarahani on 5/7/16.
 */
public class AddPartnerTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();
    }

    public void testOnCreate() throws Exception {

    }


    public void testGetRegId() throws Exception {
        AddPartner a = new AddPartner();
        a.getRegId();
        assertNotNull(a.gcm);
    }

    public void testOnClick() throws Exception {

    }
}