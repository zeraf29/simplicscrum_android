package com.example.testswipe;

import com.example.testswipe.cardlist.CardListFragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

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
				return new CardListFragment(mContext);
			case 1:
				//return null; 
				return new CardListFragment(mContext);	//별도의 Fragment를 만들어야함.(Doing의 list를 받을 같은 내용의 Fragment)
			case 2:
				//return null;
				return new CardListFragment(mContext);	//별도의 Fragment를 만들어야함.(Done의 list를 받을 같은 내용의 Fragment)
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
