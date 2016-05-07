package tests;

import android.test.ActivityInstrumentationTestCase2;

import com.vapenaysh.jace.myapplication.LoginPage;

/**
 * Created by yil261 on 5/6/2016.
 */
public class TestLoginFunc extends ActivityInstrumentationTestCase2<LoginPage> {

    LoginPage loginPage;
    public TestLoginFunc(){
        super(LoginPage.class);
    }

    public void test_first(){
        loginPage = getActivity();
    }


}
