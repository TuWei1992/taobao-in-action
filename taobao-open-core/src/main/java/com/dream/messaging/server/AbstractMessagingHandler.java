
package com.dream.messaging.server;

import org.apache.log4j.Logger;

import com.dream.messaging.engine.MessageEngine;


/**
 * 
 * @author Frank
 *
 */
public abstract class AbstractMessagingHandler implements MessagingHandler,Runnable{
	/**
	 * Logger for this class
	 */
	protected static final Logger logger = Logger.getLogger(AbstractMessagingHandler.class);
	
	protected MessageEngine engine = null;

	public MessageEngine getEngine() {
		return engine;
	}

	public void setEngine(MessageEngine engine) {
		this.engine = engine;
	}
	
	public void run() {
		try{
			handle();
		}catch(Exception e){
			logger.error("ERROR.", e);
		}
		
	}
	
}
