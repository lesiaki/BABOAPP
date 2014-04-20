package com.mixtapeictsolutions.george;

import com.actionbarsherlock.app.SherlockListFragment;
import com.actionbarsherlock.view.ActionMode;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.mixtapeictsolutions.george.R;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.AdapterView.OnItemLongClickListener;

public class InboxListFragment extends SherlockListFragment {

	private ActionMode mMode;
	SimpleAdapter adapter;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	super .onActivityCreated(savedInstanceState);	
	
	String[] values = new String[] { "Juma Ali", "Jack Swai"};
	
	adapter = new SimpleAdapter(getActivity(), values);
	setListAdapter(adapter);
	
	//When LongClick
	OnItemLongClickListener listener = new OnItemLongClickListener() {
        @Override
        public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int position, long id) {
            //Toast.makeText( getActivity().getBaseContext()  , "Long Clicked " + position , Toast.LENGTH_SHORT).show();
        	onListItemSelect(position);
            return false;
        }
    };

    getListView().setOnItemLongClickListener(listener);
    
	
	}
	
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
	// do something with the data
		//Toast toast = Toast.makeText(getActivity(),Integer.toString(position),Toast.LENGTH_SHORT);
		//toast.show();
		
	}
	private void onListItemSelect(int position){
		adapter.toggleSelection(position);
		boolean hasCheckedItems = adapter.getSelectedCount() > 0;
		if(hasCheckedItems && mMode == null){
			//There Selected items Start ActionMode
			mMode = getSherlockActivity().startActionMode(new ActionModeCallback());
		}else if(!hasCheckedItems && mMode != null){
			//There is No Selected item Finish ActionMode
			mMode.finish();
		}
		if(mMode != null){
			//Set the Counted  selected list item
			mMode.setTitle(String.valueOf(adapter.getSelectedCount())+" selected");
		}
		
	}
	
	
	 private class ActionModeCallback implements ActionMode.Callback {
		 
	        @Override
	        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
	            // inflate contextual menu
	            mode.getMenuInflater().inflate(R.menu.contextual_menu, menu);
	            
	            return true;
	        }
	 
	        @Override
	        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
	 
	            return false; 
	        }
	 
	        @Override
	        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
	        	switch(item.getItemId()){
	        	case R.id.item_delete:
	        		//Retrieve the selected ids to remove them
	        		SparseBooleanArray selected = adapter.getSelectedIds();
	        		for(int i = (selected.size()-1);i >= 0; i--){
	        			if(selected.valueAt(i)){
	        				adapter.remove(adapter.getItem(selected.keyAt(i)));
	        			}
	        		mMode.finish();
	        		}
	        	default:
	        		return false;
	        	}	        	
	 
	        }
	 
	        @Override
	        public void onDestroyActionMode(ActionMode mode) {
	            // remove selection
	        	adapter.removeSelection();
	        	mMode = null;
	        }
	    }
	

}
