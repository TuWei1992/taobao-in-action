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
package com.dream.messaging.sender;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.dream.messaging.ByteArrayMessage;
import com.dream.messaging.ConnectionException;
import com.dream.messaging.Message;
import com.dream.messaging.MessagingException;
import com.dream.messaging.StringMessage;



/**
 * The Class MessageSender is an abstract implemention of message sender that sends and recieves message from various channels by various format.
 * 
 * <p>
 * 
 * @author Liang Yanpeng
 * @author Zhang Fu
 */
public  class SimulatorMessageSender extends MessageSender{
	
	private static final Logger logger = Logger.getLogger(SimulatorMessageSender.class);
	private final static String SURFIX = ".txt";
	
	private final static String ENCODING = "encoding";
	private final static String DIRECTORY = "directory";
	
	private Map<String,String> properties;
	
	
	public Map<String, String> getProperties() {
		return properties;
	}



	public void setProperties(Map<String, String> properties) {
		this.properties = properties;
	}



	/**
	 * Instantiates a new message sender.
	 * 
	 * @param targetSysId the target sys id
	 * @param messageFormat the system id
	 */
	public SimulatorMessageSender() {
	}
	


	/**
	 * Send.
	 * 
	 * @param obj1 the obj1
	 * @param msgReq the msg req
	 * 
	 * @return the message
	 * @throws MessagingException 
	 * 
	 * @throws Exception the base exception
	 */
	public  Message send(Object obj, Message msgReq) throws ConnectionException, MessagingException{
		String dir = properties.get(DIRECTORY);
		if(dir == null){
			logger.error("Please set directory!!!!!!");
			return null;
		}
		String encoding = properties.get(ENCODING);
		if(encoding == null){
			logger.error("Please set encoding,now use UTF-8!!!!!!");
			encoding = "UTF-8";
		}
		String tranCode = msgReq.getMessageId();
		if(tranCode == null){
			logger.error("Please set transaction code!!!!!!!");
			return null;
		}
		
		String path = dir+File.separator+tranCode+SURFIX;
		logger.info("The response message path is:"+path);
		File file = new File(path);
		
		
		Object content = null;
		if (msgReq instanceof ByteArrayMessage){
			logger.info("The response message read with bytes.");
			try {
				content = FileUtils.readFileToByteArray(file);
			} catch (IOException e) {
				logger.error("ERROR.",e);
				return null;
			}
		}
		
		if(msgReq instanceof StringMessage ){
			logger.info("The response message read with string.");
			try {
				content = FileUtils.readFileToString(file ,encoding );
			} catch (IOException e) {
				logger.error("ERROR.",e);
				return null;
			}
		}
		
		Class cls =  msgReq.getClass();
		
		Message response = null;
		try {
			response = (Message) cls.newInstance();
		} catch (InstantiationException e) {
			logger.error("ERROR.",e);
			return null;
		} catch (IllegalAccessException e) {
			logger.error("ERROR.",e);
			return null;
		}
		
		try {
			response.setContent(content);
		} catch (IOException e) {
			logger.error("ERROR.",e);
			return null;
		}
		
		return response;
	}

	/**
	 * Send.
	 * 
	 * @param req the req
	 * 
	 * @return the message
	 * @throws MessagingException 
	 * 
	 * @throws Exception the base exception
	 */
	protected  Message send(Message req) throws ConnectionException, MessagingException{
		return this.send(null, req);
	}





}
