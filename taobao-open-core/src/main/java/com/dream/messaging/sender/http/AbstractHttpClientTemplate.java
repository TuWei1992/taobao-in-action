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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.auth.Credentials;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * Base <code>HttpClient</code> template class.
 * 
 * @author Frank Zhang
 */
public abstract class AbstractHttpClientTemplate<T> implements InitializingBean, DisposableBean {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AbstractHttpClientTemplate.class);

	protected HttpClient httpClient = null;
	protected String defaultUri = null;
	protected String customerId = null;
	protected ClientConnectionManager connectionManager = null;
	protected boolean authenticationPreemptive = false;
	protected String trustStore = null;
	/**
	 * @return the trustStore
	 */
	public String getTrustStore() {
		return trustStore;
	}

	/**
	 * @param trustStore the trustStore to set
	 */
	public void setTrustStore(String trustStore) {
		this.trustStore = trustStore;
	}

	protected List<Credentials> lCredentials = new ArrayList<Credentials>();
	
	protected Map<?, ?> params = null; 

	/**
	 * @return the params
	 */
	public Map<?, ?> getParams() {
		return params;
	}

	/**
	 * @param params the params to set
	 */
	public void setParams(Map<?, ?> params) {
		this.params = params;
	}

	/**
	 * Constructor.
	 */
	public AbstractHttpClientTemplate() {
	}

	/**
	 * Constructor.
	 * 
	 * @param defaultUri
	 *            Default uri.
	 */
	public AbstractHttpClientTemplate(String defaultUri) {
		this(defaultUri, false);
	}

	/**
	 * Constructor.
	 * 
	 * @param defaultUri
	 *            Default uri.
	 * @param init
	 *            Whether or not to initialize the bean (typically for
	 *            programatic use).
	 */
	public AbstractHttpClientTemplate(String defaultUri, boolean init) {
		this.defaultUri = defaultUri;

		if (init) {
			try {
				afterPropertiesSet();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * Gets http client.
	 */
	public HttpClient getHttpClient() {
		return httpClient;
	}

	/**
	 * Sets http client.
	 */
	public void setHttpClient(HttpClient client) {
		this.httpClient = client;
	}

	/**
	 * Gets connection manager.
	 */
	public ClientConnectionManager getConnectionManager() {
		return connectionManager;
	}

	/**
	 * Sets connection manager.
	 */
	public void setConnectionManager(ClientConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}

	/**
	 * Gets default uri.
	 */
	public String getDefaultUri() {
		return defaultUri;
	}

	/**
	 * Sets default uri.
	 */
	public void setDefaultUri(String defaultUri) {
		this.defaultUri = defaultUri;
	}
	
	/**
	 * Gets default customerId.
	 */
	public String getCustomerId() {
		return customerId;
	}
	
	/**
	 * Sets default customerId.
	 */
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	/**
	 * Whether or not authentication is preemptive. If <code>true</code>,
	 * authentication credentials will be sent before a challenge is issued for
	 * an authentication scope with credentials. Defaults to <code>false</code>.
	 */
	public boolean isAuthenticationPreemptive() {
		return authenticationPreemptive;
	}

	/**
	 * Sets whether or not authentication is preemptive. If <code>true</code>,
	 * authentication credentials will be sent before a challenge is issued for
	 * an authentication scope with credentials. Defaults to <code>false</code>.
	 */
	public void setAuthenticationPreemptive(boolean authenticationPreemptive) {
		this.authenticationPreemptive = authenticationPreemptive;
	}

	/**
	 * Implementation of <code>InitializingBean</code> that initializes the
	 * <code>HttpClient</code> if it is <code>null</code> and also sets the
	 * connection manager to <code>MultiThreadedHttpConnectionManager</code> if
	 * it is <code>null</code> while initializing the <code>HttpClient</code>.
	 */
	public void afterPropertiesSet() throws Exception {
		logger.info("Http client template initializing...");
		if(defaultUri==null){
			throw new BeanInitializationException("The default uri must be set in "+this.getClass().getName()+"...");
		}
		if (httpClient == null) {
			SchemeRegistry sr = new SchemeRegistry();
			// Create a ssl
			if (trustStore != null) {
				KeyStore ks = KeyStore.getInstance(KeyStore
						.getDefaultType());
				FileInputStream instream = new FileInputStream(new File(trustStore));
				try {
					ks.load(instream, "password".toCharArray());
				} finally {
					instream.close();
				}
				SSLSocketFactory socketFactory = SSLSocketFactory
						.getSocketFactory();
				Scheme sch1 = new Scheme("https", socketFactory, 443);
				sr.register(sch1);
			} else {
				// Create a plain
				PlainSocketFactory plainSocketFactory = PlainSocketFactory
						.getSocketFactory();
				Scheme sch2 = new Scheme("http", plainSocketFactory, 80);
				sr.register(sch2);
			}
			
			HttpParams hp = null;
			if (params != null) {
				hp = new BasicHttpParams();
				for (Object key : params.keySet()) {
					Object value = params.get(key);
					hp.setParameter((String) key, value);
				}
			}
			if (connectionManager == null) {
				ThreadSafeClientConnManagerFactoryBean tcm = new ThreadSafeClientConnManagerFactoryBean();
				connectionManager = tcm.newInstance(hp, sr);
			}
			httpClient = new DefaultHttpClient(connectionManager,hp);
		}

	}

	/**
	 * Implementation of <code>DisposableBean</code> that shuts down the
	 * connection manager if it is an instance of
	 * <code>MultiThreadedHttpConnectionManager</code>.
	 */
	public void destroy() throws Exception {

	}

	/**
	 * Execute get method.
	 */
	public String executeHttpGet() throws IOException {
		return executeHttpGet(defaultUri, null, null);
	}

	/**
	 * Execute get method.
	 * 
	 * @param callback
	 *            Callback with HTTP method's response.
	 */
	public String executeHttpGet(ResponseHandler<String> callback) throws IOException {
		return executeHttpGet(defaultUri, null, callback);
	}

	/**
	 * Execute get method.
	 * 
	 * @param hParams
	 *            Parameters for the HTTP get.
	 */
	public String  executeHttpGet(Map<String, String> hParams) throws IOException {
		return executeHttpGet(defaultUri, hParams, null) ;
	}

	/**
	 * Execute get method.
	 * 
	 * @param hParams
	 *            Parameters for the HTTP get.
	 * @param callback
	 *            Callback with HTTP method's response.
	 */
	public String executeHttpGet(Map<String, String> hParams,
			ResponseHandler<String> callback) throws IOException {
		return executeHttpGet(defaultUri, hParams, callback);
	}

	/**
	 * Execute get method.
	 * 
	 * @param uri
	 *            URI to use when processing this HTTP request instead of using
	 *            the default URI.
	 * @param hParams
	 *            Parameters for the HTTP get.
	 */
	public String  executeHttpGet(String uri, Map<String, String> hParams) throws IOException {
		return executeHttpGet(uri, hParams, null);
	}

	/**
	 * Execute get method.
	 * 
	 * @param uri
	 *            URI to use when processing this HTTP request instead of using
	 *            the default URI.
	 * @param hParams
	 *            Parameters for the HTTP get.
	 * @param callback
	 *            Callback with HTTP method's response.
	 */
	public String executeHttpGet(String uri, Map<String, String> hParams,
			ResponseHandler<String> callback) throws IOException {
		
		HttpGet get = new HttpGet(uri);

		processHttpParams(get, hParams);

		String response = execute(get, callback);
		
		return response;
	}

	
	
	
	/**
	 * Execute post method.
	 */
	public String executeHttpPost() throws IOException {
		return executeHttpPost(defaultUri, null, null, null);
	}

	/**
	 * Execute post method.
	 * 
	 * @param callback
	 *            Callback with HTTP method's response.
	 */
	public String executeHttpPost(ResponseHandler<String> callback) throws IOException {
		return executeHttpPost(defaultUri, null, null, null, callback);
	}

	/**
	 * Execute post method.
	 * 
	 * @param hParams
	 *            Parameters for the HTTP post.
	 */
	public String executeHttpPost(Map<String, String> hParams) throws IOException {
		return executeHttpPost(defaultUri, null, hParams, null);
	}

	/**
	 * Execute post method.
	 * 
	 * @param hParams
	 *            Parameters for the HTTP post.
	 * @param callback
	 *            Callback with HTTP method's response.
	 */
	public String executeHttpPost(Map<String, String> headers,Map<String, String> hParams,
			ResponseHandler<String> callback) throws IOException {
		return executeHttpPost(defaultUri, null, headers,hParams, callback);
	}

	/**
	 * Execute post method.
	 * 
	 * @param uri
	 *            URI to use when processing this HTTP request instead of using
	 *            the default URI.
	 * @param requestPayload
	 *            Request data to post.
	 * @param hParams
	 *            Parameters for the HTTP post.
	 */
	public String executeHttpPost(String uri, T requestPayload,Map<String, String> headers,
			Map<String, String> hParams) throws IOException {
		return executeHttpPost(uri, requestPayload,headers,hParams, null);
	}
	


	
	/**
	 * Execute post method.
	 * 
	 * @param uri
	 *            URI to use when processing this HTTP request instead of using
	 *            the default URI.
	 * @param requestPayload
	 *            Request data to post.
	 * @param hParams
	 *            Parameters for the HTTP post.
	 * @param callback
	 *            Callback with HTTP method's response.
	 */
	public abstract String executeHttpPost(String uri, T requestPayload,Map<String, String> headers,
			Map<String, String> hParams, ResponseHandler<String> callback) throws IOException;

	/**
	 * Processes <code>HttpRequest</code> by executing the method, validating the
	 * response, and calling the callback.
	 * 
	 * @param httpMethod
	 *            <code>HttpMethod</code> to process.
	 * @param callback
	 *            Callback with HTTP method's response.
	 */
	protected abstract String execute(HttpRequest request,
			ResponseHandler<String> responseHandler) throws IOException;

	/**
	 * Processes <code>HttpMethod</code> parameters.
	 * 
	 * @param httpMethod
	 *            <code>HttpMethod</code> to process.
	 * @param hParams
	 *            Parameters for the HTTP get.
	 */
	protected void processHttpParams(HttpRequest request,
			Map<String, String> hParams) {
		if (hParams != null) {
			HttpParams params = new BasicHttpParams();
			for (Map.Entry pair : hParams.entrySet()) {
				params.setParameter((String) pair.getKey(), pair.getValue());
			}
			request.setParams(params);
		}
	}
	
	
	protected void processHttpHeaders(HttpRequest request,
			Map<String, String> headers){
		if (headers != null) {
			for (Map.Entry pair : headers.entrySet()) {
				Header header = new BasicHeader((String) pair.getKey(), (String) pair.getValue());
				request.addHeader(header);
			}
		}
		
	}



}
