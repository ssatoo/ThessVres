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
import com.atei.thessvres.R.menu;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SearchResultsActivity extends ActionBarActivity {
	GoogleMap mMap;

	String plat, plon;
	double mlat, mlon, tolat, tolon;
	Location lastLoc;
	SQLiteDatabase db;
	DataBaseHelper myDbHelper = new DataBaseHelper(this);
	Cursor cursor;
	String n;
	PlacesModel pm, pmt;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.search_result);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		TextView fromtxt = (TextView) findViewById(R.id.from_txt);
		TextView totxt = (TextView) findViewById(R.id.to_txt);
		Intent i = getIntent();

		totxt.setText(i.getStringExtra("to"));

		myDbHelper.openDataBase();

		if (i.getStringExtra("from").equals("Current Location")) {

			mlat = AndroidListViewActivity.location1.getLatitude();
			mlon = AndroidListViewActivity.location1.getLongitude();
			n = getResources().getString(R.string.clocation);
			fromtxt.setText(n);
		} else {
			fromtxt.setText(i.getStringExtra("from"));
			cursor = myDbHelper.selectRecordByName(i.getStringExtra("from"));
			pm = new PlacesModel();
			if (cursor.moveToFirst()) {
				do {
					pm.setName(cursor.getString(1));
					pm.setLatitude(cursor.getDouble(4));
					pm.setLongtitude(cursor.getDouble(5));

				} while (cursor.moveToNext());
			}
			

			mlat = pm.getLatitude();
			mlon = pm.getLongtitude();
			n = pm.getName();
		}
		cursor = myDbHelper.selectRecordByName(i.getStringExtra("to"));
		pmt = new PlacesModel();
		if (cursor.moveToFirst()) {
			do {
				pmt.setName(cursor.getString(1));
				pmt.setLatitude(cursor.getDouble(4));
				pmt.setLongtitude(cursor.getDouble(5));
			} while (cursor.moveToNext());
		}
		myDbHelper.close();
		tolat = pmt.getLatitude();
		tolon = pmt.getLongtitude();

		mMap = ((SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.smap)).getMap();
		// mMap.setMyLocationEnabled(true);
		mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

		// drawMarker(new LatLng(mlat, mlon));

		drawMarker(new LatLng(mlat, mlon), n);
		drawMarker(new LatLng(tolat, tolon), pmt.getName());

		LatLng lastLatLng = new LatLng(mlat, mlon);
		mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastLatLng, 15));
		mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 4000, null);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items

		switch (item.getItemId()) {

		case R.id.search:
			Log.e("SearchResultsActivity ", "Action search");
			Intent i = new Intent(getApplicationContext(), SearchActivity.class);
			startActivity(i);
			return true;
		case R.id.action_walk:
			Log.e("SearchResultsActivity ", "Action walk");
			Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
					Uri.parse("http://maps.google.com/maps?saddr=" + mlat + ","
							+ mlon + "&daddr=" + tolat + "," + tolon));
			startActivity(intent);
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.searchwalkmenu, menu);
		return super.onCreateOptionsMenu(menu);

	}

	private void drawMarker(LatLng point, String n) {
		Log.e("MapViewActivity ", "drawMarker()");
		// Creating an instance of MarkerOptions
		MarkerOptions markerOptions = new MarkerOptions();

		// Setting latitude and longitude for the marker
		markerOptions.position(point).title(n);

		// Adding marker on the Google Map
		mMap.addMarker(markerOptions);
	}

}
