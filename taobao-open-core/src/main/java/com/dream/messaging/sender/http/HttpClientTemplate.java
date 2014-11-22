/*
 * Copyright 2002-2006 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dream.messaging.sender.http;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * Template for easier use of <code>HttpClient</code>.
 * 
 * @author Frank Zhang
 */
public class HttpClientTemplate extends AbstractHttpClientTemplate<HttpEntity> {

  private static  final Logger logger = Logger.getLogger(HttpClientTemplate.class);

    /**
     * Constructor.
     */
    public HttpClientTemplate() {}

    /**
     * Constructor.
     * 
     * @param   defaultUri      Default uri.
     */
    public HttpClientTemplate(String defaultUri) {
        super(defaultUri, false);
    }

    /**
     * Execute post method.
     * 
     * @param   input           Byte array <code>RequestEntity</code> to post 
     *                          for the request's data.
     * @throws IOException 
     */
    public String executeHttpPost(byte[] input) throws IOException {
        return executeHttpPost(input, null);
    }
    
    /**
     * Execute post method.
     * 
     * @param   input           Byte array <code>RequestEntity</code> to post 
     *                          for the request's data.
     * @param   callback        Call back with HTTP method's response.
     * @throws IOException 
     */
    public String executeHttpPost(byte[] input, ResponseHandler<String> callback) throws IOException {
    	return executeHttpPost(defaultUri,  
                          (input != null ?new InputStreamEntity(new ByteArrayInputStream(input),input.length): null), 
                          null,null, callback);
    }
    
    /**
     * 返回类型是byte的处理方法
     * @param uri
     * @param input
     * @param headers
     * @param params
     * @return
     * @throws IOException
     */
    public byte[] executeHttpPost(String uri,byte[] input, Map headers, Map params) throws IOException {
    	return executeHttpPost1(uri,  
                          (input != null ?new InputStreamEntity(new ByteArrayInputStream(input),input.length): null), 
                          headers,params);
    }
    
    /**
     * Execute post method.
     * 
     * @param   input           <code>String</code> to post 
     *                          for the request's data.
     * @throws IOException 
     */
    public String executeHttpPost(String input) throws IOException {
    	return executeHttpPost(input, null);
    }

    /**
     * Execute post method.
     * 
     * @param   input           <code>String</code> to post 
     *                          for the request's data.
     * @param   callback        Callback with HTTP method's response.
     * @throws IOException 
     */
    public String executeHttpPost(String input, ResponseHandler<String> callback) throws IOException {
    	return executeHttpPost(input,null,callback);
    }
    
  /**
   * 
   * @param input
   * @param params
   * @param callback
   * @return
   * @throws IOException
   */
    public String executeHttpPost(String input, Map params, ResponseHandler<String> callback) throws IOException {
    	return executeHttpPost(defaultUri,input,params,callback);
    }
    
    public String executeHttpPost(String uri,String input, Map params, ResponseHandler<String> callback) throws IOException {
    	return executeHttpPost(uri,input,null,params,callback);
    }
    
    public String executeHttpPost(String uri,String input,Map headers, Map params, ResponseHandler<String> callback) throws IOException {
    	String charsetName = (String) headers.get("content-encoding");
    	if(logger.isInfoEnabled()){
    		if(StringUtils.isNotBlank(input))
    			logger.info("The request messaging has "+input.getBytes(charsetName).length+" bytes,and its charset name is "+charsetName);
    	}
    	StringEntity entity = null;
    	if(StringUtils.isNotBlank(input)) {
    		entity = new StringEntity(input,charsetName);
    	} else {
    		entity = new StringEntity(uri.substring(uri.indexOf("?")+1),charsetName);
    	}
    	return executeHttpPost(uri,entity,headers,params,callback);
    }
//
//    /**
//     * Execute post method.
//     * 
//     * @param   input           <code>InputStream</code> to post 
//     *                          for the request's data.
//     * @throws IOException 
//     */
//    public String executeHttpPost(InputStream input) throws IOException {
//    	return executeHttpPost(input, null);
//    }
//
//    /**
//     * Execute post method.
//     * 
//     * @param   input           <code>InputStream</code> to post 
//     *                          for the request's data.
//     * @param   callback        Callback with HTTP method's response.
//     * @throws IOException 
//     */
//    public String executeHttpPost(InputStream input, ResponseHandler<String> callback) throws IOException {
//    	long length = input.available();
//    	return executeHttpPost(defaultUri, new InputStreamEntity(input,length),null, null, callback);
//    }
//    
//    
//    public String executeHttpPost(String uri ,InputStream input, ResponseHandler<String> callback) throws IOException {
//    	long length = input.available();
//    	return executeHttpPost(uri, new InputStreamEntity(input,length),null, null, callback);
//    }
//    
//    
//    public String executeHttpPost(String uri ,InputStream input,Map params, ResponseHandler<String> callback) throws IOException {
//    	long length = input.available();
//    	return executeHttpPost(uri, new InputStreamEntity(input,length),null, params, callback);
//    }
//    
//    public String executeHttpPost(String uri ,InputStream input,Map headers,Map params, ResponseHandler<String> callback) throws IOException {
//    	long length = input.available();
//    	return executeHttpPost(uri, new InputStreamEntity(input,length),headers, params, callback);
//    }
//    
//    
//    /**
//     * Execute post method.
//     * 
//     * @param   input           <code>InputStream</code> to post 
//     *                          for the request's data.
//     * @param   callback        Callback with HTTP method's response.
//     * @throws IOException 
//     */
//    public String executeHttpPost(InputStream input, Map params,ResponseHandler<String> callback) throws IOException {
//    	long length = input.available();
//    	return executeHttpPost(defaultUri, new InputStreamEntity(input,length),null, params, callback);
//    }
//    
//    public String executeHttpPost(InputStream input,Map headers, Map params,ResponseHandler<String> callback) throws IOException {
//    	long length = input.available();
//    	return executeHttpPost(defaultUri, new InputStreamEntity(input,length),headers, params, callback);
//    }
    
