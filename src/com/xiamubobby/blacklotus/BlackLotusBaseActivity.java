package com.xiamubobby.blacklotus;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class BlackLotusBaseActivity extends Activity {

	protected RequestQueue queue;
	protected FragmentManager fragmentManager;
	protected RelativeLayout baseRoot;
	protected Blocker blocker;
	
	protected boolean initialed;
	protected boolean tapped;
	
	public BlackLotusBaseActivity() {
		initialed = false;
		tapped = false;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_base);
		baseRoot = (RelativeLayout) findViewById(R.id.base_root);
		
		queue = Volley.newRequestQueue(this);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		if (hasFocus && !initialed) {
			getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
			initialed = true;
		}
	}
	
	public void tap() {
		tapped = true;
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);		
		blocker = new Blocker(this, Blocker.TYPE_TAP);
		blocker.show();
		tapAnimate();
	}
	
	public void tapAnimate() { }
	
	public void untap() {
		tapped = false;
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
		blocker.dismiss();
		untapAnimate();
	}
	
	public void untapAnimate() { }
	
	protected class Blocker extends Dialog {
		//int mType;
		RelativeLayout blockRoot;
		public final static int TYPE_TAP = 1;
		Blocker(Context context, int type) {
			super(context);
			//mType = type;
			init(type);
		}
		
		void init(int type) {
			setCancelable(false);
			requestWindowFeature(Window.FEATURE_NO_TITLE);		
			switch (type) {
				case TYPE_TAP:
					setContentView(R.layout.blocker_tap);
					break;
				default:
					break;
			}
			//blockRoot = (RelativeLayout) findViewById(R.id.blocker_root);
		}
	}

}
