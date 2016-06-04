package com.vapenaysh.jace.myapplication.tests;

import com.google.android.gms.maps.model.LatLng;
import com.vapenaysh.jace.myapplication.FavoriteLocation;
import com.vapenaysh.jace.myapplication.GlobalSettings;
import com.vapenaysh.jace.myapplication.RingToneSetting;
import com.vapenaysh.jace.myapplication.VibeToneSetting;
import java.lang.reflect.Method;
import junit.framework.TestCase;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.fail;

/**
 *
 * [Story 3]: User can customize the default CoupleTones location sound tone.
 *
 * Scenario 1: I get notified by default location sound tone
 * Given that My partner already set a favorite location
 * And I haven’t set a customized location tone for that location
 * When my partner arrives at that favorite location
 * Then I will be notified by a text notification shown on the top of the screen as well as
 * the default sound tone of that location followed by appropriate arrival tone notification
 *
 * Scenario 2: I get notified by customized location sound tone
 * Given that My partner already set a favorite location
 * And I already set a customized location tone for that location
 * When my partner arrives at that favorite location
 * Then I will be notified by a text notification shown on the top of the screen as well as
 * the customized sound tone of that location followed by appropriate arrival tone notification
 *
 *
 * [Story 4]: User will be notified when his/her partner arrives/departs at a location by a
 * unique and fixed arrival/departure sound tone.
 *
 * Scenario 3: I get notified by the fixed and unique departure sound tone
 * Given that that my partner is in the vicinity of one of their favorite locations
 * And I already got an entering location notification from my partner
 * When my partner leave or is no longer within 1/10 of a mile of the favorite location he/her was at
 * Then I will get notified by the departure tone which is a fixed sound notification
 *
 * Scenario 4: I will not get notified by the fixed and unique departure sound tone.
 * Given that my partner is in the vicinity of one of their favorite locations
 * And I already got an entering location notification from my partner
 * When my partner continue to remain in the vicinity or within 1/10 of a mile of the favorite location
 * Then I will not receive departure tone sound notification.
 *
 *
 * [Story 5]: User will be notified when his/her partner arrives/departs at a location by a
 * unique and fixed arrival/departure vibration tone.
 *
 * Scenario 3: I get notified by the fixed and unique departure vibration tone
 * Given that that my partner is in the vicinity of one of their favorite locations
 * And I already got an entering location notification from my partner
 * When my partner leave or is no longer within 1/10 of a mile of the favorite location he/her was at
 * Then I will get notified by the departure VibeTone which is a fixed vibration notification.
 *
 * Scenario 4: I will not get notified by the fixed and unique departure vibration tone.
 * Given that my partner is in the vicinity of one of their favorite locations
 * And I already got an entering location notification from my partner
 * When my partner continue to remain in the vicinity or within 1/10 of a mile of the favorite location
 * Then I will not receive departure VibeTone vibration notification.
 *
 * Created by XuanpeiEstherOuyang on 6/1/16.
 */
public class TestDepartureAndRingToneSetting extends TestCase {

    // Constants
    final boolean RING = true;
    final boolean NO_RING = false;
    final boolean VIBE = true;
    final boolean NO_VIBE = false;

    GlobalSettings settings;
    FavoriteLocation location;
    RingToneSetting ring;
    VibeToneSetting vibe;

    public TestDepartureAndRingToneSetting() {

        // Instantiate GlobalSettings
        settings = new GlobalSettings();

        // Instantiate RingtoneManager
        ring = new RingToneSetting();
        //ring.setRingTone();

        // Instantiate VibetoneManager
        vibe = new VibeToneSetting();

    }

