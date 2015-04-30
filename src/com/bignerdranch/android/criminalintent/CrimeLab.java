package com.bignerdranch.android.criminalintent;

import java.util.ArrayList;
import java.util.UUID;

import android.R.integer;
import android.content.Context;
import android.os.Debug;
import android.util.Log;

public class CrimeLab {
	
	private static final String TAG = "CrimeLab";
	private static final String FILENAME = "crimes.json";
	
	private ArrayList<Crime> mCrimes;
	private CriminalIntentJSONSerializer mSerializer;
	
	private static CrimeLab sCrimeLab;
	private Context mAppContext;
	
	private CrimeLab(Context appContext) {
		mAppContext = appContext;
//		mCrimes = new ArrayList<Crime>();
		mSerializer = new CriminalIntentJSONSerializer(mAppContext, FILENAME);
		try {
			mCrimes = mSerializer.loadCrimes();
		} catch (Exception e) {
			// TODO: handle exception
			mCrimes = new ArrayList<Crime>();
			Log.e(TAG, "error load crimes ", e);
		}
	}
	
	public static CrimeLab get(Context c) {
		if(sCrimeLab == null) {
			sCrimeLab = new CrimeLab(c.getApplicationContext());
		}
		return sCrimeLab;
	}
	
	public ArrayList<Crime> getCrimes() {
		return mCrimes;
	}
	
	public Crime getCrime(UUID id) {
		for(Crime crime : mCrimes) {
			if(crime.getId().equals(id)) {
				return crime;
			}
		}
		return null;
	}
	
	public void addCrime(Crime crime) {
		mCrimes.add(crime);
	}
	
	public boolean saveCrimes() {
		try {
			mSerializer.saveCrimes(mCrimes);
			Log.d(TAG, "save to file");
			return true;
		}catch(Exception e) {
			Log.d(TAG, "error save to file", e);
			return false;
		}
	}
}
