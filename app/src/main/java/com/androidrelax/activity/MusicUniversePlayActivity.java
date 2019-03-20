package com.androidrelax.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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

public class MusicUniversePlayActivity extends Activity {
	TextView TVmusicname;
	
    private boolean isPause=false; //设置当前暂停状态
    private List<String> mMusicList=new ArrayList<String>();//歌曲列表数据源
    private static final String MUSIC_PATH=new String("/sdcard/universe/");//歌曲所在路径
    public MediaPlayer mMediaPlayer1=new MediaPlayer();//Mediaplayer对象声明并实例化
	private ImageView start=null;
	private ImageView left=null;
	private ImageView right=null;
	private ImageView f_musicplay;
	private ImageView like=null;
	private boolean isLike=false;
	private int currentListItme=0;//歌曲当前ID
	SeekBar seekBar;
	private ImageView alarmU;
	private boolean isSeekBarChanging;//互斥变量，防止进度条与定时器冲突。  
	private int currentPosition;//当前音乐播放的进度  
	private Timer timer;
	private SimpleDateFormat time = new SimpleDateFormat("mm:ss");
	private TextView totaltime;
	private TextView nowtime;
    public Handler handler;
	public Runnable runnable;   
	private AlertDialog alertDialog; //单选框
    private ImageView shareU;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_music_universe_play);
		
		start=(ImageView) findViewById(R.id.playU);
		left=(ImageView) findViewById(R.id.leftU);
		right=(ImageView) findViewById(R.id.rightU);
		like=(ImageView)findViewById(R.id.like1U);
		f_musicplay=(ImageView)findViewById(R.id.fanhui_musicplayuniverse);
		totaltime=(TextView) findViewById(R.id.totaltime);
		nowtime=(TextView) findViewById(R.id.nowtime);
		alarmU=(ImageView) findViewById(R.id.alarmU);
		shareU=(ImageView) findViewById(R.id.shareU);
		View view=findViewById(R.id.bg_universe);
		view.getBackground().setAlpha(150);
		
		//监听滚动条
	     seekBar = (SeekBar) findViewById(R.id.SeekBarU);
	     seekBar.setOnSeekBarChangeListener(new MySeekBar()); 
//		 left.setEnabled(true);
//		 right.setEnabled(true);
		 File home = new File(MUSIC_PATH);// 创建file实例指向SDcard
			if (home.listFiles(new MusicFilter()).length >0) {
				for (File file : home.listFiles(new MusicFilter())) {
					mMusicList.add(file.getName());
				}
			}
		
					
		 
		 
		 
		//接收数据
				Intent intent=getIntent();
				TVmusicname=(TextView) findViewById(R.id.musicnameU);
			    final String musicname=intent.getStringExtra("Universe");
			    int position=intent.getIntExtra("Position",1);
			    TVmusicname.setText(musicname);//将收到的数据显示在TextView中
		        currentListItme=position;
		        System.out.println(currentListItme);
		        
		        //totaltime.setText(mMediaPlayer1.getDuration());
		        
			    //开始播放按钮点击事件
		       start.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
				  	  if(mMediaPlayer1.isPlaying()) {
							 
							 start.setImageResource(R.drawable.play);
							 mMediaPlayer1.pause();
					 } else if(!isPause) {
							  playMusic(MUSIC_PATH + mMusicList.get(currentListItme));
//							   Log.i("数据源", mMusicList.toString());
//							   Log.i("数据源...", mMusicList.get(currentListItme));
							   start.setImageResource(R.drawable.pause);
							   isPause=true;
							 //  System.out.print(mMediaPlayer1.getDuration());
							   currentPosition = mMediaPlayer1.getCurrentPosition();//记录播放的位置
							   
							   timer.purge();//移除所有任务;  
						 } else{					
								mMediaPlayer1.start();//继续播放	
								start.setImageResource(R.drawable.pause);
								isPause = false;					
								//startButton.setEnabled(false);//“播放”按钮不可用				}
							}
//					   pauseButton.setEnabled(true);//“暂停/继续”按钮可用				
//					   startButton.setEnabled(false);//“播放”按钮不可用			
//					   stopButton.setEnabled(true);//停止按钮可用
					
					   
					}
				});
		       
		       //下一首点击事件
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
		       
		       //上一首点击事件
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
		         //收藏点击事件
		         like.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if(isLike) {
							like.setImageResource(R.drawable.xihuan1);
							Toast.makeText(getApplicationContext(), "取消收藏", 200).show();
							
							isLike=false;
						}else {
							like.setImageResource(R.drawable.xihuan);
							Toast.makeText(getApplicationContext(), "已收藏", 200).show();
							isLike=true;
						}
						
					}
				});
		         
		         //定时关闭点击事件
		         alarmU.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						AlertDialog.Builder builder=new AlertDialog.Builder(MusicUniversePlayActivity.this);
						final String[] items = {"不开启", "播完当前音乐", "播完两首音乐", "播完三首音乐"};
						builder.setTitle("请选择");
						builder.setSingleChoiceItems(items, 0,new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
//								switch (which) {
//								case 0:
//									
//								case 1:
//									Toast.makeText(getApplicationContext(), "播完当前音乐后关闭", 200).show();
//									break;
//
//								case 2:
//									Toast.makeText(getApplicationContext(), "播完两首音乐后关闭", 200).show();
//									break;
//								case 3:
//									Toast.makeText(getApplicationContext(), "播完三首音乐后关闭", 200).show();
//								}
								
								 Toast.makeText(MusicUniversePlayActivity.this, items[which], Toast.LENGTH_SHORT).show();
								
								
							}
						});

						
						
						//添加确定按钮
