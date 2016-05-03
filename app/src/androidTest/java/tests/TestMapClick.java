package tests;

import android.os.Handler;
import android.test.ActivityInstrumentationTestCase2;
import android.text.Layout;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.vapenaysh.jace.myapplication.MainActivity;
import com.vapenaysh.jace.myapplication.MapsActivity;
import com.vapenaysh.jace.myapplication.R;
import com.vapenaysh.jace.myapplication.SavedLocations;

import org.w3c.dom.Text;

/**
 * Created by Matt on 5/1/16.
 */
public class TestMapClick extends ActivityInstrumentationTestCase2<MapsActivity> {

    MapsActivity mapsActivity;

    public TestMapClick(){
        super(MapsActivity.class);
    }

    public void test_ClickOnMap(){
        mapsActivity = (MapsActivity) getActivity();

        Handler mainHandler = new Handler(mapsActivity.getMainLooper());

        Runnable myRunnable = new Runnable() {
            @Override
            public void run() {
                mapsActivity.onMapClick(new LatLng(32, -117));
                TextView namePrompt = (TextView) mapsActivity.findViewById(R.id.custom_name_prompt);
                assertEquals( namePrompt.getText().toString(), "(Optional) Add custom name!" );

                RelativeLayout namePromptLayout = (RelativeLayout) mapsActivity.findViewById(R.id.custom_name_layout);
                assertEquals(namePromptLayout.getVisibility(), View.VISIBLE);
            } // This is your code
        };
        mainHandler.post(myRunnable);
    }

    public void test_SaveName(){
        int numLocs = SavedLocations.getSize();
        test_ClickOnMap();
        mapsActivity.saveCustomName(null);
        assertEquals( numLocs+1, SavedLocations.getSize() );
    }

    public void test_CancelName(){
        int numLocs = SavedLocations.getSize();
        test_ClickOnMap();
        mapsActivity.cancelCustomName(null);
        assertEquals( numLocs, SavedLocations.getSize() );
    }


}
