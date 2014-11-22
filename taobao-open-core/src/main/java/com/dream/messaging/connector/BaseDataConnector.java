package com.dream.messaging.connector;

/**
 * 
 * @author Frank
 *
 */
public abstract class BaseDataConnector implements DataConnector {
	
	public Object executeRequest(Object domain, String messageType, String transCode, String extSystemId) {
		
		throw new UnsupportedOperationException();		
	}

	public Object executeRequest(Object domain, String messageType, String transCode, String extSystemId, String[] files){
		
		throw new UnsupportedOperationException();
		
	}

}
