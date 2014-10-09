package com.xiamubobby.blacklotus;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

public class BlackLotusBaseActivity extends Activity {

	protected RequestQueue queue;
	protected Dialog blocker;
	protected FragmentManager fragmentManager;
	protected BlockFragment blockFragment;
	
	protected boolean tapped;
	
	public BlackLotusBaseActivity() {
		// TODO Auto-generated constructor stub
		tapped = false;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		queue = Volley.newRequestQueue(this);
		blocker = new Dialog(this);
		blocker.setCancelable(false);
		blocker.setContentView(R.layout.base_activity_blocker);
		
		fragmentManager = getFragmentManager();
		blockFragment = new BlockFragment();
	}
	
	public void tap() {
		Log.v("test","You are and will be tapped from this day, to the end of my network loading work.");
		tapped = true;
		//blocker.show();
		blockFragment.show(fragmentManager, "blocker");
	}
	
	public void untap() {
		Log.v("test","Now you're free to go, enjoy the manas and the spells.");
		tapped = false;
		//blocker.dismiss();
		blockFragment.dismiss();
	}
	
	public class BlockFragment extends DialogFragment {
	    @Override
	    public View onCreateView(LayoutInflater inflater, ViewGroup container,
	            Bundle savedInstanceState) {
	        return inflater.inflate(R.layout.base_activity_blocker, container, false);
	    }
	    @Override
	    public Dialog onCreateDialog(Bundle savedInstanceState) {
	        Dialog dialog = super.onCreateDialog(savedInstanceState);
	        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
	        return dialog;
	    }
	}

}
