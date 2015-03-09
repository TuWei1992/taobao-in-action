package com.dream.nio.channel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class Server {
	
	private Selector selector;
	
	private void initServer(int port) throws IOException{
		ServerSocketChannel ssc = ServerSocketChannel.open(); 
		ssc.configureBlocking(false);
		ssc.socket().bind(new InetSocketAddress(port));  
		this.selector = Selector.open(); 
		ssc.register(this.selector, SelectionKey.OP_ACCEPT);
	}
	
	
	private void listen() throws IOException{
		while(true){
			selector.select();
			Iterator itr = this.selector.selectedKeys().iterator();
			while(itr.hasNext()){
				SelectionKey key = (SelectionKey) itr.next();
				itr.remove();
				if(key.isAcceptable()){
					 ServerSocketChannel server = (ServerSocketChannel) key.channel(); 
					 SocketChannel channel = server.accept(); 
					 channel.configureBlocking(false); 
					 channel.write(ByteBuffer.wrap(new String("向客户端发送了一条信息").getBytes()));
					 channel.register(this.selector, SelectionKey.OP_READ);  
				}
				if(key.isReadable()){
					 read(key); 
				}
			}
		}
	}


	private void read(SelectionKey key) throws IOException {
		// TODO Auto-generated method stub
		 // 服务器可读取消息:得到事件发生的Socket通道  
        SocketChannel channel = (SocketChannel) key.channel();  
        // 创建读取的缓冲区  
        ByteBuffer buffer = ByteBuffer.allocate(10);  
        channel.read(buffer);  
        byte[] data = buffer.array();  
        String msg = new String(data).trim();  
//        logger.debug("服务端收到信息："+msg);  
        ByteBuffer outBuffer = ByteBuffer.wrap(msg.getBytes());  
        channel.write(outBuffer);// 将消息回送给客户端  
	}
	
	
	public static void main(String[] args) throws IOException{
		int port = 9001;
		Server s = new Server();
		s.initServer(port);
		s.listen();
	}
}
