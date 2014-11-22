package com.dream.messaging.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author Frank
 * 
 */
public class SocketMessagingServer extends AbstractMessagingServer {

	private static final Logger logger = LoggerFactory.getLogger(SocketMessagingServer.class);

	private final ExecutorService executor = Executors.newCachedThreadPool();

	private SocketMessagingHandler socketMessagingHandler;

	private int port;

	private Thread thread;

	private volatile boolean continued = true;

	public SocketMessagingHandler getSocketMessagingHandler() {
		return socketMessagingHandler;
	}

	public void setSocketMessagingHandler(
			SocketMessagingHandler socketMessagingHandler) {
		this.socketMessagingHandler = socketMessagingHandler;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void reset() {
		this.stop();
		this.continued = true;
		this.start();
	}

	public void start() {
		thread = new Thread(this,this.getClass().getName());
		thread.start();
	}

	public void stop() {
		this.continued = false;
	}

	private void process(Socket socket) {
		socketMessagingHandler.setSocket(socket);
		executor.execute(socketMessagingHandler);
	}

	public void run() {
		ServerSocket server = null;
		try {
			server = new ServerSocket(port);
			server.setReuseAddress(true);
			while (continued) {
				logger.info("===============================Socket Server is waiting to recieve message========================================");
				Socket socket = server.accept();
				logger.info("===============================Socket Server recieves message and is going to process========================================");
				process(socket);
			}
		} catch (IOException e) {
			logger.error("IOException occurs in SocketServer#run",e);
		}

	}

}
