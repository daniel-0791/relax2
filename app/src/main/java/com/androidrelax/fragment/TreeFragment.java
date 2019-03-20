package com.androidrelax.fragment;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.androidrelax.activity.TreeHoleSendActivity;
import com.androidrelax.relax.R;



public class TreeFragment extends Fragment {
	
	private TextView lvtreehole;
	private TextView lvtreehole1;
	private ImageButton btntreeshow;
//	private TreeAdapter adapter;
//	private List<tree> data = null;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view=inflater.inflate(R.layout.fg_tree1, container,false);
		return view;
	}

	@SuppressLint("NewApi")
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);
		lvtreehole=(TextView)view.findViewById(R.id.treeshowlist);
		lvtreehole1=(TextView)view.findViewById(R.id.treeshow1list);
		btntreeshow=(ImageButton)view.findViewById(R.id.treeshowbtn);
		
		/*Bundle bundle =this.getArguments();//得到从Activity传来的数据
        String mess = null;
        String mess1=null;
        if(bundle!=null){
            ShowMessage.title = bundle.getString("111");
           
        }*/
        
       
       

		btntreeshow.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(getActivity(),TreeHoleSendActivity.class);
				startActivityForResult(intent, 3);
			}
		});
		
		
	}
	
}
