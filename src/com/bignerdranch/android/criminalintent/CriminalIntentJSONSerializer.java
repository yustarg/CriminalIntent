package com.bignerdranch.android.criminalintent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;
import org.xml.sax.InputSource;

import com.bignerdranch.android.criminalintent.R.string;

import android.content.Context;

public class CriminalIntentJSONSerializer {
	
	private Context mContext;
	private String mFileName;
	
	public CriminalIntentJSONSerializer(Context context, String fileName) {
		mContext = context;
		mFileName = fileName;
	}
	
	public void saveCrimes(ArrayList<Crime> crimes) throws JSONException, IOException {
		JSONArray array = new JSONArray();
		for (Crime crime : crimes) {
			array.put(crime.toJSON());
		}
		
		Writer writer = null;
		try {
			OutputStream out = mContext.openFileOutput(mFileName, Context.MODE_PRIVATE);
			writer = new OutputStreamWriter(out);
			writer.write(array.toString());
		} finally {
			if(writer != null) {
				writer.close();
			}
		}
	}
	
	public ArrayList<Crime> loadCrimes() throws IOException, JSONException {
		ArrayList<Crime> crimes = new ArrayList<Crime>();
		BufferedReader reader = null;
		try {
			InputStream in = mContext.openFileInput(mFileName);
			reader = new BufferedReader(new InputStreamReader(in));
			StringBuilder jsonString = new StringBuilder();
			String line = null;
			while((line = reader.readLine()) != null) {
				jsonString.append(line);
			}
			JSONArray array = (JSONArray)new JSONTokener(jsonString.toString()).nextValue();
			for(int i = 0; i < array.length(); i++) {
				crimes.add(new Crime(array.getJSONObject(i)));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			if(reader != null) {
				reader.close();
			}
		}
		return crimes;
	}
	
}
