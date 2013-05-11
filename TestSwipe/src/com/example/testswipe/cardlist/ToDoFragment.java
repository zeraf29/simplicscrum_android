package com.example.testswipe.cardlist;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.testswipe.R;

@SuppressLint("ValidFragment")
public class ToDoFragment extends Fragment{
	ListView mList;
	Context mContext;
	
	public ToDoFragment(Context context){
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
		obj.add(new Card(0,"test1"));
		obj.add(new Card(0,"test2"));
		obj.add(new Card(0,"test3"));
		obj.add(new Card(0,"test4"));
		
		return obj;
	}
}
