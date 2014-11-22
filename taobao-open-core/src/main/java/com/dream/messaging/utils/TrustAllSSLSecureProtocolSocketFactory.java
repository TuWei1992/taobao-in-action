package com.dream.messaging.utils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.params.HttpConnectionParams;

public class TrustAllSSLSecureProtocolSocketFactory {

	private SSLContext sslContext = null;

	/**
	 * Constructor for MySecureProtocolSocketFactory.
	 */
	public TrustAllSSLSecureProtocolSocketFactory() {
	}

	/**
	 * 
	 * @return
	 */
	private static SSLContext createEasySSLContext() {
		try {
			SSLContext context = SSLContext.getInstance("SSL");
			context.init(null,
					new TrustManager[] { new TrustAllX509TrustManager() }, null);
			return context;
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * 
	 * @return
	 */
	private SSLContext getSSLContext() {
		if (this.sslContext == null) {
			this.sslContext = createEasySSLContext();
		}
		return this.sslContext;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.commons.httpclient.protocol.ProtocolSocketFactory#createSocket
	 * (java.lang.String, int, java.net.InetAddress, int)
	 */
	public Socket createSocket(String host, int port, InetAddress clientHost,
			int clientPort) throws IOException, UnknownHostException {

		return getSSLContext().getSocketFactory().createSocket(host, port,
				clientHost, clientPort);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.commons.httpclient.protocol.ProtocolSocketFactory#createSocket
	 * (java.lang.String, int, java.net.InetAddress, int,
	 * org.apache.commons.httpclient.params.HttpConnectionParams)
	 */
	public Socket createSocket(final String host, final int port,
			final InetAddress localAddress, final int localPort,
			final HttpConnectionParams params) throws IOException,
			UnknownHostException, ConnectTimeoutException {
		if (params == null) {
			throw new IllegalArgumentException("Parameters may not be null");
		}
		return null;
//		int timeout = params.getConnectionTimeout();
//		if (timeout == 0) {
//			return createSocket(host, port, localAddress, localPort);
//		} else {
//			return ControllerThreadSocketFactory.createSocket(this, host, port,
//					localAddress, localPort, timeout);
//		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see SecureProtocolSocketFactory#createSocket(java.lang.String,int)
	 */
	public Socket createSocket(String host, int port) throws IOException,
			UnknownHostException {
		return getSSLContext().getSocketFactory().createSocket(host, port);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * SecureProtocolSocketFactory#createSocket(java.net.Socket,java.lang.String
	 * ,int,boolean)
	 */
	public Socket createSocket(Socket socket, String host, int port,
			boolean autoClose) throws IOException, UnknownHostException {
		return getSSLContext().getSocketFactory().createSocket(socket, host,
				port, autoClose);
	}

}
