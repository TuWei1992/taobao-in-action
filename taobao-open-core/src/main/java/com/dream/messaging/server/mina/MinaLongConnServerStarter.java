package com.dream.messaging.server.mina;

import java.io.IOException;

public class MinaLongConnServerStarter {
	
	public static void main(String[] args) throws IOException{
		
		MinaLongConnServer minaLongConnServer = new MinaLongConnServer();
		minaLongConnServer.start();
		
	}

}
