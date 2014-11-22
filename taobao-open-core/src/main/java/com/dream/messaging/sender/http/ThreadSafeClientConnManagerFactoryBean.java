/**
 * 
 */
package com.dream.messaging.sender.http;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ClientConnectionManagerFactory;
import org.apache.http.conn.params.ConnManagerPNames;
import org.apache.http.conn.params.ConnManagerParamBean;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;

/**
 * @author Frank
 * 
 */
public class ThreadSafeClientConnManagerFactoryBean implements ClientConnectionManagerFactory, FactoryBean, ResourceLoaderAware,InitializingBean, DisposableBean {

	private static final Logger logger = Logger.getLogger(ThreadSafeClientConnManagerFactoryBean.class);

	private ClientConnectionManager clientConnectionManager = null;

	private ResourceLoader resourceLoader = null;

	private String keyStoreFile = null;

	private String keyStorePassword = null;

	private String trustStoreFile = null;

	private String trustStorePassword = null;

	private static char[] usbkeyPwd = null;

	private String usbKeyConfig = null;

	public String getUsbKeyConfig() {
		return usbKeyConfig;
	}

	public void setUsbKeyConfig(String usbKeyConfig) {
		this.usbKeyConfig = usbKeyConfig;
	}

	private int sslPort = 443;

	private int port = 80;

	private boolean autoEvictor = false;

	private Map params = null;

	public String getKeyStoreFile() {
		return keyStoreFile;
	}

	public void setKeyStoreFile(String keyStoreFile) {
		this.keyStoreFile = keyStoreFile;
	}

	public String getKeyStorePassword() {
		return keyStorePassword;
	}

	public void setKeyStorePassword(String keyStorePassword) {
		this.keyStorePassword = keyStorePassword;
	}

	public int getSslPort() {
		return sslPort;
	}

