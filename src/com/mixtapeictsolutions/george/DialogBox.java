package com.mixtapeictsolutions.george;

import com.mixtapeictsolutions.george.R;

import android.support.v4.app.DialogFragment;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;


public class DialogBox extends DialogFragment {
	
	@Override
	public AlertDialog onCreateDialog(Bundle savedInstanceState) {
	    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    builder.setTitle("Profile Photo")
	           .setItems(R.array.profile_image_option, new DialogInterface.OnClickListener() {
	               public void onClick(DialogInterface dialog, int which) {
	               // The 'which' argument contains the index position
	               // of the selected item
	            	   switch(which){
	            	   case 0:
	            		   Toast toast = Toast.makeText(getActivity(), "Ya kwanza", Toast.LENGTH_SHORT);
	           			   toast.show();
	           			   dialog.dismiss();
	           		
	           			   break;
	            	   case 1:
	            		   Toast toast2 = Toast.makeText(getActivity(), "Ya Pili", Toast.LENGTH_SHORT);
	           			   toast2.show();
	           			   dialog.dismiss();
	            		   break;
	            	   case 2:
	            		   Toast toast3 = Toast.makeText(getActivity(), "Ya Tatu", Toast.LENGTH_SHORT);
	           			   toast3.show();
	           			   dialog.dismiss();
	           			   break;

	            	   }
	            	   
	           }
	    });
	    return builder.create();
	}
	
	
	

}
