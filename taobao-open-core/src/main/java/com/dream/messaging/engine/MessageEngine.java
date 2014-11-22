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

import java.util.Map;

import org.apache.log4j.Logger;
import org.w3c.dom.Node;

import com.dream.messaging.DataConvertException;
import com.dream.messaging.InvalidXpathException;
import com.dream.messaging.Message;
import com.dream.messaging.MessagingException;
import com.dream.messaging.utils.ConfigLoader;
import com.dream.messaging.utils.QueryNode;



/**
 * Abstract implementation of an message parser.
 * 
 * <p>
 * This message parser contains two important abstract methods {@link #toMessage(Object,String,String, String,Map) <code>toMessage</code>} 
 * and {@link #toObject(Message, String, String,String, Map) <code>toObject</code>}.
 * 
 * @author Liang Yanpeng
 * @author Zhang Fu
 * @see #toMessage(Object,String,String, String,Map) <code>toMessage</code>
 * @see #toObject(Message, String, String,String, Map) <code>toObject</code>
 */
public abstract class MessageEngine {
	/**
	 * Logger for this class
	 */
	protected Logger logger = Logger.getLogger(getClass());

	/** The Constant DELIMETER_VALIDVALUE. */
	protected static final String DELIMETER_VALIDVALUE = "delimiter_validvalue";

	/** The Constant MTYPE_REQUEST. */
	public static final String MTYPE_REQUEST = "REQ";

	/** The Constant MTYPE_RESPONSE. */
	public static final String MTYPE_RESPONSE = "RSP";

	/** The Constant VESION_UNDERLINE. */
	private static final String VESION_UNDERLINE = "_V";

	/** The Constant VESION_ZORE. */
	private static final String VESION_ZORE = "_V0";

	/** The messages configuration. */
	protected Map<String,Node> messagesConfig;

	/** The properties. */
	protected Map<String,Object> properties;

	/** The system id. */
	protected String systemId;
	
	
	

	/**
	 * Extract the header from message.
	 * 
	 * @param msg message
	 * @param header header transaction code
	 * @param version header version
	 * @param condition the condition
	 * 
	 * @return the object
	 * 
	 * @throws DataConvertException the data convert exception
	 */
	public abstract Object extractHeader(Message msg, String header,
			String version, Map condition) throws Exception;

	/**
	 * Gets the message configuration.
	 * 
	 * @param transCode the transaction code
	 * 
	 * @return the message configuration
	 */
	public Node getMessageConfig(String transCode) {
		return getMessageConfig(transCode, null);
	}

	/**
	 * Gets the message configuration.
	 * 
	 * @param transCode the transaction code
	 * @param version the version
	 * 
	 * @return the message configuration
	 */
	public Node getMessageConfig(String transCode, String version) {
		String key = (version == null ? transCode + VESION_ZORE : transCode
				+ VESION_UNDERLINE + version);
		return (Node) getRefNode(key);
	}

	/**
	 * Gets the properties.
	 * 
	 * @return the properties
	 */
	public Map<String,Object> getProperties() {
		return properties;
	}

	/**
	 * Gets the ref node.
	 * 
	 * @param name the name
	 * 
	 * @return the ref node
	 */
	public Node getRefNode(String name) {
		if (messagesConfig == null) {
			messagesConfig = ConfigLoader.getInstance().getMessageConfig(systemId);
		}
		return (Node) messagesConfig.get(name);
	}

	/**
	 * Gets the request config.
	 * 
	 * @param transCode the trans code
	 * @param version the version
	 * 
	 * @return the request config
	 * 
	 * @throws InvalidXpathException the invalid xpath exception
	 */
	public Node getRequestConfig(String transCode, String version)
			throws InvalidXpathException {
		return QueryNode.getNode(getMessageConfig(transCode, version),
				"./request");
	}

	/**
	 * Gets the response config.
	 * 
	 * @param transCode the trans code
	 * @param version the version
	 * 
	 * @return the response config
	 * 
	 * @throws InvalidXpathException the invalid xpath exception
	 */
	public Node getResponseConfig(String transCode, String version)
			throws InvalidXpathException {
		return QueryNode.getNode(getMessageConfig(transCode, version),
				"./response");
	}

	/**
	 * Gets the system id.
	 * 
	 * @return the system id
	 */
	public String getSystemId() {
		return systemId;
	}
	
	
	
	

	/**
	 * Sets the properties.
	 * 
	 * @param properties the new properties
	 */
	public void setProperties(Map properties) {
		this.properties = properties;
	}

	/**
	 * Sets the system id.
	 * 
	 * @param systemId the new system id
	 */
	public void setSystemId(String systemId) {
		this.systemId = systemId;
	}

	/**
	 * Generate the message from the given object.
	 * 
	 * @param object the object
	 * @param transCode the transaction code
	 * @param version the version
	 * @param type the type
	 * @param condition the condition
	 * 
	 * @return the message
	 * 
	 * @throws DataConvertException the data converting exception
	 */
	public abstract Message toMessage(Object object, String transCode,
			String version, String type, Map condition)
			throws MessagingException;

	/**
	 * Parse the message to existing object.
	 * 
	 * @param msg the msg
	 * @param obj the obj
	 * @param transCode the transaction code
	 * @param version the version
	 * @param type the type
	 * @param condition the condition
	 * 
	 * @return the object
	 * 
	 * @throws DataConvertException the data converting exception
	 */
	public abstract Object toObject(Message msg,Object obj, String transCode,
			String version, String type, Map condition)
			throws MessagingException;
	
	/**
	 * Parse the message to existing object.
	 * 
	 * @param msg
	 * @param transCode
	 * @param version
	 * @param type
	 * @param condition
	 * @return
	 * @throws MessagingException
	 */
	public Object toObject(Message msg,String transCode,
			String version, String type, Map condition)
			throws MessagingException{
		return this.toObject(msg, null, transCode, version, type, condition);
	}
	
	
	public String toString(){
		return "system id:"+this.systemId;
	}


}