//						builder.setPositiveButton("确定", null);
						//添加取消按钮
						builder.setNegativeButton("关闭", null);
						builder.create().show();
						
						}
				});
		         
		        	//分享音乐，这里只是可以分享到qq	
//		        	shareU.setOnClickListener(new OnClickListener() {
//						
//						@Override
//						public void onClick(View v) {
//							// TODO Auto-generated method stub
//							String url = Environment.getExternalStorageDirectory() + File.separator + "Heal The World1_1.1.mp3";			
//			        		Intent share = new Intent(Intent.ACTION_SEND);			
//			        		//创建组件，			
//			        		ComponentName component = new ComponentName("com.tencent.mobileqq","com.tencent.mobileqq.activity.JumpActivity");			
//			        		share.setComponent(component);		//设置组件			
//			        		File file = new File(url);//参数为谋音乐在手机中的路径			
//			        		System.out.println("file " + file.exists());			
//			        		share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));			
//			        		share.setType("*/*");			
//			        		startActivity(Intent.createChooser(share, "分享到")); 
//						}
//					});
		         
		         
			    //返回
		         f_musicplay.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						Intent intent =new Intent(MusicUniversePlayActivity.this,MusicUniverseActivity.class);
						startActivity(intent);
						
					}
				});

		         
		     
			      handler = new Handler(){
			        	@Override
			        	public void handleMessage(Message msg) {
			        	super.handleMessage(msg);
			        	
			        	//更新Seekbar
			        	seekBar.setProgress(msg.what);
			        	//更新TextView
			        	nowtime.setText(time.format(msg.what));
			        	}
			        	}; 
		  
	
	}

	//播放歌曲
			private void playMusic(String path) {
				try {
					
					/*重置MediaPlayer */
					mMediaPlayer1.reset();
					/*初始化播放路径 */
					mMediaPlayer1.setDataSource(path);
					/*播放器初始化 */
					mMediaPlayer1.prepare();
					/*开始播放*/
					mMediaPlayer1.start();
					mMediaPlayer1.setOnCompletionListener(new OnCompletionListener() {
						public void onCompletion(MediaPlayer arg0) {
							// 当前歌曲播放完，自动播放下一首。
							currentListItme=currentListItme==mMusicList.size()-1?0:currentListItme+1;
							playMusic(MUSIC_PATH + mMusicList.get(currentListItme));
							TVmusicname.setText(mMusicList.get(currentListItme));
						}
					});
					 /*监听缓存 事件，在缓冲完毕后，开始播放*/  
					mMediaPlayer1.setOnPreparedListener(new OnPreparedListener() {  
	                    public void onPrepared(MediaPlayer mp) {  
	                        mp.start();   
	                        mp.seekTo(currentPosition);  
	                       // playButton.setText("播放");  
	                        seekBar.setMax(mMediaPlayer1.getDuration());  
	                        String Ctotal=time.format(mMediaPlayer1.getDuration());
	    	                System.out.println(Ctotal);
	    	                totaltime.setText(Ctotal);
	                    }  
	                });  
					//监听播放时回调函数  
	                timer = new Timer();  
	                timer.schedule(new TimerTask() {  
	                    @Override  
	                    public void run() {  
	                        if(!isSeekBarChanging){  
	                            seekBar.setProgress(mMediaPlayer1.getCurrentPosition()); 
	                            handler.sendEmptyMessage(mMediaPlayer1.getCurrentPosition());//获取当前的点击位置；
	                        }  
	                    }  
	                },0,50);  
				} catch (IOException e) {
				}
				
			}		

			@Override    
			protected void onDestroy() {    	
				if(mMediaPlayer1.isPlaying()){    		
					mMediaPlayer1.stop();//停止音频的播放    	
					}    	
				mMediaPlayer1.release();//释放资源    	
				timer.cancel();  
		        timer = null;  
		        mMediaPlayer1 = null;  
				super.onDestroy();   
				}

			/*歌曲过滤器， */
			public class MusicFilter implements FilenameFilter {
				public boolean accept(File dir, String name) {
					// 返回MP3文件的文件名列表；
					return (name.endsWith(".mp3"));
			
			}
			}
		  
			/*进度条处理*/  
		    public class MySeekBar implements OnSeekBarChangeListener{  
		  
		        public void onProgressChanged(SeekBar seekBar, int progress,  
		                boolean fromUser) {  
		        }  
		  
		        /*滚动时,应当暂停后台定时器*/  
		        public void onStartTrackingTouch(SeekBar seekBar) {  
		            isSeekBarChanging = true;  
		        }  
		        /*滑动结束后，重新设置值*/  
		        public void onStopTrackingTouch(SeekBar seekBar) {  
		            isSeekBarChanging = false;  
		            mMediaPlayer1.seekTo(seekBar.getProgress());  
		        }  
		    }   

//		  //将毫秒数转化为时间格式
//		    public String timeChange(double time){
//		    	 double t=time/1000;
//		    	 System.out.println(t);
//		    	 int minute=(int) (t/60);
//		    	 System.out.println(minute);
//		    	 int second=(int) (t%60);
//		    	 System.out.println(second);
//		    	 return (""+minute+":"+""+second);
//
//		    }

}
