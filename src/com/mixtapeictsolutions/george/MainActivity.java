/*
 * Copyright (C) 2011 The Android Open Source Project
 */

package com.mixtapeictsolutions.george;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;

import android.support.v4.app.FragmentTransaction;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.ActionMode;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Window;
import com.mixtapeictsolutions.george.R;


public class MainActivity extends SherlockFragmentActivity implements ActionBar.TabListener {

	ActionMode mMode;
    Fragment MapFragment;
    Fragment UsersListFragment;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_PROGRESS);
         
        setContentView(R.layout.main_activity_layout);
             
        final ActionBar ab = getSupportActionBar();
        // set defaults for logo & home up
        ab.setDisplayUseLogoEnabled(false);
        ab.setDisplayHomeAsUpEnabled(false);
        
        //Frgment
        MapFragment = new MapFragment();
        UsersListFragment = new UsersListFragment();
        
        // set up tabs nav
        
        ab.addTab(ab.newTab().setText("Location").setTabListener(this));
        ab.addTab(ab.newTab().setText("List").setTabListener(this));
        // default to tab navigation
        showTabsNav();
        
        
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.menu_upgrade:
        	startActivity(new Intent(getApplicationContext(),RegistrationActivity.class));
        	return true;
        case R.id.menu_star:
        	startActivity(new Intent(getApplicationContext(),HistroryActivity.class));
        	return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }
    
    private void showStandardNav() {
        ActionBar ab = getSupportActionBar();
        if (ab.getNavigationMode() != ActionBar.NAVIGATION_MODE_STANDARD) {
            ab.setDisplayShowTitleEnabled(true);
            ab.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        }
    }

  

    private void showTabsNav() {
        ActionBar ab = getSupportActionBar();
        if (ab.getNavigationMode() != ActionBar.NAVIGATION_MODE_TABS) {
            ab.setDisplayShowTitleEnabled(true);
            ab.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        }
    }

    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
    	// When the given tab is selected, show the tab contents in the
    	// container view.
    		switch(tab.getPosition()){
    		case 0:
    			ft.replace(R.id.fragment_container,MapFragment);
    			break;
    		case 1:
    			ft.replace(R.id.fragment_container,UsersListFragment);
    			break;
    		}
				
    }
    
    

    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
    	switch(tab.getPosition()){
		case 0:
			ft.remove(MapFragment);
			break;
		case 1:
			ft.remove(UsersListFragment);
			break;
		}
    }

    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
        // FIXME implement this
    }
    
   
}