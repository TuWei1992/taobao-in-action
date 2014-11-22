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

import com.dream.messaging.ConnectionException;
import com.dream.messaging.Message;
import com.dream.messaging.MessagingException;



/**
 * The Class MessageSender is an abstract implemention of message sender that sends and recieves message from various channels by various format.
 * 
 * <p>
 * 
 * @author Liang Yanpeng
 * @author Zhang Fu
 */
public abstract class MessageSender {
	
	
	
	/**
	 * Instantiates a new message sender.
	 * 
	 * @param targetSysId the target sys id
	 * @param messageFormat the system id
	 */
	public MessageSender() {
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
	public abstract Message send(Object obj, Message msgReq) throws ConnectionException, MessagingException;

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
	protected abstract Message send(Message req) throws ConnectionException, MessagingException;





}
