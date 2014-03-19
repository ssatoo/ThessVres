package com.atei.thessvres.activities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import com.atei.thessvres.MyLocation;
import com.atei.thessvres.PlacesModel;
import com.atei.thessvres.R;
import com.atei.thessvres.DataBase.DataBaseHelper;
import com.atei.thessvres.MyLocation.LocationResult;
import com.atei.thessvres.R.drawable;
import com.atei.thessvres.R.id;
import com.atei.thessvres.R.layout;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import android.annotation.SuppressLint;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationManager;
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

public class MapViewActivity extends ActionBarActivity {
	// private GoogleMap map;
	GoogleMap mMap;

	Cursor cursor;
	String[] listname;
	PlacesModel[] place;
	double[] lat;
	double[] lon;
	SQLiteDatabase db;
	double mlat;
	double mlon;
	 int minLat ;
	 int maxLat ;
	 int minLon  ;
	 int maxLon ;
	 List<PlacesModel> items = null;	
	DataBaseHelper myDbHelper = new DataBaseHelper(this);

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mapview);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		Intent i = getIntent();
		String mapmode = i.getStringExtra("mapflag");
		String cat = i.getStringExtra("MCategory");
		String pName = i.getStringExtra("pName");
		/*
		 * mMap = ((MapFragment)
		 * getFragmentManager().findFragmentById(R.id.map)).getMap();
		 * mMap.setMyLocationEnabled(true);
		 * mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
		 */

		/*	*/

		mMap = ((SupportMapFragment) getSupportFragmentManager()
				.findFragmentById(R.id.map)).getMap();
		mMap.setMyLocationEnabled(true);
		mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

		if (mapmode.equals("Placemode")) {
			Log.e("Mapviewactivity", "Placemode");
			myDbHelper.openDataBase();
			cursor = myDbHelper.selectRecordBycategory(cat);
			ShowMapView();

		} else if (mapmode.equals("singleformap")) {
			myDbHelper.openDataBase();
			cursor = myDbHelper.selectRecordByName(pName);
			ShowMapView();
		} else {
			showPlace();
		}

	}

	public void showPlace() {

		Log.e("TEST", "showPlace()");

		double plat = AndroidListViewActivity.location1.getLatitude();
		double plon = AndroidListViewActivity.location1.getLongitude();
		drawMarker(new LatLng(plat, plon),
				getResources().getString(R.string.clocation));
		// Moving CameraPosition to last clicked position
		mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(plat, plon)));
		mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 4000, null);

	}

	public void ShowMapView() {
		Log.e("Mapviewactivity", "showmapview()");
		final PlacesModel pm = new PlacesModel();

		if (cursor.moveToFirst()) {
			do {

				pm.setName(cursor.getString(1));
				pm.setLatitude(cursor.getDouble(4));
				pm.setLongtitude(cursor.getDouble(5));

				drawMarker(new LatLng(pm.getLatitude(), pm.getLongtitude()),
						pm.getName());

			} while (cursor.moveToNext());
		}
		myDbHelper.close();
		LatLng lastLatLng = new LatLng(pm.getLatitude(), pm.getLongtitude());
		mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(lastLatLng, 15));
		mMap.animateCamera(CameraUpdateFactory.zoomTo(15), 4000, null);

	}

	public void getPlaces(Cursor cursor) {
		Log.e("TEST", "getPlaces()");
		String[] lat = new String[cursor.getCount()];
		String[] lon = new String[cursor.getCount()];
		String[] placename = new String[cursor.getCount()];

		final PlacesModel pm = new PlacesModel();
		if (cursor.moveToFirst()) {
			int cout = 0;
			do {
				pm.setName(cursor.getString(1));
				pm.setLatitude(cursor.getDouble(4));
				pm.setLongtitude(cursor.getDouble(5));
				placename[cout] = pm.getName();
				lon[cout] = Double.toString(pm.getLongtitude());
				lat[cout] = Double.toString(pm.getLatitude());
				cout++;
			} while (cursor.moveToNext());
		}
		myDbHelper.close();
	}

	public void openDatapase() {
		Log.e("MapViewActivity ", "openDatapase()");

		db = openOrCreateDatabase("mydb", SQLiteDatabase.CREATE_IF_NECESSARY,
				null);
		String parentPath = getApplicationContext().getDatabasePath("mydb")
				.getParent();
		String path = getApplicationContext().getDatabasePath("mydb").getPath();
		File file = new File(parentPath);
		InputStream is = null;
		OutputStream os = null;
		try {
			is = getApplicationContext().getAssets().open("mydb");
			os = new FileOutputStream(path);
			byte[] buffer = new byte[1024];
			while (is.read(buffer) > 0) {
				os.write(buffer);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Cursor cursor;

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

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items

		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;
		case R.id.search:
			Log.e("MapViewActivity ", "Action search");
			Intent i = new Intent(getApplicationContext(), SearchActivity.class);
			startActivity(i);
			return true;
		case R.id.action_list:
			Log.e("MapViewActivity ", "Action list");
			myDbHelper.close();
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.searcmapmenu, menu);
		return super.onCreateOptionsMenu(menu);
	}
	
	/*protected LatLng getCameraCenter() {
		  
		  minLat = Integer.MAX_VALUE;
		  maxLat = Integer.MIN_VALUE;
		  minLon = Integer.MAX_VALUE;
		  maxLon = Integer.MIN_VALUE;
		  
		  for(int i = 0; i<items.size();i++){
		        maxLat = Math.max((items.get(i).getCenter().latitude), maxLat);
		        minLat = Math.min((items.get(i).getCenter().latitude), minLat);
		        maxLon = Math.max((items.get(i).getCenter().longitude), maxLon);
		        minLon = Math.min((items.get(i).getCenter().longitude), minLon);   
		  }
		  
		  return new LatLng(maxLat - ((maxLat - minLat)/2), maxLon - ((maxLon - minLon)/2));
		 }*/

}
