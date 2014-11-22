/**
 * 
 */
package com.dream.messaging.sender.http;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ResponseHandler;
import org.apache.log4j.Logger;

import com.dream.messaging.ConnectionException;
import com.dream.messaging.Message;
import com.dream.messaging.MessagingException;
import com.dream.messaging.StringMessage;
import com.dream.messaging.sender.MessageSender;

/**
 * @author Frank
 *
 */
public class HTTPMessageSender extends MessageSender {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(HTTPMessageSender.class);
	private HttpClientTemplate httpClientTemplate = null;
	private ResponseHandler<String> responseHandler = null;
	private Map<String,String> headers = null;
	private String commonUri;

	/**
	 * @return the responseHandler
	 */
	public ResponseHandler<String> getResponseHandler() {
		return responseHandler;
	}

	/**
	 * @param responseHandler the responseHandler to set
	 */
	public void setResponseHandler(ResponseHandler<String> responseHandler) {
		this.responseHandler = responseHandler;
	}
	

	@Override
	public Message send(Object obj, Message msgReq) throws ConnectionException,MessagingException {
		Map hParams = getHParams(obj);
		Message msgRes = null;
		try {
			String reqStr = (String)msgReq.getContent();
			String uri = this.getCommonUri();
			String resString = getHttpClientTemplate().executeHttpPost(uri,reqStr,headers,hParams,getResponseHandler());
			msgRes = new StringMessage();
			msgRes.setContent(resString);
		} catch (IOException e) {
			logger.error("IOException raised in the procedure of sending message:", e);
			throw new ConnectionException("ERROR","IOException raised in the procedure of sending message...",e);
		}
		return msgRes;
	}

	@Override
	protected Message send(Message msgReq) throws ConnectionException,MessagingException {
		Map hParams = new HashMap();
		return send(hParams, msgReq);
	}
	
	
	protected Map getHParams(Object obj){
		Map hParams = new HashMap();
		return hParams;
	}

	/**
	 * @param httpClientTemplate the httpClientTemplate to set
	 */
	public void setHttpClientTemplate(HttpClientTemplate httpClientTemplate) {
		this.httpClientTemplate = httpClientTemplate;
	}

	/**
	 * @return the httpClientTemplate
	 */
	public HttpClientTemplate getHttpClientTemplate() {
		return httpClientTemplate;
	}

	/**
	 * @param headers the headers to set
	 */
	public void setHeaders(Map<String,String> headers) {
		this.headers = headers;
	}

	/**
	 * @return the headers
	 */
	public Map<String,String> getHeaders() {
		return headers;
	}
	
	public String toString(){
		return getHttpClientTemplate().getDefaultUri();
	}
	
	public String getCommonUri() {
		return commonUri;
	}

	public void setCommonUri(String commonUri) {
		this.commonUri = commonUri;
	}
	
}
