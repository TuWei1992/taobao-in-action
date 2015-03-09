package com.dream.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Server {
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private static final int PORT = 8001;
	public void start() throws IOException{
		ServerSocket ss = new ServerSocket(PORT);
		while(true){
			Socket socket = ss.accept();	
			process(socket);
		}
	}
	
	private void process(Socket socket) throws IOException{
		InputStream is = socket.getInputStream();
		int i = is.read();
		logger.debug(""+i);
		return ;
	}
	
	
	public static void  main(String[] args) throws IOException{
		Server s = new Server();
		s.start();
	}
}
