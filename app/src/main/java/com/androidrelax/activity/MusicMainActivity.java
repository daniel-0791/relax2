package com.androidrelax.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.androidrelax.relax.R;


public class MusicMainActivity extends Activity {
    ImageView f_mm;    //����������ķ��ذ�ť
	ImageView imgUniverse;
	ImageView imgForest;
	ImageView imgWater;
	ImageView imgNight;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_music_main);

		f_mm=(ImageView) findViewById(R.id.fanhui_login);
		imgUniverse=(ImageView) findViewById(R.id.button_universe);
		imgForest=(ImageView) findViewById(R.id.button_forest);
		imgWater=(ImageView) findViewById(R.id.button_water);
		imgNight=(ImageView) findViewById(R.id.button_goodnight);
		
		//�����¼�
		f_mm.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				setResult(3);
				finish();
			
			}
		});
		
		//����ҳ����ת
		imgUniverse.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent uniItn=new Intent(MusicMainActivity.this,MusicUniverseActivity.class);
				startActivity(uniItn);
			}
		});
		
		//ɭ��ҳ����ת
		imgForest.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent forItn=new Intent(MusicMainActivity.this,MusicForestActivity.class);
				startActivity(forItn);
			}
		});
		
		
		//��ˮҳ����ת
		imgWater.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent watItn=new Intent(MusicMainActivity.this,MusicWaterActivity.class);
				startActivity(watItn);
			}
		});
		
		//��ҳ����ת
		imgNight.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent nigItn=new Intent(MusicMainActivity.this,MusicGoodnightActivity.class);
				startActivity(nigItn);
			}
		});
		
		
	}

	
}
