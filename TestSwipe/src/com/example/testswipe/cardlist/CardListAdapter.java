package com.example.testswipe.cardlist;

import java.util.ArrayList;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.testswipe.R;

public class CardListAdapter extends BaseAdapter {
	Context mContext;
	ArrayList<Card> mCardData;	//나중에 card class 추가요망(추가 후에 String 대신 사용)
	LayoutInflater layoutInflater;
	int layout;
	
	
	public CardListAdapter(Context context, int layout, ArrayList<Card> cards){
		mContext = context;
		mCardData = cards;
		
		layoutInflater = (LayoutInflater)mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.layout = layout;
	}	
	
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mCardData.size();
	}

	
	public Card getItem(int position) {
		// TODO Auto-generated method stub
		return mCardData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public int getItemViewType(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Card card = getItem(position);
		if(convertView == null){
			convertView = layoutInflater.inflate(layout, parent, false);
		}
		
		TextView subject = (TextView)convertView.findViewById(R.id.item_subject);
		TextView vote = (TextView)convertView.findViewById(R.id.item_vote_tv);
		//ImageView vote_img = (ImageView)convertView.findViewById(R.id.item_vote_button);
		
		subject.setText(card.getSubject());
		vote.setText(card.getVote());
		
		return convertView;
	}

	@Override
	public int getViewTypeCount() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void registerDataSetObserver(DataSetObserver observer) {
		// TODO Auto-generated method stub

	}

	@Override
	public void unregisterDataSetObserver(DataSetObserver observer) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean areAllItemsEnabled() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled(int position) {
		// TODO Auto-generated method stub
		return false;
	}

}
