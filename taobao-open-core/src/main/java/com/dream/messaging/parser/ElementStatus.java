
package com.dream.messaging.parser;

// TODO: Auto-generated Javadoc
/**
 * The Class ElementStatus.
 * 
 * <p>
 * 
 * @author Liang Yanpeng
 * @author Zhang Fu
 */
public class ElementStatus {

	/** The Constant STATUS_M. */
	public static final byte STATUS_M = 0;// mandatory

	/** The Constant STATUS_C. */
	public static final byte STATUS_C = 1;// condition

	/** The Constant STATUS_O. */
	public static final byte STATUS_O = 2;// optional

	/**
	 * Gets the status.
	 * 
	 * @param status the status
	 * 
	 * @return the status
	 */
	public static byte getStatus(String status) {
		if (status == null)
			return STATUS_O;
		if (status.equalsIgnoreCase("M"))
			return STATUS_M;
		if (status.equalsIgnoreCase("C"))
			return STATUS_C;
		if (status.equalsIgnoreCase("O"))
			return STATUS_O;
		return STATUS_O;
	}
}
