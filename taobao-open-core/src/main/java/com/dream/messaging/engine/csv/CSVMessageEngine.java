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

import java.util.Map;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;

import com.dream.messaging.DataConvertException;
import com.dream.messaging.InvalidXpathException;
import com.dream.messaging.Message;
import com.dream.messaging.MessageConst;
import com.dream.messaging.MessagingException;
import com.dream.messaging.engine.Message2Obj;
import com.dream.messaging.engine.MessageEngine;
import com.dream.messaging.engine.Obj2Message;

/**
 * The Class CSVMessageEngine parses Comma-Separated Values messages and generate Comma-Separated Values messages using {@link CSVDataHandler}.
 * 
 * <p>
 * 
 * @author Liang Yanpeng
 * @author Zhang Fu
 */
public class CSVMessageEngine extends MessageEngine {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CSVMessageEngine.class);
	
	/** The Constant logger. */
	
	
	/** The Constant DELIMETER. */
	public static final String DELIMETER = "delimiter";

	/** The Constant RESPONSE_HEADER. */
	private static final String RESPONSE_HEADER = "RspHeader";

	/** The Constant REQUEST_HEADER. */
	private static final String REQUEST_HEADER = "ReqHeader";

	/** The Constant NEW_TRANSCODE. */
	private static final String NEW_TRANSCODE = "newTransCode";

	/** The Constant RESPONSE_CODE. */
	private static final String RESPONSE_CODE = "responseCode";

	/** The Constant RESPONSE_SUCCESSFUL. */
	private static final String RESPONSE_SUCCESSFUL = "000000";

	/** The Constant RESPONES_FD6666. */
	private static final String RESPONES_FD6666 = "FD6666";

	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.engine.MessageEngine#toMessage(java.lang.Object, java.lang.String, java.lang.String, java.lang.String, java.util.Map)
	 */
	@Override
	public Message toMessage(Object object, String transCode, String version,
			String type, Map condition) throws MessagingException {
		try {
			Obj2Message convertor = new Obj2Message(getSystemId(),new CSVDataHandler((String) this.getProperties().get(DELIMETER)));
			if(condition!=null)
				convertor.getConditionMap().putAll(condition);
			String delimiterOfValidValue = (String) getProperties().get(DELIMETER_VALIDVALUE);
			if (delimiterOfValidValue != null)convertor.setDelimiterOfValidValue(delimiterOfValidValue);
			Node msgNode = null;
			if (MessageEngine.MTYPE_REQUEST.equals(type)) {
				msgNode = this.getRequestConfig(transCode, version);
			} else if (MessageEngine.MTYPE_RESPONSE.equals(type)) {
				msgNode = this.getResponseConfig(transCode, version);
			} else {
				throw new MessagingException("request or response configuration has not been found.");
			}
			Message msg = convertor.generate(msgNode, object);
			return msg;
		} catch (InvalidXpathException e) {
			throw e;
		}
	}

	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.engine.MessageEngine#toObject(com.dawninfotek.base.packet.common.Message, java.lang.Object, java.lang.String, java.lang.String, java.lang.String, java.util.Map)
	 */
	@Override
	public Object toObject(Message msg, Object obj, String transCode,
			String version, String type, Map condition)
			throws DataConvertException,MessagingException {
		// should get header first
		Object header = extractHeader(msg, RESPONSE_HEADER, version, condition);
		if(header!=null){
			transCode = (String) ((Map) header).get(NEW_TRANSCODE);
			String responseCode = (String) ((Map) header).get(RESPONSE_CODE);
			
			if (!(RESPONSE_SUCCESSFUL.equalsIgnoreCase(responseCode) || RESPONES_FD6666
					.equalsIgnoreCase(responseCode))) {
				return header;
			}
		}
		try {
			Message2Obj convertor = new Message2Obj(getSystemId(),
					new CSVDataHandler((String) this.getProperties().get(
							DELIMETER)));
			if(condition!=null)
				convertor.getConditionMap().putAll(condition);
			String delimiterOfValidValue = (String) getProperties().get(
					DELIMETER_VALIDVALUE);
			if (delimiterOfValidValue != null)
				convertor.setDelimiterOfValidValue(delimiterOfValidValue);
			Node msgNode = null;
			if (MessageEngine.MTYPE_REQUEST.equals(type)) {
				msgNode = this.getRequestConfig(transCode, version);
			} else if (MessageEngine.MTYPE_RESPONSE.equals(type)) {
				msgNode = this.getResponseConfig(transCode, version);
			} else {
			}
			return convertor.generate(msgNode, msg, obj);
		} catch (InvalidXpathException e) {
			return null;
		} 
	}

	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.engine.MessageEngine#extractHeader(com.dawninfotek.base.packet.common.Message, java.lang.String, java.lang.String, java.util.Map)
	 */
	@Override
	public Object extractHeader(Message msg, String header, String version,
			Map condition) throws DataConvertException {
		Message2Obj convertor = new Message2Obj(
				getSystemId(),
				new CSVDataHandler((String) this.getProperties().get(DELIMETER)));
		if(condition!=null)
			convertor.getConditionMap().putAll(condition);
		String delimiterOfValidValue = (String) getProperties().get(
				DELIMETER_VALIDVALUE);
		if (delimiterOfValidValue != null)
			convertor.setDelimiterOfValidValue(delimiterOfValidValue);
		Node msgNode = this.getMessageConfig(header, version);
		if(msgNode==null){
			return null;
		}
		return convertor.generate(msgNode, msg);
		
	}

}