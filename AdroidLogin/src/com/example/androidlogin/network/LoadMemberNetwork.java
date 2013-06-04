package com.example.androidlogin.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.impl.cookie.BrowserCompatSpec;
import org.apache.http.impl.cookie.CookieSpecBase;
import org.apache.http.message.BasicNameValuePair;
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

public class LoadMemberNetwork {
	public ArrayList<String> ExecuteLoadMembers(Context Context,String pid){
		ArrayList<String> members = new ArrayList<String>();
		
		HttpPost post = new HttpPost("http://jinhyupkim.iptime.org/~sscrum/SimplicScrum/api/project/getProjectUsers");
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
			ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
			postParameters.add(new BasicNameValuePair("pid",pid));
			
			HttpParams params = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(params, 5000);
			HttpConnectionParams.setSoTimeout(params, 5000);
			
			UrlEncodedFormEntity entityRequest = new UrlEncodedFormEntity(postParameters,"utf-8");
			post.setEntity(entityRequest);
			
			CookieSpecBase cookieSpecBase = new BrowserCompatSpec();
			List<Cookie> cookies = client.getCookieStore().getCookies();
			
			List<?> cookieHeader = cookieSpecBase.formatCookies(cookies);
			
			post.setHeader((Header)cookieHeader.get(0));
			post.setParams(params);
			
			
			HttpResponse response = client.execute(post,localContext);
			is = response.getEntity().getContent();
			message = convertStreamToString(is,Context);
			JSONObject obj = new JSONObject(message);
			JSONArray array = obj.getJSONArray("item");			
			
			for(int i=0; i<array.length(); i++){
				members.add(array.getJSONObject(i).getString("nickname"));
			}			
		}catch(Exception e){e.printStackTrace();}		
		return members;
		
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
    	return sb.toString();
    }
}
