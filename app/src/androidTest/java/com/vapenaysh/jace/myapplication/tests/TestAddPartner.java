package com.vapenaysh.jace.myapplication.tests;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static junit.framework.Assert.*;
import com.vapenaysh.jace.myapplication.AddPartner;
import com.vapenaysh.jace.myapplication.PartnerSettings;
import com.vapenaysh.jace.myapplication.PartnershipTracker;
import com.vapenaysh.jace.myapplication.R;

/**
 * Created by bijanfarahani
 *            XuanpeiEstherOuyang on 5/7/16.
 *
 * Testing the functionality of AddPartner.java
 *
 * Scenario 1: User does not have a partner but wants to add one
 * Given that I does not have a partner already,
 * When I wants to add a partner,
 * Then I can enter his/her name and phone number to add the person as my partner
 *
 *
 */
public class TestAddPartner extends ActivityInstrumentationTestCase2<AddPartner> {

    AddPartner addPartner;

    // Constructor
    public TestAddPartner() {

        // Call Superclass
        super(AddPartner.class);
    }

    // Test add Partner Button
    public void test_addPartnerButton() {

        addPartner = getActivity();

        //Check remove partner button
        Button addPartnerButton = (Button) addPartner.findViewById(R.id.add_par);

        // Simulate press
        addPartnerButton.callOnClick();
        assertNotNull(addPartnerButton);
    }

    // Test getRegID Button
    public void test_getRegIdButton() {

        addPartner = getActivity();

        //Check remove partner button
        Button getIDButton = (Button) addPartner.findViewById(R.id.get_self_id);

        // Simulate press
        getIDButton.callOnClick();
        assertNotNull(getIDButton);
    }

    public void test_textViewName() {

        addPartner = getActivity();
        TextView name = (TextView) addPartner.findViewById(R.id.textView);
        assertNotNull(name);
        String partnerName = name.getText().toString();
        assertEquals("Name:", partnerName);
    }

    public void test_textViewPhone() {

        addPartner = getActivity();
        TextView phone = (TextView) addPartner.findViewById(R.id.textView4);
        assertNotNull(phone);
        String partnerPhone = phone.getText().toString();
        assertEquals("Phone Number:", partnerPhone);
    }

    // Test Name Text Edit Field
    public void test_nameTextEdit() {

        addPartner = getActivity();

        // Check Name Text Field
        EditText nameLabel = (EditText) addPartner.findViewById(R.id.par_name);
        String name = nameLabel.getText().toString();
        assertEquals(name, "");

        nameLabel.setText("Esther");
        name = nameLabel.getText().toString();
        assertEquals(name, "Esther");
    }


    // Test Name Text Edit Field
    public void test_numberTextEdit() {

        addPartner = getActivity();

        // Check Phone Text Field
        TextView phoneLabel = (TextView) addPartner.findViewById(R.id.par_name);
        String phone = phoneLabel.getText().toString();
        assertEquals(phone, "");

        phoneLabel.setText("5556");
        phone = phoneLabel.getText().toString();
        assertEquals(phone, "5556");
    }

    // Test the button for RegId
    public void test_RegID(){

        addPartner = getActivity();

        //Check remove partner button
        Button getIDButton = (Button) addPartner.findViewById(R.id.get_self_id);

        // check the edittext for getID
        EditText idText = (EditText) addPartner.findViewById(R.id.self_regId);
        assertEquals(idText.getText().toString(), "Register id");

        // Simulate press
        getIDButton.callOnClick();

        String id = idText.getText().toString();
        assertTrue(id instanceof String );

    }

    // Test the scenario 1 for adding partner
    public void test_add_scenario() {

        AddPartner addPartnerObj = new AddPartner();

        addPartner = getActivity();

        // Check Name Text Field
        EditText nameLabel = (EditText) addPartner.findViewById(R.id.par_name);
        String name = nameLabel.getText().toString();
        assertEquals(name, "");

        nameLabel.setText("Esther");
        name = nameLabel.getText().toString();
        assertEquals(name, "Esther");

        // Check Phone Text Field
        TextView phoneLabel = (TextView) addPartner.findViewById(R.id.par_name);
        String phone = phoneLabel.getText().toString();
        assertEquals(phone, "");

        phoneLabel.setText("5556");
        phone = phoneLabel.getText().toString();
        assertEquals(phone, "5556");

        //Check remove partner button
        Button addPartnerButton = (Button) addPartner.findViewById(R.id.add_par);

        // Simulate press
        addPartnerButton.callOnClick();

        assertEquals(addPartnerObj.getUname(),"Esther");
        assertEquals(addPartnerObj.getPhone(),"5556");
    }
}