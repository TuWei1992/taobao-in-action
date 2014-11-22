/**************************************************************************
 * Licensed Material - Property of Dawn InfoTek                           *
 * Copyright (c) Dawn InfoTek Inc. 1999, 2004, 2008 -All rights reserved. * 
 * (<http://www.dawninfotek.com>)                                         *
 *                                                                        *
 * This file contains proprietary intellectual property of                *
 * Dawn InfoTek Inc. The contents of and information in this file         *
 * is only to be used in conjunction with a valid Dawn4J license          *
 * as specified in the Dawn4J license agreement. All other use            *
 * is prohibited.                                                         *
 **************************************************************************/
package com.dream.messaging.sender.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.net.SocketTimeoutException;

import org.apache.log4j.Logger;

import com.dream.messaging.ByteArrayMessage;
import com.dream.messaging.ConnectionException;
import com.dream.messaging.Message;
import com.dream.messaging.sender.MessageSender;



/**
 * The Class CCMessageSender sends and recieves message relative to credit card system whose format is fixed format.
 * 
 * <p>
 * 
 * @author Liang Yanpeng
 * @author Zhang Fu
 */
public class SocketMessageSender extends MessageSender {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(SocketMessageSender.class);

	/** The Constant ENCODING. */
	public static final String CONST_ENCODING = "encoding";

	/** The Constant PORT. */
	public static final String CONST_PORT = "servicePort";

	/** The Constant SERVER. */
	public static final String CONST_SERVER = "host";

	/** The Constant SOCKET_PATH. */
	public static final String CONST_SOCKET_PATH = "externalSystem";

	/** The Constant TIMEOUT. */
	public static final String CONST_TIMEOUT = "timeOut";

	/** The encoding. */
	private String encoding;

	/** The port. */
	private int port;

	/** The server. */
	private String server;

	/** The server id. */
	private String serverId;

	/** The timeout. */
	private int timeout;
	
	
	public SocketMessageSender() {
		
	}
	

	/**
	 * @return the encoding
	 */
	public String getEncoding() {
		return encoding;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @return the server
	 */
	public String getServer() {
		return server;
	}

	/**
	 * @return the serverId
	 */
	public String getServerId() {
		return serverId;
	}

	/**
	 * @return the timeout
	 */
	public int getTimeout() {
		return timeout;
	}

	/**
	 * @param encoding the encoding to set
	 */
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(int port) {
		this.port = port;
	}

	/**
	 * @param server the server to set
	 */
	public void setServer(String server) {
		this.server = server;
	}

	/**
	 * @param serverId the serverId to set
	 */
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	/**
	 * @param timeout the timeout to set
	 */
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}
	
	


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dawninfotek.base.packet.sender.MessageSender#send(com.dawninfotek.base.packet.common.Message)
	 */
	@Override
	protected Message send(Message req) throws ConnectionException {
		Socket socket = new Socket();
		InputStream is = null;
		OutputStream os = null;
		try {
			SocketAddress endpoint = new InetSocketAddress(server, port);
			socket.connect(endpoint, timeout);
			os = socket.getOutputStream();
			SocketUtil.writeByte(os, (byte[]) req.getContent(), encoding);
			is = socket.getInputStream();
			byte[] bytes = SocketUtil.read(is,encoding);
			Message rsp = new ByteArrayMessage();
			rsp.setContent(bytes);
			return rsp;
		} catch (SocketTimeoutException e) {
			throw new ConnectionException("ERROR","Can not connect to the remote host:"+toString() ,e);
		} catch (IOException e) {
			throw new ConnectionException("ERROR","Can not write or read the message to or from the remote host:"+toString(),e);
		} finally {
			try {
				if(os != null) {
					os.close();
				}
				if(is != null) {
					is.close();
				}
				socket.close();
			} catch (IOException e) {
				throw new ConnectionException("","Can not write or read the message to or from the remote host:"+toString(),e);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dawninfotek.base.packet.sender.MessageSender#send(java.lang.Object,
	 *      com.dawninfotek.base.packet.common.Message)
	 */
	@Override
	public Message send(Object obj, Message msgReq) throws ConnectionException {
		return send(msgReq);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return server + ":" + port + " timeout:" + timeout + " encoding:"
				+ encoding;
	}
}
