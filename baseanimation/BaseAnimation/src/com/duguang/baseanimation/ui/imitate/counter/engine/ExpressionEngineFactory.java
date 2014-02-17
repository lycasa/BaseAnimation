package com.duguang.baseanimation.ui.imitate.counter.engine;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.jexl2.JexlArithmetic;
import org.apache.commons.jexl2.JexlEngine;


import com.duguang.baseanimation.ui.imitate.counter.util.math.CalMath;

public class ExpressionEngineFactory {
	private ExpressionEngineFactory() {
	}

	public static JexlEngine createEngine() {
		Map<String, Object> ns = new HashMap<String, Object>();
		ns.put("math", CalMath.class);
		JexlEngine jexl = new JexlEngine(null, new Arithmetic(), ns, null);
		jexl.setStrict(true);
		return jexl;
	}
}

class Arithmetic extends JexlArithmetic {
	public Arithmetic() {
		super(false);
	}

	protected double divideZero(BigDecimal x) {
		int ls = x.signum();
		if (ls < 0) {
			return Double.NEGATIVE_INFINITY;
		} else if (ls > 0) {
			return Double.POSITIVE_INFINITY;
		} else {
			return Double.NaN;
		}
	}

	protected double divideZero(BigInteger x) {
		throw new ArithmeticException("divide by zero");
		/*
		 * int ls = x.signum(); if (ls < 0) { return Double.NEGATIVE_INFINITY; }
		 * else if (ls > 0) { return Double.POSITIVE_INFINITY; } else { return
		 * Double.NaN; }
		 */
	}

	@Override
	public Object divide(Object left, Object right) {
		if (left == null && right == null) {
			return controlNullNullOperands();
		}
		// if either are bigdecimal use that type
		if (left instanceof BigDecimal || right instanceof BigDecimal) {
			BigDecimal l = toBigDecimal(left);
			BigDecimal r = toBigDecimal(right);
			if (BigDecimal.ZERO.equals(r)) {
				return divideZero(l);
			}
			BigDecimal result = l.divide(r, getMathContext());
			return narrowBigDecimal(left, right, result);
		}
		// if either are floating point (double or float) use double
		if (isFloatingPointNumber(left) || isFloatingPointNumber(right)) {
			double l = toDouble(left);
			double r = toDouble(right);
			return new Double(l / r);
		}
		// otherwise treat as integers
		BigInteger l = toBigInteger(left);
		BigInteger r = toBigInteger(right);
		if (BigInteger.ZERO.equals(r)) {
			return divideZero(l);
		}
		BigInteger result = l.divide(r);
		return narrowBigInteger(left, right, result);
	}

	@Override
	public Object mod(Object left, Object right) {
		if (left == null && right == null) {
			return controlNullNullOperands();
		}
		// if either are bigdecimal use that type
		if (left instanceof BigDecimal || right instanceof BigDecimal) {
			BigDecimal l = toBigDecimal(left);
			BigDecimal r = toBigDecimal(right);
			if (BigDecimal.ZERO.equals(r)) {
				return divideZero(l);
			}
			BigDecimal remainder = l.remainder(r, getMathContext());
			return narrowBigDecimal(left, right, remainder);
		}
		// if either are floating point (double or float) use double
		if (isFloatingPointNumber(left) || isFloatingPointNumber(right)) {
			double l = toDouble(left);
			double r = toDouble(right);
			return new Double(l % r);
		}
		// otherwise treat as integers
		BigInteger l = toBigInteger(left);
		BigInteger r = toBigInteger(right);
		BigInteger result = l.mod(r);
		if (BigInteger.ZERO.equals(r)) {
			return divideZero(l);
		}
		return narrowBigInteger(left, right, result);
	}
}