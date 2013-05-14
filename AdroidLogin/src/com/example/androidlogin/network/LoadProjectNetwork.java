package com.example.androidlogin.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.cookie.BrowserCompatSpec;
import org.apache.http.impl.cookie.CookieSpecBase;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONArray;
import org.json.JSONObject;

import android.content.Context;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Toast;

import com.example.androidlogin.component.Project;

public class LoadProjectNetwork {	
	public ArrayList<Project> ExecuteLoadProject(Context Context){
		ArrayList<String> titles = new ArrayList<String>();
		ArrayList<Project> lists = new ArrayList<Project>();
		HttpPost post = new HttpPost("http://jinhyupkim.iptime.org/~sscrum/SimplicScrum/index.php/api/project/getList");
		DefaultHttpClient client = new DefaultHttpClient();
		InputStream is = null;
		@SuppressWarnings("unused")
		CookieManager cookieManager;
		String message="";
		
		CookieSyncManager.createInstance(Context);
		cookieManager = CookieManager.getInstance();
		BasicCookieStore cookieStore = new BasicCookieStore();
		HttpContext localContext = new BasicHttpContext();
		localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
		String[] keyValueSets = CookieManager.getInstance().getCookie("http://jinhyupkim.iptime.org/~sscrum/SimplicScrum/index.php/api/login/getLogin").split(";");
		for(String cookie : keyValueSets){
			String[] keyValue = cookie.split("=");
			String key = keyValue[0];
			String value="";
			if(keyValue.length > 0){
				value = keyValue[1];
			}
			client.getCookieStore().addCookie(new BasicClientCookie(key,value));
		}
		
		try{
			HttpParams params = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(params, 5000);
			HttpConnectionParams.setSoTimeout(params, 5000);
			
			CookieSpecBase cookieSpecBase = new BrowserCompatSpec();
			List<Cookie> cookies = client.getCookieStore().getCookies();
			
			List<?> cookieHeader = cookieSpecBase.formatCookies(cookies);
			
			post.setHeader((Header)cookieHeader.get(0));
			post.setParams(params);
			
			//BasicResponseHandler myHandler = new BasicResponseHandler();
			//String endResult = null;
			
			
			HttpResponse response = client.execute(post,localContext);
			//endResult = myHandler.handleResponse(response);
			
			is = response.getEntity().getContent();
			message = convertStreamToString(is,Context);
			JSONObject obj = new JSONObject(message);
			JSONArray array = obj.getJSONArray("key");
			//Toast.makeText(Context, String.valueOf(array.length()), Toast.LENGTH_SHORT).show();
			ArrayList<String> keylist = new ArrayList<String>();
			for(int i=0; i<array.length(); i++){
				keylist.add(String.valueOf(array.getInt(i)));
			}
			
			JSONObject item = obj.getJSONObject("item");			
			ArrayList<JSONObject> list = new ArrayList<JSONObject>();
			for(int i=0; i<keylist.size(); i++){
				list.add(item.getJSONObject(keylist.get(i)));
			}
			
			titles = new ArrayList<String>();
			for(int i=0; i<list.size(); i++){
				titles.add(list.get(i).getString("title"));
				Project p = new Project();
				p.setTitle(list.get(i).getString("title"));
				p.setDesc(list.get(i).getString("desc"));
				p.setRlevel(list.get(i).getInt("rlevel"));
				lists.add(p);
			}
			/*
			BufferedReader bufreader = new BufferedReader(new InputStreamReader(is,"utf-8"));
			String line = null;
			String page = "";
			while((line = bufreader.readLine())!= null){
				page += line;
			}			
			
			Toast.makeText(Context, page, Toast.LENGTH_SHORT).show();
			JSONObject obj = new JSONObject(page);
			result = obj.getJSONArray("item");	
			*/
		}catch(Exception e){e.printStackTrace();}		
		return lists;
		
	}
	public static String convertStreamToString(InputStream is,Context context) 
    {
    	BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    	StringBuilder sb = new StringBuilder();
    	
    	String line = null;
    	try {
    		while ((line = reader.readLine()) != null) {
    			sb.append(line + "\n");
    		}
    	} 
    	catch (IOException e) {
    		e.printStackTrace();
    	} 
    	finally {
    		try {
    			is.close();
    		} catch (IOException e) {
				e.printStackTrace();
    		}
    	}
    	//Toast.makeText(context, sb.toString(), Toast.LENGTH_SHORT).show();
    	
    	return sb.toString();
    }
}
