/**
 * 
 */
package com.dream.messaging.connector;

import java.util.Map;

import org.apache.log4j.Logger;

import com.dream.messaging.MessagingException;
import com.dream.messaging.transaction.Transaction;


/**
 * @author Frank
 *
 */
public class MessagingDataConnector extends Transaction implements DataConnector {

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(MessagingDataConnector.class);
	

	public Object executeRequest(Object domain, String sourceSystemId,String transCode, String targetSystemId) {
		try {
			return doTransaction(domain,sourceSystemId,transCode,targetSystemId);
		} catch (MessagingException e) {
			logger.error("Error happens when request external system.",e);
			return null;
		}
	}

	
	

	public Object executeRequest(Object domain, String sourceSystemId,String transCode, String targetSystemId, Map<String, Object> condition){
		try {
			return doTransaction(domain,sourceSystemId,transCode,targetSystemId,condition);
		} catch (MessagingException e) {
			logger.error("Error happens when request external system.",e);
			return null;
		}
	}
	
	
	protected void hanldeMessagingException(){
		
	}
	

}
