package com.duguang.baseanimation.ui.imitate.counter.util;

import java.text.DecimalFormat;

import org.apache.commons.jexl2.Expression;
import org.apache.commons.jexl2.JexlArithmetic;
import org.apache.commons.jexl2.JexlEngine;
import com.duguang.baseanimation.ui.imitate.counter.engine.ExpressionEngineFactory;

public class Calculation {
	private static final DecimalFormat decimalFormat = new DecimalFormat();
	private static final JexlEngine jexl = ExpressionEngineFactory.createEngine();
	static {
		decimalFormat.setDecimalSeparatorAlwaysShown(false);
		decimalFormat.setMaximumFractionDigits(6);
		decimalFormat.setMinimumFractionDigits(0);
		decimalFormat.setGroupingSize(Byte.MAX_VALUE);
	}

	private Calculation() {
	}

	public static String cal(String express) {
		if (express == null || (express = express.trim()).length() == 0) {
			return Constants.ERROR_PREFIX + "Empty Express";
		}
		Object tmp = null;
		try {
			Expression expression = jexl.createExpression(express);
			tmp = expression.evaluate(null);
		} catch (Exception e) {
			return Constants.ERROR_PREFIX + "Wrong Expression";
		}

		if (tmp == null) {
			return Constants.ERROR_PREFIX + "No Result";
		}

		try {
			return decimalFormat.format(tmp);
		} catch (Exception e) {
			return tmp.toString();
		}
	}
}
