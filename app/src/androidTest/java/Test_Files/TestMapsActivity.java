package Test_Files;

import android.test.ActivityInstrumentationTestCase2;
import android.widget.*;

import com.vapenaysh.jace.myapplication.MapsActivity;
import com.vapenaysh.jace.myapplication.R;

import junit.framework.Test;

/**
 * Created by adamabadilla on 5/7/16.
 *
 * Given that the user taps a coordinate/address on the map
 * When the user selects the coordinate,
 * And the user enters a name for the favorite location
 * Then the location should be added to the list of favorites with the name the user entered
 *      and a default notification sound associated with the location.
 * And the location on the map will be marked with a red pin and location name
 *
 *
 */
public class TestMapsActivity extends ActivityInstrumentationTestCase2<MapsActivity> {

    MapsActivity mapActivity;

    // Constructor
    public TestMapsActivity() {

        // Call Superclass
        super(MapsActivity.class);
    }

    // Test Custom Name Field
    public void test_customNameField() {

        // Get Activity
        mapActivity = getActivity();

        //Check Name Text Field
        EditText customName = (EditText) mapActivity.findViewById(R.id.custom_name);
        String name = customName.getHint().toString();
        assertEquals("Pick a custom name", name);

    }

    // Test Save Button
    public void test_saveButton() {

        // Get Activity
        mapActivity = getActivity();

        // Test Save Button Functionality
        Button saveButton = (Button) mapActivity.findViewById(R.id.save_btn);

        assertNotNull(saveButton);
    }

    // Test Cancel Button
    public void test_cancelButton() {

        // Get Activity
        mapActivity = getActivity();

        // Test Cancel Button functionality
        Button cancelButton = (Button) mapActivity.findViewById(R.id.cancel_btn);

        assertNotNull(cancelButton);
    }

}
