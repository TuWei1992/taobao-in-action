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
package com.dream.messaging.engine;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Node;

import com.dream.messaging.DataConvertException;
import com.dream.messaging.Message;
import com.dream.messaging.parser.Element;
import com.dream.messaging.parser.ElementAttr;
import com.dream.messaging.parser.ElementClassType;
import com.dream.messaging.parser.ElementFormat;
import com.dream.messaging.parser.ElementFormatParseException;
import com.dream.messaging.parser.Strings;
import com.dream.messaging.utils.QueryNode;


/**
 * Abstract implementation of a handler which process the message.
 * 
 * <p>
 * 
 * @author Liang Yanpeng
 * @author Zhang Fu
 */
public abstract class DataHandler {
	protected  Logger  logger = Logger.getLogger(getClass());
	
	/** The count. */
	private int count = 0;

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}

	/** The default value. */
	protected Map defaultValue;

	/** The delimiterValidValue. */
	protected String delimiterValidValue = "|";

	/** The offset. */
	protected int offset;

	/**
	 * Append data to an exsiting message.
	 * 
	 * @param message the message
	 * @param append the append
	 * 
	 * @throws BaseException the exception
	 */
	public abstract void appendData(Message message, Object append)
			throws DataConvertException;

	/**
	 * Append fixed data to an exsiting message.
	 * 
	 * @param message the message
	 * @param append the append
	 * 
	 * @throws BaseException the exception
	 */
	public abstract void appendFixedData(Message message, Object append)
			throws DataConvertException;

	/**
	 * Append message.
	 * 
	 * @param message the message
	 * @param append the append
	 * 
	 * @throws BaseException the exception
	 */
	public abstract void appendMessage(Message message, Message append)
			throws DataConvertException;

	/**
	 * Generate message data filed.
	 * 
	 * @param node the node
	 * @param objData the obj data
	 * 
	 * @return the object
	 * 
	 * @throws BaseException the exception
	 */
	public abstract Object createMessageData(Object objData, Node node)
			throws DataConvertException;

	/**
	 * Generate string message content by object property value.
	 * 
	 * @param value the value
	 * @param node the node
	 * 
	 * @return the string
	 */
	public String createStringMessage(Object value, Node node) {
		if (value == null)
			return "";
		String msg = null;
		String format = QueryNode.getAttribute(node, ElementAttr.Attr_Format);

		if (value instanceof Number) {
			msg = formatNumberData((Number) value, node);
		} else if (value instanceof Date) {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			msg = sdf.format((Date) value);
		} else {
			msg = value.toString();
			msg = formatStringData(msg, node);
		}
		return msg;
	}

	/**
	 * Generate object property value by the class.
	 * 
	 * @param node the node
	 * @param msgData the msg data
	 * 
	 * @return the object
	 * 
	 * @throws BaseException the exception
	 */
	public abstract Object createValue(Object msgData, Node node)
			throws DataConvertException;

	/**
	 * Generate object property value, the message content is String.
	 * 
	 * @param data the data
	 * @param node the node
	 * 
	 * @return the object
	 */
	public Object createValue(String data, Node node) {

		String clsname = QueryNode.getAttribute(node, ElementAttr.Attr_Class);
		try {
			Class cls = null;
			Object value = null;
			if (clsname == null
					|| clsname.equalsIgnoreCase(ElementClassType.CLASS_STRING)) {
				value = StringUtils.stripToEmpty(data);
			} else if (clsname.equalsIgnoreCase(ElementClassType.CLASS_INT)
					|| clsname.equalsIgnoreCase(ElementClassType.CLASS_COUNTER)) {
				try {
					value = Integer.valueOf(data.trim());
				} catch (NumberFormatException e) {
					value = 0;
				}
			} else if (clsname.equalsIgnoreCase(ElementClassType.CLASS_LONG)) {
				try {
					value = Long.valueOf(data.trim());
				} catch (NumberFormatException e) {
					value = 0;
				}
			} else if (clsname.equalsIgnoreCase(ElementClassType.CLASS_DOUBLE)) {
				try {
					value = Float.valueOf(data.trim());
				} catch (NumberFormatException e) {
					value = Float.valueOf("0");
				}
			} else if (clsname.equalsIgnoreCase(ElementClassType.CLASS_FLOAT)) {
				try {
					value = Double.valueOf(data.trim());
				} catch (NumberFormatException e) {
					value = Double.valueOf("0");
				}
			} else if (clsname.equalsIgnoreCase(ElementClassType.CLASS_DECIMAL)) {
				try {
					value = new java.math.BigDecimal(data.trim());
				} catch (NumberFormatException e) {
					value = java.math.BigDecimal.ZERO;
				}
			} else if (clsname.equalsIgnoreCase(ElementClassType.CLASS_DATE)
					|| clsname.equalsIgnoreCase(ElementClassType.CLASS_TIME)) {
				String format = QueryNode.getAttribute(node,
						ElementAttr.Attr_Format);
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				try {
					value = sdf.parse(data);
				} catch (ParseException e) {
					value = data;
				}
			} else {
				cls = Class.forName(clsname);
				Constructor c = cls.getConstructor(String.class);
				value = c.newInstance(new Object[] { data });
			}
			return value;
		} catch (ClassNotFoundException e) {
		
			return null;
		} catch (IllegalArgumentException e) {
			
			return null;
		} catch (InstantiationException e) {
			
			return null;
		} catch (IllegalAccessException e) {
			
			return null;
		} catch (InvocationTargetException e) {
		
			return null;
		} catch (SecurityException e) {
			
			return null;
		} catch (NoSuchMethodException e) {
			
			return null;
		}
	}

	/**
	 * Format the number by the given pattern.
	 * 
	 * @param number the number
	 * @param pattern the pattern
	 * 
	 * @return the string
	 */
	protected String format(Number number, String pattern) {
		java.text.DecimalFormat decFmt = new java.text.DecimalFormat(pattern);
		decFmt.setDecimalSeparatorAlwaysShown(false);
		return decFmt.format(number);

	}

	/**
	 * Format the message data.
	 * 
	 * @param msgData the msg data
	 * @param node the node
	 * 
	 * @return the object
	 */
	public abstract Object formatMessage(Object msgData, Node node);

	/**
	 * Format number data.
	 * 
	 * @param value the value
	 * @param node the node
	 * 
	 * @return the string
	 */
	protected String formatNumberData(Number value, Node node) {
		String format = QueryNode.getAttribute(node, ElementAttr.Attr_Format);
		if (format == null) {
			return value.toString();
		}
		try {

			ElementFormat eformat = new ElementFormat(format);
			int max = eformat.getMaxLen();
			int min = eformat.getMinLen();
			if (max < min)
				max = min;
			String pattern = (max > min) ? Strings.fill('#', max - 1) : Strings
					.fill('#', min - 1);
			pattern += "0";
			double num = value.doubleValue();
			double MAX = Double.parseDouble(Strings.fill('9', max) + "."
					+ Strings.fill('9', eformat.getFraction()));
			if (num > MAX) {
				value = new Double(MAX);
			}
			double MIN = 0;
			if (eformat.isHasPoint()) {
				pattern += "." + Strings.fill('0', eformat.getFraction());
				MIN = -MAX;
			}
			if (num < MIN) {
			
				value = new Double(MIN);
			}
			String append = format(value, pattern);
			return append;
		} catch (ElementFormatParseException e) {
			
			return null;
		}

	}

	/**
	 * Format string data.
	 * 
	 * @param value the value
	 * @param node the node
	 * 
	 * @return the string
	 */
	protected String formatStringData(String value, Node node) {
		String length = QueryNode.getAttribute(node, ElementAttr.Attr_Length);
		if (length != null) {
			int len = Integer.parseInt(length);
			if (value.length() > len) {
				value = value.substring(0, len);
			}
		}
		String format = QueryNode.getAttribute(node, ElementAttr.Attr_Format);
		if (format == null) {
			return value;
		}
		try {

			ElementFormat eformat = new ElementFormat(format);
			int max = eformat.getMaxLen();
			int min = eformat.getMinLen();
			if (max < min)
				max = min;
			if (value.length() < min) {
				
				// return null;
			}
			if (value.length() > max) {
			
				value = value.substring(0, max);
			}
			if (validateStringElemnet(eformat, value)) {
				return value;
			} else
			{
				
			}

		} catch (ElementFormatParseException e) {
		
			return null;
		}
		return null;
	}

	/**
	 * Gets the delimiterValidValue.
	 * 
	 * @return the delimiterValidValue
	 */
	public String getDelimiterValidValue() {
		return delimiterValidValue;
	}

	/**
	 * Gets the index.
	 * 
	 * @return the index
	 */
	public int getIndex() {
		return offset;
	}

	/**
	 * New message.
	 * 
	 * @return the message
	 */
	public abstract Message newMessage();

	/**
	 * Read size of a list records from the message.
	 * 
	 * @param message the message
	 * @param node the node
	 * 
	 * @return the int
	 */
	public abstract int readCount(Message message, Node node);

	/**
	 * Read a message field, and the index will move forward.
	 * 
	 * @param message the message
	 * @param node the node
	 * 
	 * @return the object
	 * 
	 * @throws BaseException the exception
	 */
	public abstract Object readData(Message message, Node node)
			throws DataConvertException;

	/**
	 * Read fixed data.
	 * 
	 * @param message the message
	 * @param fixed the fixed
	 * 
	 * @return the object
	 * 
	 * @throws BaseException the exception
	 */
	public abstract Object readFixedData(Message message, String fixed)
			throws DataConvertException;

	/**
	 * Sets the delimiter valid value.
	 * 
	 * @param delimiter the delimiter
	 */
	public void setDelimiterValidValue(String delimiter) {
		delimiterValidValue = delimiter;
	}

	/**
	 * Sets the index.
	 * 
	 * @param index the new index
	 */
	public void setIndex(int index) {
		offset = index;
	}

	/**
	 * validate message data.
	 * 
	 * @param node the node
	 * @param msgData the msg data
	 * 
	 * @return true, if validate data
	 */
	public abstract boolean validateData(Object msgData, Node node);

	/**
	 * validate string message data.
	 * 
	 * @param data the data
	 * @param node the node
	 * 
	 * @return true, if validate data
	 */
	public boolean validateData(String data, Node node) {

		String fixed = QueryNode.getAttribute(node, ElementAttr.Attr_Fixed);
		if (fixed != null) {
			fixed = Strings.replaceESC(fixed);
			if (!fixed.equals(data))
				return false;
		}
		String validValue = QueryNode.getAttribute(node,
				ElementAttr.Attr_Validvalue);
		if (validValue != null
				&& (data == null || (delimiterValidValue + validValue + delimiterValidValue)
						.indexOf(data + "|") < 0)) {
			return false;
		}
		// validate by format

		String format = QueryNode.getAttribute(node, ElementAttr.Attr_Format);
		if (format != null) {
			try {
				ElementFormat elefmt = new ElementFormat(format);
				return validateStringElemnet(elefmt, data);
			} catch (ElementFormatParseException e) {
				return false;
			}
		}
		return true;
	}

	/**
	 * Validate string elemnet.
	 * 
	 * @param elmtFormat the elmt format
	 * @param token the token
	 * 
	 * @return true, if successful
	 */
	protected boolean validateStringElemnet(ElementFormat elmtFormat,
			String token) {
		if (elmtFormat.getMinLen() > token.length())
			return false;
		char[] elmtFmt = elmtFormat.getFormat();
		for (int i = 0; i < token.length(); i++) {
			char data = token.charAt(i);
			switch (elmtFmt[i]) {
			case 'a':
			case 'A':
				if (!Element.isAlphabetic(data))
					return false;
				break;
			case 'm':
			case 'M':
				if (!Element.isMixedAlpha(data))
					return false;
				break;
			case 'n':
			case 'N':
				if (elmtFormat.isHasPoint()) {
					if (!Element.isDecimal(data))
						return false;
				} else {
					if (!Element.isNumeric(data))
						return false;
				}
				break;
			case 't':
			case 'T':
				if (!Element.isText(data))
					return false;
				break;
			default:
			}

		}
		return true;
	}
}
