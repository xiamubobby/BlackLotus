package com.xiamubobby.blacklotus;


import org.json.JSONObject;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends BlackLotusBaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void getARandom(View view) {
		
		String url = "http://api.mtgdb.info/cards/random";
		url = "https://api.deckbrew.com/mtg/cards/?multiverseid=178023";
		JsonObjectRequest getCardAsJson = new JsonObjectRequest
				(JsonObjectRequest.Method.GET, url, null,
					new Response.Listener<JSONObject>() {
			    		@Override
		    			public void onResponse(JSONObject response) {
			    			untap();
		    			}
					},
					new Response.ErrorListener() {
						@Override
						public void onErrorResponse(VolleyError arg0) {
							untap();
						}
					});
		
		tap();
		queue.add(getCardAsJson);
	}
}
