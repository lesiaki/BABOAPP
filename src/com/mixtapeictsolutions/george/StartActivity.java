/*
 * Copyright (C) 2011 The Android Open Source Project
 */

package com.mixtapeictsolutions.george;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Window;
import com.mixtapeictsolutions.george.R;


public class StartActivity extends SherlockActivity implements OnClickListener{
	
	//Variable Declaration
	Button register_btn;


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_PROGRESS);
        
        setContentView(R.layout.start_main_activity_layout);
         
        register_btn = (Button)findViewById(R.id.register_btn);
        register_btn.setOnClickListener(this);
        
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            return super.onOptionsItemSelected(item);
        
    }

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		//Start the Home Activity
        Intent home = new Intent(getApplicationContext(),MainActivity.class);
        startActivity(home);
		
	}

}