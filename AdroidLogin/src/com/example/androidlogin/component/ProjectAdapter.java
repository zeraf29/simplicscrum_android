package com.example.androidlogin.component;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.adroidlogin.R;

public class ProjectAdapter extends BaseAdapter {
	private Context mContext;
	private ArrayList<Project> project_list;
	private LayoutInflater li;
	private int mLayout;
	
	public ProjectAdapter(Context context,int layout,ArrayList<Project> ProjectList){
		mContext = context;
		mLayout = layout;
		project_list = ProjectList;
		li = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return project_list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return project_list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub		
		if(convertView == null){
			convertView = li.inflate(mLayout, parent, false);
		}
		TextView project_title = (TextView)convertView.findViewById(R.id.p_title);
		project_title.setText(project_list.get(position).getTitle().toString());
		ImageView label = (ImageView)convertView.findViewById(R.id.member_icon);
		
		if(project_list.get(position).getRlevel() == 1){
			//label.setBackgroundColor(Color.WHITE);
		}else{
			//label.setBackgroundColor(Color.parseColor("#CE4F4F"));
		}
		
		label.setOnClickListener(new OnClickListener(){
			public void onClick(View v){
				
			}
		});
		return convertView;
	}

}
