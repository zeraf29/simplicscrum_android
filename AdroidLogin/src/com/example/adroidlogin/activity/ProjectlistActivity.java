package com.example.adroidlogin.activity;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.adroidlogin.R;
import com.example.androidlogin.network.LoadProjectNetwork;

public class ProjectlistActivity extends Activity /*implements DndListView.DragListener, DndListView.DropListener*/{
	private ArrayList<String> data = new ArrayList<String>();
	//private ArrayList<Project> projects = new ArrayList<Project>();
	private ListView lv;
	//private boolean isDnd = false;
	private ArrayAdapter<String> mAdapter;
	
	public ProjectlistActivity(){
		super();
		data.add("testing");		
	}
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.boardlist); 
        
        LoadProjectNetwork net = new LoadProjectNetwork();
        ArrayList<String> titles = net.ExecuteLoadProject(getApplicationContext());   
		        
        
        lv = (ListView) findViewById(R.id.project_list);
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, titles);
        lv.setAdapter(mAdapter);
        //lstInfoType.setDragListener(this);
		//lstInfoType.setDropListener(this);
    }
	/*
	public void drag(int from, int to) {
		if(!isDnd){
			isDnd = true;
			Log.i("Drag and Drop : drag", "from : " + from + ", to : " + to);
		}
	}

	public void drop(int from, int to) {
		if(isDnd){
			Log.i("Drag and Drop : drop", "from : " + from + ", to : " + to);
			if(from == to)
				return;
			
			String item = data.remove(from);
			data.add(to, item);
			
			isDnd = false;
			mAdapter.notifyDataSetChanged();
		}
	}
	*/
}
