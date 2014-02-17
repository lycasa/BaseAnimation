package com.duguang.baseanimation.ui.imitate.counter.listener;

import java.util.Arrays;

import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;

import com.duguang.baseanimation.ui.imitate.counter.util.ButtonType;
import com.duguang.baseanimation.ui.imitate.counter.util.Calculation;
import com.duguang.baseanimation.ui.imitate.counter.util.Constants;
import com.duguang.baseanimation.ui.imitate.counter.util.ExpressionParse;

public class CommonButtonClickListener implements OnClickListener {
	private EditText expressText;
	private ButtonType buttonType;
	private String appendStr;
	
	public CommonButtonClickListener(EditText expressText, ButtonType buttonType, String... appendText) throws IllegalArgumentException {
		if(buttonType == ButtonType.SYNTHETIC_COMMON_APPEND) {
			if(appendText != null && appendText.length == 1) {
				appendStr = appendText[0];
			} else {
				throw new IllegalArgumentException("ButtonType:" + buttonType + ", appendText" + (appendText == null ? null : Arrays.toString(appendText)));
			}
		}
		this.expressText = expressText;
		this.buttonType = buttonType;
	}

	public void onClick(View view) {
//		Editable text = expressText.getText();
		Editable text = expressText.getEditableText();
		switch(buttonType) {
		case BACKSPACE:
			int length = text.length();
			if(length > 0) {
				if(text.toString().startsWith(Constants.ERROR_PREFIX)) {
					text.clear();
				} else {
					text.delete(length-1, length);
				}
			}
			break;
		case CLEAR:
			text.clear();
			break;
		case SYNTHETIC_COMMON_APPEND:
			text.append(appendStr);
			break;
		case EQUALS:
			String exp = text.toString();
			if(exp == null || (exp = exp.trim()).length() == 0) {
				break;
			}
			String result = Calculation.cal(ExpressionParse.parse(exp));
			if(!result.startsWith(Constants.ERROR_PREFIX)) {
				result = exp + "=" + result;
			}
			expressText.setText(result);
			expressText.setSelection(result.length());
			break;
		}
	}

}
