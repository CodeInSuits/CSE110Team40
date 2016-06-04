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
 *
 * Story 4: User will be notified when his/her partner arrives/departs at a location by a unique
 * and fixed arrival/departure sound tone.
 *
 * Scenario 1: I will not get notified by the fixed and unique arrival sound tone
 * Given that my partner already set a favorite location
 * And my partner is not within 1/10 of a mile range of that favorite location
 * When my partner does not arrive at or is within 1/10 of a mile range of that favorite location
 * Then I should not get notified by the arrival tone which is a fixed sound notification
 *
 * Scenario 2: I get notified by the fixed and unique arrival sound tone
 * Given that my partner already set a favorite location
 * And my partner is not yet within 1/10 of a mile range of that favorite location
 * When my partner arrives at or is within 1/10 of a mile range of that favorite location
 * Then I should get notified by the arrival tone which is a fixed sound notification
 *
 *
 * Story 5: User will be notified when his/her partner arrives/departs at a location by a unique
 * and fixed arrival/departure vibration tone.
 *
 * Scenario 1: I will not get notified by the fixed and unique arrival vibration tone
 * Given that my partner already set a favorite location
 * And my partner is not within 1/10 of a mile range of that favorite location
 * When my partner does not arrive at or is within 1/10 of a mile range of that favorite location
 * Then I should not get notified by the arrival VibeTone which is a fixed vibration notification.
 *
 *
 * Scenario 2: I get notified by the fixed and unique arrival vibration tone
 * Given that my partner already set a favorite location
 * And my partner is not yet within 1/10 of a mile range of that favorite location
 * When my partner arrives at or is within 1/10 of a mile range of that favorite location
 * Then I should get notified by the arrival VibeTone which is a fixed vibration notification.
 *
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

    /* User Stories: 4.3, 7.3

    4.3
    Scenario 3: I get notified by the fixed and unique departure sound tone
    Given that that my partner is in the vicinity of one of their favorite locations
    And I already got an entering location notification from my partner
    When my partner leave or is no longer within 1/10 of a mile of the favorite location he/her was at
    Then I will get notified by the departure tone which is a fixed sound notification

    7.3
    Scenario 3: I will only be notified by the sound
    Given that I leave vibration notification off but sound notification on in the global settings
    When my partner visits one of his/her favorite locations
    And leaves that location later
    Then I will get a notification message shown in the top of app screen and notified by arrival tone
     sounds, location tone sounds and departure tone sounds without any vibration.
     */

    public void testArrival_SettingRing_Default() {

        /* PRECONDITIONS */

        // GlobalSettings - sound: true, vibe: true
        settings.setNotificationSetting(RING, NO_VIBE);

        // Instantiate Location
        location = new FavoriteLocation(new LatLng(1,1), "Location1");
        location.setVisited(true);

        // Ringtone / VibeTone retain default values
        vibe.setVibeToneIndex(1);
        ring.save();

        /* Test */

        // check isVisited()
        if(location.isVisited()) {

            assertTrue(location.isVisited());

            // check correct notification type
            if(settings.getNotificationMode() == 1) {

                assertEquals(1, settings.getNotificationMode());

                // check current RingTone / VibeTone
                ring.save();


                if(vibe.getVibeToneIndex() == 1) {

                    assertNotNull(vibe);
                    assertNotNull(ring);

                } else {

                    // Wrong VibeTone
                    fail();
                }

            }

        } else {

            // isVisited was unset, so notification did not occur
            fail();
        }

    }

    /* User Stories: 4.3, 7.3

    4.3
    Scenario 3: I get notified by the fixed and unique departure sound tone
    Given that that my partner is in the vicinity of one of their favorite locations
    And I already got an entering location notification from my partner
    When my partner leave or is no longer within 1/10 of a mile of the favorite location he/her was at
    Then I will get notified by the departure tone which is a fixed sound notification

    7.3
    Scenario 3: I will only be notified by the sound
    Given that I leave vibration notification off but sound notification on in the global settings
    When my partner visits one of his/her favorite locations
    And leaves that location later
    Then I will get a notification message shown in the top of app screen and notified by arrival tone
     sounds, location tone sounds and departure tone sounds without any vibration.

    */
    public void testArrival_SettingRing_Custom() {

        /* PRECONDITIONS */

        // GlobalSettings - sound: true, vibe: true
        settings.setNotificationSetting(RING, NO_VIBE);

        // Instantiate Location
        location = new FavoriteLocation(new LatLng(1,1), "Location1");
        location.setVisited(true);

        // Ringtone retains default / VibeTone is custom
        vibe.setVibeToneIndex(8);
        ring.save();

        /* Test */

        // check isVisited()
        if(location.isVisited()) {

            assertTrue(location.isVisited());

            // check correct notification type
            if(settings.getNotificationMode() == 1) {

                assertEquals(1, settings.getNotificationMode());

                // check current Ring / VibeTone
                ring.save();

                if(vibe.getVibeToneIndex() == 8) {

                    assertEquals(8, vibe.getVibeToneIndex());

                } else {

                    // Wrong VibeTone
                    fail();
                }

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
