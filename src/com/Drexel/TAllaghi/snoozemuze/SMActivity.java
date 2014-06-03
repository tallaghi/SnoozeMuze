package com.Drexel.TAllaghi.snoozemuze;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;

public class SMActivity extends Activity {

	//Initializing buttons, seekbar, and etc for global use
	private ImageButton mNextButton;
	private ImageButton mPreviousButton;
	private ImageButton mPlayButton;
	private ImageButton mPicture;
	private Button mCredits;
	private SeekBar mVolSlider;
	private AudioManager mAudioManager;
	private static MediaPlayer mp=new MediaPlayer();
	private static int mCurrentIndex;
	private static final String KEY_INDEX="index";
	private static final String TAG="SMActivity";
	private static int SoundRID;
	
	//Initializing Picture Sound object array to house the pointers
	// of the sound and picture assets, Picture Sound object array.
	private Picture_Sound[] storage= new Picture_Sound[]{
		new Picture_Sound(R.drawable.forest_resized_2,R.raw.forest_and_birds_mpthree),
		new Picture_Sound(R.drawable.rain_resized,R.raw.rain_and_thunder_mpthree),
		new Picture_Sound(R.drawable.food_court_resized,R.raw.food_court_mpthree),
		new Picture_Sound(R.drawable.beach_resized,R.raw.beach_mpthree)
	};
	
	//Method that updates the picture with the current picture from the 
	// drawable file.
	private void updatePicture(){
		int pic=storage[mCurrentIndex].getPRID();
        try{
        	mPicture.setImageResource(pic);
        }
        catch(NullPointerException e){
        	Log.d(TAG,"pic is"+pic);
        }
	}
	
	//Method that updates the sound asset with the current sound pointer
	// that is stored in the Picture Sound object array.
	private void updateSound(){
		try{
			SoundRID=storage[mCurrentIndex].getSRID();
		}
		catch(NullPointerException e){
			Log.d(TAG,"sound created");
		}
	}
	
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
    	//Calls standard onCreate method.  	
    	super.onCreate(savedInstanceState);
    	
    	//Sets the layout to the activity_sm xml file.
        setContentView(R.layout.activity_sm);
        
        //Sets the current index to 0, so the first picture shows
        // up when onCreate is called.
        mCurrentIndex=0;
        
        
        //Ties the buttons and image buttons to the id's
        // that the xml defines.
        mNextButton=(ImageButton)findViewById(R.id.next_button);
        mPreviousButton=(ImageButton)findViewById(R.id.previous_button);
        mPlayButton=(ImageButton)findViewById(R.id.play_button);
        mPicture=(ImageButton)findViewById(R.id.sound_picture);
        mCredits=(Button)findViewById(R.id.toCredits);
        
        //Initializing variables for the volume slider integration
        mAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        mVolSlider = (SeekBar)findViewById(R.id.volumeSlider);
        
        //Setting max volume of the slider to the max volume, and the
        // progress to the current volume.
        mVolSlider.setMax(maxVolume);
        mVolSlider.setProgress(curVolume);
        
        //Adding a listener to the volume slider
        mVolSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar arg0) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar arg0) {
            }
            
            //Setting volume on the device to the position set on the slider 
            @Override
            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
                mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, arg1, 0);
            }
        });
        
        mPicture.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				if(!mp.isPlaying()){
				mp.reset();
				mp=MediaPlayer.create(SMActivity.this, SoundRID);
				mp.start();
				mp.setLooping(true);
				}
				else{
					mp.stop();
					mp.setLooping(false);					
				}
			}
        	
        });
        
        mPlayButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				if(!mp.isPlaying()){
					mp.reset();
					mp=MediaPlayer.create(SMActivity.this, SoundRID);
					mp.start();
					}
					else{
						mp.stop();
						mp.setLooping(false);
					}
				
			}
        	
        });
        
        mNextButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				mp.stop();
				mp.setLooping(false);
				if (mCurrentIndex<=2){
					mCurrentIndex++;}
				else
					mCurrentIndex=0;
				updatePicture();
				updateSound();
			}
        	
        });
        mPreviousButton.setOnClickListener(new OnClickListener(){
        	
        	@Override
        	public void onClick(View v){
        		mp.stop();
        		mp.setLooping(false);
        		if(mCurrentIndex>0){
        			mCurrentIndex--;}
        		else{
        			mCurrentIndex=3;
        		}
        		updatePicture();
        		updateSound();
        	}
        });
        mCredits.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent i=new Intent(SMActivity.this, CreditsActivity.class);
				startActivity(i);
				
			}
        	
        });
        
        if(savedInstanceState!=null){
        	mCurrentIndex=savedInstanceState.getInt(KEY_INDEX,0);
        }
        updatePicture();
        updateSound();
        
    }
    
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
    	super.onSaveInstanceState(savedInstanceState);
    	//Log.i(TAG, "onSaveInstanceState");
    	savedInstanceState.putInt(KEY_INDEX,mCurrentIndex);
    }
    @Override
    public void onStart(){
    	super.onStart();
    	Log.d(TAG,"onStart() called");
    }
    
    @Override
    public void onPause(){
    	super.onPause();
    	Log.d(TAG,"onPause() called");
    }
    
    @Override
    public void onResume(){
    	super.onResume();
    	Log.d(TAG,"onResume() called");
    }
    
    @Override
    public void onStop(){
    	super.onStop();
    	Log.d(TAG,"onStop() called");
    }
    
    @Override
    public void onDestroy(){
    	super.onDestroy();
    	Log.d(TAG,"onDestroy() called");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.sm, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
