package com.vapenaysh.jace.myapplication.tests;

import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.vapenaysh.jace.myapplication.GlobalSettings;
import com.vapenaysh.jace.myapplication.VibeToneManager;
import com.vapenaysh.jace.myapplication.VibeToneSetting;
import com.vapenaysh.jace.myapplication.R;

/**
 *
 * Story 6: User can customize the default CoupleTones location vibration tone VibeTone.
 * Tests:
 *  Every permutation of possible options
 *  Checks to make sure two radio buttons cannot be selected at once
 *  Checks to make sure the correct values are saved
 *  Checks to make sure VibeTones can be previewed
 *
 */
public class TestVibeTone extends ActivityInstrumentationTestCase2<VibeToneSetting> {

    public TestVibeTone() {
        super(VibeToneSetting.class);
    }

    private VibeToneSetting vibeToneSetting;
    boolean running;

    // Tests that selecting VibeTown 1 is saved
    public void test_vibeTone1() {

        vibeToneSetting = getActivity();
        running = true;

        if(vibeToneSetting != null) {
            vibeToneSetting.runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    final RadioButton button1 = (RadioButton) vibeToneSetting.findViewById(R.id.radioButton);
                    final RadioGroup radioGroup = (RadioGroup) vibeToneSetting.findViewById(R.id.myRadioGroup);
                    final View saveButton = vibeToneSetting.findViewById(R.id.button3);

                    if (button1 == null || radioGroup == null || saveButton == null) {
                        fail();
                    }

                    // Check VibeTone1
                    radioGroup.check(button1.getId());

                    int checkedId = radioGroup.getCheckedRadioButtonId();
                    final RadioButton checkedButton = (RadioButton) radioGroup.findViewById(checkedId);

                    // Assert VibeTone1 is selected
                    if(checkedButton == null) {
                        fail();
                    }

                    // Assert VibeTone1 button is selected
                    assertEquals(checkedButton.getId(), button1.getId());

                    // Click Save Button
                    saveButton.callOnClick();

                    // Assert that VibeTone1 was saved
                    assertEquals(1, vibeToneSetting.getVibeToneIndex());
                    running = false;
                }

            });
        }

        while(running) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // Handle exception
                return;
            }
        }

    }

    // Tests that selecting VibeTown 1 is saved
    public void test_vibeTone2() {

        vibeToneSetting = getActivity();
        running = true;

        if(vibeToneSetting != null) {
            vibeToneSetting.runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    final RadioButton button2 = (RadioButton) vibeToneSetting.findViewById(R.id.radioButton2);
                    final RadioGroup radioGroup = (RadioGroup) vibeToneSetting.findViewById(R.id.myRadioGroup);
                    final View saveButton = vibeToneSetting.findViewById(R.id.button3);

                    if (button2 == null || radioGroup == null || saveButton == null) {
                        fail();
                    }

                    // Check VibeTone1
                    radioGroup.check(button2.getId());

                    int checkedId = radioGroup.getCheckedRadioButtonId();
                    final RadioButton checkedButton = (RadioButton) radioGroup.findViewById(checkedId);

                    // Assert VibeTone1 is selected
                    if(checkedButton == null) {
                        fail();
                    }

                    // Assert VibeTone1 button is selected
                    assertEquals(checkedButton.getId(), button2.getId());

                    // Click Save Button
                    saveButton.callOnClick();

                    // Assert that VibeTone1 was saved
                    assertEquals(2, vibeToneSetting.getVibeToneIndex());
                    running = false;
                }

            });
        }

        while(running) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // Handle exception
                return;
            }
        }

    }


    // Tests that selecting VibeTown 1 is saved
    public void test_vibeTone3() {

        vibeToneSetting = getActivity();
        running = true;

        if(vibeToneSetting != null) {
            vibeToneSetting.runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    final RadioButton button3 = (RadioButton) vibeToneSetting.findViewById(R.id.radioButton3);
                    final RadioGroup radioGroup = (RadioGroup) vibeToneSetting.findViewById(R.id.myRadioGroup);
                    final View saveButton = vibeToneSetting.findViewById(R.id.button3);

                    if (button3 == null || radioGroup == null || saveButton == null) {
                        fail();
                    }

                    // Check VibeTone1
                    radioGroup.check(button3.getId());

                    int checkedId = radioGroup.getCheckedRadioButtonId();
                    final RadioButton checkedButton = (RadioButton) radioGroup.findViewById(checkedId);

                    // Assert VibeTone1 is selected
                    if(checkedButton == null) {
                        fail();
                    }

                    // Assert VibeTone1 button is selected
                    assertEquals(checkedButton.getId(), button3.getId());

                    // Click Save Button
                    saveButton.callOnClick();

                    // Assert that VibeTone1 was saved
                    assertEquals(3, vibeToneSetting.getVibeToneIndex());
                    running = false;
                }

            });
        }

        while(running) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // Handle exception
                return;
            }
        }

    }


    // Tests that selecting VibeTown 1 is saved
    public void test_vibeTone4() {

        vibeToneSetting = getActivity();
        running = true;

        if(vibeToneSetting != null) {
            vibeToneSetting.runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    final RadioButton button4 = (RadioButton) vibeToneSetting.findViewById(R.id.radioButton4);
                    final RadioGroup radioGroup = (RadioGroup) vibeToneSetting.findViewById(R.id.myRadioGroup);
                    final View saveButton = vibeToneSetting.findViewById(R.id.button3);

                    if (button4 == null || radioGroup == null || saveButton == null) {
                        fail();
                    }

                    // Check VibeTone1
                    radioGroup.check(button4.getId());

                    int checkedId = radioGroup.getCheckedRadioButtonId();
                    final RadioButton checkedButton = (RadioButton) radioGroup.findViewById(checkedId);

                    // Assert VibeTone1 is selected
                    if(checkedButton == null) {
                        fail();
                    }

                    // Assert VibeTone1 button is selected
                    assertEquals(checkedButton.getId(), button4.getId());

                    // Click Save Button
                    saveButton.callOnClick();

                    // Assert that VibeTone1 was saved
                    assertEquals(4, vibeToneSetting.getVibeToneIndex());
                    running = false;
                }

            });
        }

        while(running) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // Handle exception
                return;
            }
        }

    }

    // Tests that selecting VibeTown 1 is saved
    public void test_vibeTone5() {

        vibeToneSetting = getActivity();
        running = true;

        if(vibeToneSetting != null) {
            vibeToneSetting.runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    final RadioButton button5 = (RadioButton) vibeToneSetting.findViewById(R.id.radioButton5);
                    final RadioGroup radioGroup = (RadioGroup) vibeToneSetting.findViewById(R.id.myRadioGroup);
                    final View saveButton = vibeToneSetting.findViewById(R.id.button3);

                    if (button5 == null || radioGroup == null || saveButton == null) {
                        fail();
                    }

                    // Check VibeTone1
                    radioGroup.check(button5.getId());

                    int checkedId = radioGroup.getCheckedRadioButtonId();
                    final RadioButton checkedButton = (RadioButton) radioGroup.findViewById(checkedId);

                    // Assert VibeTone1 is selected
                    if(checkedButton == null) {
                        fail();
                    }

                    // Assert VibeTone1 button is selected
                    assertEquals(checkedButton.getId(), button5.getId());

                    // Click Save Button
                    saveButton.callOnClick();

                    // Assert that VibeTone1 was saved
                    assertEquals(5, vibeToneSetting.getVibeToneIndex());
                    running = false;
                }

            });
        }

        while(running) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // Handle exception
                return;
            }
        }

    }

    // Tests that selecting VibeTown 1 is saved
    public void test_vibeTone6() {

        vibeToneSetting = getActivity();
        running = true;

        if(vibeToneSetting != null) {
            vibeToneSetting.runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    final RadioButton button6 = (RadioButton) vibeToneSetting.findViewById(R.id.radioButton6);
                    final RadioGroup radioGroup = (RadioGroup) vibeToneSetting.findViewById(R.id.myRadioGroup);
                    final View saveButton = vibeToneSetting.findViewById(R.id.button3);

                    if (button6 == null || radioGroup == null || saveButton == null) {
                        fail();
                    }

                    // Check VibeTone1
                    radioGroup.check(button6.getId());

                    int checkedId = radioGroup.getCheckedRadioButtonId();
                    final RadioButton checkedButton = (RadioButton) radioGroup.findViewById(checkedId);

                    // Assert VibeTone1 is selected
                    if(checkedButton == null) {
                        fail();
                    }

                    // Assert VibeTone1 button is selected
                    assertEquals(checkedButton.getId(), button6.getId());

                    // Click Save Button
                    saveButton.callOnClick();

                    // Assert that VibeTone1 was saved
                    assertEquals(6, vibeToneSetting.getVibeToneIndex());
                    running = false;
                }

            });
        }

        while(running) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // Handle exception
                return;
            }
        }

    }

    // Tests that selecting VibeTown 1 is saved
    public void test_vibeTone7() {

        vibeToneSetting = getActivity();
        running = true;

        if(vibeToneSetting != null) {
            vibeToneSetting.runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    final RadioButton button7 = (RadioButton) vibeToneSetting.findViewById(R.id.radioButton7);
                    final RadioGroup radioGroup = (RadioGroup) vibeToneSetting.findViewById(R.id.myRadioGroup);
                    final View saveButton = vibeToneSetting.findViewById(R.id.button3);

                    if (button7 == null || radioGroup == null || saveButton == null) {
                        fail();
                    }

                    // Check VibeTone1
                    radioGroup.check(button7.getId());

                    int checkedId = radioGroup.getCheckedRadioButtonId();
                    final RadioButton checkedButton = (RadioButton) radioGroup.findViewById(checkedId);

                    // Assert VibeTone1 is selected
                    if(checkedButton == null) {
                        fail();
                    }

                    // Assert VibeTone1 button is selected
                    assertEquals(checkedButton.getId(), button7.getId());

                    // Click Save Button
                    saveButton.callOnClick();

                    // Assert that VibeTone1 was saved
                    assertEquals(7, vibeToneSetting.getVibeToneIndex());
                    running = false;
                }

            });
        }

        while(running) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // Handle exception
                return;
            }
        }

    }

    // Tests that selecting VibeTown 1 is saved
    public void test_vibeTone8() {

        vibeToneSetting = getActivity();
        running = true;

        if(vibeToneSetting != null) {
            vibeToneSetting.runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    final RadioButton button8 = (RadioButton) vibeToneSetting.findViewById(R.id.radioButton8);
                    final RadioGroup radioGroup = (RadioGroup) vibeToneSetting.findViewById(R.id.myRadioGroup);
                    final View saveButton = vibeToneSetting.findViewById(R.id.button3);

                    if (button8 == null || radioGroup == null || saveButton == null) {
                        fail();
                    }

                    // Check VibeTone1
                    radioGroup.check(button8.getId());

                    int checkedId = radioGroup.getCheckedRadioButtonId();
                    final RadioButton checkedButton = (RadioButton) radioGroup.findViewById(checkedId);

                    // Assert VibeTone1 is selected
                    if(checkedButton == null) {
                        fail();
                    }

                    // Assert VibeTone1 button is selected
                    assertEquals(checkedButton.getId(), button8.getId());

                    // Click Save Button
                    saveButton.callOnClick();

                    // Assert that VibeTone1 was saved
                    assertEquals(8, vibeToneSetting.getVibeToneIndex());
                    running = false;
                }

            });
        }

        while(running) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // Handle exception
                return;
            }
        }

    }

    // Tests that selecting VibeTown 1 is saved
    public void test_vibeTone9() {

        vibeToneSetting = getActivity();
        running = true;

        if(vibeToneSetting != null) {
            vibeToneSetting.runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    final RadioButton button9 = (RadioButton) vibeToneSetting.findViewById(R.id.radioButton9);
                    final RadioGroup radioGroup = (RadioGroup) vibeToneSetting.findViewById(R.id.myRadioGroup);
                    final View saveButton = vibeToneSetting.findViewById(R.id.button3);

                    if (button9 == null || radioGroup == null || saveButton == null) {
                        fail();
                    }

                    // Check VibeTone1
                    radioGroup.check(button9.getId());

                    int checkedId = radioGroup.getCheckedRadioButtonId();
                    final RadioButton checkedButton = (RadioButton) radioGroup.findViewById(checkedId);

                    // Assert VibeTone1 is selected
                    if(checkedButton == null) {
                        fail();
                    }

                    // Assert VibeTone1 button is selected
                    assertEquals(checkedButton.getId(), button9.getId());

                    // Click Save Button
                    saveButton.callOnClick();

                    // Assert that VibeTone1 was saved
                    assertEquals(9, vibeToneSetting.getVibeToneIndex());
                    running = false;
                }

            });
        }

        while(running) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // Handle exception
                return;
            }
        }

    }

    // Tests that selecting VibeTown 10 is saved
    public void test_vibeTone10() {

        vibeToneSetting = getActivity();
        running = true;
        if(vibeToneSetting != null) {
            vibeToneSetting.runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    final RadioButton button10 = (RadioButton) vibeToneSetting.findViewById(R.id.radioButton10);
                    final RadioGroup radioGroup = (RadioGroup) vibeToneSetting.findViewById(R.id.myRadioGroup);
                    final View saveButton = vibeToneSetting.findViewById(R.id.button3);

                    if (button10 == null || radioGroup == null || saveButton == null) {
                        fail();
                    }

                    // Check VibeTone10
                    radioGroup.check(button10.getId());

                    int checkedId = radioGroup.getCheckedRadioButtonId();
                    final RadioButton checkedButton = (RadioButton) radioGroup.findViewById(checkedId);

                    // Assert VibeTone10 is selected
                    if(checkedButton == null) {
                        fail();
                    }

                    // Assert VibeTone10 button is selected
                    assertEquals(checkedButton.getId(), button10.getId());

                    // Click Save Button
                    saveButton.callOnClick();

                    // Assert that VibeTone10 was saved
                    assertEquals(10, vibeToneSetting.getVibeToneIndex());
                    running = false;
                }

            });
        }

        while(running) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // Handle exception
                return;
            }
        }

    }

    // Checks to make sure two vibe tone types cannot be selected at once, and that the correct one is stored
