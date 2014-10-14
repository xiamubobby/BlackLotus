package com.xiamubobby.blacklotus;


import info.mtgdb.api.Card;

import org.json.JSONObject;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout;

public class MainActivity extends BlackLotusBaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getLayoutInflater().inflate(R.layout.activity_main, baseRoot);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
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
		JsonObjectRequest getCardAsJson = new JsonObjectRequest
				(JsonObjectRequest.Method.GET, url, null,
					new Response.Listener<JSONObject>() {
			    		@Override
		    			public void onResponse(JSONObject response) {
			    			Card card = new Card(new info.mtgdb.json.JSONObject(response.toString()));
			    			int mId = card.getId();
			    			ImageRequest getCardImage = new ImageRequest(
			    					"http://mtgimage.com/multiverseid/"+mId+".crop.hq.jpg",
			    					new Response.Listener<Bitmap>() {
										@Override
										public void onResponse(Bitmap imgRes) {
											Log.v("imgFin",imgRes.toString());
											untap();
										}
			    					}, 3000, 3000, Config.ARGB_8888, new Response.ErrorListener(){
										@Override
										public void onErrorResponse(VolleyError arg0) {											
										}
			    					});
			    			queue.add(getCardImage);
			    			//untap();
		    			}
					},
					new Response.ErrorListener() {
						@Override
						public void onErrorResponse(VolleyError arg0) {
							//untap();
						}
					});
		
		tap();
		queue.add(getCardAsJson);
	}
}
