package com.duguang.baseanimation.ui.imitate.counter.widget;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.KeyListener;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.EditText;

public class ExpressEditText extends EditText {
	private boolean isEditable = false;
	private final KeyListener defaultKeyListener = getKeyListener();
	
	
	public ExpressEditText(Context context, AttributeSet attrs) {
		super(context, attrs);
		setKeyListener(null);
		addTextChangedListener(new TextWatcher() {
			private String currentDisplay = "";//当前EditText中显示的全部字符
			private String appendStr;//替换成 或  在某个位置追加的字符
			private String strBeforeAppendIndex;//在追加字符位置前面的所有字符
			private String replacedStr;//被替换掉的字符
			public void afterTextChanged(Editable s){
				
				//避免输入连续两个操作符的情况（忽略空格）
				if(strBeforeAppendIndex!=null && strBeforeAppendIndex.matches("^.*[-%+×÷/*]\\s*$") && appendStr != null && appendStr.matches("\\s*[-%+×÷/*,)].*$")) {
					//如果前面是-%+×÷/*，后面就不能再为这些符号了
						if(replacedStr == null) {
							//若不是替换
							int start = strBeforeAppendIndex.length();
							int end = start + appendStr.length();
							s.delete(start, end);
						} else {
							//若是替换就将原先的替换回去
							//但这会有个问题，若表达式是粘贴过去的，假如为9++
							//用键盘或内置键盘将第二个+替换为*就会出现循环替换
						}
				}
				
				//.后面不能接非数字
				if(strBeforeAppendIndex!=null && strBeforeAppendIndex.matches("^.*[.]\\s*$") && appendStr != null && appendStr.matches("\\s*[^0-9].*$")) {
					//如果前面是-%+×÷/*，后面就不能再为这些符号了
					if(replacedStr == null) {
						//若不是替换
						int start = strBeforeAppendIndex.length();
						int end = start + appendStr.length();
						s.delete(start, end);
					} else {
						//若是替换就将原先的替换回去
						//但这会有个问题，若表达式是粘贴过去的，假如为9++
						//用键盘或内置键盘将第二个+替换为*就会出现循环替换
					}
				}
				
				//避免在开头输入%+×÷*/,)  但可以输入-
				if(strBeforeAppendIndex != null && strBeforeAppendIndex.matches("^\\s*$") && appendStr != null && appendStr.matches("^\\s*[%+×÷*/,)].*")) {
					
					int start = strBeforeAppendIndex.length();
					int end = start + appendStr.length();
					s.delete(start, end);
				}
				
				
				
				currentDisplay = s.toString();
		    }
		    public void  beforeTextChanged(CharSequence s, int start, int count, int after){
		    }
		    public void  onTextChanged (CharSequence s, int start, int before,int count) {
//		    	System.out.println("onTextChanged[CharSequence:" + s + "  start:" + start + "  before:" + before + "  count:" + count + "]");
		    	String sStr = s.toString();
		    	try {
			    	if(count > 0) {
			    		//count>0表示有新字符进来（新增或替换）
			    		strBeforeAppendIndex = sStr.substring(0, start);
			    		appendStr = sStr.substring(start, start+count);
			    		if(before>0) {
			    			//before>0表示某些字符被删掉了
			    			replacedStr = currentDisplay.substring(start, start+before);
			    		} else {
			    			replacedStr = null;
			    		}
			    	} else {
			    		strBeforeAppendIndex = null;
			    		appendStr = null;
			    		replacedStr = null;
			    	}
		    	} catch (Exception e) {
		    		strBeforeAppendIndex = null;
		    		appendStr = null;
		    		replacedStr = null;
		    	}
		    } 
		    	
		});
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (isEditable) {
			return super.onTouchEvent(event);
		}
		return true;
	}

	public void setEditable(boolean editable) {
		this.isEditable = editable;
		
		if(editable) {
			setKeyListener(defaultKeyListener);
			setCursorVisible(true);
		} else {
			setKeyListener(null);
			setCursorVisible(false);
		}
	}
}