package com.vapenaysh.jace.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by paulodichone on 2/4/15.
 */
public class CustomListViewAdapter extends BaseAdapter {

    private Context mContext;
    private  ArrayList<FavoriteLocation> locations;
    private static LayoutInflater inflater = null;


    public CustomListViewAdapter(Context context, ArrayList<FavoriteLocation> data){

        mContext = context;
        locations = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    @Override
    public int getCount() {
        return locations.size();
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
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (convertView == null){

            view = inflater.inflate(R.layout.list_row, null);

            TextView title = (TextView) view.findViewById(R.id.title);
            TextView location = (TextView) view.findViewById(R.id.location);
            ImageView image = (ImageView) view.findViewById(R.id.tonesetting);

            image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //REQUEST TO SET THE TONE


                }
            });



            FavoriteLocation mLocation = locations.get(position);



            title.setText(mLocation.getName());
            location.setText(mLocation.getMyLatLng().getLat() + ", " + mLocation.getMyLatLng().getLon());


        }


        return view;
    }
}
