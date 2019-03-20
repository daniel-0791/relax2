package com.androidrelax.fragment;

import com.androidrelax.relax.PersonalDataActivity;
import com.androidrelax.relax.R;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MailFragment extends Fragment{
//	ImageView close;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.fg_mail, container,false);
		return view;
	}
	
//	@Override
//	public void onViewCreated(View view, Bundle savedInstanceState) {
//		// TODO Auto-generated method stub
//		super.onViewCreated(view, savedInstanceState);
//		close= (ImageView) view.findViewById(R.id.close);
//		
//		close.setOnClickListener(new OnClickListener() {
//			
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent intent=new Intent(getActivity(),PersonalDataActivity.class); 
//				startActivityForResult(intent, 2);
//		    }
//		});
//	}
}
