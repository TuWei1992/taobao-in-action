package com.dream.messaging.connector;

import java.util.Map;


/**
 * 
 * @author Frank
 *
 */
public interface DataConnector {
	
	/**
	 * 
	 * @param domain
	 * @param sourceSystemId
	 * @param transCode
	 * @param extSystemId
	 * @param condition
	 * @return
	 */
	public Object executeRequest(Object domain, String sourceSystemId,String transCode, String extSystemId, Map<String,Object> condition) ;
	
	/**
	 * 
	 * @param domain
	 * @param sourceSystemId
	 * @param transCode
	 * @param extSystemId
	 * @return
	 */
	public Object executeRequest(Object domain, String sourceSystemId,String transCode, String extSystemId) ;
	
	
	

}
