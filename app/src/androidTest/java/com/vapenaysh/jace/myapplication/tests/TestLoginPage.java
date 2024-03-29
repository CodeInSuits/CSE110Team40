package com.vapenaysh.jace.myapplication.tests;

import android.media.Image;
import android.os.CountDownTimer;
import android.test.ActivityInstrumentationTestCase2;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.dd.processbutton.FlatButton;
import com.vapenaysh.jace.myapplication.LoginPage;
import com.vapenaysh.jace.myapplication.R;

/**
 * Created by adamabadilla on 5/7/16.
 *
 * [Story 1]: Log in the app
 *
 * Scenario 1: User tries registering with their Google Account
 *
 * Given that the user has a google account
 * And didn’t log in the app before
 * When the user tries to log in
 * Then user will be directed to the proper homepage
 *
 */
public class TestLoginPage extends ActivityInstrumentationTestCase2<LoginPage> {

    LoginPage loginPage;

    // Constructor
    public TestLoginPage() {

        // Call Superclass
        super(LoginPage.class);
    }


    // Test Google Sign in button
    public void test_googleSignIn() {

        //Get Activity
        loginPage = getActivity();

        // Simulate Login
        final View googleSignIn = loginPage.findViewById(R.id.google_signin);

        // Login Box exists
        assertNotNull(googleSignIn);

        new CountDownTimer(3500, 1000) {

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                assertTrue(googleSignIn.getVisibility() == View.VISIBLE);

            }
        }.start();


    }

    // Check logo graphic is displayed
    public void test_logoGraphic() {
        //Get Activity
        loginPage = getActivity();

        // Check logo graphic is displayed
        ImageView logoGraphic = (ImageView) loginPage.findViewById(R.id.logo);
        if (logoGraphic != null) {
            assertTrue(logoGraphic.getVisibility() == View.VISIBLE);
        }
        else {
            assertTrue(logoGraphic == null);
        }
    }
    // testing the yinyang graphic
    public void test_yinyangGraphic() {

        loginPage = getActivity();

        // Check animation graphic is displayed
        ImageView yinyangGraphic = (ImageView) loginPage.findViewById(R.id.yinyang);
        if (yinyangGraphic != null)
            assertTrue(yinyangGraphic.getVisibility() == View.VISIBLE);
        else
            assertTrue(yinyangGraphic == null);

    }



}
