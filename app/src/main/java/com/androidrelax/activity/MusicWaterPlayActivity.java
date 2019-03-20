package com.androidrelax.activity;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.androidrelax.relax.R;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MusicWaterPlayActivity extends AppCompatActivity {
	TextView TVmusicname;
    private boolean isPause=false; //???????????
    private List<String> mMusicList=new ArrayList<String>();//?????§Ò??????
    private static final String MUSIC_PATH=new String("/sdcard/water/");//????????¡¤??
    public MediaPlayer mMediaPlayer1=new MediaPlayer();//Mediaplayer???????????????
	private ImageView start=null;
	private ImageView left=null;
	private ImageView right=null;
	private ImageView f_musicplay;
	private ImageView like=null;
	private boolean isLike=false;
	private int currentListItme=0;//???????ID
	SeekBar seekBarW;
	private ImageView alarmW;
	private boolean isSeekBarChanging;//????????????????????????????  
	private int currentPosition;//??????????????  
	private Timer timer;
	private SimpleDateFormat time = new SimpleDateFormat("mm:ss");
	private TextView totaltime;
	private TextView nowtime;
    public Handler handler;
	public Runnable runnable;   
	private AlertDialog alertDialog; //?????
    private ImageView shareW;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_music_water_play);
		View view=findViewById(R.id.bg_water);
		view.getBackground().setAlpha(150);
		start=(ImageView) findViewById(R.id.playW);
		left=(ImageView) findViewById(R.id.leftW);
		right=(ImageView) findViewById(R.id.rightW);
		like=(ImageView)findViewById(R.id.like1W);
		f_musicplay=(ImageView)findViewById(R.id.fanhui_musicplaywater);
		totaltime=(TextView) findViewById(R.id.totaltime);
		nowtime=(TextView) findViewById(R.id.nowtime);
		alarmW=(ImageView) findViewById(R.id.alarmW);
		shareW=(ImageView) findViewById(R.id.shareW);
		//??????????
	     seekBarW = (SeekBar) findViewById(R.id.SeekBarW);
	     seekBarW.setOnSeekBarChangeListener(new MySeekBar()); 
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
				TVmusicname=(TextView) findViewById(R.id.musicnameW);
			    final String musicname=intent.getStringExtra("Water");
			    int position=intent.getIntExtra("PositionW",1);
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
//								   Log.i("?????", mMusicList.toString());
//								   Log.i("?????...", mMusicList.get(currentListItme));
								   start.setImageResource(R.drawable.pause);
								   isPause=true;
								 //  System.out.print(mMediaPlayer1.getDuration());
								   currentPosition = mMediaPlayer1.getCurrentPosition();//????????¦Ë??
								   
								   timer.purge();//???????????;  
							 } else{					
									mMediaPlayer1.start();//????????	
									start.setImageResource(R.drawable.pause);
									isPause = false;					
									//startButton.setEnabled(false);//????????????????				}
								}
//						   pauseButton.setEnabled(true);//?????/?????????????				
//						   startButton.setEnabled(false);//????????????????			
//						   stopButton.setEnabled(true);//?????????
						
						   
						}
					});
		       
		       //??????????
		         right.setOnClickListener(new OnClickListener() {
		        	@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
//		        		if(!isPause) {
//		        			 start.setImageResource(R.drawable.pause);
//		        		}
						currentListItme=currentListItme==mMusicList.size()-1?0:currentListItme+1;
						playMusic(MUSIC_PATH + mMusicList.get(currentListItme));
						TVmusicname.setText(mMusicList.get(currentListItme));
						start.setImageResource(R.drawable.pause);
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
//		 				if(!isPause) {
//		        			 start.setImageResource(R.drawable.pause);
//		        		}
		 				start.setImageResource(R.drawable.pause);
		 				playMusic(MUSIC_PATH + mMusicList.get(currentListItme));
		 				TVmusicname.setText(mMusicList.get(currentListItme));
		 			}
		 		}); 
		         //????????
		         like.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if(isLike) {
							like.setImageResource(R.drawable.xihuan1);
							Toast.makeText(getApplicationContext(), "??????", 200).show();
							
							isLike=false;
						}else {
							like.setImageResource(R.drawable.xihuan);
							Toast.makeText(getApplicationContext(), "?????", 200).show();
							isLike=true;
						}
						
					}
				});
		         
		         //???????????
		         alarmW.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						AlertDialog.Builder builder=new AlertDialog.Builder(MusicWaterPlayActivity.this);
						final String[] items = {"??????", "?????????", "????????????", "????????????"};
						builder.setTitle("?????");
						builder.setSingleChoiceItems(items, 0,new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
//								switch (which) {
//								case 0:
//									
//								case 1:
//									Toast.makeText(getApplicationContext(), "????????????", 200).show();
//									break;
//
//								case 2:
//									Toast.makeText(getApplicationContext(), "???????????????", 200).show();
//									break;
//								case 3:
//									Toast.makeText(getApplicationContext(), "???????????????", 200).show();
//								}
								
								 Toast.makeText(MusicWaterPlayActivity.this, items[which], Toast.LENGTH_SHORT).show();
								
								
							}
						});

						
						
						//?????????
