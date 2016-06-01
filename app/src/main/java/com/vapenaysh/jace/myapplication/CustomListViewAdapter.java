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
 * Created by paulodichone on 2/4/15.
 */
public class CustomListViewAdapter extends BaseAdapter  {

    private Context mContext;
    private ArrayList<FavoriteLocation> locations;
    private static LayoutInflater inflater = null;
    private String tonePath;
    private Activity activity;

    public CustomListViewAdapter(Context context, ArrayList<FavoriteLocation> data){

        mContext = context;
        locations = data;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        final int currPosition = position;

        if (convertView == null){

            view = inflater.inflate(R.layout.list_row, null);

            TextView title = (TextView) view.findViewById(R.id.title);

            Button soundButton = (Button) view.findViewById(R.id.button_ringTone);
            Button vibeButton = (Button) view.findViewById(R.id.button_vibe);

            final FavoriteLocation mLocation = locations.get(position);
            title.setText(mLocation.getName());

            soundButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent i = new Intent(mContext, RingToneSetting.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("locIndex", currPosition);
                    mContext.startActivity(i);
                }

            });

            vibeButton.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    Intent i = new Intent(mContext, VibeToneSetting.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    i.putExtra("locName", mLocation.getName());
                    mContext.startActivity(i);

                }
            });

        }

        return view;
    }
}
