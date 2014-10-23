package com.xiamubobby.blacklotus;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;

public class BaseMirari extends View {
	
	float growPercentage = 0;
	float touchPercentage = 0;
	boolean initialed = false;
	ValueAnimator growAnimator;
	ValueAnimator touchAnimator;
	int[] testColor = new int[3];
	Paint paint;
	
	public BaseMirari(Context context) {
		super(context);
		init();
	}

	public BaseMirari(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public BaseMirari(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}
	
	void init() {
		setVisibility(View.GONE);
		testColor[0] = (int) (Math.random() * 255);
		testColor[1] = (int) (Math.random() * 255);
		testColor[2] = (int) (Math.random() * 255);
		growAnimator = ValueAnimator.ofFloat(0, 1);
		growAnimator.setDuration(1500);
		growAnimator.setInterpolator(new DecelerateInterpolator());
		growAnimator.addListener(new AnimatorListener() {

			@Override
			public void onAnimationStart(Animator animation) {
				setVisibility(View.VISIBLE);
			}

			@Override
			public void onAnimationEnd(Animator animation) {
				initialed = true;
				onGrowed();
			}

			@Override
			public void onAnimationCancel(Animator animation) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onAnimationRepeat(Animator animation) {
				// TODO Auto-generated method stub
				
			}
			
		});
		growAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				growPercentage = (Float) animation.getAnimatedValue();
				invalidate();
			}
		});
		
		touchAnimator = ValueAnimator.ofFloat(0, 1);
		touchAnimator.setDuration(250);
		touchAnimator.setInterpolator(new LinearInterpolator());
		touchAnimator.addListener(new AnimatorListener() {

			@Override
			public void onAnimationStart(Animator animation) {
				
			}

			@Override
			public void onAnimationEnd(Animator animation) {
				
			}

			@Override
			public void onAnimationCancel(Animator animation) {
								
			}

			@Override
			public void onAnimationRepeat(Animator animation) {
								
			}
			
		});
		touchAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
			
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				touchPercentage = (Float) animation.getAnimatedValue();
				invalidate();
			}
		});
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		drawBase(canvas);
		drawTouch(canvas);
	}
	
	void drawBase(Canvas canvas) {
		paint = new Paint();
		paint.setColor(Color.argb(255, testColor[0], testColor[1], testColor[2]));
		canvas.drawCircle(getWidth()/2, getHeight()/2, getWidth()/2 * growPercentage, paint);
	}
	void drawTouch(Canvas canvas) {
		paint = new Paint();
		float a = 16/7*touchPercentage*touchPercentage + 9/7;
		paint.setColor(Color.argb(255, testColor[1], testColor[2], testColor[0]));
		float r = (touchPercentage == 0)?0:(float) (getWidth()/2*touchPercentage*0.2 + getWidth()/2*0.8);
		canvas.drawCircle(getWidth()/2, getHeight()/2, r, paint);
	}
	
	public void initial() {
		growAnimator.start();
	}
	
	public void onGrowed() {
		((Mirrordin) getParent()).onEnchantGrowed(((ViewGroup) getParent()).indexOfChild(this));
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (touchAnimator.isRunning()) {
				touchAnimator.reverse();
			}
			else {
				touchAnimator.start();
			}
			return true;
		case MotionEvent.ACTION_UP:
			touchAnimator.reverse();
			return true;
		default:
			return true;
		}
	}
}
