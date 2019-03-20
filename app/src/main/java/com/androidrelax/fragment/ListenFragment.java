package com.androidrelax.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.androidrelax.activity.MainActivity;
import com.androidrelax.activity.MusicMainActivity;
import com.androidrelax.relax.R;

@SuppressLint("NewApi")
public class ListenFragment extends Fragment{
	ImageView qingyinyue;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.fg_listen1, container,false);
		return view;
	}
    
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		qingyinyue=(ImageView) view.findViewById(R.id.mainmusic);
		qingyinyue.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getActivity(),MusicMainActivity.class); 
				startActivityForResult(intent, 1);
			}
		});
	}
	
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		//跳到listenfragment
		if(requestCode==1&&resultCode==3) {
			Log.i("qingyinyue", "yyyyyyyy");
			MainActivity mainActivity=(MainActivity) getActivity();
			
			mainActivity.getFragmentManager().beginTransaction().replace(R.id.vpager, new ListenFragment()).commit();
		}
//		//跳到mailfragment
//		if(resultCode==3) {
//			MainActivity mainActivity=(MainActivity) getActivity();
//			mainActivity.getFragmentManager().beginTransaction().replace(R.id.vpager, new MailFragment()).commit();
//		}
		//跳到treefragment 
		if(requestCode==3&&resultCode==10) {
			MainActivity mainActivity=(MainActivity) getActivity();
			mainActivity.getFragmentManager().beginTransaction().replace(R.id.vpager, new TreeFragment()).commit();
		}
		//跳到mefragmment
		if(requestCode==4&&resultCode==10) {
			MainActivity mainActivity=(MainActivity) getActivity();
			mainActivity.getFragmentManager().beginTransaction().replace(R.id.vpager, new MeFragment()).commit();
		}
	}
	
	
	
	

}
