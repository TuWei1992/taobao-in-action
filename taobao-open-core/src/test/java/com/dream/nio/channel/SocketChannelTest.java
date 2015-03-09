/**
 * 
 */
package com.dream.nio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Frank
 *
 */
public class SocketChannelTest {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	@Test
	public void testOpen() throws IOException{
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.socket().bind(new InetSocketAddress(9999));
		while(true){
			SocketChannel sc = ssc.accept();
			ByteBuffer buf = ByteBuffer.allocate(48);
			int bytes =  sc.read(buf);
//			logger.debug(bytes);
		}
	}
	
	
	@Test
	public void testConnect() throws IOException{
		SocketChannel sc = SocketChannel.open();
		sc.socket().connect( new InetSocketAddress("localhost",9999));
		String newData = "New String to write to file..." + System.currentTimeMillis();
		ByteBuffer buf = ByteBuffer.allocate(48);
		buf.clear();
		buf.put(newData.getBytes());
		while(buf.hasRemaining()) {
		    sc.write(buf);
		}
	}

}
