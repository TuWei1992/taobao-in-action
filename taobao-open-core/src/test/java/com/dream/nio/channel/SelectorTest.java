package com.dream.nio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SelectorTest {

	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	private static final long TIMEOUT = 3000;

	@Test
	public void test() throws IOException{
		Selector selector = Selector.open();
		ServerSocketChannel ssc = ServerSocketChannel.open();
		ssc.socket().bind(new InetSocketAddress(9999));
		ssc.configureBlocking(false); //nonblocking
		ssc.register(selector, SelectionKey.OP_ACCEPT); //Channel注册selector,并告知channel感兴趣的操作
		
		while(true){
			if(selector.select(TIMEOUT)==0){ //返回准备就绪I/O的SelectionKey数
				logger.debug("."); //to do others
				continue;
			}
			
			Iterator<SelectionKey> keyIter =selector.selectedKeys().iterator();//获取已选的SelectionKey集合
			while(keyIter.hasNext()){
				SelectionKey key =keyIter.next();
				if(key.isAcceptable()){
					logger.debug("Accept.");
				}
				if(key.isReadable())
					logger.debug("Read.");
				
				//SelectionKey is invalid if it is cancelled, its channel is closed, or its selector is closed.
				if(key.isValid() && key.isWritable()) 
					logger.debug("Write.");
				
				keyIter.remove(); //手动清空,因为Selector只会在已选的SelectionKey集中添加
			}
		}
	}
}