    /**
     * Execute post method.
     * 
     * @param   uri             URI to use when processing this HTTP request instead 
     *                          of using the default URI.
     * @param   requestPayload  <code>RequestEntity</code> data to post.
     * @param   hParams         Parameters for the HTTP post.
     * @param   callback        Callback with HTTP method's response.
     */
    public String executeHttpPost(String uri,  
    		HttpEntity requestPayload, Map<String, String> headers ,Map<String, String> hParams,
    		ResponseHandler<String> callback) throws IOException {
    	
    	
        HttpPost post = new HttpPost(uri);
        
        
        if (requestPayload != null) {
            post.setEntity(requestPayload);
        }
        
        processHttpHeaders(post,headers);
        
        processHttpParams(post,hParams);
        
        
        String response = null;
        try {
        	response = execute(post, callback);
		} finally {
			//post.releaseConnection();
			//httpClient.getConnectionManager().closeIdleConnections(0, TimeUnit.SECONDS);
		}
        
        return response; 
    }
    
    
    /**
     * 返回时byte的的处理方法
     * @param uri
     * @param requestPayload
     * @param headers
     * @param hParams
     * @return
     * @throws IOException
     */
    public byte[] executeHttpPost1(String uri,  
    		HttpEntity requestPayload, Map<String, String> headers ,Map<String, String> hParams) throws IOException {
    	
    	
        HttpPost post = new HttpPost(uri);
        
        
        if (requestPayload != null) {
            post.setEntity(requestPayload);
        }
        
        processHttpHeaders(post,headers);
        
        processHttpParams(post,hParams);
        
        
        byte[] response = execute(post);
        
        return response; 
    }


	@Override
	protected String execute(HttpRequest request,
			ResponseHandler<String> responseHandler) throws IOException {
			if(logger.isInfoEnabled()){
				logger.info("Going to send a http message:"+request.getRequestLine());
			}
			
			String response = null;
			
			try{
				response = httpClient.execute((HttpUriRequest) request, responseHandler);
			}catch(Exception e){
				if(e instanceof HttpHostConnectException) {
					throw new IOException("ERQ9900001");
				} else if (e instanceof NoHttpResponseException || e instanceof SocketTimeoutException) {
					throw new IOException("ERS9900001");
				}
				logger.error("The communication to the http endpoint faild...", e);
				throw new IOException("The communication to the http endpoint faild...");
			}
			
			if(logger.isInfoEnabled()){
				logger.info("Recieved a http message:"+response);
			}
			return response;
	}
	
	/**
	 * 返回时byte的处理方法
	 * @param request
	 * @return
	 * @throws IOException
	 */
	protected byte[] execute(HttpRequest request) throws IOException {
			if(logger.isInfoEnabled()){
				logger.info("Going to send a http message:"+request.getRequestLine());
			}
			
			
			HttpResponse  response;
			byte[] bytes = null;
			try{
				response = httpClient.execute((HttpUriRequest) request);
				HttpEntity entity = response.getEntity();
				bytes = EntityUtils.toByteArray(entity);
			}catch(Exception e){
				logger.error("The communication to the http endpoint faild...", e);
				throw new IOException("The communication to the http endpoint faild...");
			}
			
			if(logger.isInfoEnabled()){
				logger.info("Recieved a http message:"+response);
			}
			return bytes;
	}


    
}
