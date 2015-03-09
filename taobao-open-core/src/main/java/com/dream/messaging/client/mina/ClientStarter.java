package com.dream.messaging.client.mina;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import com.dream.messaging.pool.NIOConnectionPool;

public class ClientStarter {
	/*缓冲区大小*/  
    private static int BLOCK = 8*4096;   
    /*发送数据缓冲区*/  
    private static ByteBuffer sendbuffer = ByteBuffer.allocate(BLOCK);   
   
    /*接受数据缓冲区*/ 
    private static ByteBuffer messageLen = ByteBuffer.allocate(4);
    private static ByteBuffer receivebuffer = null; 
    
    
    private SocketChannel client = null;
    private Selector selector = null;
    
    private boolean readable = true;
    private boolean writable = true;

    public ClientStarter() throws Exception {
    	client = NIOConnectionPool.getConnection();
    	selector = NIOConnectionPool.getSelector();
    }

	public String send(String data) throws Exception {
		Set<SelectionKey> selectionKeys = null;
		Iterator<SelectionKey> iterator = null;
		SelectionKey selectionKey = null;
		int count = 0;
		boolean flag = true;
		String receiveText = null;
		while (flag) {
			selector.select();
			// 返回此选择器的已选择键集。
			selectionKeys = selector.selectedKeys();
			iterator = selectionKeys.iterator();
			while (iterator.hasNext()) {
				selectionKey = iterator.next();
				if (selectionKey.isWritable() && (writable)) {
					sendbuffer.clear();
					sendbuffer.put(data.getBytes());
					sendbuffer.flip();
					client.write(sendbuffer);
					client.register(selector, SelectionKey.OP_READ);
					writable = false;
				} else if (selectionKey.isReadable() && (readable)) {
					messageLen.clear();
					messageLen.rewind();
					int length = messageLen.asIntBuffer().get(0);
					receivebuffer = ByteBuffer.allocate(length - 12);
					receivebuffer.clear();

					// read方式竟然不阻塞
					int total = 0;
					while (total != (length - 12)) {
						count = client.read(receivebuffer);
						total += count;
					}
					client.register(selector, SelectionKey.OP_WRITE);
					receiveText = new String(receivebuffer.array(), "GBK");
					flag = false;
					readable = false;
					break;
				}
			}
		}
		NIOConnectionPool.releaseConnection(client);
		return receiveText.trim();
	}  
	
	public static void main(String[] args) throws Exception{
		ClientStarter start = new ClientStarter();
		start.send("test");
	}
}
