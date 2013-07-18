package com.springsource.insight.plugin.jsf;

public final class FacesUtils {

	private static final String JSF_EXPRESSION_START = "#{";

	private FacesUtils() {
	}

	/**
	 * Extract the bean name from a JSF expression. For example, in the
	 * expression #{myBean.myMethod} return myBean
	 * 
	 * @param expression
	 *            the JSF expression
	 * @return the name of the bean used in the expression
	 */
	public static String extractBeanNameFromExpression(String expression) {
		String beanName = null;

		int expressionStartIndex = expression.indexOf(JSF_EXPRESSION_START);
		int index = expression.indexOf('.');

		beanName = expression.substring(expressionStartIndex
				+ JSF_EXPRESSION_START.length(), index);

		return beanName;
	}

	/**
	 * Extract the method name from a JSF expression. For example, in the
	 * expression #{myBean.myMethod} return myMethod
	 * 
	 * @param expression
	 *            the JSF expression
	 * @return the name of the method used in the expression
	 */
	public static String extractMethodNameFromExpression(String expression) {
		String result = null;

		int index = expression.indexOf('.');
		result = expression.substring(index + 1, expression.length() - 1);

		return result;
	}
}
