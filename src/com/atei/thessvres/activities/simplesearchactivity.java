package com.atei.thessvres.activities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.atei.thessvres.PlacesModel;
import com.atei.thessvres.R;
import com.atei.thessvres.DataBase.DataBaseHelper;
import com.atei.thessvres.R.id;
import com.atei.thessvres.R.layout;
import com.atei.thessvres.utilities.Utilities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

public class simplesearchactivity extends ActionBarActivity {
	AutoCompleteTextView placeauto;
	Cursor cursor;
	PlacesModel[] place;
	double[] lat;
	double[] lon;
	SQLiteDatabase db;
	String categ;
	PlacesModel pm;
	ArrayList<PlacesModel> tmp;
	DataBaseHelper myDbHelper = new DataBaseHelper(this);
	String icon ;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simplesearchview);
		placeauto = (AutoCompleteTextView) findViewById(R.id.placetxt);
		myDbHelper.openDataBase();
		cursor = myDbHelper.selectAllRecord();
		if (cursor.getCount() == 0) {
			Utilities.showCustomToast("No source!", Toast.LENGTH_SHORT,
					getApplicationContext());
		} else {
			List<String> placename = new ArrayList<String>();

			if (cursor.moveToFirst()) {

				do {
					placename.add(cursor.getString(1));
					categ=cursor.getString(2);
				} while (cursor.moveToNext());
			}

			myDbHelper.close();
			ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(
					getApplicationContext(),
					android.R.layout.simple_dropdown_item_1line, placename);
			AutoCompleteTextView autotext = (AutoCompleteTextView) findViewById(R.id.placetxt);
			autotext.setAdapter(dataAdapter);

			// Log.e( "",""+tmp.size());
		}
		Button S_btn = (Button) findViewById(R.id.Search_btn);
		S_btn.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent i = new Intent(getApplicationContext(),
						AndroidListViewActivity.class);
				i.putExtra("pName", placeauto.getText().toString());
				String sh = "1";
				i.putExtra("flag", sh);
				i.putExtra("MCategory", categ);
				
				myDbHelper.openDataBase();
				cursor = myDbHelper.selectRecordByName(placeauto.getText().toString());
				if (cursor.moveToFirst()) {

					do {
						
						Log.e("apo to select","categ : "+cursor.getString(2));
						categ=cursor.getString(2);
					} while (cursor.moveToNext());
				}myDbHelper.close();

				
				Log.d("DES","categ : "+categ);
				if (categ.equals("Μουσείο")){ // analoga me tin
					icon = "" + R.drawable.list_m_icon; // katigoria pou exei
				} else if (categ.equals("Αξιοθέατο")) { // dialexei
					icon = "" + R.drawable.list_ar_icon; // fortonei i analogi
				} else if (categ.equals("Εκκλησία")) { // eikona gia na
					icon = "" + R.drawable.list_ch_icon; // emfanistei
				} else if (categ.equals("Εστιατόριο")) { // sti lista
					icon = "" + R.drawable.list_rest_icon; //
				} else if (categ.equals("Νοσοκομείo")) { // sti lista
					icon = "" + R.drawable.list_hos_icon;
				} else if (categ.equals("Αστυνομία")) { // sti lista
					icon = "" + R.drawable.list_polic_icon;
				} else if (categ.equals("Προξενείo")) { // sti lista
					icon = "" + R.drawable.list_presvia_icon;
				} else if (categ.equals("event")) { // sti lista
					icon = "" + R.drawable.list_events_icon;
				}else{icon = "" + R.drawable.list_events_icon;}
				Log.d("DES","ICON : "+icon);
				i.putExtra("icon", icon);
				startActivity(i);
			}

		});
	}

}

/*
*/