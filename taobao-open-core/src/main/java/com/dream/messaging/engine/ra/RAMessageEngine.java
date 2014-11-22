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

import java.util.HashMap;
import java.util.Map;
import org.w3c.dom.Node;

import com.dream.messaging.ByteArrayMessage;
import com.dream.messaging.DataConvertException;
import com.dream.messaging.InvalidXpathException;
import com.dream.messaging.Message;
import com.dream.messaging.engine.Message2Obj;
import com.dream.messaging.engine.MessageEngine;
import com.dream.messaging.engine.Obj2Message;




// TODO: Auto-generated Javadoc
/**
 * The Class RAMessageEngine.
 * 
 * <p>
 * 
 * @author Liang Yanpeng
 * @author Zhang Fu
 */
public class RAMessageEngine extends MessageEngine {
	
	/** The Constant RSP_CODE_SUCCESS. */
	private static final int RSP_CODE_SUCCESS = 0;
	
	/** The Constant ENCODING. */
	public static final String ENCODING = "encoding";
	
	/** The Constant ENVELOPE. */
	public static final String ENVELOPE = "envelope";
	
	/** The Constant ENVELOPE_VERSION. */
	public static final String ENVELOPE_VERSION = "envelope_version";
	
	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.engine.MessageEngine#toMessage(java.lang.Object, java.lang.String, java.lang.String, java.lang.String, java.util.Map)
	 */
	@Override
	public Message toMessage(Object object, String transCode, String version, String type, Map condition) throws DataConvertException {
		try {
			Obj2Message convertor = new Obj2Message(getSystemId(), new RADataHandler((String) this
					.getProperties().get(ENCODING)));
			String delimiterOfValidValue = (String)getProperties().get(DELIMETER_VALIDVALUE);			
			if(delimiterOfValidValue!=null)convertor.setDelimiterOfValidValue(delimiterOfValidValue);
			Node msgNode = null;
			if(MessageEngine.MTYPE_REQUEST.equals(type)){
				msgNode = this.getRequestConfig(transCode, version);
			}else if(MessageEngine.MTYPE_RESPONSE.equals(type)){
				msgNode = this.getResponseConfig(transCode, version);
			}else{
				//log error
				throw new DataConvertException("request or response configuration has not been found.");
			}
			Message msg = convertor.generate(msgNode, object);
			condition.putAll(convertor.getConditionMap());
			//envelope
			RADomainEnvelope rade = new RADomainEnvelope();
			rade.setPkgType(1);
			rade.setMsgLen(msg.length());
			rade.setTranscode(Integer.parseInt(transCode));
			//rade.setUserId(0);
			MessageEngine raee = new RAEnvelopeEngine();
			raee.setProperties(properties);
			raee.setSystemId(getSystemId());
			String envelope_code = (String)getProperties().get(ENVELOPE);
			String envelope_version = (String)getProperties().get(ENVELOPE_VERSION);
			Message envelope = raee.toMessage(rade, envelope_code, envelope_version, type, condition);
			RAEnvelope rae = new RAEnvelope();
			rae.setContent(envelope.getContent());
			RAPackage pkg = new RAPackage();
			pkg.fill(rae, msg);
			return pkg;
		} catch (InvalidXpathException e) {
			throw new DataConvertException("error happen when parse ra request or response cofiguration .",e);
		} catch (Exception e) {
			throw new DataConvertException("error happen when parse ra request or response cofiguration .",e);
		}
	}

	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.engine.MessageEngine#toObject(com.dawninfotek.base.packet.common.Message, java.lang.Object, java.lang.String, java.lang.String, java.lang.String, java.util.Map)
	 */
	@Override
	public Object toObject(Message msg,Object obj, String transCode, String version, String type, Map condition) throws DataConvertException {
		try {
			RADomainEnvelope envelope = (RADomainEnvelope)extractHeader(msg, (String)getProperties().get(ENVELOPE), (String)getProperties().get(ENVELOPE_VERSION), condition);
			int len = envelope.getMsgLen();
			byte[] data = new byte[len];
			System.arraycopy((byte[])msg.getContent(),msg.length() - envelope.getMsgLen(), data, 0, len) ;
			Message content = new ByteArrayMessage();
			content.setContent(data);
			
			//if the rspCode not equals 0,we will handle the error message.
			if(!(RSP_CODE_SUCCESS==envelope.getRspCode())){
				Map result = new HashMap();
				result.put("rspCode", envelope.getRspCode());
				int errorLen = (int)(data[0]);
				byte[] error  = new byte[errorLen];
				System.arraycopy(data,1, error, 0, errorLen);
				result.put("rspMessage", new String(error,(String) this
						.getProperties().get(ENCODING)));
				return result;
			}
			Message2Obj convertor = new Message2Obj(getSystemId(), new RADataHandler((String) this
					.getProperties().get(ENCODING)));
			convertor.getConditionMap().putAll(condition);
			String delimiterOfValidValue = (String)getProperties().get(DELIMETER_VALIDVALUE);
			if(delimiterOfValidValue!=null)convertor.setDelimiterOfValidValue(delimiterOfValidValue);
			Node msgNode = null;
			if(MessageEngine.MTYPE_REQUEST.equals(type)){
				msgNode = this.getRequestConfig(transCode, version);
			}else if(MessageEngine.MTYPE_RESPONSE.equals(type)){
				msgNode = this.getResponseConfig(transCode, version);
			}else{
				//log error
				throw new DataConvertException("request or response configuration has not been found.");
			}
			return convertor.generate(msgNode, content,obj);
		} catch (InvalidXpathException e) {
			throw new DataConvertException("error happen when parse ra request or response cofiguration .",e);
		} catch (Exception e) {
			throw new DataConvertException("error happen when parse ra request or response cofiguration .",e);
		}
	}
	
	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.engine.MessageEngine#extractHeader(com.dawninfotek.base.packet.common.Message, java.lang.String, java.lang.String, java.util.Map)
	 */
	public  Object extractHeader(Message msg,String header,String version,Map condition) throws Exception{
		//envelope
		
		MessageEngine raee = new RAEnvelopeEngine();
		raee.setProperties(properties);
		raee.setSystemId(getSystemId());
		return raee.toObject(msg, header, version, null, condition);

	}
}
