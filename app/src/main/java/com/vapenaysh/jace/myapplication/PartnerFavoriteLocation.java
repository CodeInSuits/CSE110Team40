package com.vapenaysh.jace.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * PartnerFavoriteLocation.java
 *
 * Class for displaying the partner favorite location information.
 * This class uses a adapter class for UI.
 *
 * Created by Esther on 5/29/16.
 */
public class PartnerFavoriteLocation extends AppCompatActivity {

    private ListView listView;
    private CustomListViewAdapter customListViewAdapter;
    FirebaseDatabase locationsDB = FirebaseDatabase.getInstance();
    ArrayList<FavoriteLocation> fll;
    private static String email;
    private Button refreshBu;

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
        this.email = uid;
        Toast.makeText(getApplicationContext(), "My Partner is " + uid, Toast.LENGTH_SHORT).show();

        DatabaseReference db;
        db = locationsDB.getReference(uid + Constants.LOC_URL);
        fll = new ArrayList<>();

        db.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                GenericTypeIndicator<ArrayList<FavoriteLocation>> t = new GenericTypeIndicator<ArrayList<FavoriteLocation>>() {
                };

                fll = dataSnapshot.getValue(t);

                if (fll != null) {
                    Log.d("NOTE", "Locations: " + fll.toString());
                    Toast.makeText(getBaseContext(), "Partner has " + fll.size() + " favorite locations", Toast.LENGTH_SHORT).show();
                }


                listView = (ListView) findViewById(R.id.list);
                customListViewAdapter = new CustomListViewAdapter(getApplicationContext(), fll);
                customListViewAdapter.setPartnerEmail(getPartnerEmail());
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

            @Override
            public void onCancelled(DatabaseError firebaseError) {
                // Failed to read value
                Log.w("ERROR:", "Failed to read value.", firebaseError.toException());
            }
        });

        refreshBu = (Button)findViewById(R.id.buttonref);

        refreshBu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                refresh();
            }
        });
    }

    public static String getPartnerEmail(){
        return email;
    }

    private void refresh(){
        Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}
