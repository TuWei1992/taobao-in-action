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

import org.w3c.dom.Node;

import com.dream.messaging.ByteArrayMessage;
import com.dream.messaging.DataConvertException;
import com.dream.messaging.Message;
import com.dream.messaging.engine.DataHandler;
import com.dream.messaging.parser.ElementAttr;
import com.dream.messaging.parser.ElementClassType;
import com.dream.messaging.utils.BitConverter;
import com.dream.messaging.utils.QueryNode;

/**
 * The Class RABitDataHandler processes L_V format data.
 * 
 * <p>
 * 
 * @author Liang Yanpeng
 * @author Zhang Fu
 */
public class RABitDataHandler extends DataHandler{

	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.engine.DataHandler#appendData(com.dawninfotek.base.packet.common.Message, java.lang.Object)
	 */
	@Override
	public void appendData(Message message, Object append) throws DataConvertException {
		try {
			message.append(append);
		} catch (IOException e) {
			throw new DataConvertException("", e);
		}
		
	}

	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.engine.DataHandler#appendFixedData(com.dawninfotek.base.packet.common.Message, java.lang.Object)
	 */
	@Override
	public void appendFixedData(Message message, Object append) throws DataConvertException {
		try {
			message.append(append);
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
		String clsname = QueryNode.getAttribute(node,ElementAttr.Attr_Class);
		String length = QueryNode.getAttribute(node,ElementAttr.Attr_Length);
		int len = Integer.parseInt(length);
		if(clsname.equalsIgnoreCase(ElementClassType.CLASS_INT) || clsname.equalsIgnoreCase(ElementClassType.CLASS_LONG)){
			if (objData instanceof Number){
				int n = ((Number)objData).intValue();
				if(len == 1){
					return new byte[]{(byte)n};
				}else if(len == 2){
					return BitConverter.toHH((short)n);
				}else if(len == 4){
					return BitConverter.toHH(n);
				}
			}
			
		}else if(clsname.equalsIgnoreCase(ElementClassType.CLASS_CHAR)){

			byte[] data = ((String)objData).getBytes();
			byte[] b = new byte[len];
			if(data.length > len){
				System.arraycopy(data, 0, b, 0, len);
			}else{
				System.arraycopy(data, 0, b, 0, data.length);
			}
			return b;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.engine.DataHandler#createValue(java.lang.Object, org.w3c.dom.Node)
	 */
	@Override
	public Object createValue(Object msgData, Node node) throws DataConvertException {
		String clsname = QueryNode.getAttribute(node,ElementAttr.Attr_Class);
		byte[] data = (byte[])msgData;
		if(clsname.equalsIgnoreCase(ElementClassType.CLASS_INT) || clsname.equalsIgnoreCase(ElementClassType.CLASS_LONG)){
			return Integer.valueOf(BitConverter.bytesToInt(data));		
			
		}else if(clsname.equalsIgnoreCase(ElementClassType.CLASS_CHAR)){
			return new String(data);
		}else{
			return null;
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
		
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.engine.DataHandler#readData(com.dawninfotek.base.packet.common.Message, org.w3c.dom.Node)
	 */
	@Override
	public Object readData(Message message, Node node) throws DataConvertException {
		//String fixed = QueryNode.getAttribute(node, ElementAttr.Attr_Fixed);
		String len = QueryNode.getAttribute(node, ElementAttr.Attr_Length);
		int length = Integer.parseInt(len);
		byte[]  data=null;
		byte[] msg = (byte[]) message.getContent();
		
		data =new byte[length];
		System.arraycopy(msg, offset, data, 0, length);
		offset += length;
		return data;
		
	}

	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.engine.DataHandler#validateData(java.lang.Object, org.w3c.dom.Node)
	 */
	@Override
	public boolean validateData(Object msgData, Node node) {
		
		return true;
	}

	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.engine.DataHandler#readFixedData(com.dawninfotek.base.packet.common.Message, java.lang.String)
	 */
	@Override
	public Object readFixedData(Message message, String fixed) throws DataConvertException {
		
		return null;
	}
	
}
