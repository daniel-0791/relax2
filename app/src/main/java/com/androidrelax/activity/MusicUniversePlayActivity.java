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
	
    private boolean isPause=false; //���õ�ǰ��ͣ״̬
    private List<String> mMusicList=new ArrayList<String>();//�����б�����Դ
    private static final String MUSIC_PATH=new String("/sdcard/universe/");//��������·��
    public MediaPlayer mMediaPlayer1=new MediaPlayer();//Mediaplayer����������ʵ����
	private ImageView start=null;
	private ImageView left=null;
	private ImageView right=null;
	private ImageView f_musicplay;
	private ImageView like=null;
	private boolean isLike=false;
	private int currentListItme=0;//������ǰID
	SeekBar seekBar;
	private ImageView alarmU;
	private boolean isSeekBarChanging;//�����������ֹ�������붨ʱ����ͻ��  
	private int currentPosition;//��ǰ���ֲ��ŵĽ���  
	private Timer timer;
	private SimpleDateFormat time = new SimpleDateFormat("mm:ss");
	private TextView totaltime;
	private TextView nowtime;
    public Handler handler;
	public Runnable runnable;   
	private AlertDialog alertDialog; //��ѡ��
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
		
		//����������
	     seekBar = (SeekBar) findViewById(R.id.SeekBarU);
	     seekBar.setOnSeekBarChangeListener(new MySeekBar()); 
//		 left.setEnabled(true);
//		 right.setEnabled(true);
		 File home = new File(MUSIC_PATH);// ����fileʵ��ָ��SDcard
			if (home.listFiles(new MusicFilter()).length >0) {
				for (File file : home.listFiles(new MusicFilter())) {
					mMusicList.add(file.getName());
				}
			}
		
					
		 
		 
		 
		//��������
				Intent intent=getIntent();
				TVmusicname=(TextView) findViewById(R.id.musicnameU);
			    final String musicname=intent.getStringExtra("Universe");
			    int position=intent.getIntExtra("Position",1);
			    TVmusicname.setText(musicname);//���յ���������ʾ��TextView��
		        currentListItme=position;
		        System.out.println(currentListItme);
		        
		        //totaltime.setText(mMediaPlayer1.getDuration());
		        
			    //��ʼ���Ű�ť����¼�
		       start.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
				  	  if(mMediaPlayer1.isPlaying()) {
							 
							 start.setImageResource(R.drawable.play);
							 mMediaPlayer1.pause();
					 } else if(!isPause) {
							  playMusic(MUSIC_PATH + mMusicList.get(currentListItme));
//							   Log.i("����Դ", mMusicList.toString());
//							   Log.i("����Դ...", mMusicList.get(currentListItme));
							   start.setImageResource(R.drawable.pause);
							   isPause=true;
							 //  System.out.print(mMediaPlayer1.getDuration());
							   currentPosition = mMediaPlayer1.getCurrentPosition();//��¼���ŵ�λ��
							   
							   timer.purge();//�Ƴ���������;  
						 } else{					
								mMediaPlayer1.start();//��������	
								start.setImageResource(R.drawable.pause);
								isPause = false;					
								//startButton.setEnabled(false);//�����š���ť������				}
							}
//					   pauseButton.setEnabled(true);//����ͣ/��������ť����				
//					   startButton.setEnabled(false);//�����š���ť������			
//					   stopButton.setEnabled(true);//ֹͣ��ť����
					
					   
					}
				});
		       
		       //��һ�׵���¼�
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
		       
		       //��һ�׵���¼�
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
		         //�ղص���¼�
		         like.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						if(isLike) {
							like.setImageResource(R.drawable.xihuan1);
							Toast.makeText(getApplicationContext(), "ȡ���ղ�", 200).show();
							
							isLike=false;
						}else {
							like.setImageResource(R.drawable.xihuan);
							Toast.makeText(getApplicationContext(), "���ղ�", 200).show();
							isLike=true;
						}
						
					}
				});
		         
		         //��ʱ�رյ���¼�
		         alarmU.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						AlertDialog.Builder builder=new AlertDialog.Builder(MusicUniversePlayActivity.this);
						final String[] items = {"������", "���굱ǰ����", "������������", "������������"};
						builder.setTitle("��ѡ��");
						builder.setSingleChoiceItems(items, 0,new DialogInterface.OnClickListener() {
							
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub
//								switch (which) {
//								case 0:
//									
//								case 1:
//									Toast.makeText(getApplicationContext(), "���굱ǰ���ֺ�ر�", 200).show();
//									break;
//
//								case 2:
//									Toast.makeText(getApplicationContext(), "�����������ֺ�ر�", 200).show();
//									break;
//								case 3:
//									Toast.makeText(getApplicationContext(), "�����������ֺ�ر�", 200).show();
//								}
								
								 Toast.makeText(MusicUniversePlayActivity.this, items[which], Toast.LENGTH_SHORT).show();
								
								
							}
						});

						
						
						//���ȷ����ť
