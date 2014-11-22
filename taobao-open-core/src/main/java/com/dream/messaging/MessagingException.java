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
package com.dream.messaging;

/**
 * The Class CommunicationException.
 * 
 * <p>
 * 
 * @author Liang Yanpeng
 * @author Zhang Fu
 */
public class MessagingException extends Exception {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2680376671022538112L;
	
	private String errCode;
	
	private String errMsg;
	

	
	/**
	 * Instantiates a new communication exception.
	 * 
	 * @param message the message
	 */
	public MessagingException(String errCode){
		this.setErrCode(errCode);
	}

	public MessagingException(String errCode,String errMsg) {
		this.setErrCode(errCode);
		this.setErrMsg(errMsg);
	}
	
	/**
	 * Instantiates a new communication exception.
	 * 
	 * @param message the message
	 * @param cause the cause
	 */
	public MessagingException(String errCode, Throwable cause){
		super(cause);
		this.setErrCode(errCode);
	}
	
	/**
	 * Instantiates a new communication exception.
	 * 
	 * @param message the message
	 * @param cause the cause
	 */
	public MessagingException(String errCode,String errMsg, Throwable cause){
		super(cause);
		this.setErrCode(errCode) ;
		this.setErrMsg(errMsg) ;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}
	
	
	
	
}
