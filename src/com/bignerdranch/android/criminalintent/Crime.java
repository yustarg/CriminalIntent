package com.bignerdranch.android.criminalintent;

import java.security.PrivilegedExceptionAction;
import java.util.Date;
import java.util.UUID;

public class Crime {
	
	private UUID mId;
	private String mTitle;
	private Date mDate;
	private boolean mSovled;
	
	public Crime()
	{
		mId = UUID.randomUUID();
		mDate = new Date();
	}

	public String getTitle() {
		return mTitle;
	}

	public void setTitle(String title) {
		mTitle = title;
	}

	public UUID getId() {
		return mId;
	}

	public Date getDate() {
		return mDate;
	}

	public void setDate(Date date) {
		mDate = date;
	}

	public boolean isSovled() {
		return mSovled;
	}

	public void setSovled(boolean sovled) {
		mSovled = sovled;
	}
	
	@Override
	public String toString() {
		return mTitle;
	}
}
