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
