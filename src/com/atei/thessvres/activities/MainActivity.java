package com.atei.thessvres.activities;

import java.util.Locale;

import com.atei.thessvres.R;
import com.atei.thessvres.services.GPSTracker;
import com.atei.thessvres.services.LoaderService;
import com.atei.thessvres.utilities.Utilities;

import android.content.Context;
import android.content.Intent;
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
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity implements OnClickListener {

	protected static final String LOG_TAG = null;
	ImageView Mouseia;
	ImageView Aksiotheata;
	ImageView Ekklhsies;
	ImageView Estiatoria;
	ImageView brcl;
	ImageView hospital;
	ImageView police;
	ImageView events;
	ImageView presvies;
	static boolean gpsIsShowed=true;
	String icon;
	Context context;
	LocationManager locationMgr;
	GPSTracker gps;
	Intent serviceIntent;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ActionBar actionBar = getSupportActionBar();

		locationMgr = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);
		gps = new GPSTracker(MainActivity.this);

		Log.e("MainActivity",
				"Gps is : "
						+ locationMgr
								.isProviderEnabled(LocationManager.GPS_PROVIDER));

		if (locationMgr.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			Utilities.showCustomToast("GPS On.", Toast.LENGTH_LONG,
					MainActivity.this);
			gpsIsShowed=false;
		/*	Utilities.showCustomToast(
					"Your Location is - \nLat: "
							+ AndroidListViewActivity.location1.getLatitude()
							+ "\nLong: "
							+ AndroidListViewActivity.location1.getLongitude(),
					Toast.LENGTH_LONG, MainActivity.this);*/
		} else {
			if (gpsIsShowed){Utilities.showCustomToast(
					"" + getResources().getString(R.string.gps),
					Toast.LENGTH_LONG, MainActivity.this);

			gps.showSettingsAlert();
			gpsIsShowed=false;
			}
			
			// serviceIntent = new Intent(MainActivity.this,
			// LoaderService.class);

			// startService(serviceIntent);

			// Log.e("MainActivity","Run service");

		}

		setContentView(R.layout.activity_main);
		Estiatoria = (ImageView) findViewById(R.id.Estiator);
		Mouseia = (ImageView) findViewById(R.id.Mouseio);
		Aksiotheata = (ImageView) findViewById(R.id.Aksioth);
		Ekklhsies = (ImageView) findViewById(R.id.Ekklhsies);
		brcl = (ImageView) findViewById(R.id.icon5);
		hospital = (ImageView) findViewById(R.id.hospital_);
		police = (ImageView) findViewById(R.id.police_);
		presvies = (ImageView) findViewById(R.id.presvies_);
		events = (ImageView) findViewById(R.id.event_);

		

		Mouseia.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String Category = "Μουσείο";
				icon = "" + R.drawable.list_m_icon;
				Intent i = new Intent(getApplicationContext(),
						AndroidListViewActivity.class);
				i.putExtra("MCategory", Category);
				String sh = "2";
				i.putExtra("flag", sh);
				i.putExtra("icon", icon);
				startActivity(i);

			}
		});

		Aksiotheata.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				String Category = "Αξιοθέατο";
				icon = "" + R.drawable.list_ar_icon;
				Intent i = new Intent(getApplicationContext(),
						AndroidListViewActivity.class);
				i.putExtra("MCategory", Category);
				String sh = "2";
				i.putExtra("flag", sh);
				i.putExtra("icon", icon);
				Log.e("MainActivity ", "flag: " + sh + "CAtegory/icon"
						+ Category + "/" + icon);
				startActivity(i);

			}
		});

		Ekklhsies.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String Category = "Εκκλησία";
				icon = "" + R.drawable.list_ch_icon;
				Intent i = new Intent(getApplicationContext(),
						EListViewActivity.class);
				i.putExtra("MCategory", Category);
				String sh = "2";
				i.putExtra("flag", sh);
				i.putExtra("icon", icon);
				Log.e("MainActivity", "flag: " + sh + "CAtegory/icon"
						+ Category + "/" + icon);
				startActivity(i);

			}

		});

		Estiatoria.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String Category = "Εστιατόριο";
				icon = "" + R.drawable.list_rest_icon;
				Intent i = new Intent(getApplicationContext(),
						EListViewActivity.class);
				i.putExtra("MCategory", Category);
				String sh = "2";
				i.putExtra("flag", sh);
				i.putExtra("icon", icon);
				Log.e("MainActivity", "flag: " + sh + "CAtegory/icon"
						+ Category + "/" + icon);
				startActivity(i);

			}

		});

		hospital.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String Category = "Νοσοκομείo";
				icon = "" + R.drawable.list_hos_icon;
				Intent i = new Intent(getApplicationContext(),
						AndroidListViewActivity.class);
				i.putExtra("MCategory", Category);
				String sh = "2";
				i.putExtra("flag", sh);
				i.putExtra("icon", icon);
				Log.e("MainActivity", "flag: " + sh + "CAtegory/icon"
						+ Category + "/" + icon);
				startActivity(i);

			}

		});

		police.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String Category = "Αστυνομία";
				icon = "" + R.drawable.list_polic_icon;
				Intent i = new Intent(getApplicationContext(),
						AndroidListViewActivity.class);
				i.putExtra("MCategory", Category);
				String sh = "2";
				i.putExtra("flag", sh);
				i.putExtra("icon", icon);
				Log.e("MainActivity", "flag: " + sh + "Category/icon"
						+ Category + "/" + icon);
				startActivity(i);

			}

		});

		presvies.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String Category = "Προξενείo";
				icon = "" + R.drawable.list_presvia_icon;
				Intent i = new Intent(getApplicationContext(),
						AndroidListViewActivity.class);
				i.putExtra("MCategory", Category);
				String sh = "2";
				i.putExtra("flag", sh);
				i.putExtra("icon", icon);
				Log.e("MainActivity", "flag: " + sh + "CAtegory/icon"
						+ Category + "/" + icon);
				startActivity(i);

			}
		});
		brcl.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String Category = "bar-club";
				// icon=""+R.drawable.list;
				Intent i = new Intent(getApplicationContext(),
						EListViewActivity.class);
				i.putExtra("MCategory", Category);
				String sh = "2";
				i.putExtra("flag", sh);
				i.putExtra("icon", icon);
				Log.e("MainActivity", "flag: " + sh + "CAtegory/icon"
						+ Category + "/" + icon);
				startActivity(i);

			}
		});

		events.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String Category = "event";
				icon = "" + R.drawable.list_events_icon;
				Intent i = new Intent(getApplicationContext(),
						AndroidListViewActivity.class);
				i.putExtra("MCategory", Category);
				String sh = "2";
				i.putExtra("flag", sh);
				i.putExtra("icon", icon);
				Log.e("MainActivity", "flag: " + sh + "CAtegory/icon"
						+ Category + "/" + icon);
				startActivity(i);

			}
		});
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		Log.e("MainActivity", "onDestroy");
		//gpsIsShowed=true;
		stopService(new Intent(MainActivity.this, LoaderService.class));
		super.onDestroy();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		Log.e("MainActivity", "onResume");
		if (locationMgr.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
			Log.e("MainActivity",
					"onResume :"
							+ locationMgr
									.isProviderEnabled(LocationManager.GPS_PROVIDER)
							+ " start service...");
			serviceIntent = new Intent(MainActivity.this, LoaderService.class);
			startService(serviceIntent);
			// Utilities.showCustomToast("Your Location is - \nLat: " +
			// AndroidListViewActivity.location1.getLatitude() + "\nLong: " +
			// AndroidListViewActivity.location1.getLongitude(),Toast.LENGTH_LONG,
			// MainActivity.this);
			Log.e("MainActivity", "Run service");
		}
		super.onResume();
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		Log.e("MainActivity", "onStop");
		// stopService(new Intent(MainActivity.this,LoaderService.class));
		super.onStop();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items

		switch (item.getItemId()) {

		case R.id.search:
			Intent i = new Intent(getApplicationContext(), SearchActivity.class);
			startActivity(i);
			return true;
		case R.id.action_map:
			// openSettings();
			Intent i1 = new Intent(getApplicationContext(),
					MapViewActivity.class);
			String mapmode = "mapviewmode";
			i1.putExtra("mapflag", mapmode);
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
	}

}
