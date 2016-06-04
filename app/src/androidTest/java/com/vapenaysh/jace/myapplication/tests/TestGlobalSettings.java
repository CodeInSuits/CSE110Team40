package com.vapenaysh.jace.myapplication.tests;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.test.ActivityInstrumentationTestCase2;
import android.test.UiThreadTest;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static junit.framework.Assert.*;
import com.vapenaysh.jace.myapplication.AddPartner;
import com.vapenaysh.jace.myapplication.GlobalSettings;
import com.vapenaysh.jace.myapplication.PartnerSettings;
import com.vapenaysh.jace.myapplication.R;

/**
 * [Story 7]: User has the option of being notified via both LocationTone and VibeTone
 * notification, just one or neither
 *
 *
 * Scenario 1: I will be notified by both types of notifications
 * Given that I leave both options (vibration notification and sound notification) on in the
 * global settings
 * When my partner visits one of his/her favorite locations
 * And leaves that location later
 * Then I will get a notification message shown in the top of app screen and notified by both
 * arrival vibration tone and arrival location tone followed by the location sound and both
 * departure vibration tone and departure location tone in order.

 *
 * Scenario 2: I will only be notified by the vibration
 * Given that I leave vibration notification on but sound notification off in the global settings
 * When my partner visits one of his/her favorite locations
 * And leaves that location later
 * Then I will get a notification message shown in the top of app screen and notified by
 * arrival vibration tone and departure vibration tone in that order without any sound.
 *

 * Scenario 3: I will only be notified by the sound
 * Given that I leave vibration notification off but sound notification on in the global settings
 * When my partner visits one of his/her favorite locations
 * And leaves that location later
 * Then I will get a notification message shown in the top of app screen and notified by
 * arrival tone sounds, location tone sounds and departure tone sounds without any vibration.

 *
 * Scenario 4: I mute both vibration and sound
 * Given that I turn both tones and vibrations off in the global settings
 * When my partner visits one of his/her favorite locations
 * Then I will only get a notification message shown in the top of app screen without any
 * vibration and sounds.
 *
 * Created by XuanpeiEstherOuyang on 5/24/16.
 */
public class TestGlobalSettings extends ActivityInstrumentationTestCase2<GlobalSettings>{

    GlobalSettings gl;

    // Constructor
    public TestGlobalSettings() throws Exception {

        // Set Up J-Unit Test Environment
        super(GlobalSettings.class);
    }

    @UiThreadTest
    public void TestMuteNotification() {

        gl = getActivity();

        Button MuteButton = (Button) gl.findViewById(R.id.mute);
        MuteButton.callOnClick();
        assertEquals(GlobalSettings.getNotificationMode(), 4);
    }

    @UiThreadTest
    public void TestBothNotification() {

        gl = getActivity();

        Button bothButton = (Button) gl.findViewById(R.id.both);
        bothButton.callOnClick();
        assertEquals(GlobalSettings.getNotificationMode(), 1);

    }

    @UiThreadTest
    public void TestVibeOnlyNotification() {

        gl = getActivity();

        Button vibeButton = (Button) gl.findViewById(R.id.vibeonly);
        vibeButton.callOnClick();
        assertEquals(GlobalSettings.getNotificationMode(), 3);
    }

    @UiThreadTest
    public void TestSoundOnlyNotification() {

        gl = getActivity();

        Button soundButton = (Button) gl.findViewById(R.id.soundonly);
        soundButton.callOnClick();
        assertEquals(GlobalSettings.getNotificationMode(), 2);
    }

}
