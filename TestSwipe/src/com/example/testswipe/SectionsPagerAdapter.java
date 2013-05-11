package com.example.testswipe;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.testswipe.cardlist.DoingFragment;
import com.example.testswipe.cardlist.DoneFragment;
import com.example.testswipe.cardlist.ToDoFragment;

public class SectionsPagerAdapter extends FragmentPagerAdapter{
	Context mContext;
	
	public SectionsPagerAdapter(Context context, FragmentManager fm) {
		super(fm);
		mContext = context;
	}

	
	//선택한 페이지의 번호를 받고 페이지 번호에 맞게 페이지를 넘겨줌
	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		switch(arg0){
			case 0:
				//return null;
				return new ToDoFragment(mContext);
			case 1:
				//return null; 
				return new DoingFragment(mContext);	
			case 2:
				//return null;
				return new DoneFragment(mContext);	
		}
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
	}
	
	
	//페이지 정보를 받아서 페이지 상단의 title을 고쳐줌.
	public CharSequence getPageTitle(int position){
		switch(position){
			case 0:
				return "To do".toUpperCase();
			case 1:
				return "Doing".toUpperCase();
			case 2:
				return "Done".toUpperCase();
		}
		return null;
	}
}
