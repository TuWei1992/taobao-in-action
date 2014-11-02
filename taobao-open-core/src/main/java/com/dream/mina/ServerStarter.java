package com.dream.mina;

import java.io.IOException;

public class ServerStarter {
	
	public static void main(String[] args) throws IOException{
		MinaLongConnServer minaLongConnServer = new MinaLongConnServer();
		minaLongConnServer.start();
		
		MinaShortConnServer minaShortConnServer = new MinaShortConnServer();
		minaShortConnServer.start();
		
	}

}
