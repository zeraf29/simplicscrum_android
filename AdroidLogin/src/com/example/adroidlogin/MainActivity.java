package com.example.adroidlogin;

import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	EditText un,pw;
	TextView error;
	Button ok;
	String message="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        un = (EditText)findViewById(R.id.et_un);
        pw = (EditText)findViewById(R.id.et_pw);
        ok = (Button)findViewById(R.id.btn_login);
        error = (TextView)findViewById(R.id.tv_error);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().build();
        StrictMode.setThreadPolicy(policy);
        ok.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				HttpClient http = new DefaultHttpClient();
				try{
					ArrayList<NameValuePair> postParameters = new ArrayList<NameValuePair>();
					postParameters.add(new BasicNameValuePair("email",un.getText().toString()));
					postParameters.add(new BasicNameValuePair("pw",pw.getText().toString()));
					
					HttpParams params = http.getParams();
					HttpConnectionParams.setConnectionTimeout(params, 5000);
					HttpConnectionParams.setSoTimeout(params, 5000);
					
					HttpPost httpPost = new HttpPost("http://jinhyupkim.iptime.org/~sscrum/SimplicScrum/index.php/api/login/getLogin");
					UrlEncodedFormEntity entityRequest = new UrlEncodedFormEntity(postParameters,"utf-8");
					httpPost.setEntity(entityRequest);
					HttpResponse responsePost = http.execute(httpPost);
					HttpEntity resEntity = responsePost.getEntity();
					message = EntityUtils.toString(resEntity);
					
					if(message.equals("1")){
						Toast.makeText(getApplicationContext(), "다음 액티비티로...", Toast.LENGTH_SHORT).show();
						Intent intent = new Intent("android.intent.action.ProjectList");
						intent.putExtra("id", un.getText().toString());
						startActivity(intent);
					}else if(message.equals("0")){						
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
    
}
