 package com.duguang.baseanimation.ui.imitate.counter.util;


public class ExpressionParse {
	public static String parse(String origExp) {
		if(origExp == null || origExp.trim().length() == 0) {
			return origExp;
		}
		//将×改成*
		origExp = origExp.replaceAll("×", "*")
			.replaceAll("π", "PI")
			.replaceAll("[eE]", "E")
			.replaceAll("(?<!\\d)(\\.\\d+)", "0$1")//将小数的小数点前面没有数字的时候添0
			.replaceAll("(?<!math:get)(PI|E)", "math:get$1()")
			.replaceAll("÷\\s*0+(?:(?=[^.])|$)", "/0")//将算式中所有的÷0都换成/0
//			.replaceAll("÷\\s*(\\d+)(?:(?=[^.0-9])|$)", "/$1.0")//将算式中所有形如÷32的地方都换成÷32.0
			.replaceAll("[÷/]\\s*(?!0+)(\\d+)(?:(?=[^.0-9])|$)", "/$1.0")//将算式中所有形如÷32或/32的地方都换成÷32.0
			.replaceAll("÷", "/")//将除数本来就是小数的地方替换成/
			.replaceAll("(?<!math:)(sin|cos|tan|pow|sqrt|abs|asin|acos|atan|cbrt|exp|ln|log)","math:$1");
		return origExp;
	}
}
