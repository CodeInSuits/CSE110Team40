package com.vapenaysh.jace.myapplication.tests;

import android.app.Activity;
import android.os.Vibrator;

import com.google.android.gms.maps.model.LatLng;
import com.vapenaysh.jace.myapplication.FavoriteLocation;
import com.vapenaysh.jace.myapplication.GlobalSettings;
import com.vapenaysh.jace.myapplication.NotificationService;
import com.vapenaysh.jace.myapplication.RingToneManager;
import com.vapenaysh.jace.myapplication.RingToneSetting;
import com.vapenaysh.jace.myapplication.VibeToneManager;
import com.vapenaysh.jace.myapplication.VibeToneSetting;

import android.os.Vibrator;

import junit.framework.TestCase;

/**
 * Tests User Stories: 5.1, 5.2, 6.1, 6.2, 7.1, 7.2, 7.3, 7.4

 */



public class TestArrivalTone extends TestCase {

    // Constants
    final boolean RING = true;
    final boolean NO_RING = false;
    final boolean VIBE = true;
    final boolean NO_VIBE = false;

    GlobalSettings settings;
    FavoriteLocation location;
    RingToneSetting ring;
    VibeToneSetting vibe;

    public TestArrivalTone() {

        // Instantiate GlobalSettings
        settings = new GlobalSettings();

        // Instantiate RingtoneManager
        ring = new RingToneSetting();
        //ring.setRingTone();

        // Instantiate VibetoneManager
        vibe = new VibeToneSetting();

    }

    /* User Stories: 6.1, 7.1

    6.1
    Scenario 1: I get notified by default location vibration tone
    Given that My partner already set a favorite location
    And I haven’t set a customized location tone for that location
    When my partner arrives at that favorite location
    Then I will be notified by a text notification shown on the top of
        the screen as well as the default vibration tone of that location
        followed by appropriate arrival notification.

    7.1
    Given that I leave both options (vibration notification and sound notification) on in the global settings
    When my partner visits one of his/her favorite locations
    And leaves that location later
    Then I will get a notification message shown in the top of app screen
        and notified by both arrival vibration tone and arrival location tone
        followed by the location sound and both departure vibration tone and
        departure location tone in order.

    */
    public void testArrival_SettingRingVibe_Default() {

        /* PRECONDITIONS */

        // GlobalSettings - sound: true, vibe: true
        settings.setNotificationSetting(RING, VIBE);

        // Instantiate Location
        location = new FavoriteLocation(new LatLng(1,1), "Location1");
        location.setVisited();

        // Ringtone / VibeTone retain default values
        vibe.setVibeToneIndex(1);

        /* Test */

        // check isVisited()
        if(location.isVisited()) {

            assertTrue(location.isVisited());

            // check correct notification type
            if(settings.getNotificationMode() == 1) {

                assertEquals(1, settings.getNotificationMode());

                // check current RingTone / VibeTone
                if(vibe.getVibeToneIndex() == 1) {

                    assertEquals(1, vibe.getVibeToneIndex());
                } else {

                    // Wrong VibeTone
                    fail();
                }

            } else {

                // Wrong notification Type
                fail();
            }

        } else {

            // isVisited was unset, so notification did not occur
            fail();
        }

    }

    /* User Stories: 6.2, 7.1

    6.2
    Scenario 2: I get notified by customized location vibration tone
    Given that My partner already set a favorite location
    And I already set a customized location tone for that location
    When my partner arrives at that favorite location
    Then I will be notified by a text notification shown on the top of the
        screen as well as the customized vibration tone of that location followed
        by appropriate arrival notification.

    7.1
    Given that I leave both options (vibration notification and sound notification) on in the global settings
    When my partner visits one of his/her favorite locations
    And leaves that location later
    Then I will get a notification message shown in the top of app screen
        and notified by both arrival vibration tone and arrival location tone
        followed by the location sound and both departure vibration tone and
        departure location tone in order.
     */
    public void testArrival_SettingRingVibe_Custom() {

        /* PRECONDITIONS */

        // GlobalSettings - sound: true, vibe: true
        settings.setNotificationSetting(RING, VIBE);

        // Instantiate Location
        location = new FavoriteLocation(new LatLng(1,1), "Location1");
        location.setVisited();

        // Ringtone retains default / VibeTone is custom
        vibe.setVibeToneIndex(8);

        /* Test */

        // check isVisited()
        if(location.isVisited()) {

            assertTrue(location.isVisited());

            // check correct notification type
            if(settings.getNotificationMode() == 1) {

                assertEquals(1, settings.getNotificationMode());

                // check current VibeTone
                if(vibe.getVibeToneIndex() == 8) {

                    assertEquals(8, vibe.getVibeToneIndex());
                } else {

                    // Wrong VibeTone
                    fail();
                }

            } else {

                // Wrong notification Type
                fail();
            }

        } else {

            // isVisited was unset, so notification did not occur
            fail();
        }

    }

