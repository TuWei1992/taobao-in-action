package com.dream.messaging.pool;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class ConnectionPoolClientStarter {
	private static final Logger logger = LoggerFactory.getLogger(ConnectionPoolClientStarter.class);
	
	
	private ConnectionProvider provider;
	public ConnectionPoolClientStarter() throws Exception {
		provider = ConnectionManager.getConnectionProvider();
	}

	public String send(String data) throws Exception {
		Socket socket = provider.getConnection();
		InputStream input = socket.getInputStream();
		OutputStream output = socket.getOutputStream();
		
		// write body
		byte[] sendBytes = data.getBytes();// 收到的包字节数组
		logger.debug("Sending data :"+ new String(sendBytes));
		output.write(sendBytes, 0, sendBytes.length);
		output.flush();

		// read body
		byte[] receiveBytes = new byte[1024*8];
		input.read(receiveBytes);
		socket.close();
		String result = new String(receiveBytes);
		return result;

	}

	public static void main(String[] args) throws Exception {
		ConnectionPoolClientStarter start = new ConnectionPoolClientStarter();
		for(int i = 0 ; i < 1 ; i++){
			Thread t = new Thread(new ConnectionThread(start,"test"+1));
			t.start();
		}
		
	}
}
