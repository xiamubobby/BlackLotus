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
import android.widget.ViewAnimator;

public class MainActivity extends BlackLotusBaseActivity {
	
	Card inspiredCard;
	Bitmap inspiredScan;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getLayoutInflater().inflate(R.layout.activity_main, baseRootRela);
		//getLayoutInflater().inflate(R.layout.welcome, animatorRoot);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		//return super.onOptionsItemSelected(item);
		return false;
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
		if (hasFocus && !initialed) {
			JsonObjectRequest getCardAsJson = new JsonObjectRequest
				(JsonObjectRequest.Method.GET, "http://api.mtgdb.info/cards/random", null,
				new Response.Listener<JSONObject>() {
		    		@Override
	    			public void onResponse(JSONObject response) {
		    			inspiredCard = new Card(new info.mtgdb.json.JSONObject(response.toString()));
		    			int mId = inspiredCard.getId();
		    			ImageRequest getCardImage = new ImageRequest(
	    					"http://mtgimage.com/multiverseid/"+mId+".crop.hq.jpg",
	    					new Response.Listener<Bitmap>() {
								@Override
								public void onResponse(Bitmap imgRes) {
									inspiredScan = imgRes;
									untap();
									initialed = true;
									materializeMain();									
								}
	    					}, 3000, 3000, Config.ARGB_8888,
	    					new Response.ErrorListener(){
								@Override
								public void onErrorResponse(VolleyError arg0) {
									untap();
									initialed = true;
								}
	    					});
		    			queue.add(getCardImage);
	    			}
				},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError arg0) {
						untap();
						initialed = true;
					}
				});
			tap();
			queue.add(getCardAsJson);
		}
	}
	
	public void getARandom(View view) {
		
	}
	
	public void materializeMain() {
		
	}
	
}
