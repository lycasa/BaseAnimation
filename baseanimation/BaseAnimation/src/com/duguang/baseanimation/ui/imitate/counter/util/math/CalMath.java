package com.duguang.baseanimation.ui.imitate.counter.util.math;

/**
 * 此类的目的在于将(反)正弦，(反)余弦，(反)正切，传入或返回的弧度转换成角度
 * @author Administrator
 *
 */
public class CalMath {
	
	private CalMath(){}
	public static double abs(double d) {
		return Math.abs(d);
	}

	public static float abs(float d) {
		return Math.abs(d);
	}

	public static int abs(int d) {
		return Math.abs(d);
	}

	public static long abs(long d) {
		return Math.abs(d);
	}

	public static double acos(double d) {
		return Math.toDegrees(Math.acos(d));
	}

	public static double asin(double d) {
		return Math.toDegrees(Math.asin(d));
	}

	public static double atan(double d) {
		return Math.toDegrees(Math.atan(d));
	}

	public static double cbrt(double d) {
		return Math.cbrt(d);
	}

	public static double cos(double d) {
		return Math.cos(Math.toRadians(d));
	}

	public static double exp(double d) {
		return Math.exp(d);
	}


	public static double ln(double d) {
		return Math.log(d);
	}

	public static double log(double d) {
		return Math.log10(d);
	}

	public static double pow(double a, double b) {
		return Math.pow(a, b);
	}

	public static double sin(double d) {
		return Math.sin(Math.toRadians(d));
	}

	public static double sqrt(double d) {
		return Math.sqrt(d);
	}

	public static double tan(double d) {
		return Math.tan(Math.toRadians(d));
	}
	
	public static double getE() {
		return Math.E;
	}
	
	public static double getPI() {
		return Math.PI;
	}
}