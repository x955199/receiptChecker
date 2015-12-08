package com.mj.receiptchecker;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

/**
 * Created by Michael on 11/28/2015.
 */
public class ReceiptAdapter extends ArrayAdapter<Receipt> {
    // View lookup cache
    private static class ViewHolder {
        TextView id;
        TextView date;
    }

    public ReceiptAdapter(Context context, List<Receipt> receipts) {
        super(context, R.layout.receipt_row_view, receipts);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Receipt receipt = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.receipt_row_view, parent, false);
            viewHolder.id = (TextView) convertView.findViewById(R.id.receipt_id);
            viewHolder.date = (TextView) convertView.findViewById(R.id.receipt_date);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data into the template view using the data object
        if(receipt.matchLevel > 0) {
            viewHolder.id.setTextColor(Color.GREEN);
        } else {
            viewHolder.id.setTextColor(Color.RED);
        }
        viewHolder.id.setText(receipt.id);
        viewHolder.date.setText(receipt.date.toString("MM/dd/yyyy"));
        // Return the completed view to render on screen
        return convertView;
    }
}