	public void setSslPort(int sslPort) {
		this.sslPort = sslPort;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getTrustStoreFile() {
		return trustStoreFile;
	}

	public void setTrustStoreFile(String trustStoreFile) {
		this.trustStoreFile = trustStoreFile;
	}

	public String getTrustStorePassword() {
		return trustStorePassword;
	}

	public void setTrustStorePassword(String trustStorePassword) {
		this.trustStorePassword = trustStorePassword;
	}

	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	/**
	 * @return the params
	 */
	public Map getParams() {
		return params;
	}

	/**
	 * @param params
	 *            the params to set
	 */
	public void setParams(Map params) {
		this.params = params;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.http.conn.ClientConnectionManagerFactory#newInstance(org.apache
	 * .http.params.HttpParams, org.apache.http.conn.scheme.SchemeRegistry)
	 */
	public ClientConnectionManager newInstance(HttpParams params,
			SchemeRegistry schemeRegistry) {
		return new ThreadSafeClientConnManager(params, schemeRegistry);
	}

	public Object getObject() throws Exception {

		return this.clientConnectionManager;

	}

	public Class getObjectType() {
		return ThreadSafeClientConnManager.class;
	}

	public boolean isSingleton() {
		return true;
	}

	public void afterPropertiesSet() throws Exception {
		SchemeRegistry schemeRegistry = new SchemeRegistry();

		SSLSocketFactory socketFactory = getSSLSocketFactory();

		Scheme sch1 = new Scheme("https", socketFactory, sslPort);
		schemeRegistry.register(sch1);

		PlainSocketFactory plainSocketFactory = PlainSocketFactory.getSocketFactory();
		Scheme sch2 = new Scheme("http", plainSocketFactory, port);
		schemeRegistry.register(sch2);

		HttpParams hp = new BasicHttpParams();
		if (params != null) {
			ConnManagerParamBean cpb = new ConnManagerParamBean(hp);
			String timeout = (String) params.get(ConnManagerPNames.TIMEOUT);
			if (timeout != null)
				cpb.setTimeout(Long.parseLong(timeout));

			String maxConnections = (String) params
					.get(ConnManagerPNames.MAX_TOTAL_CONNECTIONS);
			if (maxConnections != null)
				cpb.setMaxTotalConnections(Integer.parseInt(maxConnections));

			String connPerRoute = (String) params
					.get(ConnManagerPNames.MAX_CONNECTIONS_PER_ROUTE);
			if (connPerRoute != null) {
				ConnPerRouteBean cprb = new ConnPerRouteBean(Integer
						.parseInt(connPerRoute));
				cpb.setConnectionsPerRoute(cprb);
			}
		}

		this.clientConnectionManager = newInstance(hp, schemeRegistry);

		if (isAutoEvictor()) {
			IdleConnectionEvictor connEvictor = new IdleConnectionEvictor(
					this.clientConnectionManager);
			connEvictor.start();
		}
	}

	public void destroy() throws Exception {
		clientConnectionManager.shutdown();
	}


	public static class IdleConnectionEvictor extends Thread {

		private final ClientConnectionManager connMgr;

		private volatile boolean shutdown;

		private long checkPeriod = 0;

		private long idleSeconds = 0;

		public IdleConnectionEvictor(ClientConnectionManager connMgr) {
			super();
			this.connMgr = connMgr;
			this.checkPeriod = 5000;
			this.idleSeconds = 5000;
		}

		public IdleConnectionEvictor(ClientConnectionManager connMgr,
				long checkPeriod, long idleSeconds) {
			super();
			this.connMgr = connMgr;
			this.checkPeriod = checkPeriod;
			this.idleSeconds = idleSeconds;
		}

		@Override
		public void run() {
			try {
				while (!shutdown) {
					synchronized (this) {
						wait(checkPeriod);
						// Close expired connections
						connMgr.closeExpiredConnections();
						// Optionally, close connections
						// that have been idle longer than 5 sec
						connMgr.closeIdleConnections(idleSeconds,
								TimeUnit.SECONDS);
					}
				}
			} catch (InterruptedException ex) {
				// terminate
			}
		}

		public void shutdown() {
			shutdown = true;
			synchronized (this) {
				notifyAll();
			}
		}

	}

	protected KeyManager[] getKeyManagers() throws IOException,
			GeneralSecurityException {
		if (this.keyStoreFile == null) {
			//InputStream fis = this.resourceLoader.getResource(this.usbKeyConfig).getInputStream();
//			CustomUSBKeySSL cus = new CustomUSBKeySSL(fis);
//			return cus.getKeyManagers();
			return null;
		} else {
			// First, get the default KeyManagerFactory.
			String alg = KeyManagerFactory.getDefaultAlgorithm();
			KeyManagerFactory kmFact = KeyManagerFactory.getInstance(alg);
			// Next, set up the KeyStore to use. We need to load the file into
			// a KeyStore instance.
			InputStream fis = null;
			try {
				fis = this.resourceLoader
						.getResource(this.keyStoreFile).getInputStream();
				// we should check the file surfix.
				KeyStore ks = null;
				if (keyStoreFile.endsWith(".pfx")) {
					ks = KeyStore.getInstance("pkcs12");
				} else {
					ks = KeyStore.getInstance(KeyStore.getDefaultType());
				}
				ks.load(fis, this.keyStorePassword.toCharArray());
				// Now we initialise the KeyManagerFactory with this KeyStore
				kmFact.init(ks, this.keyStorePassword.toCharArray());
			} catch (IOException e) {
				throw e;
			} finally {
				if(fis != null) {
					fis.close();
				}
			}
			// And now get the KeyManagers
			KeyManager[] kms = kmFact.getKeyManagers();
			return kms;
		}

	}

	protected TrustManager[] getTrustManagers() throws IOException,GeneralSecurityException {
		if (this.trustStoreFile == null) {
			return null;
		}
		String alg = TrustManagerFactory.getDefaultAlgorithm();
		TrustManagerFactory tmFact = TrustManagerFactory.getInstance(alg);
		InputStream fis = null;
		try {
			fis = this.resourceLoader.getResource(this.trustStoreFile).getInputStream();
			KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
			ks.load(fis, this.trustStorePassword.toCharArray());
			// Now we initialise the TrustManagerFactory with this KeyStore
			tmFact.init(ks);
		} finally {
			if(fis != null) {
				fis.close();
			}
		}
		// And now get the TrustManagers
		TrustManager[] tms = tmFact.getTrustManagers();
		return tms;
	}

	protected SSLSocketFactory getSSLSocketFactory() throws IOException,
			GeneralSecurityException {
		TrustManager[] tms = getTrustManagers();
		KeyManager[] kms = getKeyManagers();
		SSLContext context = SSLContext.getInstance("TLS");
		context.init(kms, tms, null);
		SSLSocketFactory ssf = new SSLSocketFactory(context);
		return ssf;
	}

	/**
	 * @param autoEvictor
	 *            The autoEvictor to set.
	 */
	public void setAutoEvictor(boolean autoEvictor) {
		this.autoEvictor = autoEvictor;
	}

	/**
	 * @return Returns the autoEvictor.
	 */
	public boolean isAutoEvictor() {
		return autoEvictor;
	}

}
