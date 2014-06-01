package com.Drexel.TAllaghi.snoozemuze;


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

public class SMActivity extends ActionBarActivity {

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
	
	private Picture_Sound[] storage= new Picture_Sound[]{
		new Picture_Sound(R.drawable.forest_resized_2,R.raw.forest_and_birds_mpthree),
		new Picture_Sound(R.drawable.rain_resized,R.raw.rain_and_thunder_mpthree),
		new Picture_Sound(R.drawable.food_court_resized,R.raw.food_court_mpthree),
		new Picture_Sound(R.drawable.beach_resized,R.raw.beach_mpthree)
	};
	
	private void updatePicture(){
		int pic=storage[mCurrentIndex].getPRID();
        try{
        	mPicture.setImageResource(pic);
        }
        catch(NullPointerException e){
        	Log.d(TAG,"pic is"+pic);
        }
	}
	
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
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sm);
        
        mCurrentIndex=0;
        //updatePicture();
        
        mNextButton=(ImageButton)findViewById(R.id.next_button);
        mPreviousButton=(ImageButton)findViewById(R.id.previous_button);
        mPlayButton=(ImageButton)findViewById(R.id.play_button);
        mPicture=(ImageButton)findViewById(R.id.sound_picture);
        mCredits=(Button)findViewById(R.id.toCredits);
        mAudioManager = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        
        int maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        mVolSlider = (SeekBar)findViewById(R.id.volumeSlider);
        
        mVolSlider.setMax(maxVolume);
        mVolSlider.setProgress(curVolume);
        
        mVolSlider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar arg0) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar arg0) {
            }

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
