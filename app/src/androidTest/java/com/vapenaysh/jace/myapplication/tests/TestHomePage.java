package com.vapenaysh.jace.myapplication.tests;

import android.content.Context;
import android.content.SharedPreferences;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.Button;

import com.vapenaysh.jace.myapplication.HomePage;
import com.vapenaysh.jace.myapplication.R;

/**
 * Created by adamabadilla
 *            XuanpeiEstherOuyang on 5/7/16.
 *
 * Scenario 1:
 * Given that the user already logged in the app
 * And the user doesn’t have a partner,
 * When the user click on the partnerSetting button
 * Then user will be shown the page for adding a partner
 *
 *
 * Scenario 2:
 * Given that the user already logged in the app
 * And the user has a partner,
 * When the user click on the partnerSetting button
 * Then user will be shown the page for showing partner information and removing partner
 *
 * Scenario 3:
 * Given that the user already logged in the app
 * When the user click on the add favorite location button
 * Then user will be shown the map page
 *
 * Scenario 4:
 * Given that the user already logged in the app
 * When the user click on the remove favorite location button
 * Then all the favorite location user stores will be removed
 *
 * Tests: Add Favorite Location button, Add Partner Button, Sign Out button
 *        Unit test for isSingle
 *        Test for four scenarios
 *
 */
public class TestHomePage extends ActivityInstrumentationTestCase2<HomePage> {

    HomePage homePage;

    // Constructor
    public TestHomePage() {

        // Call Superclass
        super(HomePage.class);
    }

    // JUnit test for isSingle method
    public void test_isSingle(){

        HomePage hp = new HomePage();
        assertFalse(hp.isSingleForTest("name", "5556"));
        assertTrue(hp.isSingleForTest("N/A", "N/A"));
    }

    // Test adding partner scenario
    public void test_PartnerSettingsScenario() {

        homePage = getActivity();

        //Check Add New Location button
        Button parSettingButton = (Button) homePage.findViewById(R.id.partner_setting_btn);

        // Simulate press
        assertNotNull(parSettingButton);
    }

    // Test add new favorite location scenario
    public void test_addFavoriteLocationScenario() {

        homePage = getActivity();

        //Check Add New Location button
        Button addLocButton = (Button) homePage.findViewById(R.id.add_fave_loc_btn);

        // Simulate press
        //assertNotNull(addLocButton);
    }

    // Test remove favorite location Scenario
    public void test_removeFavoriteLocationScenario(){

        homePage = getActivity();

        //Check sign out partner button
        Button removeLocButton = (Button) homePage.findViewById(R.id.remove_locs_btn);

        // Simulate press
        assertNotNull(removeLocButton);
    }

}
