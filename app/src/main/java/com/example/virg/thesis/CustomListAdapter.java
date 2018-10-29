package com.example.virg.thesis;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class CustomListAdapter extends ArrayAdapter {
    private Context context;
    private String [] name;

    public CustomListAdapter(Context context,  String [] name){
        super(context, R.layout.listview_row, name);
        this.context = context;
        this.name = name;
    }

    @Override

    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_row, parent, false);
        }
        TextView names = convertView.findViewById(R.id.name);
        // Populate the data into the template view using the data object
        names.setText(name[position]);

        // Return the completed view to render on screen
        return convertView;
    }
}


