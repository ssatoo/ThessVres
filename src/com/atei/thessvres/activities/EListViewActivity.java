package com.atei.thessvres.activities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.atei.thessvres.PlacesModel;
import com.atei.thessvres.R;
import com.atei.thessvres.DataBase.DataBaseHelper;
import com.atei.thessvres.adapters.MyExLvAdapter;

import android.app.Activity;
import android.app.ExpandableListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.os.Bundle;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class EListViewActivity extends ActionBarActivity implements
		OnChildClickListener {
	ExpandableListView exlv;
	DataBaseHelper myDbHelper = new DataBaseHelper(this);
	Cursor cursor;
	MyExLvAdapter listAdapter;
	List<String> listDataHeader;
	List<String> tmplistDataCh;
	List<String> tmplistDataname;
	HashMap<String, List<String>> tmplistname;
	HashMap<String, List<String>> listDataChild;
	String cat;
	ImageView img;

	float km;
	String name;
	String Lat;
	String Lon;
	String description;
	String tell;
	String link;
	String email;
	String menu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		setContentView(R.layout.exlist);
		Intent i = getIntent();
		cat = i.getStringExtra("MCategory");
		img = (ImageView) findViewById(R.id.imglist);
		prepareListDatasql();
		exlv = (ExpandableListView) findViewById(R.id.expandableListView1);
		listAdapter = new MyExLvAdapter(this, listDataHeader, listDataChild);

		exlv.setAdapter(listAdapter);

		exlv.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				Intent i = new Intent(getApplicationContext(),
						SingleListItem.class);
				Log.e("TEST GIA CLICK"," MPIKA!!");
				i.putExtra("title",""+ tmplistname.get(listDataHeader.get(groupPosition)).get(childPosition));

				// getchildatt(listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition).trim());
				/*
				 * i.putExtra("info", placed[position]); i.putExtra("la",
				 * lat[position]); i.putExtra("lo", lon[position]);
				 * i.putExtra("tell", tell[position]); i.putExtra("link",
				 * link[position]); i.putExtra("email", email[position]);
				 * i.putExtra("menu", menustr[position]);
				 */
				myDbHelper.openDataBase();
				Log.i("Elist","Child name : "+tmplistname.get(listDataHeader.get(groupPosition)).get(childPosition));
				cursor = myDbHelper.selectRecordByName(tmplistname.get(listDataHeader.get(groupPosition)).get(childPosition));
				if (cursor.moveToFirst()) {
					do {

						Log.w("child", "-onclick: " + cursor.getString(0));
						Log.w("child", "-onclick: " + cursor.getString(1));
						Log.w("child", "-onclick: " + cursor.getString(2));
						Log.w("child", "-onclick: " + cursor.getString(3));
						Log.w("child", "-onclick: " + cursor.getString(4));
						Log.w("child", "-onclick: " + cursor.getString(5));
						Log.w("child", "-onclick: " + cursor.getString(6));
						Log.w("child", "-onclick: " + cursor.getString(7));
						Log.w("child", "-onclick: " + cursor.getString(8));

						name = cursor.getString(1);
						description = cursor.getString(3);
						Lat = Double.toString(cursor.getDouble(4));
						Lon = Double.toString(cursor.getDouble(5));
						tell = cursor.getString(6).trim();
						link = cursor.getString(7).trim();
						email = cursor.getString(8).trim();
						menu = cursor.getString(9).trim();
						Log.i("child--", "" + name);

					} while (cursor.moveToNext());
				}
				myDbHelper.close();

				i.putExtra("info", description);
				i.putExtra("la", Lat);
				i.putExtra("lo", Lon);
				i.putExtra("tell", tell);
				i.putExtra("link", link);
				i.putExtra("email", email);
				i.putExtra("menu", menu);

				Log.v("name :",""+ listDataChild.get(listDataHeader.get(groupPosition)).get(childPosition) + " name  " + name);
				startActivity(i);
				return false;
			}
		});

	}

	private void prepareListDatasql() {
		listDataHeader = new ArrayList<String>();

		listDataChild = new HashMap<String, List<String>>();
		tmplistname = new HashMap<String, List<String>>();
		myDbHelper.openDataBase();

		Cursor chcursor;
		cursor = myDbHelper.selectChbycatRecord(cat);
		int count = 0;
		if (cursor.moveToFirst()) {
			do {
				tmplistDataCh = new ArrayList<String>();
				tmplistDataname = new ArrayList<String>();

				// gia na diamorfosw o kathe pedio tis liastas
				Log.v("parent", "" + cursor.getString(0));
				listDataHeader.add(cursor.getString(0));
				DataBaseHelper myDbHelper1 = new DataBaseHelper(this);
				myDbHelper1.openDataBase();
				Log.e("pedia", "" + cat + " " + cursor.getString(0));
				chcursor = myDbHelper1.selectRecordByCh(cat,cursor.getString(0));
				if (chcursor.moveToFirst()) {
					do {
						PlacesModel pm = new PlacesModel();
						Log.i("child", "" + chcursor.getString(0));
						Log.i("child", "" + chcursor.getString(1));
						Log.i("child", "" + chcursor.getString(2));
						Log.i("child", "" + chcursor.getString(3));
						Log.i("child", "" + chcursor.getString(4));
						Log.i("child", "" + chcursor.getString(5));
						Log.i("child", "" + chcursor.getString(6));
						Log.i("child", "" + chcursor.getString(7));
						Log.i("child", "" + chcursor.getString(8));

						pm.setID(chcursor.getInt(0));
						pm.setName(chcursor.getString(1));
						pm.setCategory(chcursor.getString(2));
						pm.setDescription(chcursor.getString(3));
						pm.setLatitude(chcursor.getDouble(4));
						pm.setLongtitude(chcursor.getDouble(5));
						pm.setTell(chcursor.getString(6).trim());
						pm.setLink(chcursor.getString(7).trim());
						pm.setEmail(chcursor.getString(8).trim());
						pm.setMenu(chcursor.getString(9).trim());

						Location location2 = new Location("");
						location2.setLatitude(pm.getLatitude());
						location2.setLongitude(pm.getLongtitude());
						double distanceInMeters = AndroidListViewActivity.location1
								.distanceTo(location2);
						float distanceInKm = (float) distanceInMeters / 1000;
						pm.setkm(distanceInKm);
						tmplistDataCh.add(pm.getName() + " \n "
								+ String.format("%.2f", pm.getkm()) + "km");
						tmplistDataname.add(pm.getName());

					} while (chcursor.moveToNext());

				}
				myDbHelper1.close();

				listDataChild.put(listDataHeader.get(count), tmplistDataCh);
				tmplistname.put(listDataHeader.get(count), tmplistDataname);
				count++;
			} while (cursor.moveToNext());
		}
		myDbHelper.close();
		// Adding child data
	}

	/*
	 * private void getchildatt(String name) {
	 * 
	 * myDbHelper.openDataBase();
	 * 
	 * 
	 * cursor=myDbHelper.selectRecordByName(name); int count=0; if
	 * (cursor.moveToFirst()) { do {
	 * 
	 * Log.i("child",""+cursor.getString(0));
	 * Log.i("child",""+cursor.getString(1));
	 * Log.i("child",""+cursor.getString(2));
	 * Log.i("child",""+cursor.getString(3));
	 * Log.i("child",""+cursor.getString(4));
	 * Log.i("child",""+cursor.getString(5));
	 * Log.i("child",""+cursor.getString(6));
	 * Log.i("child",""+cursor.getString(7));
	 * Log.i("child",""+cursor.getString(8));
	 * 
	 * name=cursor.getString(1); Lat= cursor.getString(4); Lon=
	 * Double.toString(cursor.getDouble(5)); description=cursor.getString(3);
	 * tell=cursor.getString(6).trim(); link=cursor.getString(7).trim();
	 * email=cursor.getString(8).trim(); Log.i("child--",""+name);
	 * 
	 * 
	 * }while (cursor.moveToNext()); } myDbHelper.close(); // Adding child data
	 * }
	 */

	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items

		switch (item.getItemId()) {

		case R.id.search:
			Intent i = new Intent(getApplicationContext(),
					simplesearchactivity.class);
			startActivity(i);
			return true;
		case R.id.action_map:
			// openSettings();
			Intent i1 = new Intent(getApplicationContext(),
					MapViewActivity.class);
			String mapmode = "Placemode";
			i1.putExtra("mapflag", mapmode);
			i1.putExtra("MCategory", cat);
			startActivity(i1);

			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.searchmenu, menu);
		return super.onCreateOptionsMenu(menu);
	}
}
