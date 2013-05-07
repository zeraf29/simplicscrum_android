package com.example.adroidlogin;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BoadList extends Activity {
	ListView lv;
	ArrayAdapter<String> adapter;
	ArrayList<String> list;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.boardlist);
	    
	    list.add("hahaha");
	    list.add("fuck you");
	    
	    // TODO Auto-generated method stub
	    lv = (ListView)findViewById(R.id.board_list);
	    adapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_list_item_1,list);
	    lv.setAdapter(adapter);
	}

}
