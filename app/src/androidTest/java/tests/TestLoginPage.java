package tests;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.*;
import com.vapenaysh.jace.myapplication.R;

import com.vapenaysh.jace.myapplication.LoginPage;

/**
 * Created by adamabadilla on 5/7/16.
 *
 * User tries registering with their Google Account
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
        View googleSignIn = loginPage.findViewById(R.id.google_signin);

        if(googleSignIn != null) {
            googleSignIn.callOnClick();
        }

        // Login Box exists
        assertNotNull(googleSignIn);

    }

    // Check logo graphic is displayed
    public void test_logoGraphic() {

        //Get Activity
        loginPage = getActivity();

        // Check logo graphic is displayed
        ImageView logoGraphic = (ImageView) loginPage.findViewById(R.id.logo);
        assertTrue(logoGraphic.getVisibility() == View.VISIBLE);

    }


}
