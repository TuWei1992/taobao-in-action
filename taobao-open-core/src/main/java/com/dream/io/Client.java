package com.dream.io;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class Client {
	private static final int PORT = 8001;
	
	public void start() throws IOException{
		Socket socket  = new Socket();
		SocketAddress endpoint = new InetSocketAddress(PORT);
		socket.connect(endpoint);
		OutputStream os = socket.getOutputStream();
		os.write(1);
		os.flush();
	}
	
	
	public static void  main(String[] args) throws IOException{
		Client c = new Client();
		c.start();
	}

}
