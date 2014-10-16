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
import android.widget.ViewAnimator;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class BlackLotusBaseActivity extends Activity {

	protected RequestQueue queue;
	protected FragmentManager fragmentManager;
	protected BlackLotusFrame baseRootBLFrame;
	protected RelativeLayout baseRootRela;
	
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
		baseRootBLFrame = (BlackLotusFrame) findViewById(R.id.base_root_animator);
		baseRootRela = (RelativeLayout) findViewById(R.id.base_root_rela);
		queue = Volley.newRequestQueue(this);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
	}
	
	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		super.onWindowFocusChanged(hasFocus);
	}
	
	public void tap() {
		tapped = true;
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);		
		baseRootBLFrame.setSecondLayout(R.layout.blocker_tap);
		baseRootBLFrame.switchToSecond();
	}
	
	public void tapAnimate() { }
	
	public void untap() {
		tapped = false;
		getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
		baseRootBLFrame.switchToMain();
	}
	
	public void untapAnimate() { }

}
