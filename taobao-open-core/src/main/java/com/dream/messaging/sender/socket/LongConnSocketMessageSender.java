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
package com.dream.messaging.sender.socket;

import java.util.NoSuchElementException;

import org.apache.commons.pool2.ObjectPool;
import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.future.WriteFuture;
import org.apache.mina.core.session.IoSession;

import com.dream.messaging.ConnectionException;
import com.dream.messaging.Message;
import com.dream.messaging.sender.MessageSender;



/**
 * The Class CCMessageSender sends and recieves message relative to credit card system whose format is fixed format.
 * 
 * <p>
 * 
 * @author Zhang Fu
 */
public class LongConnSocketMessageSender extends MessageSender {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(LongConnSocketMessageSender.class);
	

	private ObjectPool<IoSession> objectPool;
	
	public ObjectPool<IoSession> getObjectPool(){
		return objectPool;
	}
	
	public void setObjectPool(ObjectPool<IoSession> objectPool){
		this.objectPool = objectPool;
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dawninfotek.base.packet.sender.MessageSender#send(com.dawninfotek.base.packet.common.Message)
	 */
	@Override
	protected Message send(Message req) throws ConnectionException {
		IoSession iosession  = null;
		Message response = null;
		try{
			iosession = getObjectPool().borrowObject();
			IoBuffer buffer = IoBuffer.allocate(4);
			WriteFuture future = iosession.write(buffer);
			future.await();
		} catch (NoSuchElementException e) {
			logger.error(e.getMessage(), e);
		} catch (IllegalStateException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}finally{
			try {
				getObjectPool().returnObject(iosession);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
		return response;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dawninfotek.base.packet.sender.MessageSender#send(java.lang.Object,
	 *      com.dawninfotek.base.packet.common.Message)
	 */
	@Override
	public Message send(Object obj, Message msgReq) throws ConnectionException {
		return send(msgReq);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "LongConnSocketMessageSender";
	}
}
