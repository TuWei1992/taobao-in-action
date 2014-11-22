/**
 * 
 */
package com.dream.messaging.sender.http;

import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLContext;

import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.Logger;

import com.dream.messaging.Message;
import com.dream.messaging.MessagingException;
import com.dream.messaging.StringMessage;
import com.dream.messaging.sender.MessageSender;
/**
 * 
 * <p>基于Https的通信
 *
 * @author Frank
 */
public class HTTPsMessageSender extends MessageSender {
	
	private static final Logger logger = Logger.getLogger(HTTPMessageSender.class);
	private HttpClient  httpclient = null;
	private Map<String,String> headers = null;
	private String commonUri;
	private ResponseHandler<String> responseHandler;
	
	public void init() throws Exception{
		KeyStore trustStore = null;
		FileInputStream instream = null;
		try{
			trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
			instream = new FileInputStream(new File("my.keystore"));
			trustStore.load(instream, "nopassword".toCharArray());
			SSLContext sslcontext = SSLContexts.custom().loadTrustMaterial(trustStore, new TrustSelfSignedStrategy()).build();
			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
	                sslcontext,
	                new String[] { "TLSv1" },
	                null,
	                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
			httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
		}finally{
			if(instream != null){
				instream.close();
			}
		}
	}
	
	public void destroy() throws Exception{
		httpclient = null;
	}
	
	
	@Override
	public Message send(Message msgReq) throws  MessagingException {
		Map hParams = new HashMap();
		return this.send(hParams, msgReq);
	}

	@Override
	public Message send(Object obj, Message msgReq) throws MessagingException {
       try {
            HttpGet httpget = new HttpGet(this.getCommonUri());
            String response = httpclient.execute(httpget,responseHandler);
            Message message = new StringMessage();
            message.setContent(response);
            return message;
        }catch(Exception e){
        	logger.error("Can not get the response.", e);
        	throw new MessagingException("ERROR","Can not get the response.",e);
        }
	}
	
	
	protected Map getHParams(Object obj){
		Map hParams = new HashMap();
		return hParams;
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
	
	
	public String getCommonUri() {
		return commonUri;
	}

	public void setCommonUri(String commonUri) {
		this.commonUri = commonUri;
	}
	
	
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

	

}
