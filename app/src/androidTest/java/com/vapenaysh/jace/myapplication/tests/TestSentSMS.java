package com.vapenaysh.jace.myapplication.tests;

import com.vapenaysh.jace.myapplication.SentSMS;

import static junit.framework.Assert.assertEquals;


/**
 * Created by XuanpeiEstherOuyang on 5/8/16.
 *
 * Testing the functionality of SentSMS.java
 */


public class TestSentSMS {

    SentSMS sentSMS_obj;


    // Constructor
    public TestSentSMS() {

        // Call the constructor in SentSMS
        sentSMS_obj = new SentSMS();
    }

    // test on method sendSms
    public void test_sendSms_withlocation(){

        String msg = sentSMS_obj.sendSms("CSE Lab");
        assertEquals("Your partner is nearby CSE Lab", msg);
    }

}
