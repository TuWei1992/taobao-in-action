/**
 * 
 */
package com.dream.messaging.sender.http;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerPNames;
import org.apache.http.conn.params.ConnManagerParamBean;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpConnectionParamBean;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author Frank
 *
 */
public class HttpClientFacotryBean implements FactoryBean<HttpClient>,InitializingBean,DisposableBean  {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(HttpClientFacotryBean.class);
	
	private DefaultHttpClient httpClient;
	private ClientConnectionManager connectionManager = null;
	private Map params = null;
	
	/**
	 * @return the connectionManager
	 */
	public ClientConnectionManager getConnectionManager() {
		return connectionManager;
	}

	/**
	 * @return the parameters
	 */
	public Map getParams() {
		return params;
	}


	/**
	 * @param connectionManager the connectionManager to set
	 */
	public void setConnectionManager(ClientConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}

	/**
	 * @param params the parameters to set
	 */
	public void setParams(Map params) {
		this.params = params;
	}


	
	
	public HttpClient getObject() throws Exception {
		return this.httpClient;
	}

	public Class<HttpClient> getObjectType() {
		return HttpClient.class;
	}

	public boolean isSingleton() {
		return false;
	}

	public void afterPropertiesSet() throws Exception {
		//test
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
		schemeRegistry.register(new Scheme("https",SSLSocketFactory.getSocketFactory(), 443));
		
		HttpParams hp = new BasicHttpParams();
		if (params != null) {
			HttpConnectionParamBean hcp = new HttpConnectionParamBean(hp);
			String connectionTimeout = (String) params.get(CoreConnectionPNames.CONNECTION_TIMEOUT);
			if(connectionTimeout!=null)
				hcp.setConnectionTimeout(Integer.parseInt(connectionTimeout));
			
			String soTimeout = (String) params.get(CoreConnectionPNames.SO_TIMEOUT);
			if(connectionTimeout!=null)
				hcp.setSoTimeout(Integer.parseInt(soTimeout));
			
			ConnManagerParamBean cpb = new ConnManagerParamBean(hp);
			// 设置最大连接数
			cpb.setMaxTotalConnections(Integer.parseInt(params.get("maxTotalConns")==null ? "500" : params.get("maxTotalConns").toString()));
			// 设置每个地址允许最大连接数
			cpb.setConnectionsPerRoute(new ConnPerRouteBean(Integer.parseInt(params.get("maxConnsPerHost")==null ? "100" : params.get("maxConnsPerHost").toString())));
			String timeout = (String) params.get(ConnManagerPNames.TIMEOUT);
			if(timeout!=null)
				cpb.setTimeout(Long.parseLong(timeout));
		}
		
		connectionManager = new ThreadSafeClientConnManager(hp, schemeRegistry);

		this.httpClient = new DefaultHttpClient(connectionManager,hp);
		
		this.httpClient.addRequestInterceptor(new HttpRequestInterceptor() {
		    public void process(final HttpRequest request, final HttpContext context) throws HttpException, IOException {
		        AtomicInteger count = (AtomicInteger) context.getAttribute("count");
		        if(count==null){
		        	count = new AtomicInteger(1);
		        	context.setAttribute("count",count);
		        }
		        request.addHeader("Count", Integer.toString(count.getAndIncrement()));
		        if(logger.isDebugEnabled()){
		        	logger.debug("This http client going to execute " + count + " times.");
		        }
		    }
		    
		});
		
	}

	public void destroy() throws Exception {
		if (params != null)
			params.clear();
		if (connectionManager != null)
			connectionManager.closeExpiredConnections();
	}

}
