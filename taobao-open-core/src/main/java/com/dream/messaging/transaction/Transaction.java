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
package com.dream.messaging.transaction;

import java.util.Map;

import org.apache.log4j.Logger;

import com.dream.messaging.DataConvertException;
import com.dream.messaging.Message;
import com.dream.messaging.MessagingException;
import com.dream.messaging.engine.MessageEngine;
import com.dream.messaging.sender.MessageSender;




/**
 * Abstract implementation of a single transaction which was generated by E-banking channel.
 * 
 * <p>Provides base implementations of all convenience methods, with the
 * implementation of actual property access left to subclasses.
 * 
 * @author Liang Yanpeng
 * @author Zhang Fu
 */
public abstract class Transaction {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(Transaction.class);
	
	
	/**
	 * @return the engine
	 */
	public MessageEngine getEngine() {
		return engine;
	}

	/**
	 * @param engine the engine to set
	 */
	public void setEngine(MessageEngine engine) {
		this.engine = engine;
	}

	/**
	 * @return the sender
	 */
	public MessageSender getSender() {
		return sender;
	}

	/**
	 * @param sender the sender to set
	 */
	public void setSender(MessageSender sender) {
		this.sender = sender;
	}



	private MessageEngine engine;
	
	private MessageSender sender;
	
	protected String version;
	
	


	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}



	/** The properties. */
	private Map<String,Object> properties;

	/**
	 * Gets the properties.
	 * 
	 * @return the properties
	 */
	public Map<String,Object> getProperties() {
		return properties;
	}

	/**
	 * Sets the properties.
	 * 
	 * @param properties the new properties
	 */
	public void setProperties(Map<String,Object> properties) {
		this.properties = properties;
	}
	
	

	/**
	 * According to the parameter,packaging the reqObj to {@link Message},send
	 * it through a specific {@link MessageSender},and then receive the
	 * {@link Message}, unpacking the {@link Message} to an Object and return
	 * it.
	 * 
	 * @param reqObj the request object holding transaction data
	 * @param systemId the system id represents the mode of packaging,actually it
	 * decides the type of {@link MessageEngine}
	 * @param transCode the transaction code represents a specific transaction
	 * @param version the version represents the version of message configuration
	 * @param targetSysId the target system id which is going to connect
	 * 
	 * @return the object
	 * 
	 * @throws Exception the exception
	 * @throws BaseException the base exception
	 */
	public Object doTransaction(Object reqObj, String sourceSystemId, String transCode,String targetSystemId) throws MessagingException {
		if (logger.isDebugEnabled()) {
			logger.debug("Entry into Transaction.doTransaction - message format:["+sourceSystemId+"],to target system:["+targetSystemId+"],version:["+version+"]"); //$NON-NLS-1$
		}
		
		Object returnObject = doTransaction(reqObj, transCode, null);
		
		if (logger.isDebugEnabled()) {
			logger.debug("Exit Transaction.doTransaction - message format:["+sourceSystemId+"],to target system:["+targetSystemId+"],version:["+version+"]"); //$NON-NLS-1$
		}
		return returnObject;
	}
	
	
	/**
	 * According to the parameter,packaging the reqObj to {@link Message},send
	 * it through a specific {@link MessageSender},and then receive the
	 * {@link Message}, unpacking the {@link Message} to an Object and return
	 * it.
	 * 
	 * @param reqObj the request object holding transaction data
	 * @param systemId the system id represents the mode of packaging,actually it
	 * decides the type of {@link MessageEngine}
	 * @param transCode the transaction code represents a specific transaction
	 * @param version the version represents the version of message configuration
	 * @param targetSysId the target system id which is going to connect
	 * 
	 * @return the object
	 * 
	 * @throws Exception the exception
	 * @throws BaseException the base exception
	 */
	public Object doTransaction(Object reqObj, String sourceSystemId, String transCode,String targetSystemId, Map<String,Object> condition) throws MessagingException {
		if (logger.isDebugEnabled()) {
			logger.debug("Entry into Transaction.doTransaction - message format:["+sourceSystemId+"],to target system:["+targetSystemId+"],version:["+version+"]"); //$NON-NLS-1$
		}
		
		Object returnObject = doTransaction(reqObj, transCode, condition);
		
		if (logger.isDebugEnabled()) {
			logger.debug("Exit Transaction.doTransaction - message format:["+sourceSystemId+"],to target system:["+targetSystemId+"],version:["+version+"]"); //$NON-NLS-1$
		}
		return returnObject;
	}

	

	/**
	 * Overload method of doTrans,left to subclass.
	 * 
	 * @param reqObj the request object
	 * @param transCode the transaction code
	 * @param condition
	 * 
	 * @return the object
	 * @throws Exception 
	 * 
	 * @throws Exception the exception
	 * @throws BaseException the base exception
	 */
	public Object doTransaction(Object reqObj, String transCode, Map<String,Object> condition)
			throws MessagingException{
		if(transCode==null||reqObj==null){
			throw new DataConvertException("The message code or the request object cannot be null.");
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("=================================Entry into Transaction with transaction code:"+transCode+"=========================================="); //$NON-NLS-1$
		}
		
		Message msgReq = engine.toMessage(reqObj, transCode,version,MessageEngine.MTYPE_REQUEST, condition);
		
		if(msgReq==null){
			throw new DataConvertException("The message engine "+ engine + " can not generate a request message.");
		}
		
		if (logger.isInfoEnabled()) {
			//logger.debug("==========================Request Message via Messaging Component Start===================================="); //$NON-NLS-1$
			logger.info("==========================Request Message Start:" + msgReq.toString()); //$NON-NLS-1$
			//logger.debug("==========================Request Message via Messaging Component End======================================"); //$NON-NLS-1$
		}
		
		msgReq.setMessageId(transCode);
		
		
		if (logger.isDebugEnabled()) {
			logger.debug("=================================Going to send via message sender:"+sender+" Start=========================================="); //$NON-NLS-1$
		}
		
		Message msgRsp = sender.send(reqObj, msgReq);
		
		if (logger.isDebugEnabled()) {
			logger.debug("=================================Going to send via message sender:"+sender+" End=========================================="); //$NON-NLS-1$
		}
		
		if(msgRsp==null){
			throw new DataConvertException("The message sender "+ sender + " can not recieve a response message.");
		}
		
		if (logger.isInfoEnabled()) {
			//logger.debug("==========================Response Message via Messaging Component Start===================================="); //$NON-NLS-1$
			logger.info("==========================Response Message Start:" + msgRsp.toString()); //$NON-NLS-1$
			//logger.debug("==========================Response Message via Messaging Component End===================================="); //$NON-NLS-1$
		}


		Object rspObj = engine.toObject(msgRsp, transCode, version,MessageEngine.MTYPE_RESPONSE, condition);
		
		if(rspObj==null){
			throw new DataConvertException("The message engine "+ engine + " can not construct a response object.");
		}
		

		if (logger.isDebugEnabled()) {
			logger.debug("=================================Exit Transaction with transaction code:"+transCode+"========================================="); //$NON-NLS-1$
		}
		return rspObj;
	}

}
