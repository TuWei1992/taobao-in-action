/**
 * 
 */
package com.dream.messaging;

/**
 * @author Frank
 * @date 2010-4-13
 * @time 上午12:01:40
 */
public class ConnectionException extends MessagingException {

	/**
	 * @param errCode
	 */
	public ConnectionException(String errCode) {
		super(errCode);
	}
	
	public ConnectionException(String errCode,Throwable e) {
		super(errCode,e);
	}
	
	public ConnectionException(String errCode,String errMsg,Throwable e) {
		super(errCode,errMsg,e);
	}
	
	 public String toString(){
	    	return super.toString();
	    }

	/**
	 * 
	 */
	private static final long serialVersionUID = 4349972959239481866L;

}
