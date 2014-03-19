package com.atei.thessvres.services;

import com.atei.thessvres.activities.AndroidListViewActivity;
import com.atei.thessvres.utilities.Utilities;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class LoaderService extends Service {
	GPSTracker gps;

	public LoaderService() {
		// gps = new GPSTracker(LoaderService.this);
		// check if GPS enabled
		// if(gps.canGetLocation()){
		// AndroidListViewActivity.location1.setLatitude(gps.getLatitude()) ;
		// AndroidListViewActivity.location1.setLongitude( gps.getLongitude());
		// \n is for new line
		// Utilities.showCustomToast("Your Location is - \nLat: " +
		// AndroidListViewActivity.location1.getLatitude() + "\nLong: " +
		// AndroidListViewActivity.location1.getLongitude(),Toast.LENGTH_LONG,
		// LoaderService.this);
		// Toast.makeText(getApplicationContext(), "Your Location is - \nLat: "
		// + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
		// }

	}

	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		Log.e("LoadereService", "onDestroy()");
		super.onDestroy();
	}

	@Override
	public void onStart(Intent intent, int startId) {
		// TODO Auto-generated method stub
		Log.e("LoadereService", "onCreate()");
		gps = new GPSTracker(LoaderService.this);
		// check if GPS enabled
		if (gps.canGetLocation()) {
			AndroidListViewActivity.location1.setLatitude(gps.getLatitude());
			AndroidListViewActivity.location1.setLongitude(gps.getLongitude());
			// \n is for new line
			// Utilities.showCustomToast("Your Location is - \nLat: " +
			// AndroidListViewActivity.location1.getLatitude() + "\nLong: " +
			// AndroidListViewActivity.location1.getLongitude(),Toast.LENGTH_LONG,
			// LoaderService.this);
			// Toast.makeText(getApplicationContext(),
			// "Your Location is - \nLat: " + latitude + "\nLong: " + longitude,
			// Toast.LENGTH_LONG).show();
		}

		super.onStart(intent, startId);
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

}
