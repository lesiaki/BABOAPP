package com.mixtapeictsolutions.george;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragment;
import android.annotation.SuppressLint;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class PersonalDetailFragment extends SherlockFragment {

	


	String name;
	
	@SuppressLint("ValidFragment")
	public PersonalDetailFragment(String name){
		this.name = name;	
	}
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ActionBar actionBar = getSherlockActivity().getSupportActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		
		
		
		//bar.hide();
	}
	
	public void onPause() {

	    super.onPause();
	    
	    ActionBar actionBar = getSherlockActivity().getSupportActionBar();
	    actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
	   
	}

	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view  = inflater.inflate(R.layout.personal_detail_fragment_layout, container, false);
		
		TextView txt = (TextView)view.findViewById(R.id.person_name);
		txt.setText(name);
		//bar.setTitle("Another look");
		//bar.removeAllTabs();
		return view;
	}
	
	
	



}
