package tests;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;
import android.widget.Button;
import com.vapenaysh.jace.myapplication.PartnerSettings;
import com.vapenaysh.jace.myapplication.R;

/**
 * Created by adamabadilla on 5/7/16.
 *
 * Testing the functionality of RemovePartner.java
 *
 *  Given that I have a partner
 *  When I click on remove button in the partner panel
 *  Then I will be unpaired with the previous paired partner after confirming the removal of the partner.
 *
 *  Tests: Remove Button functionality, TextEdit functionality
 *
 */

public class TestRemovePartner extends ActivityInstrumentationTestCase2<PartnerSettings> {

    PartnerSettings removePartner;

    // Constructor
    public TestRemovePartner() {

        // Call Superclass
        super(PartnerSettings.class);
    }


    // Test Remove Partner Button
    public void test_removePartner() {

        removePartner = getActivity();

        //Check remove partner button
        Button removePartnerButton = (Button) removePartner.findViewById(R.id.remove_partner_button);

        // Simulate press
        removePartnerButton.callOnClick();
        assertNotNull(removePartnerButton);


    }

    // Test Name Text Edit Field
    public void test_nameTextEdit() {

        removePartner = getActivity();
/*
        //Check Name Text Field
        TextView nameLabel = (TextView) removePartner.findViewById(R.id.partner_name);
        String name = nameLabel.getText().toString();
        assertEquals("N/A", name);
        */

    }

    // Test Name Label Field
    public void test_name() {

        removePartner = getActivity();

        //Check Name Label Field
        TextView nameLabel = (TextView) removePartner.findViewById(R.id.show_name);
        String name = nameLabel.getText().toString();
        assertEquals("Name:", name);

    }

}
