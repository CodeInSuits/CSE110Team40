package com.vapenaysh.jace.myapplication.tests;

import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.TextView;
import android.widget.Button;
import com.vapenaysh.jace.myapplication.PartnerSettings;
import com.vapenaysh.jace.myapplication.R;

/**
 * Created by XuanpeiEstherOuyang on 5/7/16.
 *
 * Testing the functionality of RemovePartner.java
 *
 * Scenario 1: User has a partner and wants to remove it
 * Given that I have a partner
 * When I click on remove button in the partner panel
 * Then I will be unpaired with the previous paired partner after clicking on the remove partner
 * button and return back to Homepage.
 *
 *
 * Tests: Remove Button functionality
 *        TextEdit functionality
 *
 */

public class TestRemovePartner extends ActivityInstrumentationTestCase2<PartnerSettings> {

    PartnerSettings removePartner;

    public TestRemovePartner() {

        // Call Superclass
        super(PartnerSettings.class);
    }


    // Test Remove Partner Button
    public void test_removePartnerButton() {

        removePartner = getActivity();

        //Check remove partner button
        Button removePartnerButton = (Button) removePartner.findViewById(R.id.remove_partner_button);

        // Simulate press
        removePartnerButton.callOnClick();
        assertNotNull(removePartnerButton);
    }

    // Test Name Text Edit Field
    @UiThreadTest
    public void test_nameTextEdit() {

        removePartner = getActivity();

        // Check Name Text Field
        TextView nameLabel = (TextView) removePartner.findViewById(R.id.show_name);

        // Given Partner Added
        nameLabel.setText("Esther");

        assertEquals(nameLabel.getText().toString(), "Esther");
    }


    // Test Name Text Edit Field
    @UiThreadTest
    public void test_numberTextEdit() {

        removePartner = getActivity();

        // Check Phone Text Field
        TextView phoneLabel = (TextView) removePartner.findViewById(R.id.show_phone);

        // Given Partner Added
        phoneLabel.setText("5556");

        assertEquals(phoneLabel.getText().toString(), "5556");

    }

    // Test the scenario 1 for removing partner
    @UiThreadTest
    public void testRemoveScenario(){

        removePartner = getActivity();

        // Given Partner Added

        // Get the Name Text Field
        TextView nameLabel = (TextView) removePartner.findViewById(R.id.show_name);
        nameLabel.setText("Esther");
        String name = nameLabel.getText().toString();

        // Get the Phone Number Text Field
        TextView phoneLabel = (TextView) removePartner.findViewById(R.id.show_phone);
        phoneLabel.setText("5556");
        String phone = phoneLabel.getText().toString();

        assertEquals(removePartner.getName(), name);
        assertEquals(removePartner.getNumber(), phone);

        Button removePartnerButton = (Button) removePartner.findViewById(R.id.remove_partner_button);
        removePartnerButton.callOnClick();

        // name and phone should be "N/A" now
        assertEquals("N/A",removePartner.getName());
        assertEquals("N/A",removePartner.getNumber());
    }

}
