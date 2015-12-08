package com.mj.receiptchecker.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mj.receiptchecker.R;

/**
 * Created by Michael on 12/8/2015.
 */
public class GridViewAdapter extends BaseAdapter {

    private Context context;
    private final String[] strings;

    // View lookup cache
    private static class ViewHolder {
        ImageView image;
        TextView text;
    }

    public GridViewAdapter(Context context, String[] strings) {
        this.context = context;
        this.strings = strings;
    }

    @Override
    public int getCount() {
        return strings.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.image_text_grid, parent, false);
           //viewHolder.image = (ImageView) convertView.findViewById(R.id.receipt_id);
            viewHolder.text= (TextView) convertView.findViewById(R.id.grid_text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        //viewHolder.image.setImageResource(R.drawable.wally);
        viewHolder.text.setText(strings[position]);
        // Return the completed view to render on screen
        return convertView;
    }
}