package com.bignerdranch.android.criminalintent;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.R.integer;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;

public class DatePickerFragment extends DialogFragment {
	
	public static final String EXTRA_DATE = "com.bignerdranch.android.criminalintent.date";
	
	private Date mDate;
	
	public static DatePickerFragment newInstance(Date date) {
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_DATE, date);
		
		DatePickerFragment fragment = new DatePickerFragment();
		fragment.setArguments(args);
		return fragment;
	}
	
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
//		super.onCreateDialog(savedInstanceState);
		mDate = (Date)getArguments().getSerializable(EXTRA_DATE);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(mDate);
		final int year = calendar.get(calendar.YEAR);
		final int month = calendar.get(calendar.MONTH);
		final int day = calendar.get(calendar.DAY_OF_MONTH);
		
		View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_date, null);
		
		DatePicker datePicker = (DatePicker)view.findViewById(R.id.dialog_date_datePicker);
		datePicker.init(year, month, day, new OnDateChangedListener() {
			
			@Override
			public void onDateChanged(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				// TODO Auto-generated method stub
				mDate = new GregorianCalendar(year, month, day).getTime();
				getArguments().putSerializable(EXTRA_DATE, mDate);
			}
		});
		
		return new AlertDialog.Builder(getActivity())
		.setView(view)
		.setTitle(R.string.date_picker_title)
		.setPositiveButton(android.R.string.ok, null)
		.create();
	}
	
}
