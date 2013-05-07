package com.example.adroidlogin;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ProjectListActivity extends Activity {
	ArrayList<String> project_list;
	ArrayAdapter<String> adapter;
	ListView lv;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.boardlist);
	    // TODO Auto-generated method stub
	    
	    project_list = new ArrayList<String>();
	    
	    project_list.add("Test1");
	    project_list.add("Test2");
	    project_list.add("Test3");
	    project_list.add("Test4");
	    project_list.add("Test5");
	    
	    lv = (ListView)findViewById(R.id.board_list);
	    
	    adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,project_list);
	    lv.setAdapter(adapter);
	    
	}

}
