package com.androidrelax.relax;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class PersonalDataActivity extends Activity {
    ImageView fanhui_personaldata;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_data);
		
		fanhui_personaldata=(ImageView) findViewById(R.id.fanhui_personaldata);
		fanhui_personaldata.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setResult(10);
				finish();
			}
		});
	}


}