    /* User Stories: 4.3, 5.3, 7.1

    4.3
    Scenario 3: I get notified by the fixed and unique departure sound tone
    Given that that my partner is in the vicinity of one of their favorite locations
    And I already got an entering location notification from my partner
    When my partner leave or is no longer within 1/10 of a mile of the favorite location he/her was at
    Then I will get notified by the departure tone which is a fixed sound notification

    5.3
    Scenario 3: I get notified by the fixed and unique departure vibration tone
    Given that that my partner is in the vicinity of one of their favorite locations
    And I already got an entering location notification from my partner
    When my partner leave or is no longer within 1/10 of a mile of the favorite location he/her was at
    Then I will get notified by the departure VibeTone which is a fixed vibration notification.

    7.1
    Scenario 1: I will be notified by both types of notifications
    Given that I leave both options (vibration notification and sound notification) on in the global settings
    When my partner visits one of his/her favorite locations
    And leaves that location later
    Then I will get a notification message shown in the top of app screen and notified by
        both arrival vibration tone and arrival location tone followed by the location sound
        and both departure vibration tone and departure location tone in order.

    */
    public void testDeparture_SettingRingVibe_Default() {

        /* PRECONDITIONS */

        // GlobalSettings - sound: true, vibe: true
        settings.setNotificationSetting(RING, VIBE);

        // Instantiate Location
        location = new FavoriteLocation(new LatLng(1,1), "Location1");
        location.setVisited(true);
        location.setVisited(false);

        // Ringtone / VibeTone retain default values
        vibe.setVibeToneIndex(1);
        ring.save();

        /* Test */

        // check isVisited()
        if(location.isVisited() == false) {

            assertTrue((location.isVisited() == false));

            // check correct notification type
            if(settings.getNotificationMode() == 1) {

                assertEquals(1, settings.getNotificationMode());

                // check current RingTone / VibeTone
                ring.save();


                if(vibe.getVibeToneIndex() == 1) {


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

    /* User Stories: 4.3, 5.3, 7.1

    4.3
    Scenario 3: I get notified by the fixed and unique departure sound tone
    Given that that my partner is in the vicinity of one of their favorite locations
    And I already got an entering location notification from my partner
    When my partner leave or is no longer within 1/10 of a mile of the favorite location he/her was at
    Then I will get notified by the departure tone which is a fixed sound notification

    5.3
    Scenario 3: I get notified by the fixed and unique departure vibration tone
    Given that that my partner is in the vicinity of one of their favorite locations
    And I already got an entering location notification from my partner
    When my partner leave or is no longer within 1/10 of a mile of the favorite location he/her was at
    Then I will get notified by the departure VibeTone which is a fixed vibration notification.

    7.1
    Scenario 1: I will be notified by both types of notifications
    Given that I leave both options (vibration notification and sound notification) on in the global settings
    When my partner visits one of his/her favorite locations
    And leaves that location later
    Then I will get a notification message shown in the top of app screen and notified by
        both arrival vibration tone and arrival location tone followed by the location sound
        and both departure vibration tone and departure location tone in order.

    */
    public void testDeparture_SettingRingVibe_Custom() {

        /* PRECONDITIONS */

        // GlobalSettings - sound: true, vibe: true
        settings.setNotificationSetting(RING, VIBE);

        // Instantiate Location
        location = new FavoriteLocation(new LatLng(1,1), "Location1");
        location.setVisited(true);
        location.setVisited(false);

        // Ringtone retains default / VibeTone is custom
        vibe.setVibeToneIndex(8);
        ring.save();

        /* Test */

        // check isVisited()
        if(location.isVisited() == false) {

            assertTrue(location.isVisited() == false);

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

            } else {

                // Wrong notification Type
                fail();
            }

        } else {

            // isVisited was unset, so notification did not occur
            fail();
        }

    }

    /* User Stories: 4.3, 5.3, 7.1

    4.3
    Scenario 3: I get notified by the fixed and unique departure sound tone
    Given that that my partner is in the vicinity of one of their favorite locations
    And I already got an entering location notification from my partner
    When my partner leave or is no longer within 1/10 of a mile of the favorite location he/her was at
    Then I will get notified by the departure tone which is a fixed sound notification

    5.3
    Scenario 3: I get notified by the fixed and unique departure vibration tone
    Given that that my partner is in the vicinity of one of their favorite locations
    And I already got an entering location notification from my partner
    When my partner leave or is no longer within 1/10 of a mile of the favorite location he/her was at
    Then I will get notified by the departure VibeTone which is a fixed vibration notification.

    7.1
    Scenario 1: I will be notified by both types of notifications
    Given that I leave both options (vibration notification and sound notification) on in the global settings
    When my partner visits one of his/her favorite locations
    And leaves that location later
    Then I will get a notification message shown in the top of app screen and notified by
        both arrival vibration tone and arrival location tone followed by the location sound
        and both departure vibration tone and departure location tone in order.

    */
    public void testDeparture_SettingRing_Default() {

        /* PRECONDITIONS */

        // GlobalSettings - sound: true, vibe: true
        settings.setNotificationSetting(RING, NO_VIBE);

        // Instantiate Location
        location = new FavoriteLocation(new LatLng(1,1), "Location1");
        location.setVisited(true);
        location.setVisited(false);

        // Ringtone / VibeTone retain default values
        vibe.setVibeToneIndex(1);
        ring.save();

        /* Test */

        // check isVisited()
        if(location.isVisited() == false) {

            assertTrue((location.isVisited() == false));

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

    /* User Stories: 4.3, 5.3, 7.3

    4.3
    Scenario 3: I get notified by the fixed and unique departure sound tone
    Given that that my partner is in the vicinity of one of their favorite locations
    And I already got an entering location notification from my partner
    When my partner leave or is no longer within 1/10 of a mile of the favorite location he/her was at
    Then I will get notified by the departure tone which is a fixed sound notification

    5.3
    Scenario 3: I get notified by the fixed and unique departure vibration tone
    Given that that my partner is in the vicinity of one of their favorite locations
    And I already got an entering location notification from my partner
    When my partner leave or is no longer within 1/10 of a mile of the favorite location he/her was at
    Then I will get notified by the departure VibeTone which is a fixed vibration notification.

    7.3
    Scenario 3: I will only be notified by the sound
    Given that I leave vibration notification off but sound notification on in the global settings
    When my partner visits one of his/her favorite locations
    And leaves that location later
    Then I will get a notification message shown in the top of app screen and notified by arrival tone
     sounds, location tone sounds and departure tone sounds without any vibration.

    */
    public void testDeparture_SettingRing_Custom() {

        /* PRECONDITIONS */

        // GlobalSettings - sound: true, vibe: true
        settings.setNotificationSetting(RING, NO_VIBE);

        // Instantiate Location
        location = new FavoriteLocation(new LatLng(1,1), "Location1");
        location.setVisited(true);
        location.setVisited(false);

        // Ringtone retains default / VibeTone is custom
        vibe.setVibeToneIndex(8);
        ring.save();

        /* Test */

        // check isVisited()
        if(location.isVisited() == false) {

            assertTrue(location.isVisited() == false);

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
    public void testDeparture_SettingVibe_Default() {

        /* PRECONDITIONS */

        // GlobalSettings - sound: false, vibe: true
        settings.setNotificationSetting(NO_RING, VIBE);

        // Instantiate Location
        location = new FavoriteLocation(new LatLng(1,1), "Location1");
        location.setVisited(true);
        location.setVisited(false);

        // Ringtone / VibeTone retain default values
        vibe.setVibeToneIndex(1);
        ring.save();

        /* Test */

        // check isVisited()
        if((location.isVisited() == false)) {

            assertTrue((location.isVisited() == false));

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

    /* User Stories: 5.3, 7.2

    5.3
    Scenario 3: I get notified by the fixed and unique departure vibration tone
    Given that that my partner is in the vicinity of one of their favorite locations
    And I already got an entering location notification from my partner
    When my partner leave or is no longer within 1/10 of a mile of the favorite location he/her was at
    Then I will get notified by the departure VibeTone which is a fixed vibration notification.

    7.2
    Scenario 2: I will only be notified by the vibration
    Given that I leave vibration notification on but sound notification off in the global settings
    When my partner visits one of his/her favorite locations
    And leaves that location later
    Then I will get a notification message shown in the top of app screen and notified by arrival vibration tone and departure vibration tone in that order without any sound.

    */
    public void testDeparture_SettingVibe_Custom() {

        /* PRECONDITIONS */

        // GlobalSettings - sound: false, vibe: true
        settings.setNotificationSetting(NO_RING, VIBE);

        // Instantiate Location
        location = new FavoriteLocation(new LatLng(1,1), "Location1");
        location.setVisited(true);
        location.setVisited(false);

        // Ringtone retains default / VibeTone is custom
        vibe.setVibeToneIndex(8);
        ring.save();

        /* Test */

        // check isVisited()
        if((location.isVisited() == false)) {

            assertTrue((location.isVisited() == false));

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

    /* User Stories: 5.3, 7.2

    5.3
    Scenario 3: I get notified by the fixed and unique departure vibration tone
    Given that that my partner is in the vicinity of one of their favorite locations
    And I already got an entering location notification from my partner
    When my partner leave or is no longer within 1/10 of a mile of the favorite location he/her was at
    Then I will get notified by the departure VibeTone which is a fixed vibration notification.

    7.2
    Scenario 2: I will only be notified by the vibration
    Given that I leave vibration notification on but sound notification off in the global settings
    When my partner visits one of his/her favorite locations
    And leaves that location later
    Then I will get a notification message shown in the top of app screen and notified by arrival vibration tone and departure vibration tone in that order without any sound.

    */
    public void testDeparture_SettingNone() {

        /* PRECONDITIONS */

        // GlobalSettings - sound: false, vibe: true
        settings.setNotificationSetting(NO_RING, NO_VIBE);

        // Instantiate Location
        location = new FavoriteLocation(new LatLng(1,1), "Location1");
        location.setVisited(true);
        location.setVisited(false);

        // Ringtone retains default / VibeTone is custom
        vibe.setVibeToneIndex(8);
        ring.save();

        /* Test */

        // check isVisited()
        if((location.isVisited() == false)) {

            assertTrue((location.isVisited() == false));

            assertNotNull(ring);
            assertNotNull(vibe);

        } else {

            // isVisited was unset, so notification did not occur
            fail();
        }

    }


}
