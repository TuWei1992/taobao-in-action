/**
 * 
 */
package com.dream.messaging.engine.xml;

import org.w3c.dom.Node;

import com.dream.messaging.DataConvertException;
import com.dream.messaging.Message;
import com.dream.messaging.engine.DataHandler;

/**
 * @author Frank
 *
 */
public class XMLDataHandler extends DataHandler {

	@Override
	public void appendData(Message message, Object append)
			throws DataConvertException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void appendFixedData(Message message, Object append)
			throws DataConvertException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void appendMessage(Message message, Message append)
			throws DataConvertException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Object createMessageData(Object objData, Node node)
			throws DataConvertException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object createValue(Object msgData, Node node)
			throws DataConvertException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object formatMessage(Object msgData, Node node) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Message newMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int readCount(Message message, Node node) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object readData(Message message, Node node)
			throws DataConvertException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object readFixedData(Message message, String fixed)
			throws DataConvertException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validateData(Object msgData, Node node) {
		// TODO Auto-generated method stub
		return false;
	}

}
