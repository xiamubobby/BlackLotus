package com.xiamubobby.blacklotus;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class Mirrordin extends FrameLayout {
	
	final int MAIN_TIER = 0;
	final int ENCHANT_TIER = 1;
	final int COVER_TIER = 2;
	
	View mainTier;
	View enchanteTier;
	View coverTier;
	
	boolean enchanting = false;
	
	public Mirrordin(Context context) {
		super(context);
	}

	public Mirrordin(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public Mirrordin(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	void init() {
		
	}
	
	public void setMain(View view) {
		mainTier = view;
		removeViewAt(MAIN_TIER);
		addView(mainTier, MAIN_TIER, new FrameLayout.LayoutParams(getWidth(), getHeight(), Gravity.CENTER));
	}
	
	public void replaceTier(View child, int tier) {
		if ((tier > 3) || (tier < 0)) {
			return;
		}
		else {
			removeViewAt(tier);
			child.setVisibility(View.GONE);
			addView(child, tier, new FrameLayout.LayoutParams(getWidth(), getHeight(), Gravity.CENTER));
		}
	}
	
	public void enchant(View view) {
		enchanting = true;
		addView(view);
		((BaseMirari) view).initial();
	}
	
	public void onEnchantGrowed(int idx) {
		if (idx == getChildCount()) {
			enchanting = false;
		}
		if (idx > 1) {
			removeViewAt(idx-1);
		}
	}

}
