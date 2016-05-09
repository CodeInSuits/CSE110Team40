package com.vapenaysh.jace.myapplication.tests;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.TextView;

import static junit.framework.Assert.*;
import com.vapenaysh.jace.myapplication.AddPartner;
import com.vapenaysh.jace.myapplication.PartnerSettings;
import com.vapenaysh.jace.myapplication.PartnershipTracker;
import com.vapenaysh.jace.myapplication.R;

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
    AddPartner addPartner;

    // Constructor
    public TestAddPartner_Scenario1() {

        // Call Superclass
        super(AddPartner.class);
    }


    public void test_textViewName() {
        addPartner = getActivity();
        TextView name = (TextView) addPartner.findViewById(R.id.textView);
        assertNotNull(name);
        String partnerName = name.getText().toString();
        assertEquals("Name:",partnerName);
    }
    public void test_textViewPhone() {
        addPartner = getActivity();
        TextView phone = (TextView) addPartner.findViewById(R.id.textView4);
        assertNotNull(phone);
        String partnerPhone = phone.getText().toString();
        assertEquals("Phone Number:",partnerPhone);
    }

    public void test_button() {
        addPartner = getActivity();
        Button button = (Button) addPartner.findViewById(R.id.add_par);

    }
}