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

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dream.messaging.ByteArrayMessage;
import com.dream.messaging.Message;
import com.dream.messaging.Package;



/**
 * The Class RAPackage.
 * 
 * <p>
 * 
 * @author Liang Yanpeng
 * @author Zhang Fu
 */
public class RAPackage extends Package{
	
	public final static Logger log = LoggerFactory
			.getLogger(RAPackage.class);

	/** The bos. */
	ByteArrayOutputStream bos=new ByteArrayOutputStream();
	
	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.common.Message#append(java.lang.Object)
	 */
	public Message append(Object msg) throws IOException {
		if(msg instanceof ByteArrayMessage){
				bos.write((byte[])((ByteArrayMessage)msg).getContent());
		}else if(msg instanceof byte[]){
				bos.write((byte[])msg);
		}else if(msg instanceof Byte){
				bos.write(new byte[]{((Byte)msg).byteValue()});
		}
		return this;
	}

	
	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.common.Message#getContent()
	 */
	public Object getContent() {
		if(bos.size()==0){
			try {
				bos.write((byte[])this.getEnvelope().getContent());
				bos.write((byte[])this.getMessage().getContent());
				
			} catch (IOException e) {

				log.error("",e);
			}
		}
		return bos.toByteArray();
		
	}

	
	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.common.Message#length()
	 */
	public int length() {
		return this.getEnvelope().length() + this.getMessage().length();
	}

	
	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.common.Message#setContent(java.lang.Object)
	 */
	public void setContent(Object content) throws IOException {
			bos.reset();
			bos.write((byte[])content);
	}


	
	

}
