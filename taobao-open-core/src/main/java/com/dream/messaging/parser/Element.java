
package com.dream.messaging.parser;

/**
 * The Class Element holds universal methods.
 * 
 * @author Liang Yanpeng
 */
public class Element {
	
	/** The Constant FREETEXT. */
	private static final String FREETEXT = "-. ";

	/**
	 * Checks if is alphabetic.
	 * 
	 * @param c the c
	 * 
	 * @return true, if is alphabetic
	 */
	public static boolean isAlphabetic(char c) {
		return ((c >= 'a') && (c <= 'z')) || ((c >= 'A') && (c <= 'Z'));
	}

	/**
	 * Checks if is decimal.
	 * 
	 * @param c the c
	 * 
	 * @return true, if is decimal
	 */
	public static boolean isDecimal(char c) {
		return Element.isNumeric(c) || (c == '.') || (c == '-') || (c == '+');
	}

	/**
	 * Checks if is mixed alpha.
	 * 
	 * @param c the c
	 * 
	 * @return true, if is mixed alpha
	 */
	public static boolean isMixedAlpha(char c) {
		return Element.isAlphabetic(c) || Element.isNumeric(c);
	}

	/**
	 * Checks if is numeric.
	 * 
	 * @param c the c
	 * 
	 * @return true, if is numeric
	 */
	public static boolean isNumeric(char c) {
		return ((c >= '0') && (c <= '9'));
	}

	/**
	 * Checks if is text.
	 * 
	 * @param c the c
	 * 
	 * @return true, if is text
	 */
	public static boolean isText(char c) {
		return Element.isMixedAlpha(c) || (Element.FREETEXT.indexOf(c) >= 0);
	}

}
