package com.atei.thessvres.JSON;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class getdataJSON {
	private static String url = "http://thessvres.netii.net/get_all.php";
	static JSONArray places = null;

	public static ArrayList<HashMap<String, String>> getdata() {
		ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();

		JSONParser jParser = new JSONParser();

		JSONObject json = jParser.getJSONFromUrl(url);
		Log.e("getdataJSON", "" + json.toString());
		try {
			places = json.getJSONArray("places");
			for (int i = 0; i < places.length(); i++) {
				JSONObject c = places.getJSONObject(i);
				String id = c.getString("id");
				String name = c.getString("name");
				String category = c.getString("category");
				String description = c.getString("description");
				String latitude = c.getString("latitude");
				String longtitude = c.getString("longtitude");
				String tell = c.getString("tell");
				String link = c.getString("link");
				String email = c.getString("email");
				String menu = c.getString("menu");
				String Ch = c.getString("Ch");
				String Name_en = c.getString("Name_en");
				String Description_en = c.getString("Description_en");
				String Ch_en = c.getString("Ch_en");

				HashMap<String, String> map = new HashMap<String, String>();

				map.put("id", id);
				map.put("Name", name);
				map.put("Category", category);
				map.put("Description", description);
				map.put("Latitude", latitude);
				map.put("Longtitude", longtitude);
				map.put("Tell", tell);
				map.put("Link", link);
				map.put("email", email);
				map.put("menu", menu);
				map.put("Ch", Ch);
				map.put("Name_en", Name_en);
				map.put("Description_en", Description_en);
				map.put("Ch_en", Ch_en);

				mylist.add(map);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return mylist;
	}

}