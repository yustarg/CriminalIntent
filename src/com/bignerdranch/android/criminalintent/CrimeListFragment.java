package com.bignerdranch.android.criminalintent;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Intent;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.ListView;
import android.widget.TextView;

public class CrimeListFragment extends ListFragment {
	
	private static final String TAG = "CrimeListFragment";
	
	private ArrayList<Crime> mCrimes;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getActivity().setTitle(R.string.crime_title);
		mCrimes = CrimeLab.get(getActivity()).getCrimes();
	
//		ArrayAdapter<Crime> adapter = new ArrayAdapter<Crime>(getActivity(), android.R.layout.simple_list_item_1, mCrimes);
		CrimeAdapter adapter = new CrimeAdapter(mCrimes);
		setListAdapter(adapter);
	}
	
	
	@Override
	public void onResume() {
		super.onResume();
		((CrimeAdapter)getListAdapter()).notifyDataSetChanged();
	}
	
	
	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Crime crime = ((CrimeAdapter)getListAdapter()).getItem(position);
//		Log.d(TAG, crime.getTitle() + "  " + crime.getId() + " was clicked");
		Intent intent = new Intent(getActivity(), CrimeActivity.class);
		intent.putExtra(CrimeFragment.EXTRA_CRIME_ID, crime.getId());
		startActivity(intent);
	}
	
	
	private class CrimeAdapter extends ArrayAdapter<Crime> {
		
		public CrimeAdapter(ArrayList<Crime> crimes) {
			super(getActivity(), 0, crimes);
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if(convertView == null) {
				convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_crime, null);
			}
			Crime crime = getItem(position);
			
			TextView titleTextView = (TextView)convertView.findViewById(R.id.crime_list_item_titleTextView);
			titleTextView.setText(crime.getTitle());
			
			TextView dateTextView = (TextView)convertView.findViewById(R.id.crime_list_item_dateTextView);
			dateTextView.setText(crime.getDate().toString());
			CheckBox solvedCheckBox = (CheckBox)convertView.findViewById(R.id.crime_list_item_checkBox);
			solvedCheckBox.setChecked(crime.isSovled());
			
			return convertView;
		}
	}
}
