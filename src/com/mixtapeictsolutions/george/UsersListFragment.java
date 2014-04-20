package com.mixtapeictsolutions.george;

import com.mixtapeictsolutions.george.R;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ListView;

public class UsersListFragment extends ListFragment {
	
	


	SimpleAdapter adapter;
	String[] values = new String[] { "Juma Ali", "Jack Swai", "Fredy Mbasha",
			"Ezekiel Nyamle", "Berwin Sengo", "David Bruno", "George John", "Emanuel kampate",
			"Wisdom John", "Tariq Alli" };
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	super .onActivityCreated(savedInstanceState);	
	
	
	
	adapter = new SimpleAdapter(getActivity(), values);
	setListAdapter(adapter);
	}
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
	// do something with the data
		//Toast toast = Toast.makeText(getActivity(),Integer.toString(position),Toast.LENGTH_SHORT);
		//toast.show();
		String name = String.valueOf(adapter.getItem(position));
		FragmentTransaction ft = getFragmentManager().beginTransaction();
		ft.replace(R.id.fragment_container,new PersonalDetailFragment(name));
		ft.addToBackStack(null);
		ft.commit();
		
	}
	
	
	

}
