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

public class MusicForestPlayActivity extends AppCompatActivity {
	TextView TVmusicname;
    private boolean isPause=false; //���õ�ǰ��ͣ״̬
    private List<String> mMusicList=new ArrayList<String>();//�����б�����Դ
    private static final String MUSIC_PATH=new String("/sdcard/forest/");//��������·��
    public MediaPlayer mMediaPlayer1=new MediaPlayer();//Mediaplayer����������ʵ����
	private ImageView start=null;
	private ImageView left=null;
	private ImageView right=null;
	private ImageView f_musicplay;
	private ImageView like=null;
	private boolean isLike=false;
	private int currentListItme=0;//������ǰID
	SeekBar seekBarF;
	private boolean isSeekBarChanging;//�����������ֹ�������붨ʱ����ͻ�� 
	private SimpleDateFormat time = new SimpleDateFormat("mm:ss");
	private int currentPosition;//��ǰ���ֲ��ŵĽ���  
	private Timer timer;
	private TextView totaltime;
	private TextView nowtime;
    public Handler handler;
	public Runnable runnable;   
	private AlertDialog alertDialog; //��ѡ��
    private ImageView shareF;
    private ImageView alarmF;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_music_forest_play);
		View view=findViewById(R.id.bg_forest);
		view.getBackground().setAlpha(200);
		start=(ImageView) findViewById(R.id.playF);
		left=(ImageView) findViewById(R.id.leftF);
		right=(ImageView) findViewById(R.id.rightF);
		like=(ImageView)findViewById(R.id.like1F);
		f_musicplay=(ImageView)findViewById(R.id.fanhui_musicplayforest);
		totaltime=(TextView) findViewById(R.id.totaltime);
		nowtime=(TextView) findViewById(R.id.nowtime);
		alarmF=(ImageView) findViewById(R.id.alarmF);
		shareF=(ImageView) findViewById(R.id.shareF);
	    seekBarF = (SeekBar) findViewById(R.id.SeekBarF);//����������
	    seekBarF.setOnSeekBarChangeListener(new MySeekBar());  
	     
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
				TVmusicname=(TextView) findViewById(R.id.musicnameF);
			    final String musicname=intent.getStringExtra("Forest");
			    int position=intent.getIntExtra("PositionF",1);
			    TVmusicname.setText(musicname);//���յ���������ʾ��TextView��
		        currentListItme=position;
		        //Log.i("���ո�����", musicname);
		        
		        System.out.println(currentListItme);
		      
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
//								   Log.i("����Դ", mMusicList.toString());
//								   Log.i("����Դ...", mMusicList.get(currentListItme));
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
//						   pauseButton.setEnabled(true);//����ͣ/��������ť����				
//						   startButton.setEnabled(false);//�����š���ť������			
//						   stopButton.setEnabled(true);//ֹͣ��ť����
						
						   
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
		        		start.setImageResource(R.drawable.pause);
						currentListItme=currentListItme==mMusicList.size()-1?0:currentListItme+1;
						playMusic(MUSIC_PATH + mMusicList.get(currentListItme));
						TVmusicname.setText(mMusicList.get(currentListItme));
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
		         alarmF.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						AlertDialog.Builder builder=new AlertDialog.Builder(MusicForestPlayActivity.this);
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
								
								 Toast.makeText(MusicForestPlayActivity.this, items[which], Toast.LENGTH_SHORT).show();
								
								
							}
						});

						
						
						//���ȷ����ť
//						builder.setPositiveButton("ȷ��", null);
						//���ȡ����ť
						builder.setNegativeButton("�ر�", null);
						builder.create().show();
						
						}
				});
		         
		         
			    //����
		         f_musicplay.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent intent=new Intent(getApplicationContext(),MusicForestActivity.class);
						startActivity(intent);
					}
				});
		         
		         
		         
			      handler = new Handler(){
			        	@Override
			        	public void handleMessage(Message msg) {
			        	super.handleMessage(msg);
			        	
			        	//����Seekbar
			        	seekBarF.setProgress(msg.what);
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
                    seekBarF.setMax(mMediaPlayer1.getDuration());  
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
                        seekBarF.setProgress(mMediaPlayer1.getCurrentPosition()); 
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
			
			
	}