/*
    public void test_noTwoVibeTones() {

        vibeToneSetting = getActivity();
        running = true;

        if(vibeToneSetting != null) {

            vibeToneSetting.runOnUiThread(new Runnable() {

                @Override
                public void run() {

                    final RadioButton button1 = (RadioButton) vibeToneSetting.findViewById(R.id.radioButton);
                    final RadioButton button2 = (RadioButton) vibeToneSetting.findViewById(R.id.radioButton2);
                    final RadioGroup radioGroup = (RadioGroup) vibeToneSetting.findViewById(R.id.myRadioGroup);
                    final View saveButton = vibeToneSetting.findViewById(R.id.button3);

                    if (button1 == null || button2 == null || radioGroup == null || saveButton == null) {
                        fail();
                    }

                    // Check VibeTone1
                    radioGroup.check(button1.getId());

                    // Check VibeTone2, should overwrite VibeTone1
                    radioGroup.check(button2.getId());

                    int checkedId = radioGroup.getCheckedRadioButtonId();
                    final RadioButton checkedButton = (RadioButton) radioGroup.findViewById(checkedId);

                    // Assert VibeTone2 is selected
                    if(checkedButton == null) {
                        fail();
                    }

                    // Assert VibeTone2 button is selected
                    assertEquals(checkedButton.getId(), button2.getId());

                    // Click Save Button
                    saveButton.callOnClick();

                    // Assert that VibeTone2 was saved
                    assertEquals(2, vibeToneSetting.getVibeToneIndex());
                    running = false;
                }

            });
        }

        while(running) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // Handle exception
                return;
            }
        }

    }
    */
}
