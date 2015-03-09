package com.dream.messaging.client.mina;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LongConnClient {
	
	protected static final Logger logger = LoggerFactory.getLogger(LongConnClient.class);
	private String ip;

    private int port;

    private static Socket socket = null;

    private static int timeout = 50 * 1000;

 

    public LongConnClient(String ip, int port) {

       this.ip = ip;

       this.port = port;

    }

    
    
 

    public void receiveAndSend() throws IOException {

       InputStream input = null;

       OutputStream output = null;

 

       try {

           if (socket == null ||socket.isClosed() || !socket.isConnected()) {

              socket = new Socket();

              InetSocketAddress addr = new InetSocketAddress(ip, port);

              socket.setReceiveBufferSize(32*1024);
              
              socket.setSendBufferSize(32*1024);
              
              socket.setSoLinger(true, 10);
              
              socket.setTcpNoDelay(true);
              
              logger.debug(""+socket.getSendBufferSize());
              
              socket.connect(addr, timeout);

              socket.setSoTimeout(timeout);

              logger.debug("TcpKeepAliveClientnew ");

           }

 

           input = socket.getInputStream();

           output = socket.getOutputStream();
           
           
           // send
           
           String test = new String("test");
           
           logger.debug("TcpKeepAliveClientsend date :" +test);
           
           byte[] sendBytes = test.getBytes();

           output.write(sendBytes, 0, sendBytes.length);

           output.flush();

           // read body

           byte[] receiveBytes = {};// 收到的包字节数组

           while (true) {

              if (input.available() > 0) {

                  receiveBytes = new byte[input.available()];

                  input.read(receiveBytes);
                  

              }

           }

 

       } catch (Exception e) {

           e.printStackTrace();

           logger.debug("TcpClientnew socket error");

       }

    }

 

    public static void main(String[] args) throws Exception {

       LongConnClient client = new LongConnClient("127.0.0.1", 8002);

       client.receiveAndSend();

    }
}
