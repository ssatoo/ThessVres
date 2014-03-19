package com.atei.thessvres.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.atei.thessvres.R;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Utilities {
	public static final String NETWORK_OPERATOR = "20205";
	public static final int TAB_NOTIFICATION_IN = 0;
	public static final int TAB_NOTIFICATION_OUT = 1;
	public static String versionName;
	public static int versionCode;
	public static final String CUSTOM_INTENT_START_APP = "com.msensis.broadcast.3ghotspot.start.fromsplash";

	public static Typeface sansTypeface, sansBoldTypeface, sansItalicTypeface;

	public static Typeface getSansTypeface(Context ctx) {
		if (sansTypeface == null) {
			sansTypeface = Typeface.createFromAsset(ctx.getAssets(),
					"fonts/NotoSans-Regular.ttf");
		}
		return sansTypeface;
	}

	public static Typeface getSansBoldTypeface(Context ctx) {
		if (sansBoldTypeface == null) {
			sansBoldTypeface = Typeface.createFromAsset(ctx.getAssets(),
					"fonts/NotoSans-Bold.ttf");
		}
		return sansBoldTypeface;
	}

	public static Typeface getSanItalicTypeface(Context ctx) {
		if (sansItalicTypeface == null) {
			sansItalicTypeface = Typeface.createFromAsset(ctx.getAssets(),
					"fonts/NotoSans-Italic.ttf");
		}
		return sansItalicTypeface;
	}

	public static void showCustomToast(String message, int duration, Context ctx) {
		LayoutInflater inflater = (LayoutInflater) ctx
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		// View layout = inflater.inflate(R.layout.toast_custom, (ViewGroup)
		// findViewById(R.id.toast_layout_root));
		View layout = inflater.inflate(R.layout.toast_custom, null);

		TextView text = (TextView) layout.findViewById(R.id.text);
		text.setTypeface(Utilities.getSansTypeface(ctx));
		text.setText(message);

		Toast toast = new Toast(ctx);
		// toast.setGravity(Gravity.CENTER_HORIZONTAL, 0, 0);
		toast.setDuration(duration);
		toast.setView(layout);
		toast.show();
	}

}
