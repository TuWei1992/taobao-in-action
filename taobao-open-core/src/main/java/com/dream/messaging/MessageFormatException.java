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
 * The Class MessageFormatException.
 * 
 * <p>
 * 
 * @author Liang Yanpeng
 * @author Zhang Fu
 */
public class MessageFormatException extends MessagingException{

	/**
	 * @param errCode
	 */
	public MessageFormatException(String errCode) {
		super(errCode);
	}
	
	public MessageFormatException(String errCode, Throwable cause){
		super(errCode,cause);
	}
	
	
	public String toString(){
    	return super.toString();
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6883878758370345920L;

}
