package com.duguang.baseanimation.ui.imitate.counter;

import android.app.TabActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.widget.TabHost;
import android.widget.TextView;

import com.duguang.baseanimation.R;

public class HelpAboutActivity extends TabActivity {
	private static final String HELP_TAG = "help";
	private static final String ABOUT_TAG = "about";
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final TabHost tabHost = getTabHost();
		LayoutInflater inflater = LayoutInflater.from(this);
		inflater.inflate(R.layout.activity_imitate_counter_help, tabHost.getTabContentView());
		inflater.inflate(R.layout.activity_imitate_counter_about, tabHost.getTabContentView());
		tabHost.addTab(tabHost.newTabSpec(HELP_TAG).setIndicator("帮助").setContent(R.id.helpLayout));
		tabHost.addTab(tabHost.newTabSpec(ABOUT_TAG).setIndicator("关于").setContent(R.id.aboutLayout));
		
		TextView helpView = (TextView)tabHost.findViewById(R.id.helpView);
		helpView.setMovementMethod(ScrollingMovementMethod.getInstance());
		
		TextView aboutView = (TextView)tabHost.findViewById(R.id.aboutView);
		aboutView.setMovementMethod(ScrollingMovementMethod.getInstance());
	}
}
