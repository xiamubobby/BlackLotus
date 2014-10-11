package com.xiamubobby.blacklotus;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public class BlackLotusBaseActivity extends Activity {

	protected RequestQueue queue;
	protected FragmentManager fragmentManager;
	protected RelativeLayout baseRoot;
	RelativeLayout blocker;
	
	protected boolean tapped;
	
	public BlackLotusBaseActivity() {
		// TODO Auto-generated constructor stub
		tapped = false;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);
		baseRoot = (RelativeLayout) findViewById(R.id.base_root);
		
		queue = Volley.newRequestQueue(this);
		
		fragmentManager = getFragmentManager();
	}
	
	public void tap() {
		Log.v("test","You are and will be tapped from this day, to the end of my network loading work.");
		tapped = true;

		getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
		
		RelativeLayout.LayoutParams relaParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
		relaParams.addRule(RelativeLayout.ALIGN_PARENT_START);
		blocker = (RelativeLayout) getLayoutInflater().inflate(R.layout.base_blocker, null);
		blocker.setBackgroundResource(R.color.blocker_overlay);
		
		baseRoot.addView(blocker, relaParams);
		
		tapAnimate();
	}
	
	public void tapAnimate() { }
	
	public void untap() {
		Log.v("test","Now you're free to go, enjoy the manas and the spells.");
		tapped = false;

		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
		
		baseRoot.removeView(blocker);
		
		untapAnimate();
	}
	
	public void untapAnimate() { }

}
