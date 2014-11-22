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
package com.dream.messaging.engine.ra;

import java.util.Map;

import org.w3c.dom.Node;

import com.dream.messaging.DataConvertException;
import com.dream.messaging.Message;
import com.dream.messaging.engine.Message2Obj;
import com.dream.messaging.engine.MessageEngine;
import com.dream.messaging.engine.Obj2Message;


/**
 * The Class RAEnvelopeEngine is responsible for processing message header data.
 * 
 * <p>
 * 
 * @author Liang Yanpeng
 * @author Zhang Fu
 */
public class RAEnvelopeEngine extends MessageEngine{

	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.engine.MessageEngine#toMessage(java.lang.Object, java.lang.String, java.lang.String, java.lang.String, java.util.Map)
	 */
	@Override
	public Message toMessage(Object object, String transCode, String version, String type, Map condition)  throws DataConvertException {
		Obj2Message convertor = new Obj2Message(getSystemId(), new RABitDataHandler());
		String delimiterOfValidValue = (String)getProperties().get(DELIMETER_VALIDVALUE);
		if(delimiterOfValidValue!=null)convertor.setDelimiterOfValidValue(delimiterOfValidValue);
		Node msgNode = this.getMessageConfig(transCode, version);
		Message msg;
		try {
			msg = convertor.generate(msgNode, object);
		} catch (Exception e) {
			throw new DataConvertException("error happened when marshal",e);
		}
		condition.putAll(convertor.getConditionMap());
		return msg;

	}

	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.engine.MessageEngine#toObject(com.dawninfotek.base.packet.common.Message, java.lang.Object, java.lang.String, java.lang.String, java.lang.String, java.util.Map)
	 */
	@Override
	public Object toObject(Message msg,Object obj, String transCode, String version, String type, Map condition)  throws DataConvertException {
		
		Message2Obj convertor = new Message2Obj(getSystemId(), new RABitDataHandler());
		convertor.getConditionMap().putAll(condition);
		String delimiterOfValidValue = (String)getProperties().get(DELIMETER_VALIDVALUE);
		if(delimiterOfValidValue!=null)convertor.setDelimiterOfValidValue(delimiterOfValidValue);
		Node msgNode = this.getMessageConfig(transCode, version);
		try {
			return convertor.generate(msgNode, msg,obj);
		} catch (Exception e) {
			throw new DataConvertException("error happened when unmarshal",e);
		}

	}

	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.engine.MessageEngine#extractHeader(com.dawninfotek.base.packet.common.Message, java.lang.String, java.lang.String, java.util.Map)
	 */
	@Override
	public Object extractHeader(Message msg, String header, String version, Map condition)  throws DataConvertException {
		
		return null;
	}

}
