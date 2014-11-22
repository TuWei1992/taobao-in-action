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

import java.util.Map;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;

import com.dream.messaging.DataConvertException;
import com.dream.messaging.InvalidXpathException;
import com.dream.messaging.Message;
import com.dream.messaging.engine.Message2Obj;
import com.dream.messaging.engine.MessageEngine;
import com.dream.messaging.engine.Obj2Message;



/**
 * The Class FixformatMessageEngine parses fixed format messages using {@link com.ISO8583DataHandler.base.packet.engine.fixformat.FixformatDataHandler <code>FixformatDataHandler</code>}.
 * 
 * <p>
 * 
 * @author Liang Yanpeng
 * @author Zhang Fu
 * 
 * @see com.ISO8583DataHandler.base.packet.engine.fixformat.FixformatDataHandler <code>FixformatDataHandler</code>
 */
public class FixformatMessageEngine extends MessageEngine {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(FixformatMessageEngine.class);
	
	

	/** The Constant ENCORDING. */
	public static final String ENCORDING = "encoding";

	/** The Constant EXT_RESOURCE_KEY. */
	public static final String EXT_RESOURCE_KEY = "externalUDTResourceKey";

	/** The Constant FILL_UP_WITH. */
	public static final String FILL_UP_WITH = "fillUpWith";

	/** The Constant START_FROM_LEFT. */
	public static final String START_FROM_LEFT = "startFromLeft";

	/** The Constant TEXT. */
	public static final String TEXT = "t_";

	/** The Constant NUMBER. */
	public static final String NUMBER = "n_";

	/**
	 * Instantiates a new fixformat message engine.
	 */
	public FixformatMessageEngine() {
		super();
	}

	/**
	 * Instantiates a new fixformat message engine.
	 * 
	 * @param systemId the system id
	 */
	public FixformatMessageEngine(String systemId) {
		super();
	}


	

	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.engine.MessageEngine#toMessage(java.lang.Object, java.lang.String, java.lang.String, java.lang.String, java.util.Map)
	 */
	public Message toMessage(Object object, String transCode, String version, String type, Map condition) throws DataConvertException {
		try {
			Obj2Message convertor = new Obj2Message(getSystemId(), new FixformatDataHandler(this));
			if(condition!=null)
				convertor.getConditionMap().putAll(condition);
			Node msgNode = null;
			if (MessageEngine.MTYPE_REQUEST.equals(type)) {
				msgNode = getRequestConfig(transCode, version);
			} else if (MessageEngine.MTYPE_RESPONSE.equals(type)) {
				msgNode = getResponseConfig(transCode, version);
			} else {
				logger.fatal("request or response configuration has not been found.");
				throw new DataConvertException("request or response configuration has not been found.");
			}
			Message msg = convertor.generate(msgNode, object);
			return msg;
		} catch (InvalidXpathException e) {
			logger.error("",  e);
			throw new DataConvertException("error happened when marshal",e);
		}catch (Exception e) {
			logger.error("",  e);
			throw new DataConvertException("error happened when marshal",e);
		}

	}

	

	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.engine.MessageEngine#toObject(com.dawninfotek.base.packet.common.Message, java.lang.Object, java.lang.String, java.lang.String, java.lang.String, java.util.Map)
	 */
	public Object toObject(Message msg, Object sourceObj, String transCode, String version, String type, Map condition) throws DataConvertException {

		try {
			Message2Obj convertor = new Message2Obj(getSystemId(), new FixformatDataHandler(this));
			if(condition!=null)
				convertor.getConditionMap().putAll(condition);
			Node msgNode = null;
			if (MessageEngine.MTYPE_REQUEST.equals(type)) {
				msgNode = getRequestConfig(transCode, version);
			} else if (MessageEngine.MTYPE_RESPONSE.equals(type)) {
				msgNode = getResponseConfig(transCode, version);
			} else {
				logger.fatal("request or response configuration has not been found.");
				throw new DataConvertException("request or response configuration has not been found.");
			}
			return  convertor.generate(msgNode, msg);
		} catch (InvalidXpathException e) {
			logger.error("", e);
			throw new DataConvertException("error happened when unmarshal",e);
		}catch (Exception e) {
			logger.error("", e);
			throw new DataConvertException("error happened when unmarshal",e);
		}
	}

	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.engine.MessageEngine#extractHeader(com.dawninfotek.base.packet.common.Message, java.lang.String, java.lang.String, java.util.Map)
	 */
	@Override
	public Object extractHeader(Message msg, String header, String version, Map condition) throws DataConvertException {
		Message2Obj convertor = new Message2Obj(getSystemId(), new FixformatDataHandler(this));
		convertor.getConditionMap().putAll(condition);
		Node msgNode = this.getMessageConfig(header, version);
		return  convertor.generate(msgNode, msg);
	}

}
