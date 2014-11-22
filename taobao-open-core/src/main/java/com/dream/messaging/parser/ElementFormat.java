/**************************************************************************
 * Licensed Material - Property of Dawn InfoTek                           *
 * Copyright (c) Dawn InfoTek Inc. 1999, 2004, 2008 -All rights reserved. * 
 * (<http://www.dawninfotek.com>)                                         *
 *                                                                        *
 * This file contains proprietary intellectual property of                *
 * Dawn InfoTek Inc. The contents of and information in this file         *
 * is only to be used in conjunction with a valid Dawn4J license          *
 * as specified in the Dawn4J license agreement. All other use            *
 * is prohibited.                                                         *
 **************************************************************************/
package com.dream.messaging.parser;

import org.apache.log4j.Logger;


// TODO: Auto-generated Javadoc
/**
 * The Class ElementFormat repsents a format defines in the configuratino files.
 * 
 * @author anders liang
 */
public class ElementFormat {
	
	/** The Constant delimeter. */
	private static final String delimeter = ".";

	/** The Constant logger. */
	private static final Logger logger = null;

	/** The format. */
	private char[] format = null;

	/** The fraction. */
	private int fraction = 2;

	/** The has point. */
	private boolean hasPoint = false;

	/** The has sign. */
	private boolean hasSign = false;

	/** The max len. */
	private byte maxLen = 0;

	/** The min len. */
	private byte minLen = 0;

	/**
	 * Instantiates a new element format.
	 * 
	 * @param elmtFormat the elmt format
	 * 
	 * @throws ElementFormatParseException the element format parse exception
	 */
	public ElementFormat(String elmtFormat) throws ElementFormatParseException {
		this.parseFormat(elmtFormat);
	}

	/**
	 * Gets the format.
	 * 
	 * @return the format
	 */
	public char[] getFormat() {
		return this.format;
	}

	/**
	 * Gets the fraction.
	 * 
	 * @return the fraction
	 */
	public int getFraction() {
		return this.fraction;
	}

	/**
	 * Gets the max len.
	 * 
	 * @return the max len
	 */
	public byte getMaxLen() {
		return this.maxLen;
	}

	/**
	 * Gets the min len.
	 * 
	 * @return the min len
	 */
	public byte getMinLen() {
		return this.minLen;
	}

	/**
	 * Checks if is checks for point.
	 * 
	 * @return true, if is checks for point
	 */
	public boolean isHasPoint() {
		return this.hasPoint;
	}

	/**
	 * Checks if is checks for sign.
	 * 
	 * @return true, if is checks for sign
	 */
	public boolean isHasSign() {
		return this.hasSign;
	}

	/**
	 * Parses the format.
	 * 
	 * @param elmtFormat the elmt format
	 * 
	 * @throws ElementFormatParseException the element format parse exception
	 */
	private void parseFormat(String elmtFormat)
			throws ElementFormatParseException {
		int index = elmtFormat.indexOf('p');
		if (index < 0) {
			index = elmtFormat.indexOf('P');
		}
		if (index > 0) {
			this.setHasPoint(true);
			if (elmtFormat.length() > index + 1) {
				char ch = elmtFormat.charAt(index + 1);
				if (java.lang.Character.isDigit(ch)) {
					this.fraction = Integer.parseInt("" + ch);
				}
			} else {
				this.fraction = 2;
			}
		}
		// check have sign
		if ((elmtFormat.indexOf('s') > 0) || (elmtFormat.indexOf('S') > 0)) {
			this.setHasSign(true);
		}
		//
		index = elmtFormat.indexOf('(');
		if (index > 0) {
			this.setMinLen(index);
			// relace "(" and ")"
			String newFormat = Strings.replaceAll(Strings.replaceAll(
					elmtFormat, "(", ""), ")", "");

			this.setFormat(newFormat);
			this.setMaxLen(newFormat.length());
		} else if ((index = elmtFormat.indexOf('[')) > 0) {
			this.setRepeated(elmtFormat.substring(0, index), elmtFormat
					.substring(index + 1, elmtFormat.indexOf(']')).trim());
		} else {
			this.setFormat(elmtFormat);
			this.setMinLen(elmtFormat.length());
			this.setMaxLen(elmtFormat.length());
		}

	}

	/**
	 * Sets the format.
	 * 
	 * @param string the string
	 */
	private void setFormat(String string) {
		this.format = string.toCharArray();
	}

	/**
	 * Sets the fraction.
	 * 
	 * @param fraction the new fraction
	 */
	public void setFraction(int fraction) {
		this.fraction = fraction;
	}

	/**
	 * Sets the has point.
	 * 
	 * @param b the b
	 */
	private void setHasPoint(boolean b) {
		this.hasPoint = b;
	}

	/**
	 * Sets the checks for sign.
	 * 
	 * @param hasSign the new checks for sign
	 */
	public void setHasSign(boolean hasSign) {
		this.hasSign = hasSign;
	}

	/**
	 * Sets the max len.
	 * 
	 * @param i the i
	 */
	private void setMaxLen(int i) {
		this.maxLen = (byte) i;
	}

	/**
	 * Sets the min len.
	 * 
	 * @param i the i
	 */
	private void setMinLen(int i) {
		this.minLen = (byte) i;
	}

	/**
	 * Sets the repeated.
	 * 
	 * @param repeatedData the repeated data
	 * @param repeatedTimes the repeated times
	 * 
	 * @throws ElementFormatParseException the element format parse exception
	 */
	private void setRepeated(String repeatedData, String repeatedTimes)
			throws ElementFormatParseException {
		String[] times = Strings.split(repeatedTimes, ElementFormat.delimeter);
		int counts = times.length, min = 1, max = 1;
		if (counts == 1) {
			min = Integer.parseInt(times[0].trim());
			max = min;
		} else if (counts == 2) {
			max = Integer.parseInt(times[1].trim());
			if (!times[0].trim().equals("")) {
				min = Integer.parseInt(times[0].trim());
			} else {
				min = 1;
			}
		} else {
			// should log warning
			// max=min=1;
//			logger.warn(this, "max length equals min length,please check it.");
		}
		/*
		 * int counts = st.countTokens(),min = 1,max = 1; if (counts == 1) { min =
		 * Integer.parseInt(st.nextToken().trim()); max = min; } else if (counts ==
		 * 2) { min = Integer.parseInt(st.nextToken().trim()); max =
		 * Integer.parseInt(st.nextToken().trim()); } else { //should log
		 * warning //max=min=1; }
		 */
		if (min > max) {
			// log error and throw Exception
			throw new ElementFormatParseException(
					"the definition of element format repeated times is wrong, min > max");
		}
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < max; i++) {
			sb.append(repeatedData);
		}

		if (this.hasPoint) {
			sb.append(Strings.fill('n', this.fraction + 1));
		}
		if (this.hasSign) {
			sb.append("n");
		}

		this.setMinLen(repeatedData.length() * min);
		this.setFormat(sb.toString());
		this.setMaxLen(repeatedData.length() * max);
		if (this.hasPoint) {
			this.setMaxLen(this.getMaxLen() + 1 + this.fraction);
			this.setMinLen(this.getMinLen() + 1 + this.fraction);
		}
		if (this.hasSign) {
			sb.append("n");
			this.setMaxLen(this.getMaxLen() + 1);
			this.setMinLen(this.getMinLen() + 1);
		}
	}

}
