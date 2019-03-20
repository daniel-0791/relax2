package com.androidrelax.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;

import com.androidrelax.relax.R;

public class MusicAdapter extends BaseAdapter{

private Context context;
	
	//定义数据源，这里是一组要显示在Grallery中的图片
	private Integer[] images= {
		    R.drawable.mainmusic1,R.drawable.mainmusic2,R.drawable.mainmusic3
			
	};
	public MusicAdapter(Context context) {
		// TODO Auto-generated constructor stub
           this.context=context;		
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return images.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return images[position];
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ImageView imageView;
		if(convertView==null) {
			imageView=new ImageView(this.context);
			imageView.setImageResource(images[position]);
			imageView.setAdjustViewBounds(true);
			imageView.setLayoutParams(new Gallery.LayoutParams(250,250));
			
		}else {
			imageView=(ImageView) convertView;
		}
		return imageView;
	}
	
	

}
