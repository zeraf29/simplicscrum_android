package com.example.adroidlogin.activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONObject;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.adroidlogin.R;

public class MainActivity extends Activity {
	EditText un,pw;
	Button ok;
	String message="";
	String cookieString="";
	ImageView Login;
	
    @SuppressLint("NewApi")
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        
        CookieSyncManager.createInstance(getApplicationContext());
        CookieManager.getInstance().removeAllCookie();
        CookieSyncManager.getInstance().startSync();
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
        .detectDiskReads()             
        .detectDiskWrites()
        .detectNetwork()              
        .penaltyLog().build());
        
        Login = (ImageView)findViewById(R.id.loginbutton);
        Login.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				DefaultHttpClient http = new DefaultHttpClient();
				try{
					un = (EditText)findViewById(R.id.id);
					pw = (EditText)findViewById(R.id.password);
					ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
					postParameters.add(new BasicNameValuePair("email",un.getText().toString()));
					postParameters.add(new BasicNameValuePair("pw",pw.getText().toString()));
										
					HttpParams params = http.getParams();
					HttpConnectionParams.setConnectionTimeout(params, 5000);
					HttpConnectionParams.setSoTimeout(params, 5000);

					HttpClientParams.setRedirecting(params, false);
					
					HttpPost httpPost = new HttpPost("http://jinhyupkim.iptime.org/~sscrum/SimplicScrum/index.php/api/login/getLogin");
					UrlEncodedFormEntity entityRequest = new UrlEncodedFormEntity(postParameters,"utf-8");
					httpPost.setEntity(entityRequest);
					HttpResponse responsePost = http.execute(httpPost);
					CookieSyncManager.createInstance(getApplicationContext());
					CookieManager cookieManager = CookieManager.getInstance();
					List<Cookie> cookies = http.getCookieStore().getCookies();
					if(!cookies.isEmpty()){
						for(int i=0;i<cookies.size();i++){
							cookieString = cookies.get(i).getName() + "="+ cookies.get(i).getValue();	
							cookieManager.setCookie("http://jinhyupkim.iptime.org/~sscrum/SimplicScrum/index.php/api/login/getLogin", cookieString);
						}
					}
					
					InputStream is = null;
					is = responsePost.getEntity().getContent();
					
					message = convertStreamToString(is,getApplicationContext());
					JSONObject json = new JSONObject(message);
					String code = json.getString("code");					
					if(code.equals("100")){						
						Toast.makeText(getApplicationContext(), "성공", Toast.LENGTH_SHORT).show();
						Intent intent = new Intent("android.intent.action.ProjectList");						
						startActivity(intent);
					}else if(code.equals("200")){
						AlertDialog.Builder aDialog = new AlertDialog.Builder(MainActivity.this);
						aDialog.setTitle("로그인실패");
						aDialog.setMessage("아이디나 비밀번호를 확인해주세요.");
						aDialog.setPositiveButton("닫기", new DialogInterface.OnClickListener() {		
							@Override
							public void onClick(DialogInterface dialog, int which) {
								// TODO Auto-generated method stub								
							}
						});
						aDialog.show();						
					}					
				}catch(Exception e){e.printStackTrace();}
			}        	
        });
    } 

   
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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
