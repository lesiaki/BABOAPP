/*
 * Copyright (C) 2011 The Android Open Source Project
 */

package com.mixtapeictsolutions.george;


import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.Window;
import com.mixtapeictsolutions.george.R;


public class RegistrationActivity extends SherlockFragmentActivity implements OnClickListener{
	
	//Variable Declaration
	Button register_btn;
	ImageView profile_image,launch_camera,launch_gallery,remove_profile;
	//Variable for Camera
	// Activity request codes
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private static final int IMAGE_GALLARY_REQUEST_CODE = 0;
    public static final int MEDIA_TYPE_IMAGE = 1;
    
 
    // directory name to store captured images and videos
    private static final String IMAGE_DIRECTORY_NAME = "baboImages";
 
    private Uri fileUri; // file url to store image/video
 
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_PROGRESS);
        
        setContentView(R.layout.registration_activity_layout);
        final ActionBar ab = getSupportActionBar();

        // set defaults for logo & home up
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setDisplayUseLogoEnabled(true);
         
        register_btn = (Button)findViewById(R.id.register_btn);
        profile_image = (ImageView)findViewById(R.id.profile_image);
        launch_camera = (ImageView)findViewById(R.id.upload_profile);
        launch_gallery = (ImageView)findViewById(R.id.upload_from_gallery);
        remove_profile = (ImageView)findViewById(R.id.remove_profile);
        
        register_btn.setOnClickListener(this);
        profile_image.setOnClickListener(this);
        launch_camera.setOnClickListener(this);
        launch_gallery.setOnClickListener(this);
        remove_profile.setOnClickListener(this);
        
        // Checking camera availability
        if (!isDeviceSupportCamera()) {
            Toast.makeText(getApplicationContext(),
                    "Sorry! Your device doesn't support camera",
                    Toast.LENGTH_LONG).show();
            // will close the app if the device does't have camera
            finish();
        }
        
         //captureImage();
        //OpeingGallery();
        
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
		switch(view.getId()){
		case R.id.register_btn:
			Intent home = new Intent(getApplicationContext(),MainActivity.class);
	        startActivity(home);
			break;
		case R.id.profile_image:
//			Toast toast = Toast.makeText(getApplication(), "Profile", Toast.LENGTH_SHORT);
//			toast.show();
			FragmentManager manager =   getSupportFragmentManager();
			DialogBox profile_image_dialog = new DialogBox();
			profile_image_dialog.show(manager,"Tag");
			break;
		case R.id.upload_profile:
			captureImage();
			break;
		case R.id.upload_from_gallery:
			OpeingGallery();
			break;
		case R.id.remove_profile:
			removeProfile();
			break;
		}
        
		
	}
	/**
     * Checking device has camera hardware or not
     * */
    private boolean isDeviceSupportCamera() {
        if (getApplicationContext().getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_CAMERA)) {
            // this device has a camera
            return true;
        } else {
            // no camera on this device
            return false;
        }
    }
	
    /**
     * Capturing Camera Image will lauch camera app requrest image capture
     */
    public void captureImage() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
 
        fileUri = getOutputMediaFileUri(MEDIA_TYPE_IMAGE);
 
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
 
        // start the image capture Intent
        startActivityForResult(intent, CAMERA_CAPTURE_IMAGE_REQUEST_CODE);
    }
 
    /**
     * Here we store the file url as it will be null after returning from camera
     * app
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
 
        // save file url in bundle as it will be null on scren orientation
        // changes
        outState.putParcelable("file_uri", fileUri);
    }
 
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
 
        // get the file url
        fileUri = savedInstanceState.getParcelable("file_uri");
    }
 
    
 
    /**
     * Receiving activity result method will be called after closing the camera
     * */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	
        // if the result is capturing Image
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // successfully captured the image
                // display it in image view
                previewCapturedImage();
            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled Image capture
                Toast.makeText(getApplicationContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to capture image
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }
        } 
        
        if(requestCode == IMAGE_GALLARY_REQUEST_CODE) {
        
            if(resultCode == RESULT_OK){ 
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String filePath = cursor.getString(columnIndex); // file path of selected image
                cursor.close();
                      //  Convert file path into bitmap image using below line.
                Bitmap yourSelectedImage = BitmapFactory.decodeFile(filePath);
              
                      // put  bitmapimage in your imageview
                profile_image.setImageBitmap(yourSelectedImage);
            }
        }
    }
    /**
     * Display image from a path to ImageView
     */
    public void previewCapturedImage() {
        try {
 
            profile_image.setVisibility(View.VISIBLE);
 
            // bimatp factory
            BitmapFactory.Options options = new BitmapFactory.Options();
 
            // downsizing image as it throws OutOfMemory Exception for larger
            // images
            options.inSampleSize = 8;
 
            final Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(),
                    options);
 
            profile_image.setImageBitmap(bitmap);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
 
    
     
    /**
     * ------------ Helper Methods ---------------------- 
     * */
 
    /**
     * Creating file uri to store image/video
     */
    public Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }
 
    /**
     * returning image / video
     */
    public static File getOutputMediaFile(int type) {
 
        // External sdcard location
        File mediaStorageDir = new File(
                Environment
                        .getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                IMAGE_DIRECTORY_NAME);
 
        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d(IMAGE_DIRECTORY_NAME, "Oops! Failed create "
                        + IMAGE_DIRECTORY_NAME + " directory");
                return null;
            }
        }
 
        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss",
                Locale.getDefault()).format(new Date(type));
        File mediaFile;
        if (type == MEDIA_TYPE_IMAGE) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator
                    + "IMG_" + timeStamp + ".jpg");
        } else {
            return null;
        }
 
        return mediaFile;
    }

    /**
     * ************ Louching/Opening Image Gallery ************* 
     * */
    public void OpeingGallery(){
    	  Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
    	  intent.setType("image/*");
    	  startActivityForResult(intent, 0);
    }
    /**
     * ************ Remove Image From Profile image ************* 
     * */
    public void removeProfile(){
    	profile_image.setImageResource(R.drawable.profile);
    }
    
}