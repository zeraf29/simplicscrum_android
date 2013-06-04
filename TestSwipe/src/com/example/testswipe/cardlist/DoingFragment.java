package com.example.testswipe.cardlist;

import java.util.ArrayList;

import com.example.testswipe.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

@SuppressLint("ValidFragment")
public class DoingFragment extends Fragment {
	ListView mList;
	Context mContext;
	
	public DoingFragment(Context context){
		mContext = context;
	}
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
		Log.v("CardListFragment", "create");
		View view = inflater.inflate(R.layout.page_one_cardlist, null);
		mList = (ListView)view.findViewById(R.id.cardlist);
		mList.setAdapter(new CardListAdapter(mContext,R.layout.simple_cardlist_item,createData()));
		return view;		
	}
	
	//테스트용 데이타 값 주입
		protected ArrayList<Card> createData(){
			ArrayList<Card> obj = new ArrayList<Card>();
			obj.add(new Card(0,"test5"));
			obj.add(new Card(2,"test6"));
			obj.add(new Card(6,"test7"));
			obj.add(new Card(1,"test8"));
			
			return obj;
		}
}
