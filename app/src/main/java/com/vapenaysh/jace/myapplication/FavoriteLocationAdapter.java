package com.vapenaysh.jace.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * FavoriteLocationAdapter.java
 *
 * Adapter class for storing the favorite location information,
 * including LatLng, name Data, vibeTone and ringTone
 *
 * Created by matt on 5/30/16.
 */
public class FavoriteLocationAdapter extends BaseAdapter
{

    private Context mContext;
    private ArrayList<FavoriteLocation> locations;
    private static LayoutInflater inflater = null;
    private String partnerEmail;

    public FavoriteLocationAdapter(Context context, ArrayList<FavoriteLocation> data){

        mContext = context;
        locations = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setPartnerEmail(String email){
        partnerEmail  = email;
    }

    @Override
    public int getCount() {
        if(locations != null) {
            return locations.size();
        }
        else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {

        View view = convertView;
        final int currPosition = position;

        if (convertView == null){

            view = inflater.inflate(R.layout.user_center_list_row, null);

            TextView title = (TextView) view.findViewById(R.id.locname);
            TextView coordination = (TextView) view.findViewById(R.id.loccoordinates);


            final FavoriteLocation mLocation = locations.get(position);
            title.setText(mLocation.getName());
            coordination.setText(mLocation.getMyLatLng().toString());

        }

        return view;
    }
}