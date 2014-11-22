package com.dream.messaging.engine.xml;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.Map;

import org.apache.log4j.Logger;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.XMLContext;

import com.dream.messaging.Message;
import com.dream.messaging.MessagingException;
import com.dream.messaging.StringMessage;
import com.dream.messaging.engine.MessageEngine;



/**
 * @author Frank
 *
 */
public class XMLMessageEngine extends MessageEngine {
	
	private Map<String, XMLContext> requestXMLContexts;    /**XmlContext的集合*/ 
	private Map<String, XMLContext> responseXMLContexts;    /**XmlContext的集合*/ 
	

	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(XMLMessageEngine.class);
	
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
	   if(object == null){
		   throw new MessagingException("The request object must be given.");
	   }
	   if(transCode == null){
		   throw new MessagingException("The transaction code must be given.");
	   }
       try {
             //此处应该同步，castor非线程安全
              synchronized(requestXMLContexts){
                  XMLContext xmlContext = requestXMLContexts.get(transCode);
                  if(xmlContext == null){
                	  return null;
                  }
                  Marshaller marshaller = xmlContext.createMarshaller();
                  StringWriter writer = new StringWriter();
                  marshaller.setWriter(writer);
                  marshaller.setEncoding((String)properties.get("encoding"));
                  marshaller.marshal(object);
                  StringMessage req = new StringMessage();
                  req.append(writer.toString());
                  return req;
//	                  String xmlStr = (writer.toString()).replaceAll("\n","");
//	                  String hasLength = (String)properties.get("hasLength");
//	                  if("true".equals(hasLength)){
//	                	  String format = properties.get("msgBodyLength") == null ? "4" : properties.get("msgBodyLength").toString();
//	                	  xmlStr = String.format("%0"+format+"d",xmlStr.getBytes((String)properties.get("encoding")).length) + xmlStr;
//	                  }
              }
          } catch (Exception e) {
        	  logger.error("The process of marshaller raised an exception.", e);
              throw new MessagingException("The process of marshaller raised an exception.",e);
          }
	}

	@Override
	public Object toObject(Message msg, Object obj, String transCode,String version, String type, Map condition) throws MessagingException {
		return toObject(msg,transCode,condition);
	}
	
	private Object toObject(Message msg,String transCode,Map condition) throws MessagingException {
		if (msg == null) {
	        throw new MessagingException("The process of marshaller raised an exception.");
	    }
		if(transCode == null){
			   throw new MessagingException("The transaction code must be given.");
		}
		try{
			//此处应该同步，castor非线程安全
			synchronized(responseXMLContexts){
		    	 XMLContext xmlContext = responseXMLContexts.get(transCode);
		         Unmarshaller unmarshaller = xmlContext.createUnmarshaller();
		         StringMessage message = (StringMessage) msg;
		         String realXml = (String) message.getContent();
//		         if(properties.containsKey("xmlnsList")){
//		        	 List<String> list = (List<String>) properties.get("xmlnsList");
//		        	 for(String s : list){
//		        		 realXml = realXml.replaceFirst(s, "");
//		        	 }
//		         }
		         StringReader reader = new StringReader(realXml);
		         Object object = unmarshaller.unmarshal(reader);
		         return object;
			}
	   } catch (Exception e) {
		   logger.error("The process of unmarshaller raised an exception.", e);
		   throw new MessagingException("The process of unmarshaller raised an exception.",e);
       }
	}

	
	
	public void setRequestXMLContexts(Map<String, XMLContext> requestXMLContexts) {
		this.requestXMLContexts = requestXMLContexts;
	}

	public Map<String, XMLContext> getRequestXMLContexts() {
		return requestXMLContexts;
	}

	public void setResponseXMLContexts(Map<String, XMLContext> responseXMLContexts) {
		this.responseXMLContexts = responseXMLContexts;
	}

	public Map<String, XMLContext> getResponseXMLContexts() {
		return responseXMLContexts;
	}


}