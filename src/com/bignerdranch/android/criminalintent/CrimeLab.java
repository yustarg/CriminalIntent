package com.bignerdranch.android.criminalintent;

import java.util.ArrayList;
import java.util.UUID;

import android.R.integer;
import android.content.Context;

public class CrimeLab {
	
	private ArrayList<Crime> mCrimes;
	
	private static CrimeLab sCrimeLab;
	private Context mAppContext;
	
	private CrimeLab(Context appContext) {
		mAppContext = appContext;
		mCrimes = new ArrayList<Crime>();
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
			if(crime.getId() == id) {
				return crime;
			}
		}
		return null;
	}
}
