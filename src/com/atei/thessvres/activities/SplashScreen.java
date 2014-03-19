package com.atei.thessvres.activities;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;

import com.atei.thessvres.MyLocation;
import com.atei.thessvres.PlacesModel;
import com.atei.thessvres.R;
import com.atei.thessvres.MyLocation.LocationResult;
import com.atei.thessvres.R.layout;
import com.atei.thessvres.JSON.getdataJSON;
import com.atei.thessvres.services.GPSTracker;
import com.atei.thessvres.services.LoaderService;
import com.atei.thessvres.utilities.Utilities;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

public class SplashScreen extends Activity {

	// Splash screen timer
	private static int SPLASH_TIME_OUT = 7000;
	GPSTracker gps;
	SQLiteDatabase db;
	Intent serviceIntent;
	PlacesModel pm;
	LocationManager locationMgr;
	private ProgressDialog pDialog;
	private boolean kkk = true;

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		if (kkk) {
			Log.e("SplashActivity", "onPause()" + kkk);
			finish();

		}

		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		Log.e("SplashActivity", "onResume()" + kkk);
		kkk = true;
		super.onResume();
	}

	@Override
	protected void onPostResume() {
		// TODO Auto-generated method stub
		super.onPostResume();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		Log.e("SplashActivity", "onCreate()" + kkk);
		locationMgr = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);

		serviceIntent = new Intent(SplashScreen.this, LoaderService.class);
		startService(serviceIntent);
		// ArrayList<HashMap<String, String>> mylist = getdataJSON.getdata();

		// openDatapase();
		// for(int i=0;i<mylist.size();i++){
		/*
		 * Log.e("id",mylist.get(i).get("id"));
		 * Log.e("fullname",mylist.get(i).get("fullname"));
		 * Log.e("Category",mylist.get(i).get("Category"));
		 * Log.e("Description",mylist.get(i).get("Description"));
		 * Log.e("Latitude",mylist.get(i).get("Latitude"));
		 * Log.e("Longtitude",mylist.get(i).get("Longtitude"));
		 * Log.e("Tell",mylist.get(i).get("Tell"));
		 * Log.e("Link",mylist.get(i).get("Link"));
		 * Log.e("email",mylist.get(i).get("email"));
		 */

		// }

		/*
		 * gps = new GPSTracker(SplashScreen.this); // check if GPS enabled
		 * if(gps.canGetLocation()){
		 * 
		 * AndroidListViewActivity.location1.setLatitude(gps.getLatitude()) ;
		 * AndroidListViewActivity.location1.setLongitude( gps.getLongitude());
		 * 
		 * // \n is for new line
		 * Utilities.showCustomToast("Your Location is - \nLat: " +
		 * AndroidListViewActivity.location1.getLatitude() + "\nLong: " +
		 * AndroidListViewActivity.location1.getLongitude(),Toast.LENGTH_LONG,
		 * SplashScreen.this); //Toast.makeText(getApplicationContext(),
		 * "Your Location is - \nLat: " + latitude + "\nLong: " + longitude,
		 * Toast.LENGTH_LONG).show(); }//else{ // can't get location // GPS or
		 * Network is not enabled // Ask user to enable GPS/network in settings
		 * // gps.showSettingsAlert(); //}
		 */

		/*
		 * LocationResult locationResult = new LocationResult(){
		 * 
		 * @Override public void gotLocation(Location location){ //Got the
		 * location! if(location!=null){
		 * 
		 * AndroidListViewActivity.location1.setLatitude(location.getLatitude());
		 * AndroidListViewActivity.location1.setLongitude(
		 * location.getLongitude());
		 * Log.e("SplashActivity","lat : "+AndroidListViewActivity
		 * .location1.getLatitude()); Log.e("SplashActivity","lon : "+
		 * AndroidListViewActivity.location1.getLongitude());
		 * Log.e("",location.getLatitude() +"  "+location.getProvider()+" "+
		 * location.getLongitude() ); }
		 * 
		 * 
		 * 
		 * } };
		 * 
		 * 
		 * MyLocation myLocation = new MyLocation();
		 * myLocation.getLocation(this, locationResult);
		 */

		if (isNetworkConnected()) {

			new authentication().execute();

		} else {
			kkk = false;
			Utilities.showCustomToast("Δέν υπάρχει σύνδεδεση στο Internet",
					Toast.LENGTH_LONG, SplashScreen.this);

			final Handler handler = new Handler();
			handler.postDelayed(new Runnable() {
				@Override
				public void run() {
					startActivity(new Intent(
							android.provider.Settings.ACTION_WIRELESS_SETTINGS));
				}
			}, 1500);

		}
		new Handler().postDelayed(new Runnable() {

			/*
			 * Showing splash screen with a timer. This will be useful when you
			 * want to show case your app logo / company
			 */

			@Override
			public void run() {
				// This method will be executed once the timer is over
				// Start your app main activity
				Intent i = new Intent(SplashScreen.this, MainActivity.class);
				startActivity(i);

				// close this activity
				finish();
			}
		}, SPLASH_TIME_OUT);
	}

	private boolean isNetworkConnected() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		if (ni == null) {
			// There are no active networks.
			return false;
		} else
			return true;
	}

	public void openDatapase() {
		Log.e("SplashActivity ", "openDatapase()");

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

	class authentication extends AsyncTask<String, String, String> {

		/**
		 * Before starting background thread Show Progress Dialog
		 * */
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			Log.e("frdsdfsdf","risi kompsdkfsldflsdjkflsdkjf");
			pDialog = new ProgressDialog(SplashScreen.this);
			pDialog.setMessage("Αυθεντικοποίηση...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		/**
		 * Getting product details in background thread
		 * */
		protected String doInBackground(String... params) {

			// updating UI from Background Thread
			runOnUiThread(new Runnable() {
				public void run() {
					SplashScreen.this.deleteDatabase("mydb");
					ArrayList<HashMap<String, String>> mylist = getdataJSON
							.getdata();

					openDatapase();
					for (int i = 0; i < mylist.size(); i++) {
						ContentValues cv = new ContentValues();
						Log.v("ID", mylist.get(i).get("id"));
						cv.put("id", mylist.get(i).get("id"));
						Log.v("Name", mylist.get(i).get("Name"));
						cv.put("Name", mylist.get(i).get("Name"));
						Log.v("Category", mylist.get(i).get("Category"));
						cv.put("Category", mylist.get(i).get("Category"));
						// Log.e("Description",mylist.get(i).get("Description"));
						cv.put("Description", mylist.get(i).get("Description"));
						Log.v("Latitude", mylist.get(i).get("Latitude"));
						cv.put("Latitude", mylist.get(i).get("Latitude"));
						Log.v("Longtitude", mylist.get(i).get("Longtitude"));
						cv.put("Longtitude", mylist.get(i).get("Longtitude"));
						Log.v("Tell", mylist.get(i).get("Tell"));
						cv.put("Tell", mylist.get(i).get("Tell"));
						Log.v("Link", mylist.get(i).get("Link"));
						cv.put("Link", mylist.get(i).get("Link"));
						Log.v("email", mylist.get(i).get("email"));
						cv.put("email", mylist.get(i).get("email"));
						Log.v("menu", mylist.get(i).get("menu"));
						cv.put("menu", mylist.get(i).get("menu"));
						Log.v("Ch", mylist.get(i).get("Ch"));
						cv.put("Ch", mylist.get(i).get("Ch"));

						db.insert("Places", null, cv);

					}
					Log.e("SplashActivity", "db ready");
					db.close();
				}
			});
			return null;
		}

		/**
		 * After completing background task Dismiss the progress dialog
		 * **/
		protected void onPostExecute(String file_url) {
			// dismiss the dialog once got all details
			pDialog.dismiss();
			Utilities.showCustomToast("Data Loaded", Toast.LENGTH_LONG,
					SplashScreen.this);

		}
	}

}
