package Test_Files;

import android.test.ActivityInstrumentationTestCase2;
import com.vapenaysh.jace.myapplication.CreateAccount;
import com.vapenaysh.jace.myapplication.R;
import android.widget.*;

/**
 * Created by adamabadilla on 5/7/16.
 *
 *  Testing Login / Create Account functionality.
 *
 *  User tries registering with their Google Account
 *  Given that the user has a google account
 *  And didn’t log in the app before
 *  When the user tries to log in
 *  Then user will be directed to the proper homepage
 *  And the user will be shown a hint appears saying to add a partner
 *
 *  Tests: Name field, Password field, Button functionality.
 *
 */
public class TestCreateAccount extends ActivityInstrumentationTestCase2<CreateAccount> {

    CreateAccount testCreateAccount;

    // Constructor
    public TestCreateAccount() {

        // Call Superclass
        super(CreateAccount.class);

    }

    // Test Name Edit Text field
    public void test_nameHint() {

        testCreateAccount = getActivity();

        //Check Name Text Field
        EditText usernameField = (EditText) testCreateAccount.findViewById(R.id.username);
        String username = usernameField.getHint().toString();
        assertEquals("Username", username);

    }

    // Test Password Edit Text field
    public void test_passwordHint() {

        testCreateAccount = getActivity();

        //Check Password Text Field
        EditText passwordField = (EditText) testCreateAccount.findViewById(R.id.password);
        String password = passwordField.getHint().toString();
        assertEquals("Password", password);

    }

    // Test Create Account Button functionality
    public void test_createAccount() {

        testCreateAccount = getActivity();

        //Check Create Account Button
        Button createButton = (Button) testCreateAccount.findViewById(R.id.create_account);
        String create = createButton.getText().toString();

        //Simulate Click
        createButton.callOnClick();

        //Tests
        assertNotNull(create);
        assertNotNull(createButton);
    }





    
}
