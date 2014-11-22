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

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import org.apache.log4j.Logger;
import org.w3c.dom.Node;

import com.dream.messaging.ByteArrayMessage;
import com.dream.messaging.DataConvertException;
import com.dream.messaging.Message;
import com.dream.messaging.engine.DataHandler;
import com.dream.messaging.parser.ElementAttr;
import com.dream.messaging.parser.Strings;
import com.dream.messaging.utils.QueryNode;

// TODO: Auto-generated Javadoc
/**
 * The Class RADataHandler.
 * 
 * <p>
 * 
 * @author Liang Yanpeng
 * @author Zhang Fu
 */
public class RADataHandler extends DataHandler{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(RADataHandler.class);
	
	
	
	/** The encoding. */
	private String encoding;
	
	/**
	 * Instantiates a new RA data handler.
	 * 
	 * @param encoding the encoding
	 */
	public RADataHandler(String encoding){
		this.encoding=encoding;
	}
	
	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.engine.DataHandler#appendData(com.dawninfotek.base.packet.common.Message, java.lang.Object)
	 */
	@Override
	public void appendData(Message message, Object append) throws DataConvertException {
		
		byte[] data = (byte[])append;
		byte len = getByteLength(data.length);
		if(logger.isDebugEnabled()){
//			logger.debug(this, "**************RA Message is append data "+ new String(data)+", and its value length is " + len);
		}
		try {
			message.append(Byte.valueOf(len)).append(data);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			logger.error("",e);
		}
		
	}

	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.engine.DataHandler#appendFixedData(com.dawninfotek.base.packet.common.Message, java.lang.Object)
	 */
	@Override
	public void appendFixedData(Message message, Object append) throws DataConvertException {
		try {
			message.append((byte[])append);
		} catch (IOException e) {
			throw new DataConvertException("", e);
		}
		
	}

	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.engine.DataHandler#appendMessage(com.dawninfotek.base.packet.common.Message, com.dawninfotek.base.packet.common.Message)
	 */
	@Override
	public void appendMessage(Message message, Message append) throws DataConvertException {
		if(message!=null && append!=null)
			try {
				message.append(append.getContent());
			} catch (IOException e) {
				throw new DataConvertException("", e);
			}
		
	}

	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.engine.DataHandler#createMessageData(java.lang.Object, org.w3c.dom.Node)
	 */
	@Override
	public Object createMessageData(Object objData, Node node) throws DataConvertException {
		
		String data = this.createStringMessage(objData, node);
		
		if(data==null)return null;
		try {
			return data.getBytes(encoding);
		} catch (UnsupportedEncodingException e) {
			throw new DataConvertException("", e);
		}
	}

	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.engine.DataHandler#createValue(java.lang.Object, org.w3c.dom.Node)
	 */
	@Override
	public Object createValue(Object msgData, Node node) throws DataConvertException {
		String data = null;
		try {
			data = new String((byte[])msgData,encoding);
			return this.createValue(data, node);
		} catch (UnsupportedEncodingException e) {
			throw new DataConvertException("", e);
		}
		
	}

	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.engine.DataHandler#formatMessage(java.lang.Object, org.w3c.dom.Node)
	 */
	@Override
	public Object formatMessage(Object msgData, Node node) {
		
		return null;
	}

	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.engine.DataHandler#newMessage()
	 */
	@Override
	public Message newMessage() {
		
		return new ByteArrayMessage();
	}

	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.engine.DataHandler#readCount(com.dawninfotek.base.packet.common.Message, org.w3c.dom.Node)
	 */
	@Override
	public int readCount(Message message, Node node) {
		try{
			byte[] length = (byte[]) readData(message,node);
			if(length.length == 0)return 0;
			String len = new String(length,encoding);
			return Integer.parseInt(len);
		}catch(NumberFormatException e){
//			throw new DataConvertException("", e);
		} catch (UnsupportedEncodingException e) {
//			throw new DataConvertException("", e);
		} 
		return -1;
	}

	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.engine.DataHandler#readData(com.dawninfotek.base.packet.common.Message, org.w3c.dom.Node)
	 */
	@Override
	public Object readData(Message message, Node node){
		String fixed = QueryNode.getAttribute(node, ElementAttr.Attr_Fixed);
		byte[]  data=null;
		byte[] msg = (byte[]) message.getContent();
		if(fixed!=null){
			fixed = Strings.replaceESC(fixed);
			try{
				data = fixed.getBytes(encoding);
			}
			catch (UnsupportedEncodingException e) {
//				logger.error(this, "UnsupportedEncodingException occurs in method readData,encoding is "+encoding);
			} 
			int len = fixed.length();
			//data = msg.substring(offset, len + offset);
			offset += len;
			return data;
		}else{
			
			int length = getLength(msg[offset]);
			offset++;
			data =new byte[length];
			System.arraycopy(msg, offset, data, 0, length);
			offset += length;
			return data;
		}
		
	}

	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.engine.DataHandler#validateData(java.lang.Object, org.w3c.dom.Node)
	 */
	@Override
	public boolean validateData(Object msgData, Node node) {
		
		try {
			return validateData(new String(msgData.toString().getBytes(),encoding),node);
		} catch (UnsupportedEncodingException e) {
//			logger.error(this, "UnsupportedEncodingException occurs in method validateData,encoding is "+encoding);
		}
		return false;
	}

	/**
	 * Gets the encoding.
	 * 
	 * @return the encoding
	 */
	public String getEncoding() {
		return encoding;
	}

	/**
	 * Sets the encoding.
	 * 
	 * @param encoding the new encoding
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
	
	/**
	 * Gets the byte length.
	 * 
	 * @param len the len
	 * 
	 * @return the byte length
	 */
	private byte getByteLength(int len){
		return (byte)len;
	}
	
	/**
	 * Gets the length.
	 * 
	 * @param len the len
	 * 
	 * @return the length
	 */
	private int getLength(byte len){
		return (len & 0xff);
	}
	
	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.engine.DataHandler#readFixedData(com.dawninfotek.base.packet.common.Message, java.lang.String)
	 */
	@Override
	public Object readFixedData(Message message, String fixed) throws DataConvertException{
		if(fixed!=null){
			fixed = Strings.replaceESC(fixed);

			byte[] data = null;
			try {
				data = fixed.getBytes(encoding);
			} catch (UnsupportedEncodingException e) {
				throw new DataConvertException("", e);
			}
			int len = fixed.length();
			//data = msg.substring(offset, len + offset);
			offset += len;
			return data;
		}
		return null;
	}
}
