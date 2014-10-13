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
		int mType;
		Bitmap mScan;
		int mScanRes;
		String mText;
		RelativeLayout blockRoot;
		TextView mTitleView;
		ImageView mScanView;
		TextView mTextView;
		TextView mTypeView;
		TextView mFlavorView;
		public final static int TYPE_TAP = 1;
		Blocker(Context context, Bitmap scan, String text) {
			super(context);
			mScan = scan;
			mText = text;
			init();
		}
		Blocker(Context context, int scanRes, String text) {
			super(context);
			mScanRes = scanRes;
			mText = text;
			init();
		}
		Blocker(Context context, int type) {
			super(context);
			mType = type;
			init();
		}
		
		void init() {
			setCancelable(false);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			
			blockRoot = (RelativeLayout) LayoutInflater.from(getContext()).inflate(R.layout.base_blocker, null);
			mTitleView = (TextView) blockRoot.findViewById(R.id.blocker_title);
			mTitleView.setTypeface(null, Typeface.BOLD);
			mScanView = (ImageView) blockRoot.findViewById(R.id.blocker_scan);
			mTextView = (TextView) blockRoot.findViewById(R.id.blocker_text);
			mTypeView = (TextView) blockRoot.findViewById(R.id.blocker_type);
			mFlavorView = (TextView) blockRoot.findViewById(R.id.blocker_flavor);
			mFlavorView.setTypeface(null, Typeface.ITALIC);
			switch (mType) {
				case TYPE_TAP:
					mTitleView.setText("Internet Claustrophobia");
					mScan = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.dialogtap);
					//LinearLayout.LayoutParams relaParams = new LinearLayout.LayoutParams(mScan.getWidth(), mScan.getHeight(), 6);
					//mScanView.setLayoutParams(relaParams);
					mScanView.setImageBitmap(mScan);
					mTypeView.setText("Enchantment - Aura");
					mTextView.setText("Enchanted plainwalker doesn't untap until the data is loaded.");
					mFlavorView.setText("Oh! You're now very tapped!");
					break;
				default:
					if (mScan != null) {
						mScanView.setImageBitmap(mScan);
					}
					else {
						mScanView.setImageResource(mScanRes);
					}
					mTextView.setText(mText);
			}
			getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.blocker_tap_background)));
			setContentView(blockRoot);
			getWindow().setLayout((int) (baseRoot.getWidth() * 0.6), (int) (baseRoot.getHeight() * 0.6));
		}
	}

}
