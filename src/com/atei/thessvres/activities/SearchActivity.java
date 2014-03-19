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
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class SearchActivity extends ActionBarActivity {
	String c_f;
	String c_t;
	Cursor cursor;
	PlacesModel[] place;
	double[] lat;
	double[] lon;
	SQLiteDatabase db;
	DataBaseHelper myDbHelper = new DataBaseHelper(this);

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.searc_view);
		myDbHelper.openDataBase();
		cursor = myDbHelper.selectAllRecord();

		if (cursor.getCount() == 0) {
			Utilities.showCustomToast("No source!", Toast.LENGTH_SHORT,
					getApplicationContext());
		} else {

			List<String> placename = new ArrayList<String>();

			if (cursor.moveToFirst()) {
				int cout = 0;
				do {
					placename.add(cursor.getString(1));
					cout++;
				} while (cursor.moveToNext());
			}
			myDbHelper.close();
			// final Spinner spinerplacefrom = (Spinner)
			// findViewById(R.id.f_places);
			final ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(
					getApplicationContext(),
					android.R.layout.simple_dropdown_item_1line, placename);
			// dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

			final AutoCompleteTextView autotext = (AutoCompleteTextView) findViewById(R.id.editText1);
			autotext.setAdapter(dataAdapter);
			final AutoCompleteTextView autotext2 = (AutoCompleteTextView) findViewById(R.id.editText2);
			autotext2.setAdapter(dataAdapter);

			RadioGroup rgroup = (RadioGroup) findViewById(R.id.radioGroup1);
			rgroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

				@Override
				public void onCheckedChanged(RadioGroup group, int checkedId) {
					// TODO Auto-generated method stub

					if (checkedId == R.id.CurLocation) {
						autotext.setEnabled(false);
						autotext.setText("");
					} else if (checkedId == R.id.customLocation) {
						autotext.setEnabled(true);
					}// end else if
				}// end OnCheck
			});// end rgroup.seton
			Button search_btn = (Button) findViewById(R.id.Search_btn);
			search_btn.setOnClickListener(new View.OnClickListener() {
				public void onClick(View v) {

					if ((autotext.getText().toString().equals(""))
							&& (autotext.isEnabled())) {

						Utilities.showCustomToast("No sourse",
								Toast.LENGTH_SHORT, getApplicationContext());

					} else {
						c_f = autotext.getText().toString();
					}
					if (autotext2.getText().toString().equals("")) {
						Utilities.showCustomToast("No destination",
								Toast.LENGTH_SHORT, getApplicationContext());

					} else {
						c_t = autotext2.getText().toString();

						Intent i = new Intent(getApplicationContext(),
								SearchResultsActivity.class);
						i.putExtra("to", c_t.trim());
						if (autotext.getText().toString().equals("")) {
							i.putExtra("from", "Current Location");
						} else {
							i.putExtra("from", c_f.trim());
						}
						startActivity(i);
					}
				}
			});
		}

	}
}