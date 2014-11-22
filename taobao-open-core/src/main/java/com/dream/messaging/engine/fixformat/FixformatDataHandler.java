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
package com.dream.messaging.engine.fixformat;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Node;

import com.dream.messaging.ByteArrayMessage;
import com.dream.messaging.DataConvertException;
import com.dream.messaging.Message;
import com.dream.messaging.engine.DataHandler;
import com.dream.messaging.parser.ElementAttr;
import com.dream.messaging.parser.ElementClassType;
import com.dream.messaging.parser.ElementFormat;
import com.dream.messaging.parser.ElementFormatParseException;
import com.dream.messaging.utils.QueryNode;



/**
 * The Class FixformatDataHandler processes the fixed format data.
 * 
 * <p>
 * Note that this handler must get some properties form
 * {@link FixformatMessageEngine} while processing data.
 * 
 * @author Liang Yanpeng
 * @author Zhang Fu
 * 
 * @see FixformatMessageEngine
 */
public class FixformatDataHandler extends DataHandler {


	/** The engine. */
	private FixformatMessageEngine engine;

	/** The Constant numberClasses. */
	private static final String[] numberClasses = { "int", "long", "float",
			"double", "decimal", "counter" };


	/**
	 * Instantiates a new fixformat data handler.
	 * 
	 * @param engine
	 *            the engine
	 */
	public FixformatDataHandler(FixformatMessageEngine engine) {
		super();
		this.engine = engine;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dawninfotek.base.packet.engine.DataHandler#appendData(com.dawninfotek
	 * .base.packet.common.Message, java.lang.Object)
	 */
	@Override
	public void appendData(Message message, Object append)
			throws DataConvertException {
		try {
			message.append(append);
		} catch (IOException e) {
			throw new DataConvertException("", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @seecom.dawninfotek.base.packet.engine.DataHandler#appendFixedData(com.
	 * dawninfotek.base.packet.common.Message, java.lang.Object)
	 */
	@Override
	public void appendFixedData(Message message, Object append)
			throws DataConvertException {
		try {
			message.append(append);
		} catch (IOException e) {
			throw new DataConvertException("", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dawninfotek.base.packet.engine.DataHandler#appendMessage(com.dawninfotek
	 * .base.packet.common.Message, com.dawninfotek.base.packet.common.Message)
	 */
	@Override
	public void appendMessage(Message message, Message append)
			throws DataConvertException {
		if ((message != null) && (append != null)) {
			try {
				message.append(append.getContent());
			} catch (IOException e) {
				throw new DataConvertException("", e);
			}
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dawninfotek.base.packet.engine.DataHandler#createMessageData(java
	 * .lang.Object, org.w3c.dom.Node)
	 */
	@Override
	public Object createMessageData(Object objData, Node node)
			throws DataConvertException {
		// if the objData is the byte[],return directly
		if (objData instanceof byte[]) {
			return objData;
		}
		String formatedString = this.createStringMessage(objData, node);
		if (formatedString == null) {
			return null;
		}
		String format = QueryNode.getAttribute(node, ElementAttr.Attr_Format);
		ElementFormat elementFormat = null;
		if (format != null) {
			try {
				elementFormat = new ElementFormat(format);
			} catch (ElementFormatParseException e) {
				throw new DataConvertException(
						"ElementFormatParseException occurs when institiated the object ElementFormat",
						e);
			}
		}
		// must define the length in fixformat
		int length = Integer.parseInt(QueryNode.getAttribute(node,
				ElementAttr.Attr_Length));

		if (objData instanceof Number) {// || isNumber(clsname)

			// must define the format if this is a Number
			// remove point first
			if ((elementFormat != null) && elementFormat.isHasPoint()) {
				formatedString = StringUtils.remove(formatedString, ".");
			}
			// move the sign to the end of the number

			if (formatedString.startsWith("-")
					|| formatedString.startsWith("+")) {
				String sign = formatedString.substring(0, 1);
				formatedString = formatedString.substring(1) + sign;
			}
			return this.fillUpAsByteArray(formatedString, length, true);
		} else {
			return this.fillUpAsByteArray(formatedString, length, false);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dawninfotek.base.packet.engine.DataHandler#createValue(java.lang.
	 * Object, org.w3c.dom.Node)
	 */
	@Override
	public Object createValue(Object msgData, Node node)
			throws DataConvertException {

		Object result = null;

		if (msgData instanceof byte[]) {
			// parser the byte[] to a String which presents the object value
			String clsname = QueryNode.getAttribute(node,
					ElementAttr.Attr_Class);

			boolean isNumber = this.isNumber(clsname);

			String encoding = (String) this.engine.getProperties().get(
					FixformatMessageEngine.ENCORDING);
			String type = isNumber ? FixformatMessageEngine.NUMBER
					: FixformatMessageEngine.TEXT;
			boolean fromLeft = Boolean.getBoolean((String) this.engine
					.getProperties().get(
							type + FixformatMessageEngine.START_FROM_LEFT));
			String fillUpWith = (String) this.engine.getProperties().get(
					type + FixformatMessageEngine.FILL_UP_WITH);

			String aString = null;
			try {
				aString = new String((byte[]) msgData, encoding);
			} catch (UnsupportedEncodingException e) {
				throw new DataConvertException("encoding " + encoding
						+ " is not supported,please check it.", e);
			}

			// remove fill up
			if (fromLeft) {
				aString = StringUtils.stripEnd(aString, fillUpWith);
			} else {
				aString = StringUtils.stripStart(aString, fillUpWith);
			}

			// check if the string empty
			if (StringUtils.isEmpty(aString)) {
				if (isNumber) {
					aString = "0";
				} else {
					aString = "";
				}

				return this.createValue(aString, node);
			}

			if (isNumber) {
				// remove sign
				String sign = "";
				if (aString.equals("+") || aString.equals("-")) {
					aString = aString + "0";
				}
				if (aString.endsWith("+") || aString.endsWith("-")) {
					sign = aString.substring(aString.length() - 1);
					aString = sign + aString.substring(0, aString.length() - 1);
				}

				if (clsname.equalsIgnoreCase(ElementClassType.CLASS_DECIMAL)
						|| clsname
								.equalsIgnoreCase(ElementClassType.CLASS_FLOAT)
						|| (clsname
								.equalsIgnoreCase(ElementClassType.CLASS_DOUBLE))) {
					String format = QueryNode.getAttribute(node,
							ElementAttr.Attr_Format);
					int index = format.indexOf('p');
					if (index < 0) {
						index = format.indexOf('P');
					}
					int fractionLen = Integer.valueOf(format
							.substring(index + 1));
					if (aString.length() - fractionLen > 0) {
						aString = aString.substring(0, aString.length()
								- fractionLen)
								+ "."
								+ aString.substring(aString.length()
										- fractionLen);
					} else {
						StringBuffer sb = new StringBuffer(aString);
						while (sb.length() < fractionLen) {
							sb.insert(0, fillUpWith);
						}
						aString = sb.toString();
						aString = "0." + aString;
					}

				}
			}
			result = this.createValue(aString, node);
		} else {
			throw new UnsupportedOperationException(
					"The object for createValue must be a byte array");
		}

		return result;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dawninfotek.base.packet.engine.DataHandler#formatMessage(java.lang
	 * .Object, org.w3c.dom.Node)
	 */
	@Override
	public Object formatMessage(Object msgData, Node node) {

		return msgData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dawninfotek.base.packet.engine.DataHandler#newMessage()
	 */
	@Override
	public Message newMessage() {

		return new ByteArrayMessage();

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dawninfotek.base.packet.engine.DataHandler#readCount(com.dawninfotek
	 * .base.packet.common.Message, org.w3c.dom.Node)
	 */
	@Override
	public int readCount(Message message, Node node) {
		return getCount();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dawninfotek.base.packet.engine.DataHandler#readData(com.dawninfotek
	 * .base.packet.common.Message, org.w3c.dom.Node)
	 */
	@Override
	public Object readData(Message message, Node node) {

		Object result = null;

		String fixed = QueryNode.getAttribute(node, ElementAttr.Attr_Fixed);
		int length = Integer.parseInt(QueryNode.getAttribute(node,
				ElementAttr.Attr_Length));

		if (fixed != null) {
			// in response message, the fixed value will not be process

		} else {

			byte[] msgContent = (byte[]) message.getContent();

			if (this.offset + length <= msgContent.length) {

				result = new byte[length];
				System.arraycopy(msgContent, this.offset, result, 0, length);
			}

		}

		this.offset = this.offset + length;

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dawninfotek.base.packet.engine.DataHandler#validateData(java.lang
	 * .Object, org.w3c.dom.Node)
	 */
	@Override
	public boolean validateData(Object msgData, Node node) {
		return true;
	}

	/**
	 * Fill up as byte array.
	 * 
	 * @param source
	 *            the source
	 * @param length
	 *            the length
	 * @param isNumber
	 *            the is number
	 * 
	 * @return the byte[]
	 */
	private byte[] fillUpAsByteArray(String source, int length, boolean isNumber) {
		String aString = source;
		byte[] result = new byte[length];

		String encoding = (String) this.engine.getProperties().get(
				FixformatMessageEngine.ENCORDING);
		String type = isNumber ? FixformatMessageEngine.NUMBER
				: FixformatMessageEngine.TEXT;
		boolean fromLeft = Boolean.parseBoolean((String) this.engine
				.getProperties().get(
						type + FixformatMessageEngine.START_FROM_LEFT));
		if (source.length() <= length) {

			String fillUpWith = (String) this.engine.getProperties().get(
					type + FixformatMessageEngine.FILL_UP_WITH);
			StringBuffer sb = new StringBuffer();

			while (sb.length() < length - source.length()) {
				sb.append(fillUpWith);
			}

			if (fromLeft) {
				aString = source + sb.toString();
			} else {
				aString = sb.append(source).toString();
			}

		}
		try {
			byte[] ba;

			ba = aString.getBytes(encoding);
			// the length of byte array might larger than the length since the
			// not unicode String.

			if (fromLeft) {
				System.arraycopy(ba, 0, result, 0, length);
			} else {
				System.arraycopy(ba, ba.length - length, result, 0, length);
			}
		} catch (UnsupportedEncodingException e) {
			// FixformatDataHandler.logger.fatal(this,
			// "UnsupportedEncodingException occurs in method fillUpAsByteArray,encoding is "
			// + encoding);
		}

		return result;

	}

	/**
	 * Checks if is number.
	 * 
	 * @param className
	 *            the class name
	 * 
	 * @return true, if is number
	 */
	private boolean isNumber(String className) {
		boolean result = false;
		for (String element : FixformatDataHandler.numberClasses) {
			if (element.equals(className)) {
				result = true;
				break;
			}
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dawninfotek.base.packet.engine.DataHandler#formatStringData(java.
	 * lang.String, org.w3c.dom.Node)
	 */
	@Override
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
			if (max < min) {
				max = min;
			}
			if (value.length() < min) {
				// LogManager.logInfo("element value shorter than the min length:"
				// + QueryNode.getAttribute(node, ElementAttr.Attr_Name)
				// + " value:" + value);
				char ch = format.charAt(0);

				boolean isNumber = ((ch == 'n') || (ch == 'N')) ? true : false;
				String type = isNumber ? FixformatMessageEngine.NUMBER
						: FixformatMessageEngine.TEXT;
				boolean fromLeft = Boolean.parseBoolean((String) this.engine
						.getProperties().get(
								type + FixformatMessageEngine.START_FROM_LEFT));
				String fillUpWith = (String) this.engine.getProperties().get(
						type + FixformatMessageEngine.FILL_UP_WITH);
				StringBuffer sb = new StringBuffer();

				while (sb.length() < min - value.length()) {
					sb.append(fillUpWith);
				}

				if (fromLeft) {
					value = value + sb.toString();
				} else {
					value = sb.append(value).toString();
				}

				String clzName = QueryNode.getAttribute(node,
						ElementAttr.Attr_Class);
				if (clzName != null) {
					if (clzName.equalsIgnoreCase(ElementClassType.CLASS_DOUBLE)
							|| clzName
									.equalsIgnoreCase(ElementClassType.CLASS_DECIMAL)
							|| clzName
									.equalsIgnoreCase(ElementClassType.CLASS_FLOAT)) {
						value = StringUtils.remove(value, ".");
					}
				}
			}
			if (value.length() > max) {
				// LogManager.logError("element value longer than the max length:"
				// + QueryNode.getAttribute(node, ElementAttr.Attr_Name)
				// + " value:" + value);
				value = value.substring(0, max);
			}
			if (this.validateStringElemnet(eformat, value)) {
				// dataHandler.appendMessage(message, value);
				// return message;
				return value;
			} else {
				// LogManager.logError("element value invalid:"
				// + QueryNode.getAttribute(node, ElementAttr.Attr_Name)
				// + " value:" + value);
			}

		} catch (ElementFormatParseException e) {
			// FixformatDataHandler.logger.error(this,
			// "ElementFormatParseException occurs in method formatStringData,format is "
			// + format);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dawninfotek.base.packet.engine.DataHandler#readFixedData(com.dawninfotek
	 * .base.packet.common.Message, java.lang.String)
	 */
	@Override
	public Object readFixedData(Message message, String fixed)
			throws DataConvertException {
		return null;
	}
}
