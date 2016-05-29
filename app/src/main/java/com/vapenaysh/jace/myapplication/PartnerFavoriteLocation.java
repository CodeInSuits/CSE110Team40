package com.vapenaysh.jace.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PartnerFavoriteLocation extends AppCompatActivity {

    private ListView listView;
    private CustomListViewAdapter customListViewAdapter;
    FirebaseDatabase locationsDB = FirebaseDatabase.getInstance();
    ArrayList<FavoriteLocation> fll;

    /*
     TODO: 1. Fully integrate with the customListViewAdapter
           2. Understand Matt's code for data pulling from firebase
                i. Stored location as an ArraryList
                ii. Coordination with wrapper MyLatlng


     */


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partner_favorite_location);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String uid = getIntent().getStringExtra("PartnerEmail");
        Toast.makeText(getApplicationContext(),"Partner is " + uid, Toast.LENGTH_SHORT).show();



        DatabaseReference db;
        db = locationsDB.getReference(uid + Constants.LOC_URL);
        fll = new ArrayList<>();




        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Object o = dataSnapshot.getValue();
                if(o instanceof ArrayList) { //error checking
                    GenericTypeIndicator<ArrayList<FavoriteLocation>> t = new GenericTypeIndicator<ArrayList<FavoriteLocation>>() {};
                    fll = dataSnapshot.getValue(t);
                    Log.d("NOTE", "Locations: " + fll.toString());

                }
                else{
                    fll = new ArrayList<>();
                }
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {
                // Failed to read value
                Log.w("ERROR:", "Failed to read value.", firebaseError.toException());
            }
        });

        ArrayList<FavoriteLocation> testList = new ArrayList<>();
        testList.add(new FavoriteLocation(new LatLng(1.0, 10), "UCSD"));
        testList.add(new FavoriteLocation(new LatLng(1.0, 10), "PC"));

        testList.add(new FavoriteLocation(new LatLng(1.0, 10), "CS LAB"));
        testList.add(new FavoriteLocation(new LatLng(1.0, 10), "CS LAB"));

        testList.add(new FavoriteLocation(new LatLng(1.0, 10), "CS LAB"));

        testList.add(new FavoriteLocation(new LatLng(1.0, 10), "CS LAB"));

        testList.add(new FavoriteLocation(new LatLng(1.0, 10), "CS LAB"));

        testList.add(new FavoriteLocation(new LatLng(1.0, 10), "CS LAB"));

        testList.add(new FavoriteLocation(new LatLng(1.0, 10), "CS LAB"));

        testList.add(new FavoriteLocation(new LatLng(1.0, 10), "CS LAB"));

        testList.add(new FavoriteLocation(new LatLng(1.0, 10), "CS LAB"));

        testList.add(new FavoriteLocation(new LatLng(1.0, 10), "CS LAB"));

        testList.add(new FavoriteLocation(new LatLng(1.0, 10), "CS LAB"));

        testList.add(new FavoriteLocation(new LatLng(1.0, 10), "CS LAB"));

        testList.add(new FavoriteLocation(new LatLng(1.0, 10), "CS LAB"));

        testList.add(new FavoriteLocation(new LatLng(1.0, 10), "CS LAB"));

        testList.add(new FavoriteLocation(new LatLng(1.0, 10), "CS LAB no life"));


        listView = (ListView) findViewById(R.id.list);
        Toast.makeText(getApplicationContext(),"Partner has " + fll.size() + " favorite locations", Toast.LENGTH_SHORT).show();


        customListViewAdapter = new CustomListViewAdapter(getApplicationContext(), testList);
        listView.setAdapter(customListViewAdapter);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int myPosition = position;

                String itemClickedId = listView.getItemAtPosition(myPosition).toString();

                Toast.makeText(getApplicationContext(), "Id Clicked: " + itemClickedId, Toast.LENGTH_LONG).show();


            }
        });

    }

}
