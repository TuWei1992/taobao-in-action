/**
 * 
 */
package com.dream.messaging.support;

import com.dream.messaging.MessagingException;
import com.dream.messaging.connector.DataConnector;



/**
 * @author Frank
 * Messaging Supporter.
 * 
 */
public abstract class MessagingSupport {
    
    
	
	private DataConnector connector = null;

	/**
	 * @return the connector
	 */
	public DataConnector getConnector() {
		return connector;
	}


	/**
	 * @param connector the connector to set
	 */
	public void setConnector(DataConnector connector) {
		this.connector = connector;
	}
	/**
	 * Populate the request message header.
	 * @param domain
	 * @throws IIFException
	 */
	protected abstract void populateMessageHeader(Object domain) throws MessagingException; 
	
	/**
	 * Handle the response message header.
	 * @param domain
	 * @throws IIFException
	 */
	protected abstract void handleMessageHeader(Object domain) throws MessagingException;
	
	
	protected  Object doTransaction(Object domain, String messageType,String transCode, String extSystemId) throws MessagingException{
	    populateMessageHeader(domain);
	    Object result = getConnector().executeRequest(domain, messageType, transCode, extSystemId);
	    handleMessageHeader(result);
	    return result;
	}
}