//						builder.setPositiveButton("???", null);
						//?????????
						builder.setNegativeButton("???", null);
						builder.create().show();
						
						}
				});
			    //????
		         f_musicplay.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent=new Intent(getApplicationContext(),MusicWaterActivity.class);
						startActivity(intent);
					}
				});

		         handler = new Handler(){
			        	@Override
			        	public void handleMessage(Message msg) {
			        	super.handleMessage(msg);
			        	
			        	//????Seekbar
			        	seekBarW.setProgress(msg.what);
			        	//????TextView
			        	nowtime.setText(time.format(msg.what));
			        	}
			        	}; 
		  
	
	}

	//???????
	private void playMusic(String path) {
		try {
			
			/*????MediaPlayer */
			mMediaPlayer1.reset();
			/*?????????¡¤?? */
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
					TVmusicname.setText(mMusicList.get(currentListItme));
				}
			});
			 /*???????? ????????????????????*/  
			mMediaPlayer1.setOnPreparedListener(new OnPreparedListener() {  
                public void onPrepared(MediaPlayer mp) {  
                    mp.start();   
                    mp.seekTo(currentPosition);  
                   // playButton.setText("????");  
                    seekBarW.setMax(mMediaPlayer1.getDuration());  
                    String Ctotal=time.format(mMediaPlayer1.getDuration());
	                System.out.println(Ctotal);
	                totaltime.setText(Ctotal);
                }  
            });  
			//????????????????  
            timer = new Timer();  
            timer.schedule(new TimerTask() {  
                @Override  
                public void run() {  
                    if(!isSeekBarChanging){  
                        seekBarW.setProgress(mMediaPlayer1.getCurrentPosition()); 
                        handler.sendEmptyMessage(mMediaPlayer1.getCurrentPosition());//??????????¦Ë???
                    }  
                }  
            },0,50);  
		} catch (IOException e) {
		}
		
	}		

	@Override    
	protected void onDestroy() {    	
		if(mMediaPlayer1.isPlaying()){    		
			mMediaPlayer1.stop();//??????????    	
			}    	
		mMediaPlayer1.release();//??????    	
		timer.cancel();  
        timer = null;  
        mMediaPlayer1 = null;  
		super.onDestroy();   
		}

			/*???????????? */
			public class MusicFilter implements FilenameFilter {
				public boolean accept(File dir, String name) {
					// ????MP3???????????§Ò?
					return (name.endsWith(".mp3"));
			
			}
			}
		  
			/*??????????*/  
		    public class MySeekBar implements OnSeekBarChangeListener{  
		  
		        public void onProgressChanged(SeekBar seekBar, int progress,  
		                boolean fromUser) {  
		        }  
		  
		        /*?????,??????????????*/  
		        public void onStartTrackingTouch(SeekBar seekBar) {  
		            isSeekBarChanging = true;  
		        }  
		        /*???????????????????*/  
		        public void onStopTrackingTouch(SeekBar seekBar) {  
		            isSeekBarChanging = false;  
		            mMediaPlayer1.seekTo(seekBar.getProgress());  
		        }  
		    }   

}
