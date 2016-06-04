package com.vapenaysh.jace.myapplication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * CustomListViewAdapter.java
 *
 * Adapter class for displaying UI for partner's favorite location
 * information, including name, vibeTone, ringTone and location information
 *
 * Created by jace on 2/4/15.
 */
public class CustomListViewAdapter extends BaseAdapter  {

    private Context mContext;
    private ArrayList<FavoriteLocation> locations;
    private static LayoutInflater inflater = null;
    private String tonePath;
    private Activity activity;
    private String partnerEmail;

    /**
     * Constructor for CustomListViewAdapter
     * @param context
     * @param data
     */
    public CustomListViewAdapter(Context context, ArrayList<FavoriteLocation> data){

        mContext = context;
        locations = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * setter method for partner email
     * @param email
     */
    public void setPartnerEmail(String email){
        partnerEmail  = email;
    }

    /**
     * getter method for count
     * @return
     */
    @Override
    public int getCount() {
        if(locations != null) {
            return locations.size();
        }
        else {
            return 0;
        }
    }

    /**
     * getter method for item
     * @param position
     * @return
     */
    @Override
    public Object getItem(int position) {
        return position;
    }

    /**
     * getter method for item id
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * getter method for view
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        View view = convertView;
        final int currPosition = position;

        if (convertView == null){

            // UI setting for displaying the favorite location information

            view = inflater.inflate(R.layout.list_row, null);

            TextView title = (TextView) view.findViewById(R.id.title);
            TextView coordination = (TextView) view.findViewById(R.id.coordinates);

            Button soundButton = (Button) view.findViewById(R.id.button_ringTone);
            Button vibeButton = (Button) view.findViewById(R.id.button_vibe);


            final FavoriteLocation mLocation = locations.get(position);
            title.setText(mLocation.getName());
            coordination.setText(mLocation.getMyLatLng().toString());
            String ringToneName = mLocation.getRingTone().substring(
                    mLocation.getRingTone().length()-2, mLocation.getRingTone().length());
            soundButton.setText("Ring Tone " + ringToneName);
            vibeButton.setText("Vibe Tone " + (mLocation.getVibeTone()+1));

            // set the onClick method for the soundButton
            soundButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent i = new Intent(mContext, RingToneSetting.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("locIndex", currPosition);
                    i.putExtra("uid", partnerEmail);
                    mContext.startActivity(i);
                }

            });

            // set the onClick method for the vibeButton
            vibeButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    Intent i = new Intent(mContext, VibeToneSetting.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("locName", mLocation.getName());
                    i.putExtra("position", position);
                    i.putExtra("uid", partnerEmail);
                    mContext.startActivity(i);
                }
            });

        }

        return view;
    }
}
