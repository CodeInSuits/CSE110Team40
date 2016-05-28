package com.vapenaysh.jace.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by paulodichone on 2/4/15.
 */
public class CustomListViewAdapter extends BaseAdapter {

    private Context mContext;
    private  ArrayList<HashMap<String, String>> locations;
    private static LayoutInflater inflater = null;


    public CustomListViewAdapter(Context context, ArrayList<HashMap<String, String>> data){

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

            HashMap<String, String> mLocation = new HashMap<>();

            mLocation = locations.get(position);



            title.setText(mLocation.get("title"));
            location.setText(mLocation.get("location"));


        }


        return view;
    }
}