//						builder.setPositiveButton("ȷ��", null);
						//���ȡ����ť
						builder.setNegativeButton("�ر�", null);
						builder.create().show();
						
						}
				});
		         
		        	//�������֣�����ֻ�ǿ��Է���qq	
//		        	shareU.setOnClickListener(new OnClickListener() {
//						
//						@Override
//						public void onClick(View v) {
//							// TODO Auto-generated method stub
//							String url = Environment.getExternalStorageDirectory() + File.separator + "Heal The World1_1.1.mp3";			
//			        		Intent share = new Intent(Intent.ACTION_SEND);			
//			        		//���������			
//			        		ComponentName component = new ComponentName("com.tencent.mobileqq","com.tencent.mobileqq.activity.JumpActivity");			
//			        		share.setComponent(component);		//�������			
//			        		File file = new File(url);//����Ϊı�������ֻ��е�·��			
//			        		System.out.println("file " + file.exists());			
//			        		share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(file));			
//			        		share.setType("*/*");			
//			        		startActivity(Intent.createChooser(share, "����")); 
//						}
//					});
		         
		         
			    //����
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
			        	
			        	//����Seekbar
			        	seekBar.setProgress(msg.what);
			        	//����TextView
			        	nowtime.setText(time.format(msg.what));
			        	}
			        	}; 
		  
	
	}

	//���Ÿ���
			private void playMusic(String path) {
				try {
					
					/*����MediaPlayer */
					mMediaPlayer1.reset();
					/*��ʼ������·�� */
					mMediaPlayer1.setDataSource(path);
					/*��������ʼ�� */
					mMediaPlayer1.prepare();
					/*��ʼ����*/
					mMediaPlayer1.start();
					mMediaPlayer1.setOnCompletionListener(new OnCompletionListener() {
						public void onCompletion(MediaPlayer arg0) {
							// ��ǰ���������꣬�Զ�������һ�ס�
							currentListItme=currentListItme==mMusicList.size()-1?0:currentListItme+1;
							playMusic(MUSIC_PATH + mMusicList.get(currentListItme));
							TVmusicname.setText(mMusicList.get(currentListItme));
						}
					});
					 /*�������� �¼����ڻ�����Ϻ󣬿�ʼ����*/  
					mMediaPlayer1.setOnPreparedListener(new OnPreparedListener() {  
	                    public void onPrepared(MediaPlayer mp) {  
	                        mp.start();   
	                        mp.seekTo(currentPosition);  
	                       // playButton.setText("����");  
	                        seekBar.setMax(mMediaPlayer1.getDuration());  
	                        String Ctotal=time.format(mMediaPlayer1.getDuration());
	    	                System.out.println(Ctotal);
	    	                totaltime.setText(Ctotal);
	                    }  
	                });  
					//��������ʱ�ص�����  
	                timer = new Timer();  
	                timer.schedule(new TimerTask() {  
	                    @Override  
	                    public void run() {  
	                        if(!isSeekBarChanging){  
	                            seekBar.setProgress(mMediaPlayer1.getCurrentPosition()); 
	                            handler.sendEmptyMessage(mMediaPlayer1.getCurrentPosition());//��ȡ��ǰ�ĵ��λ�ã�
	                        }  
	                    }  
	                },0,50);  
				} catch (IOException e) {
				}
				
			}		

			@Override    
			protected void onDestroy() {    	
				if(mMediaPlayer1.isPlaying()){    		
					mMediaPlayer1.stop();//ֹͣ��Ƶ�Ĳ���    	
					}    	
				mMediaPlayer1.release();//�ͷ���Դ    	
				timer.cancel();  
		        timer = null;  
		        mMediaPlayer1 = null;  
				super.onDestroy();   
				}

			/*������������ */
			public class MusicFilter implements FilenameFilter {
				public boolean accept(File dir, String name) {
					// ����MP3�ļ����ļ����б�
					return (name.endsWith(".mp3"));
			
			}
			}
		  
			/*����������*/  
		    public class MySeekBar implements OnSeekBarChangeListener{  
		  
		        public void onProgressChanged(SeekBar seekBar, int progress,  
		                boolean fromUser) {  
		        }  
		  
		        /*����ʱ,Ӧ����ͣ��̨��ʱ��*/  
		        public void onStartTrackingTouch(SeekBar seekBar) {  
		            isSeekBarChanging = true;  
		        }  
		        /*������������������ֵ*/  
		        public void onStopTrackingTouch(SeekBar seekBar) {  
		            isSeekBarChanging = false;  
		            mMediaPlayer1.seekTo(seekBar.getProgress());  
		        }  
		    }   

//		  //��������ת��Ϊʱ���ʽ
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
