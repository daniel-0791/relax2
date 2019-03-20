package com.androidrelax.activity;


import android.app.ListActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.androidrelax.relax.R;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MusicUniverseActivity extends ListActivity {
	public List<String> mMusicList=new ArrayList<String>();//�����б�����Դ
	private static final String MUSIC_PATH=new String("/sdcard/Universe");//��������·��
	public MediaPlayer mMediaPlayer=new MediaPlayer();//Mediaplayer����������ʵ����
	private int currentListItme=0;//������ǰID
	private ImageView f_musicuniverce;
	ListView lView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_music_universe);
	    ListView lView= (ListView) findViewById(android.R.id.list);
	    f_musicuniverce=(ImageView)findViewById(R.id.fanhui_universe);
	    
	    
		 //���ĳ�б��Ÿ���
	    lView.setOnItemClickListener(new OnItemClickListener() {
	    	@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
			    currentListItme=position;
                String musicname=mMusicList.get(currentListItme);
				if(position==0) {
					//playMusic("/sdcard/universe/Robert Haigh1_1.1.mp3");
//					String musicname3="Robert Haigh1_1.1";
					
					
					
				    Intent intent=new Intent(getApplicationContext(),MusicUniversePlayActivity.class);
				    intent.putExtra("Universe", musicname);
				    intent.putExtra("Position", position);
				    startActivity(intent);
				}
				  else if(position==1) {
					  //playMusic("/sdcard/universe/Nunu! - Wa1c Oo1_1.1.mp3");
						//String musicname2="Nunu! - Wa1c Oo1_1.1";
					    Intent intent=new Intent(getApplicationContext(),MusicUniversePlayActivity.class);
					    intent.putExtra("Universe", musicname);
					    intent.putExtra("Position", position);
					    startActivity(intent);
					    
				}else if(position==2){
					
				    //playMusic("/sdcard/universe/Maurizio Arbore1_1.1.mp3");
				    //String musicname1="Maurizio Arbore1_1.1";
				    Intent intent=new Intent(getApplicationContext(),MusicUniversePlayActivity.class);
				    intent.putExtra("Universe", musicname);
				    intent.putExtra("Position", position);
				    startActivity(intent);
				}else if(position==3) {
					
				    //playMusic("/sdcard/universe/American Green1_1.1.mp3");
					  //String musicname0="American Green1_1.1";
					  Intent intent=new Intent(getApplicationContext(),MusicUniversePlayActivity.class);
					  intent.putExtra("Universe", musicname);
					  intent.putExtra("Position", position);
					  startActivity(intent);
				}
			}
		});
		
	    
	    //�����¼�
	    f_musicuniverce.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getApplicationContext(),MusicMainActivity.class);
				startActivity(intent);
			}
		});
		
		musicList();  
		
		
	}

	
	 /* ���ɸ����б� */
		public void musicList() {
			// ����fileʵ��ָ��SDcard
			File home = new File(MUSIC_PATH);
			if (home.listFiles(new MusicFilter()).length >0) {
				for (File file : home.listFiles(new MusicFilter())) {
					mMusicList.add(file.getName());
				}
				ArrayAdapter<String> musicList = new ArrayAdapter<String>(
					  MusicUniverseActivity.this, android.R.layout.simple_list_item_1, mMusicList);
				setListAdapter(musicList);
			}
		}
		
		
		
		/*������������ */
		public class MusicFilter implements FilenameFilter {
			public boolean accept(File dir, String name) {
				// ����MP3�ļ����ļ����б�
				return (name.endsWith(".mp3"));
		
		}
		}
	
		
		//���Ÿ���
		private void playMusic(String path) {
			try {
				
				/*����MediaPlayer */
				mMediaPlayer.reset();
				/*��ʼ������·�� */
				mMediaPlayer.setDataSource(path);
				/*��������ʼ�� */
				mMediaPlayer.prepare();
				/*��ʼ����*/
				mMediaPlayer.start();
				mMediaPlayer.setOnCompletionListener(new OnCompletionListener() {
					public void onCompletion(MediaPlayer arg0) {
						// ��ǰ���������꣬�Զ�������һ�ס�
						currentListItme=currentListItme==mMusicList.size()-1?0:currentListItme+1;
						playMusic(MUSIC_PATH + mMusicList.get(currentListItme));
					}
				});
			} catch (IOException e) {
			}
		}
		
		
	
	
}
