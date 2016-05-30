package com.vapenaysh.jace.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by imouto on 5/30/16.
 */
public class FavoriteLocationAdapter extends ArrayAdapter<FavoriteLocation>
{
    public FavoriteLocationAdapter(Context context, int resource, List<FavoriteLocation> favoriteLocations)
    {
        super(context, resource, favoriteLocations);
    }
    @Override
    public View getView(int pos, View convertView, ViewGroup parent)
    {
        View v = convertView;
        if (v == null)
        {
            LayoutInflater layoutInflater = LayoutInflater.from(getContext());
            v = layoutInflater.inflate(R.layout.user_center_list_row, null);
        }
        FavoriteLocation favoriteLocation = getItem(pos);
        if (favoriteLocation != null)
        {
            TextView title = (TextView) v.findViewById(R.id.name);
            TextView visited = (TextView) v.findViewById(R.id.visited);
            title.setText(favoriteLocation.getName());
            if (favoriteLocation.getDate() != null)
                visited.setText("LAST VISITED ON " + favoriteLocation.getDate().toString());
            else
                visited.setText("NEVER VISITED BEFORE");
        }
        return v;
    }
}
