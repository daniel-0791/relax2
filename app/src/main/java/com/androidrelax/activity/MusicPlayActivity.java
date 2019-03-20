package com.androidrelax.activity;


import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.androidrelax.relax.R;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MusicPlayActivity extends Activity {
    TextView TVmusicname;
    private boolean isPause=false; //???????????
    private List<String> mMusicList=new ArrayList<String>();//????????????
    private static final String MUSIC_PATH=new String("/sdcard/universe/");//????????�??
    public MediaPlayer mMediaPlayer1=new MediaPlayer();//Mediaplayer???????????????
	private ImageView start=null;
	private ImageView left=null;
	private ImageView right=null;
	private ImageView f_musicplay;
	private ImageView like=null;
	private boolean isLike=false;
	private int currentListItme=0;//???????ID
	SeekBar seekBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_music_play);
		
		start=(ImageView) findViewById(R.id.play);
		left=(ImageView) findViewById(R.id.left);
		right=(ImageView) findViewById(R.id.right);
		like=(ImageView)findViewById(R.id.like1);
		f_musicplay=(ImageView)findViewById(R.id.fanhui_musicplay);
		//??????????
	     seekBar = (SeekBar) findViewById(R.id.SeekBar);
	     
		 left.setEnabled(true);
		 right.setEnabled(true);
		 File home = new File(MUSIC_PATH);// ????file??????SDcard
			if (home.listFiles(new MusicFilter()).length >0) {
				for (File file : home.listFiles(new MusicFilter())) {
					mMusicList.add(file.getName());
				}
			}
		 
		
					
		 
		 
		 
		//????????
				Intent intent=getIntent();
				TVmusicname=(TextView) findViewById(R.id.musicname);
			    final String musicname=intent.getStringExtra("Universe");
			    int position=intent.getIntExtra("Position",1);
			    TVmusicname.setText(musicname);//????????????????TextView??
		        currentListItme=position;
		        System.out.println(currentListItme);
		      
			    //???????????????
		       start.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
				  	  if(mMediaPlayer1.isPlaying()) {
							 
							 start.setImageResource(R.drawable.play);
							 mMediaPlayer1.pause();
					 } else if(!isPause) {
							  playMusic(MUSIC_PATH + mMusicList.get(currentListItme));
//							   Log.i("?????", mMusicList.toString());
//							   Log.i("?????...", mMusicList.get(currentListItme));
							   start.setImageResource(R.drawable.pause);
							   isPause=true;
						 } else{					
								mMediaPlayer1.start();//????????	
								start.setImageResource(R.drawable.pause);
								isPause = false;					
								//startButton.setEnabled(false);//????????????????				}
							}
//					   pauseButton.setEnabled(true);//?????/?????????????				
//					   startButton.setEnabled(false);//????????????????			
//					   stopButton.setEnabled(true);//?????????
					
					   
					}
				});
		       
		       //??????????
		         right.setOnClickListener(new OnClickListener() {
		        	@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						currentListItme=currentListItme==mMusicList.size()-1?0:currentListItme+1;
						playMusic(MUSIC_PATH + mMusicList.get(currentListItme));
					}
				});
		       
		       //??????????
		         left.setOnClickListener(new OnClickListener() {
		 	    	@Override
		 			public void onClick(View v) {
		 				// TODO Auto-generated method stub
		 				if(currentListItme==0) {
		 					currentListItme=currentListItme+mMusicList.size()-1;
		 				}else {
		 					currentListItme=currentListItme-1;
		 				}
		 				playMusic(MUSIC_PATH + mMusicList.get(currentListItme));
		 			}
		 		}); 
		         //????????
		         like.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if(isLike) {
							like.setImageResource(R.drawable.xihuan1);
							isLike=false;
						}else {
							like.setImageResource(R.drawable.xihuan);
							isLike=true;
						}
						
					}
				});
			    //????
		         f_musicplay.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent=new Intent(getApplicationContext(),MusicUniverseActivity.class);
						startActivity(intent);
					}
				});

		  
	
	}

	//???????
			private void playMusic(String path) {
				try {
					
					/*????MediaPlayer */
					mMediaPlayer1.reset();
					/*?????????�?? */
					mMediaPlayer1.setDataSource(path);
					/*??????????? */
					mMediaPlayer1.prepare();
					/*???????*/
					mMediaPlayer1.start();
					mMediaPlayer1.setOnCompletionListener(new OnCompletionListener() {
						public void onCompletion(MediaPlayer arg0) {
							// ??????????????????????????
							currentListItme=currentListItme==mMusicList.size()-1?0:currentListItme+1;
							playMusic(MUSIC_PATH + mMusicList.get(currentListItme));
						}
					});
				} catch (IOException e) {
				}
				
			}		

			@Override    
			protected void onDestroy() {    	
				if(mMediaPlayer1.isPlaying()){    		
					mMediaPlayer1.stop();//??????????    	
					}    	
				mMediaPlayer1.release();//??????    	
				super.onDestroy();   
				}

			/*???????????? */
			public class MusicFilter implements FilenameFilter {
				public boolean accept(File dir, String name) {
					// ????MP3?????????????
					return (name.endsWith(".mp3"));
			
			}
			}
		  
	

	
}
