package com.duguang.baseanimation.ui.imitate.counter;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.Toast;

import com.duguang.baseanimation.R;
import com.duguang.baseanimation.ui.base.BaseActivity;
import com.duguang.baseanimation.ui.imitate.counter.listener.CommonButtonClickListener;
import com.duguang.baseanimation.ui.imitate.counter.listener.MoreFunctionsButtonListener;
import com.duguang.baseanimation.ui.imitate.counter.util.ButtonType;
import com.duguang.baseanimation.ui.imitate.counter.widget.ExpressEditText;



public class CalculatorActivity extends BaseActivity {
	private ExpressEditText expressText;
	private MenuInflater mi;
	private View tableLayout;
	private View indepLayout;
	

	@Override
	public void setView() {
		 setContentView(R.layout.activity_imitate_counter_main);
	        mi = new MenuInflater(this);
	        initButtons();
	        tableLayout = (TableLayout)findViewById(R.id.innerkeyboard);
	        indepLayout = findViewById(R.id.indepLayout);
		
	}



	@Override
	public void initView() {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void setListener() {
		// TODO Auto-generated method stub
		
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		mi.inflate(R.menu.optionsmenu, menu);
		return true;
	}



	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case R.id.exit:
			finish();
			System.exit(0);
		case R.id.changeInput:
			if(tableLayout.getVisibility() == View.GONE) {
				indepLayout.setVisibility(View.GONE);
				tableLayout.setVisibility(View.VISIBLE);
				expressText.setEditable(false);
				//如果切换的时候输入法是打开的，则关闭
				((InputMethodManager)getSystemService(INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
				expressText.clearFocus();//这个似乎不起作用
			} else {
				tableLayout.setVisibility(View.GONE);
				indepLayout.setVisibility(View.VISIBLE);
				expressText.setEditable(true);
				expressText.requestFocus();
			}
			break;
		case R.id.helpabout:
			Intent intent = new Intent(this, HelpAboutActivity.class);
			startActivity(intent);
			break;
		}
		return true;
	}



	private void initButtons() {
		expressText = (ExpressEditText)findViewById(R.id.expressEditText);

		Map<Integer, String> appendButtons = new HashMap<Integer, String>();
		appendButtons.put(R.id.add, "+");
		appendButtons.put(R.id.sub, "-");
		appendButtons.put(R.id.multiply, "×");
		appendButtons.put(R.id.divide, "÷");
		appendButtons.put(R.id.dot, ".");
		appendButtons.put(R.id.lparenthesis, "(");
		appendButtons.put(R.id.rparenthesis, ")");
		appendButtons.put(R.id.remainder, "%");
		appendButtons.put(R.id.comma, ",");
//		appendButtons.put(R.id.sin, "sin(");
//		appendButtons.put(R.id.cos, "cos(");
		appendButtons.put(R.id.pi, "π");
		appendButtons.put(R.id.e, "e");
//		appendButtons.put(R.id.or, "|");
//		appendButtons.put(R.id.and, "&");
		appendButtons.put(R.id.cbrt, "cbrt(");
//		appendButtons.put(R.id.tan, "tan(");
		appendButtons.put(R.id.sqrt, "sqrt(");
//		appendButtons.put(R.id.pi, "π");
		
		Button button = null;
		button = (Button)findViewById(R.id.backspace);
		button.setOnClickListener(new CommonButtonClickListener(expressText, ButtonType.BACKSPACE));
        
		button = (Button)findViewById(R.id.clear);
		button.setOnClickListener(new CommonButtonClickListener(expressText, ButtonType.CLEAR));
		
		button = (Button)findViewById(R.id.equals);
		button.setOnClickListener(new CommonButtonClickListener(expressText, ButtonType.EQUALS));
		
		button = (Button)findViewById(R.id.more);
		button.setOnClickListener(new MoreFunctionsButtonListener(this));
		
		button = (Button)findViewById(R.id.indepCalButton);
		button.setOnClickListener(new CommonButtonClickListener(expressText, ButtonType.EQUALS));
		
		button = (Button)findViewById(R.id.indepClearButton);
		button.setOnClickListener(new CommonButtonClickListener(expressText, ButtonType.CLEAR));

		
		for(Entry<Integer, String> entry : appendButtons.entrySet()) {
			button = (Button)findViewById(entry.getKey());
			button.setOnClickListener(new CommonButtonClickListener(expressText, ButtonType.SYNTHETIC_COMMON_APPEND, entry.getValue()));
		}
        
        try {
        	initNumButtons();
        } catch (Exception e) {
        	Toast.makeText(this, "fatal error", Toast.LENGTH_SHORT).show();
        	System.exit(-1);
        }
	}
	
	
	private void initNumButtons() throws Exception {
		for(int i=0; i<10; i++) {
			Field field = R.id.class.getDeclaredField("num" + i);
			int res = field.getInt(null);
			Button button = (Button)findViewById(res);
			button.setOnClickListener(new CommonButtonClickListener(expressText, ButtonType.SYNTHETIC_COMMON_APPEND, "" + i));
		}
	}



	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(requestCode == 1 && resultCode == 1) {
			String function = data.getStringExtra("function");
			String needLParenthesis = data.getStringExtra("needLParenthesis");
			expressText.getEditableText().append(function + ("true".equalsIgnoreCase(needLParenthesis) ? "(" : ""));
		}
	}
	
}