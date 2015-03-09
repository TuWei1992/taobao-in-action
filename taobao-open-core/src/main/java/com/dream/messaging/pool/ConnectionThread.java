package com.dream.messaging.pool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ConnectionThread implements Runnable {
	
	private final Logger logger = (Logger) LoggerFactory.getLogger(getClass());
	
	private ConnectionPoolClientStarter starter;
	
	private String data;

	public ConnectionThread(ConnectionPoolClientStarter start, String string) {
		this.starter = start;
		this.data = string;
	}

	@Override
	public void run() {
		try{
			String result = starter.send(data);
			logger.debug(result);
		}catch(Exception e){
			
		}
	}

}
