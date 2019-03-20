package com.androidrelax.fragment;

import com.androidrelax.relax.PersonalDataActivity;
import com.androidrelax.relax.R;
import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

@SuppressLint("NewApi")
public class MeFragment extends Fragment{
	 ImageView xiangyouPerson;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.fg_me, container,false);
		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
        xiangyouPerson= (ImageView) view.findViewById(R.id.xiangyouPerson);
		
		xiangyouPerson.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getActivity(),PersonalDataActivity.class); 
				startActivityForResult(intent, 4);
		    }
		});
	}
	}
