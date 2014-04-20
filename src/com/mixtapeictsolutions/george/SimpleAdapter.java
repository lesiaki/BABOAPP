package com.mixtapeictsolutions.george;

import android.content.Context;
import android.graphics.Color;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class SimpleAdapter extends ArrayAdapter<String> {

	private final Context context;
	private final String[] values;
	private SparseBooleanArray selectedIds;
	
	public SimpleAdapter(Context context, String[] values) {
		super (context, R.layout.single_list_row_layout , values);
		this.context = context;
		this.values = values;
		selectedIds = new SparseBooleanArray();
		
		}
	
	@Override
	public View getView( int position, View convertView, ViewGroup parent) {
	LayoutInflater inflater = (LayoutInflater) context
	.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	View rowView = inflater.inflate(R.layout.single_list_row_layout, parent, false);
	TextView textView = (TextView) rowView.findViewById(R.id.firstLine);
	ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
	textView.setText(values[position]);
	imageView.setImageResource(R.drawable.person);
	
	rowView.setBackgroundColor(selectedIds.get(position) ? 0x9934B5E4 : Color.TRANSPARENT);
	return rowView;
	}
	
	public void toggleSelection(int position) {
        selectView(position, !selectedIds.get(position));
    }
	
	public void selectView(int position, boolean value) {
        if (value)
        	selectedIds.put(position, value);
        else
        	selectedIds.delete(position);
 
        notifyDataSetChanged();
    }
	
	public int getSelectedCount() {
        return selectedIds.size();
    }
 
    public SparseBooleanArray getSelectedIds() {
        return selectedIds;
    }
    
    public void removeSelection() {
    	selectedIds = new SparseBooleanArray();
        notifyDataSetChanged();
    }

}