    /* User Stories: 6.1, 7.2

    6.1
    Scenario 1: I get notified by default location vibration tone
    Given that My partner already set a favorite location
    And I haven’t set a customized location tone for that location
    When my partner arrives at that favorite location
    Then I will be notified by a text notification shown on the top of
        the screen as well as the default vibration tone of that location
        followed by appropriate arrival notification.

    7.2
    Scenario 2: I will only be notified by the vibration
    Given that I leave vibration notification on but sound notification off in the global settings
    When my partner visits one of his/her favorite locations
    And leaves that location later
    Then I will get a notification message shown in the top of app screen and notified by
        arrival vibration tone and departure vibration tone in that order without any sound.
     */
    public void testArrival_SettingVibe_Default() {

        /* PRECONDITIONS */

        // GlobalSettings - sound: false, vibe: true
        settings.setNotificationSetting(NO_RING, VIBE);

        // Instantiate Location
        location = new FavoriteLocation(new LatLng(1,1), "Location1");
        location.setVisited();

        // Ringtone / VibeTone retain default values
        vibe.setVibeToneIndex(1);

        /* Test */

        // check isVisited()
        if(location.isVisited()) {

            assertTrue(location.isVisited());

            // check correct notification type
            if(settings.getNotificationMode() == 3) {

                assertEquals(3, settings.getNotificationMode());

                // check current VibeTone
                if(vibe.getVibeToneIndex() == 1) {

                    assertEquals(1, vibe.getVibeToneIndex());
                } else {

                    // Wrong VibeTone
                    fail();
                }

            } else {

                // Wrong notification Type
                fail();
            }

        } else {

            // isVisited was unset, so notification did not occur
            fail();
        }

    }

    /* User Stories: 6.2, 7.2

    6.2
    Scenario 2: I get notified by customized location vibration tone
    Given that My partner already set a favorite location
    And I already set a customized location tone for that location
    When my partner arrives at that favorite location
    Then I will be notified by a text notification shown on the top of the
        screen as well as the customized vibration tone of that location followed
        by appropriate arrival notification.

    7.2
    Scenario 2: I will only be notified by the vibration
    Given that I leave vibration notification on but sound notification off in the global settings
    When my partner visits one of his/her favorite locations
    And leaves that location later
    Then I will get a notification message shown in the top of app screen and notified by
        arrival vibration tone and departure vibration tone in that order without any sound.

     */
    public void testArrival_SettingVibe_Custom() {

        /* PRECONDITIONS */

        // GlobalSettings - sound: false, vibe: true
        settings.setNotificationSetting(NO_RING, VIBE);

        // Instantiate Location
        location = new FavoriteLocation(new LatLng(1,1), "Location1");
        location.setVisited();

        // Ringtone retains default / VibeTone is custom
        vibe.setVibeToneIndex(8);

        /* Test */

        // check isVisited()
        if(location.isVisited()) {

            assertTrue(location.isVisited());

            // check correct notification type
            if(settings.getNotificationMode() == 3) {

                assertEquals(3, settings.getNotificationMode());

                // check current VibeTone
                if(vibe.getVibeToneIndex() == 8) {

                    assertEquals(8, vibe.getVibeToneIndex());
                } else {

                    // Wrong VibeTone
                    fail();
                }

            } else {

                // Wrong notification Type
                fail();
            }

        } else {

            // isVisited was unset, so notification did not occur
            fail();
        }

    }

}
