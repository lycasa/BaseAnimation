package com.duguang.baseanimation.ui.imitate.counter.listener;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

import com.duguang.baseanimation.ui.imitate.counter.MoreFunctionsActivity;

public class MoreFunctionsButtonListener implements OnClickListener {
	private Activity curActivity;
	public MoreFunctionsButtonListener(Activity curActivity) {
		this.curActivity = curActivity;
	}
	public void onClick(View v) {
		Intent intent = new Intent(curActivity, MoreFunctionsActivity.class);
		curActivity.startActivityForResult(intent, 1);
	}

}
