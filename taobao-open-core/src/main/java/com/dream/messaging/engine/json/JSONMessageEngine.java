package com.dream.messaging.engine.json;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.dream.messaging.ByteArrayMessage;
import com.dream.messaging.Message;
import com.dream.messaging.MessagingException;
import com.dream.messaging.engine.MessageEngine;



/**
 * @author Frank
 *
 */
public class JSONMessageEngine extends MessageEngine {
	
	private ObjectMapper objectMapper;
	
	
	public void setObjectMapper(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(JSONMessageEngine.class);
	
	@Override
	public Object extractHeader(Message msg, String header, String version,Map condition) throws Exception {
		return null;
	}

	@Override
	public Message toMessage(Object object, String transCode, String version,
			String type, Map condition) throws MessagingException {
		return toMessage(object,transCode);
	}
	
	
	private Message toMessage(Object object, String transCode) throws MessagingException {
		ByteArrayMessage response = new ByteArrayMessage();
		byte[] bytes = null;
		try {
			bytes = objectMapper.writeValueAsBytes(object);
		} catch (JsonGenerationException e) {
			logger.error(e.getClass().getName(), e);
			throw new MessagingException(e.getClass().getName(),e);
		} catch (JsonMappingException e) {
			logger.error(e.getClass().getName(), e);
			throw new MessagingException(e.getClass().getName(),e);
		} catch (IOException e) {
			logger.error(e.getClass().getName(), e);
			throw new MessagingException(e.getClass().getName(),e);
		}
		try {
			response.setContent(bytes);
		} catch (IOException e) {
			logger.error(e.getClass().getName(), e);
			throw new MessagingException(e.getClass().getName(),e);
		}
		return response;
	}

	@Override
	public Object toObject(Message msg, Object obj, String transCode,String version, String type, Map condition) throws MessagingException {
		return toObject(msg,transCode,condition);
	}
	
	private Object toObject(Message msg,String transCode,Map condition) throws MessagingException {
		Class clz = (Class) condition.get("objectType");
		Object object = null;
		try {
			object = objectMapper.readValue((byte[])msg.getContent(), clz);
		} catch (JsonParseException e) {
			logger.error(e.getClass().getName(), e);
			throw new MessagingException(e.getClass().getName(),e);
		} catch (JsonMappingException e) {
			logger.error(e.getClass().getName(), e);
			throw new MessagingException(e.getClass().getName(),e);
		} catch (IOException e) {
			logger.error(e.getClass().getName(), e);
			throw new MessagingException(e.getClass().getName(),e);
		}
		return object;
	}

	


}