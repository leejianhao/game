package org.mrseige.adapter;

import org.mrseige.activity.R;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;


public class ScoreAdapter extends CursorAdapter {

	public ScoreAdapter(Context context, Cursor c) {
		super(context, c);
		// TODO Auto-generated constructor stub
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());
		View view = inflater.inflate(R.layout.score_list_item, parent, false);
		return view;
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		// TODO Auto-generated method stub
		TextView nameTextView = (TextView) view.findViewById(R.id.name_view);
		nameTextView.setText(cursor.getString(1));
		TextView scoreTextView = (TextView) view.findViewById(R.id.score_view);
		scoreTextView.setText(cursor.getString(2));
	}

}
