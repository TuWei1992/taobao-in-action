/**
 * 
 */
package com.dream.nio.selector;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.spi.AbstractSelectableChannel;
import java.util.Iterator;
import java.util.Set;

import org.junit.Test;

/**
 * @author Frank
 *
 */
public class SelectorTest {
	
@Test
public void test() throws IOException{
	Selector selector = Selector.open();
	AbstractSelectableChannel channel = null;
	channel.configureBlocking(false);
	SelectionKey key = channel.register(selector, SelectionKey.OP_READ);
	while(true) {
	  int readyChannels = selector.select();
	  if(readyChannels == 0) continue;
	  Set selectedKeys = selector.selectedKeys();
	  Iterator keyIterator = selectedKeys.iterator();
	  while(keyIterator.hasNext()) {
	    SelectionKey skey = (SelectionKey) keyIterator.next();
	    if(skey.isAcceptable()) {
	        // a connection was accepted by a ServerSocketChannel.
	    } else if (skey.isConnectable()) {
	        // a connection was established with a remote server.
	    } else if (skey.isReadable()) {
	        // a channel is ready for reading
	    } else if (skey.isWritable()) {
	        // a channel is ready for writing
	    }
	    keyIterator.remove();
	  }
	}
}

}
