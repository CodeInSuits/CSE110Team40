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
 * Created by XuanpeiEstherOuyang on 5/27/16.
 */
public class TestVibeTone extends ActivityInstrumentationTestCase2<VibeToneSetting> {

    public TestVibeTone() {
        super(VibeToneSetting.class);
    }

    private VibeToneSetting vibeToneSetting;

    public void test_vibeTone() {

        vibeToneSetting = getActivity();
        vibeToneSetting.runOnUiThread(new Runnable() {

            @Override
            public void run() {

                final RadioButton button1 = (RadioButton) vibeToneSetting.findViewById(R.id.radioButton);
                final RadioGroup radioGroup = (RadioGroup) vibeToneSetting.findViewById(R.id.myRadioGroup);

                assertNotNull(button1);

                if (button1 == null || radioGroup == null) {
                    fail("button or radiogroup null");
                }

                // Check VibeTone1
                radioGroup.check(button1.getId());

                int checkedId = radioGroup.getCheckedRadioButtonId();
                final RadioButton checkedButton = (RadioButton)radioGroup.findViewById(checkedId);

                // Assert VibeTone1 is selected
                assertEquals(checkedButton.getId(), button1.getId());

                // Access Save Button
                final Button saveButton = (Button) vibeToneSetting.findViewById(R.id.button3);
                assertNotNull(saveButton);
                saveButton.callOnClick();

                // Assert that VibeTone1 was saved
                assertEquals(1, vibeToneSetting.getVibeToneIndex());

            }

        });

    }


}
