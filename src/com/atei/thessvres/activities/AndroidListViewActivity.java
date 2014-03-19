package com.atei.thessvres.activities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.R.integer;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.atei.thessvres.MyLocation;
import com.atei.thessvres.MyLocation.LocationResult;
import com.atei.thessvres.PlacesModel;
import com.atei.thessvres.R;
import com.atei.thessvres.BSTree.BSTree;
import com.atei.thessvres.DataBase.DataBaseHelper;
import com.atei.thessvres.adapters.ListAdaptersimple;

public class AndroidListViewActivity extends ActionBarActivity {
	ListView list;
	TextView N_Of_Rows;
	int icon;
	float km;
	String strtmp;
	String Categ;
	String sh_flag;
	SQLiteDatabase db;
	AutoCompleteTextView autotext;
	ArrayAdapter<String> dataAdapter;
	ListView lv;
	List<String> autolist;
	static public Location location1 = new Location("");
	ImageView pic;
	String categ[];
	static double mlat;
	static double mlon;
	DataBaseHelper myDbHelper = new DataBaseHelper(this);
	Cursor cursor;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listview_activity);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		pic = (ImageView) findViewById(R.id.imglist);
		Intent i = getIntent();
		String pName = i.getStringExtra("pName");
		Categ = i.getStringExtra("MCategory");
		icon = Integer.parseInt(i.getStringExtra("icon"));

		sh_flag = i.getStringExtra("flag");
		myDbHelper.openDataBase();
		if (sh_flag.equals("1")) {
			cursor = myDbHelper.selectRecordByName(pName);
			getPlaces(cursor);
			sh_flag = "";
		} else {
			cursor = myDbHelper.selectRecordBycategory(Categ);
			getPlaces(cursor);
			sh_flag = "";
		}
		Log.e("twra einai : ", "" + Categ + "-" + icon);
	}// end on creat
		// pernei ola ta places apo ti lista

	
	public void getPlaces(Cursor cursor) {
		final ArrayList<PlacesModel> pModel_Alist = new ArrayList<PlacesModel>();

		final BSTree pMtree = new BSTree();
		if (cursor.moveToFirst()) {
			int cout = 0;

			do {
				final PlacesModel pm = new PlacesModel();
				// gia na diamorfosw o kathe pedio tis liastas
				HashMap<String, String> data = new HashMap<String, String>();
				pm.setName(cursor.getString(1));
				pm.setID(cursor.getInt(0));
				pm.setLatitude(cursor.getDouble(4));
				pm.setLongtitude(cursor.getDouble(5));
				pm.setDescription(cursor.getString(3));
				pm.setCategory(cursor.getString(2));
				pm.setTell(cursor.getString(6).trim());
				pm.setLink(cursor.getString(7).trim());
				pm.setEmail(cursor.getString(8).trim());
				pm.setMenu(cursor.getString(9).trim());

				Categ = pm.getCategory();
				Location location2 = new Location("");
				location2.setLatitude(pm.getLatitude());
				location2.setLongitude(pm.getLongtitude());

				double distanceInMeters = location1.distanceTo(location2);
				float distanceInKm = (float) distanceInMeters / 1000;
				pm.setkm(distanceInKm);
				Log.v("AndroidListActivity", "lat : " + location1.getLatitude());
				Log.v("AndroidListActivity",
						"lon : " + location1.getLongitude());
				Log.v("AndroidListActivity", "apostasi : " + pm.getkm());
				Log.v("AndroidListActivity", "Tell : " + pm.getTell());
				Log.v("AndroidListActivity", "Link : " + pm.getLink());
				Log.v("AndroidListActivity", "Email : " + pm.getEmail());
				Log.v("AndroidListActivity", "menu : " + pm.getMenu());
				
				cout++;

				pModel_Alist.add(pm);
				pMtree.insertElement(pm);
			} while (cursor.moveToNext());

		}
		myDbHelper.close();
		// Binding resources Array to ListAdapter
		ListAdapter adapter = new ListAdaptersimple(this,pMtree.inOrderTraversalList(), icon);

		list = (ListView) findViewById(R.id.listviewls);
		// updating listview
		N_Of_Rows = (TextView) findViewById(R.id.NofFildestxt);
		N_Of_Rows.setText("" + pModel_Alist.size());
		list.setAdapter(adapter);

		// gia xroma sti listagetListView().setBackgroundColor(Color.rgb(00, 00,
		// 00));
		// gia eikona sti
		// lista>>>>>//getListView().setBackgroundResource(R.drawable.agios_dimitrios);
		// listening to single list item on click
		list.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, final View view,
					final int position, long id) {
				// selected item
				Log.e("test gia tree: ",""+ ((PlacesModel) pMtree.inOrderTraversalList().get(position)).getName());
				String place = ((TextView) view.findViewById(R.id.label)).getText().toString();
				// Launching new Activity on selecting single List Item
				Intent i = new Intent(getApplicationContext(),SingleListItem.class);
				// sending data to new activity
				i.putExtra("product", place);
				i.putExtra("title", ((PlacesModel) pMtree.inOrderTraversalList().get(position)).getName());//
				i.putExtra("info", ((PlacesModel) pMtree.inOrderTraversalList().get(position)).getDescription());
				i.putExtra("la",""+((PlacesModel) pMtree.inOrderTraversalList().get(position)).getLatitude());
				i.putExtra("lo",""+((PlacesModel) pMtree.inOrderTraversalList().get(position)).getLongtitude());
				i.putExtra("tell", ((PlacesModel) pMtree.inOrderTraversalList().get(position)).getTell());
				i.putExtra("link", ((PlacesModel) pMtree.inOrderTraversalList().get(position)).getLink());
				i.putExtra("email", ((PlacesModel) pMtree.inOrderTraversalList().get(position)).getEmail());
				i.putExtra("menu", ((PlacesModel) pMtree.inOrderTraversalList().get(position)).getMenu());
				startActivity(i);

			}
		});

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
			i1.putExtra("MCategory", Categ);
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