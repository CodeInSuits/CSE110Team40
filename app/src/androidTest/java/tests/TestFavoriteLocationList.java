package tests;

import android.test.ActivityInstrumentationTestCase2;
import com.vapenaysh.jace.myapplication.FavoriteLocationList;

/**
 * Created by adamabadilla on 5/7/16.
 */
public class TestFavoriteLocationList extends ActivityInstrumentationTestCase2<FavoriteLocationList> {

    FavoriteLocationList testFavoriteLocationList;

    public TestFavoriteLocationList() {

        super(FavoriteLocationList.class);

    }

    public void test_first() {

        testFavoriteLocationList = getActivity();


    }







}
