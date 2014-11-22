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

import java.io.IOException;

/**
 * The Class StringMessage represents message which is made up of String.
 * 
 * <p>
 * 
 * @author Liang Yanpeng
 * @author Zhang Fu
 */
public class StringMessage extends BaseMessageImpl {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The data. */
	StringBuffer data = new StringBuffer();

	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.common.Message#append(java.lang.Object)
	 */
	public Message append(Object msg) throws IOException {
		if(msg==null){
			return null;
		}
		if(msg instanceof String){
			data.append((String)msg);
		}
		return this;
	}

	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.common.Message#getContent()
	 */
	public Object getContent() {
		return toString();
	}

	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.common.Message#length()
	 */
	public int length() {
		return data.length();
	}

	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.common.Message#setContent(java.lang.Object)
	 */
	public void setContent(Object content) throws IOException {
		data.setLength(0);
		append(content);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return data.toString();
	}

	public byte[] readFileAsByte(String filename) {
		return null;
	}

	public String readFileAsString(String filename) {
		return null;
	}

	public boolean writeFile(String filename, byte[] content) {
		return false;
	}

	public boolean writeFile(String filename, String content) {
		return false;
	}
	
	
	
}
