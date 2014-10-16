package com.xiamubobby.blacklotus;

import android.animation.AnimatorInflater;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

public class BlackLotusFrame extends FrameLayout {
	
	Context mContext;
	LayoutInflater layoutInflater;
	View secondLayout;
	View mainLayout;
	AnimatorSet secondInAnim;
	AnimatorSet secondOutAnim;
	AnimatorSet mainInAnim;
	AnimatorSet mainOutAnim;
	boolean inMain = true;

	public BlackLotusFrame(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public BlackLotusFrame(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		init(context);
	}

	public BlackLotusFrame(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		init(context);
	}
	
	private void init(Context context) {
		mContext = context;
		layoutInflater = LayoutInflater.from(mContext);
		mainInAnim = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.base_view_switch_main_in);
		mainInAnim.addListener(new AnimatorListenerAdapter(){
			public void onAnimationEnd(Animator animation) {
				inMain = true;
			}
		});
		mainOutAnim = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.base_view_switch_main_out);
		secondInAnim = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.base_view_switch_secondary_in);
		secondInAnim.addListener(new AnimatorListenerAdapter(){
			public void onAnimationStart(Animator animation) {
				secondLayout.setVisibility(VISIBLE);
			}
		});
		secondInAnim.addListener(new AnimatorListenerAdapter(){
			public void onAnimationEnd(Animator animation) {
				inMain = false;
			}
		});
		secondOutAnim = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, R.animator.base_view_switch_secondary_out);
		secondOutAnim.addListener(new AnimatorListenerAdapter(){
			public void onAnimationEnd(Animator animation) {
				secondLayout = null;
			}
		});
	}
	
	public void setSecondLayout(int layoutId) {
		removeViews(2, (getChildCount()-2));
		secondLayout = layoutInflater.inflate(layoutId, null);
		secondLayout.setVisibility(INVISIBLE);
		addView(secondLayout, 2);
		secondInAnim.setTarget(secondLayout);
		secondOutAnim.setTarget(secondLayout);
		Log.v("nowwehave", getChildCount()+"");
	}
	
	public void setSecondLayout(View layout) {
		removeViews(2, (getChildCount()-2));
		AnimatorSet set = secondOutAnim.clone();
		addView(secondLayout, 2);
		secondInAnim.setTarget(secondLayout);
		secondOutAnim.setTarget(secondLayout);
		Log.v("nowwehave", getChildCount()+"");
	}
	
	@Override
	public void onAttachedToWindow() {
		super.onAttachedToWindow();
		mainLayout = getChildAt(1);
		mainInAnim.setTarget(mainLayout);
		mainOutAnim.setTarget(mainLayout);
		if (secondLayout != null) {
			secondInAnim.setTarget(secondLayout);
			secondOutAnim.setTarget(secondLayout);
		}
	}
	
	@Override
	public void onDraw(Canvas canvas) {
		super.onDraw(canvas);
	}
	
	public void setFirstInAnimator(int animId) {
		mainInAnim = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, animId);
		mainInAnim.setTarget(getChildAt(1));
	}
	public void setFirstOutAnimator(int animId) {
		mainOutAnim = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, animId);
		mainOutAnim.setTarget(getChildAt(1));
	}
	public void setSecondInAnimation(int animId) {
		secondInAnim = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, animId);
		secondInAnim.setTarget(secondLayout);
	}
	public void setSecondOutAnimator(int animId) {
		secondOutAnim = (AnimatorSet) AnimatorInflater.loadAnimator(mContext, animId);
		secondOutAnim.setTarget(secondLayout);
	}
	
	public boolean isInMain() {
		return inMain;
	}
	
	public void switchToSecond() {
		if (secondLayout != null) {
			if (mainOutAnim != null) {
				mainOutAnim.start();
			}
			if (secondInAnim != null) {
				secondInAnim.start();
			}
		}
	}
	
	public void switchToMain() {
		if (secondOutAnim != null) {
			secondOutAnim.start();
		}
		if (mainInAnim != null) {
			mainInAnim.start();
		}
	}

}
