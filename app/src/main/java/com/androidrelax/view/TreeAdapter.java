package com.androidrelax.view;

import java.util.List;

import com.androidrelax.relax.R;


import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TreeAdapter extends BaseAdapter{
	
	
	private List<tree> trlist;
	private LayoutInflater inflater;
	
	 public TreeAdapter(Context context,List<tree> trlist) {
		// TODO Auto-generated constructor stub
		 this.inflater=LayoutInflater.from(context);
		 this.trlist=trlist;
		 
		 
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return trlist==null?0:trlist.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		  return trlist.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		View view=inflater.inflate(R.layout.treeholelist,null);
		tree trees= (tree) getItem(position);
		TextView title_tv=(TextView) view.findViewById(R.id.list_title);
		TextView content_tv=(TextView)view.findViewById(R.id.list_content);
//		TextView time_tv=(TextView) layout.findViewById(R.id.list_time);
//		title_tv.setText(titles);
		content_tv.setText(trees.getSendcontent());
		title_tv.setText(trees.getSendtitle());
		return view;
	}

}
