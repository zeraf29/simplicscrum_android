package com.example.adroidlogin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;

import android.app.Activity;
import android.content.Intent;
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
	    
	    try{
		    HttpClient http = new DefaultHttpClient();
		    ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
		    Intent intent = getIntent();
		    String id = intent.getExtras().toString();
			postParameters.add(new BasicNameValuePair("userid",id));
			
			HttpParams params = http.getParams();
			HttpConnectionParams.setConnectionTimeout(params, 5000);
			HttpConnectionParams.setSoTimeout(params, 5000);
			
			HttpPost httpPost = new HttpPost("http://jinhyupkim.iptime.org/~sscrum/SimplicScrum/index.php/api/login/getLogin");
			UrlEncodedFormEntity entityRequest = new UrlEncodedFormEntity(postParameters,"utf-8");
			httpPost.setEntity(entityRequest);
			HttpResponse responsePost = http.execute(httpPost);
			
			JSONParser jParser = new JSONParser();
			JSONArray projects = null;
			
			BufferedReader bufreader = new BufferedReader(new InputStreamReader(responsePost.getEntity().getContent(),"utf-8"));
			String line = null;
			String page = "";
			while((line = bufreader.readLine()) != null){
				page += line;
			}
			JSONObject json = new JSONObject(page);
			projects = json.getJSONArray("");
			
	    }catch(Exception e){e.printStackTrace();}
		
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
