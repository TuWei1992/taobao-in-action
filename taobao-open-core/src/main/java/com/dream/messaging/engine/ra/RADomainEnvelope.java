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
package com.dream.messaging.engine.ra;


/**
 * The Class RADomainEnvelope.
 * 
 * <p>
 * 
 * @author Liang Yanpeng
 * @author Zhang Fu
 */
public class RADomainEnvelope {
	
	/** The pkg type. */
	private int pkgType; 
	
	/** The transcode. */
	private int transcode;
	
	/** The rsp code. */
	private int rspCode;
	
	/** The body type. */
	private int bodyType;
	
	/** The user id. */
	private int userId;
	
	/** The reserve. */
	private String reserve; 
	
	/** The msg len. */
	private int msgLen;
	
	/**
	 * Gets the body type.
	 * 
	 * @return the body type
	 */
	public int getBodyType() {
		return bodyType;
	}
	
	/**
	 * Sets the body type.
	 * 
	 * @param bodyType the new body type
	 */
	public void setBodyType(int bodyType) {
		this.bodyType = bodyType;
	}
	
	/**
	 * Gets the msg len.
	 * 
	 * @return the msg len
	 */
	public int getMsgLen() {
		return msgLen;
	}
	
	/**
	 * Sets the msg len.
	 * 
	 * @param msgLen the new msg len
	 */
	public void setMsgLen(int msgLen) {
		this.msgLen = msgLen;
	}
	
	/**
	 * Gets the pkg type.
	 * 
	 * @return the pkg type
	 */
	public int getPkgType() {
		return pkgType;
	}
	
	/**
	 * Sets the pkg type.
	 * 
	 * @param pkgType the new pkg type
	 */
	public void setPkgType(int pkgType) {
		this.pkgType = pkgType;
	}
	
	/**
	 * Gets the reserve.
	 * 
	 * @return the reserve
	 */
	public String getReserve() {
		return reserve;
	}
	
	/**
	 * Sets the reserve.
	 * 
	 * @param reserve the new reserve
	 */
	public void setReserve(String reserve) {
		this.reserve = reserve;
	}
	
	/**
	 * Gets the rsp code.
	 * 
	 * @return the rsp code
	 */
	public int getRspCode() {
		return rspCode;
	}
	
	/**
	 * Sets the rsp code.
	 * 
	 * @param rspCode the new rsp code
	 */
	public void setRspCode(int rspCode) {
		this.rspCode = rspCode;
	}
	
	/**
	 * Gets the transcode.
	 * 
	 * @return the transcode
	 */
	public int getTranscode() {
		return transcode;
	}
	
	/**
	 * Sets the transcode.
	 * 
	 * @param transcode the new transcode
	 */
	public void setTranscode(int transcode) {
		this.transcode = transcode;
	}
	
	/**
	 * Gets the user id.
	 * 
	 * @return the user id
	 */
	public int getUserId() {
		return userId;
	}
	
	/**
	 * Sets the user id.
	 * 
	 * @param userId the new user id
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
}
