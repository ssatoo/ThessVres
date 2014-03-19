package com.atei.thessvres.activities;

import com.atei.thessvres.R;

import com.atei.thessvres.utilities.Utilities;

import android.app.Activity;
import android.app.AlertDialog;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SingleListItem extends ActionBarActivity {

	String plat, plon, link, tell, email, place, menu;
	double mlat, mlon, tolat, tolon;
	Location lastLoc;
	ImageButton mail, call, site, menubtn;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		this.setContentView(R.layout.single_list_item_view);

		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		TextView txtlink = (TextView) findViewById(R.id._link);
		TextView txtemail = (TextView) findViewById(R.id._email);
		TextView txttell = (TextView) findViewById(R.id._tell);
		TextView txtProduct = (TextView) findViewById(R.id.Placelabel);
		TextView txtmenu = (TextView) findViewById(R.id._menu);
		Button go_to = (Button) findViewById(R.id.xarths);

		site = (ImageButton) findViewById(R.id.tolink);
		LinearLayout llbl = (LinearLayout) findViewById(R.id.linkln);
		LinearLayout tellbl = (LinearLayout) findViewById(R.id.tellln);
		LinearLayout menubl = (LinearLayout) findViewById(R.id.menuln);
		LinearLayout emailbl = (LinearLayout) findViewById(R.id.emailln);
		Intent i = getIntent();
		// getting attached intent data
		place = i.getStringExtra("title");
		// displaying selected product name
		txtProduct.setTextColor(0xffffffff);
		tell = i.getStringExtra("tell");
		link = i.getStringExtra("link");
		email = i.getStringExtra("email");
		menu = i.getStringExtra("menu");
		String info = i.getStringExtra("info");

		txtProduct.setText(place + "\n\n\n" + info);
		txtlink.setText(link);
		txtemail.setText(email);
		txtmenu.setText(menu);
		txttell.setText(tell);
		Log.e("slitem", "link: " + link);
		Log.e("slitem", "menu: " + menu);
		Log.e("slitem", "phone: " + tell);
		Log.e("slitem", "email: " + email);
		if (link.equals("-")) {
			Log.e("", "if " + link);
			llbl.setVisibility(View.GONE);
		} else {
			Log.e("", "else " + link);
			txtlink.setText(link);
		}
		if (tell.equals("-")) {
			tellbl.setVisibility(View.GONE);
			// call.setVisibility(View.GONE);
		} else {
			txttell.setText(tell);
		}
		if (email.equals("-")) {
			emailbl.setVisibility(View.GONE);
		} else {
			txtemail.setText(email);
		}
		if (menu.equals("-")) {
			menubl.setVisibility(View.GONE);
			// menubtn.setVisibility(View.GONE);
		} else {
			txtmenu.setText(menu);
		}
		plat = i.getStringExtra("la");
		tolat = Double.parseDouble(plat);
		plon = i.getStringExtra("lo");
		tolon = Double.parseDouble(plon);
		mail = (ImageButton) findViewById(R.id.tomail);
		mail.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.e("SingleListItem", "to mail");
				if (email.equals("-")) {
					Log.e("SingleListItem", "no email!!");
					Utilities.showCustomToast("No Email.", Toast.LENGTH_LONG,
							SingleListItem.this);
				} else {
					Log.e("SingleListItem", "sending mail");
					AlertDialog.Builder alertDialog = new AlertDialog.Builder(
							SingleListItem.this);
					alertDialog.setTitle("MAIL");
					alertDialog.setMessage(""
							+ getResources().getString(R.string.tomail));

					alertDialog.setPositiveButton(""
							+ getResources().getString(R.string.yes),
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									// here you can add functions
									Intent emailIntent = new Intent(
											Intent.ACTION_SENDTO, Uri
													.fromParts("mailto", email,
															null));
									startActivity(Intent.createChooser(
											emailIntent, "Send email..."));
								}
							});
					alertDialog.setNegativeButton(""
							+ getResources().getString(R.string.no),
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									// here you can add functions
									dialog.cancel();
								}
							});
					alertDialog.show();
				}
			}
		});
		call = (ImageButton) findViewById(R.id.tocall);
		call.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.e("SingleListItem", "to call");
				if (tell.equals("-")) {
					Log.e("SingleListItem", "no tell!!");
					Utilities.showCustomToast("No phone N.", Toast.LENGTH_LONG,
							SingleListItem.this);
				} else {
					Log.e("SingleListItem", "kanw to call");
					AlertDialog.Builder alertDialog = new AlertDialog.Builder(
							SingleListItem.this);
					alertDialog.setTitle("CALL");
					alertDialog.setMessage(""
							+ getResources().getString(R.string.tocall));

					alertDialog.setPositiveButton(""
							+ getResources().getString(R.string.yes),
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									// here you can add functions
									Intent callIntent = new Intent(
											Intent.ACTION_CALL);
									// callIntent.setData(Uri.parse("tel:"+123456787));
									callIntent.setData(Uri.parse("tel:" + tell));
									startActivity(callIntent);
								}
							});
					alertDialog.setNegativeButton(""
							+ getResources().getString(R.string.no),
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									// here you can add functions
									dialog.cancel();
								}
							});
					alertDialog.show();
				}
			}
		});
		menubtn = (ImageButton) findViewById(R.id.tomenu);
		menubtn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.e("SingleListItem", "to menu");

				if (link.equals("-")) {
					Log.e("SingleListItem", "no menu!!");
					Utilities.showCustomToast("No menu.", Toast.LENGTH_LONG,
							SingleListItem.this);
				} else {
					Log.e("SingleListItem", "open link");
					AlertDialog.Builder alertDialog = new AlertDialog.Builder(
							SingleListItem.this);
					alertDialog.setTitle("MENU");
					alertDialog.setMessage(""
							+ getResources().getString(R.string.menustring));

					alertDialog.setPositiveButton(""
							+ getResources().getString(R.string.yes),
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									// here you can add functions
									String url = menu;
									Intent i = new Intent(Intent.ACTION_VIEW);
									i.setData(Uri.parse(url));
									startActivity(i);
								}
							});
					alertDialog.setNegativeButton(""
							+ getResources().getString(R.string.no),
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									// here you can add functions
									dialog.cancel();
								}
							});
					alertDialog.show();
				}
			}
		});

		site.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Log.e("SingleListItem", "to link");

				if (link.equals("-")) {
					Log.e("SingleListItem", "no link!!");
					Utilities.showCustomToast("No link.", Toast.LENGTH_LONG,
							SingleListItem.this);
				} else {
					Log.e("SingleListItem", "open link");
					AlertDialog.Builder alertDialog = new AlertDialog.Builder(
							SingleListItem.this);
					alertDialog.setTitle("LINK");
					alertDialog.setMessage(""
							+ getResources().getString(R.string.tosite));

					alertDialog.setPositiveButton(""
							+ getResources().getString(R.string.yes),
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									// here you can add functions
									String url = link;
									Intent i = new Intent(Intent.ACTION_VIEW);
									i.setData(Uri.parse(url));
									startActivity(i);
								}
							});
					alertDialog.setNegativeButton(""
							+ getResources().getString(R.string.no),
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									// here you can add functions
									dialog.cancel();
								}
							});
					alertDialog.show();
				}
			}
		});
		go_to.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Log.e("TEST",
						"" + AndroidListViewActivity.location1.getLatitude());
				Log.e("TEST",
						"" + AndroidListViewActivity.location1.getLongitude());
				Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
						Uri.parse("http://maps.google.com/maps?saddr="
								+ AndroidListViewActivity.location1
										.getLatitude()
								+ ","
								+ AndroidListViewActivity.location1
										.getLongitude() + "&daddr=" + tolat
								+ "," + tolon));
				startActivity(intent);
			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle presses on the action bar items

		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			return true;

		case R.id.search:
			Intent i = new Intent(getApplicationContext(), SearchActivity.class);
			startActivity(i);
			return true;
		case R.id.action_map:
			// openSettings();
			Intent i1 = new Intent(getApplicationContext(),
					MapViewActivity.class);
			String mapmode = "singleformap";
			i1.putExtra("mapflag", mapmode);
			i1.putExtra("pName", place);

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