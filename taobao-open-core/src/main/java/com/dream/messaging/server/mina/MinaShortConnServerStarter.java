package com.dream.messaging.server.mina;

import java.io.IOException;

public class MinaShortConnServerStarter {
	
	public static void main(String[] args) throws IOException{
		MinaLongConnServer minaLongConnServer = new MinaLongConnServer();
		minaLongConnServer.start();
		

		
	}

}
