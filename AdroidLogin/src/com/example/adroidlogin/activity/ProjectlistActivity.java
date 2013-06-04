package com.example.adroidlogin.activity;

import java.util.ArrayList;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

import com.example.adroidlogin.R;
import com.example.androidlogin.component.Project;
import com.example.androidlogin.component.ProjectAdapter;
import com.example.androidlogin.component.UserInfo;
import com.example.androidlogin.network.LoadProjectNetwork;
import com.example.androidlogin.network.LoadUserInfoNetwork;

public class ProjectlistActivity extends Activity {
	private ListView lv;
	private ProjectAdapter mAdapter;
	private TextView wellcomeUsername;
	private UserInfo User;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.boardlist);  
        LoadProjectNetwork net = new LoadProjectNetwork();
        ArrayList<Project> Plist = net.ExecuteLoadProject(getApplicationContext());  
        LoadUserInfoNetwork Usernet = new LoadUserInfoNetwork();
		User = Usernet.ExecuteLoadUserInfo(getApplicationContext());
		
		wellcomeUsername = (TextView)findViewById(R.id.wellcome_username);
		wellcomeUsername.setText(User.getNickname().toString());
        lv = (ListView) findViewById(R.id.project_list);
        
        mAdapter = new ProjectAdapter(this, R.layout.projectcard, Plist);
        lv.setAdapter(mAdapter);
    }
	
	public boolean onCreateOptionsMenu(Menu menu) {
        menu.setQwertyMode(false);
        MenuItem m0 = menu.add(0,0,0,"회원정보 보기");
        return true;
    }
	public boolean onOptionsItemSelected(MenuItem item){
		if(item.getItemId()==0){
			Context mContext = getApplicationContext();
			LayoutInflater inflater = (LayoutInflater)mContext.getSystemService(LAYOUT_INFLATER_SERVICE);
			View layout = inflater.inflate(R.layout.user_info_dialog, (ViewGroup)findViewById(R.id.uinfo_root));
			TextView email = (TextView)layout.findViewById(R.id.uinfo_email);
			TextView nickname = (TextView)layout.findViewById(R.id.nickname);
			TextView reg_date = (TextView)layout.findViewById(R.id.reg_date);		
			
			
			email.setText(User.getEmail());
			nickname.setText(User.getNickname());
			reg_date.setText(User.getReg_date());
			AlertDialog.Builder aDialog = new AlertDialog.Builder(ProjectlistActivity.this);
			aDialog.setTitle("회원 정보");
			aDialog.setView(layout);
			
			aDialog.setNegativeButton("확인", new DialogInterface.OnClickListener() {				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});
			AlertDialog ad = aDialog.create();
			ad.show();
		}
		return true;
	}	
}
