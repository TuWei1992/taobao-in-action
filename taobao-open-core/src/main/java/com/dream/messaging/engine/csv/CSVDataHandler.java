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
package com.dream.messaging.engine.csv;

import java.io.IOException;

import org.apache.commons.lang.StringUtils;
import org.w3c.dom.Node;

import com.dream.messaging.DataConvertException;
import com.dream.messaging.Message;
import com.dream.messaging.StringMessage;
import com.dream.messaging.engine.DataHandler;
import com.dream.messaging.parser.ElementAttr;
import com.dream.messaging.parser.ElementClassType;
import com.dream.messaging.parser.Strings;
import com.dream.messaging.utils.QueryNode;

// TODO: Auto-generated Javadoc
/**
 * The Class CSVDataHandler proccesses Comma-Separated Values data.
 * 
 * <p>
 * 
 * @author Liang Yanpeng
 * @author Zhang Fu
 */
public class CSVDataHandler extends DataHandler {

	/** The delimeter. */
	private String delimeter;

	/**
	 * Instantiates a new CSV data handler.
	 * 
	 * @param delimeter
	 *            the delimeter
	 */
	public CSVDataHandler(String delimeter) {
		super();
		if (delimeter != null) {
			this.delimeter = Strings.replaceESC(delimeter);
		}
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
			message.append(append == null ? "" : append).append(this.delimeter);
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
			message.append(append == null ? "" : append);
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
		if (append != null) {
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
	public Object createMessageData(Object data, Node node)
			throws DataConvertException {
		String formatedString = this.createStringMessage(data, node);
		if (formatedString == null) {
			return null;
		}
		String clzName = QueryNode.getAttribute(node, ElementAttr.Attr_Class);
		if (clzName == null) {
			return formatedString;
		} else {
			if (clzName.equalsIgnoreCase(ElementClassType.CLASS_DECIMAL)
					|| clzName.equalsIgnoreCase(ElementClassType.CLASS_FLOAT)
					|| (clzName.equalsIgnoreCase(ElementClassType.CLASS_DOUBLE))) {
				formatedString = StringUtils.remove(formatedString, ".");
			}
		}
		return formatedString;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dawninfotek.base.packet.engine.DataHandler#createValue(java.lang.
	 * Object, org.w3c.dom.Node)
	 */
	@Override
	public Object createValue(Object data, Node node) {
		String sData = (String) data;
		String clsname = QueryNode.getAttribute(node, ElementAttr.Attr_Class);
		if ((clsname != null)
				&& (clsname.equalsIgnoreCase(ElementClassType.CLASS_DECIMAL)
						|| clsname
								.equalsIgnoreCase(ElementClassType.CLASS_DOUBLE) || clsname
						.equalsIgnoreCase(ElementClassType.CLASS_FLOAT))) {
			String format = QueryNode.getAttribute(node,
					ElementAttr.Attr_Format);
			if (format != null) {
				int index = format.indexOf('s');
				char sign = 0;
				if (index >= 0) {
					format = format.substring(0, index);
					if (sData.startsWith("+") || sData.startsWith("-")) {
						sign = sData.charAt(0);
						sData = sData.substring(1);

					} else if (sData.endsWith("+") || sData.endsWith("-")) {
						sign = sData.charAt(sData.length() - 1);
						sData = sData.substring(0, sData.length() - 1);
					} else if (sData.endsWith(" ")) {
						sign = '+';
						sData = sData.substring(0, sData.length() - 1);
					}

				}
				if (sData.indexOf('.') < 0) {
					index = format.indexOf('p');
					if (index >= 0) {
						format = format.substring(index + 1);
						index = format.indexOf('s');
						if (index >= 0) {
							format = format.substring(0, index);
						}
					}
					int factor = Integer.parseInt(format);
					int len = sData.length();
					if (len > factor) {
						sData = sData.substring(0, len - factor) + "."
								+ sData.substring(len - factor);
					}
				}
				sData = sign + sData;
			}
		}
		return this.createValue(sData, node);
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
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dawninfotek.base.packet.engine.DataHandler#newMessage()
	 */
	@Override
	public Message newMessage() {

		return new StringMessage();
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

		try {
			return Integer.parseInt(this.readString(message, node));
		} catch (NumberFormatException ex) {
			return -1;
		}

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
		String fixed = QueryNode.getAttribute(node, ElementAttr.Attr_Fixed);
		Object data = null;
		if (fixed != null) {
			fixed = Strings.replaceESC(fixed);

			String msg = (String) message.getContent();
			int len = fixed.length();
			data = msg.substring(this.offset, len + this.offset);
			this.offset += len;
			return data;
		} else {
			return this.readString(message, node);
		}

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
		if (fixed != null) {
			fixed = Strings.replaceESC(fixed);

			String msg = (String) message.getContent();
			int len = fixed.length();
			String data = msg.substring(this.offset, len + this.offset);
			this.offset += len;
			return data;
		}
		return null;
	}

	/**
	 * Read string.
	 * 
	 * @param message
	 *            the message
	 * @param node
	 *            the node
	 * 
	 * @return the string
	 */
	private String readString(Message message, Node node) {
		String msg = (String) message.getContent();
		int index = msg.indexOf(this.delimeter, this.offset);
		if (index < 0) {
			return null;
		}
		String data = msg.substring(this.offset, index);
		this.offset = index + this.delimeter.length();
		// 对金额数据加上小数点

		String sData = data;
		String clsname = QueryNode.getAttribute(node, ElementAttr.Attr_Class);
		if ((clsname != null)
				&& (clsname.equalsIgnoreCase(ElementClassType.CLASS_DECIMAL)
						|| clsname
								.equalsIgnoreCase(ElementClassType.CLASS_DOUBLE) || clsname
						.equalsIgnoreCase(ElementClassType.CLASS_FLOAT))) {
			sData = StringUtils.remove(sData, ",");
			String format = QueryNode.getAttribute(node,
					ElementAttr.Attr_Format);
			if (format != null) {
				int idx = format.indexOf('s');
				char sign = 0;
				if (idx >= 0) {
					format = format.substring(0, idx);
					if (sData.startsWith("+") || sData.startsWith("-")) {
						sign = sData.charAt(0);
						sData = sData.substring(1);

					} else if (sData.endsWith("+") || sData.endsWith("-")) {
						sign = sData.charAt(sData.length() - 1);
						sData = sData.substring(0, sData.length() - 1);
					}
				}
				if (sData.indexOf('.') < 0) {
					idx = format.indexOf('p');
					if (idx >= 0) {
						format = format.substring(idx + 1);
						idx = format.indexOf('s');
						if (idx >= 0) {
							format = format.substring(0, idx);
						}
					}
					int factor = Integer.parseInt(format);
					int len = sData.length();
					if (len > factor) {
						sData = sData.substring(0, len - factor) + "."
								+ sData.substring(len - factor);
					}
				}
				sData = sData + sign;
			}
		}
		return sData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dawninfotek.base.packet.engine.DataHandler#validateData(java.lang
	 * .Object, org.w3c.dom.Node)
	 */
	@Override
	public boolean validateData(Object data, Node node) {
		return true;
	}

}
