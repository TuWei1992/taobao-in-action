package com.dream.messaging.pool;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NIOConnectionPool {
	protected static final Logger logger = LoggerFactory.getLogger(NIOConnectionPool.class);
	
	 private static String IP = "127.0.0.1";
	 private static String PORTSTRING = "8002";
	 private static int PORT = Integer.parseInt(PORTSTRING);
	 private static int CONNECTION_POOL_SIZE = 10;
	 private static NIOConnectionPool self = null;
	 private static Selector selector  = null;
	 private static InetSocketAddress SERVER_ADDRESS = null; 

	 private Hashtable<Integer, SocketChannel> socketPool = null; // 连接池
	 private boolean[] socketStatusArray = null; // 连接的状态(true-被占用,false-空闲)
	 
	 /**
	  * 初始化连接池，最大TCP连接的数量为10
	  * 
	  * @throws IOException
	  */
	 public static synchronized void init() throws Exception {
		  self = new NIOConnectionPool();
		  self.socketPool = new Hashtable<Integer, SocketChannel>();
		  self.socketStatusArray = new boolean[CONNECTION_POOL_SIZE];
		  buildConnectionPool();
	 }
	 
	 
	 
	 /**
	  * 建立连接池
	  */
	 public synchronized static void buildConnectionPool() throws Exception {
		  if (self == null) {
			  init();
		  }
		  for (int i = 0; i < CONNECTION_POOL_SIZE; i++) {
			   SocketChannel client = allocateSocketChannel();
			   self.socketPool.put(new Integer(i), client);
			   self.socketStatusArray[i] = false;
		  }
	 }
	 
	 
	 /**
	  * 从连接池中获取一个空闲的Socket
	  * 
	  * @return 获取的TCP连接
	  */
	public static SocketChannel getConnection() throws Exception {
		if (self == null){
			init();
		}
		int i = 0;
		for (i = 0; i < CONNECTION_POOL_SIZE; i++) {
			if (!self.socketStatusArray[i]) {
				self.socketStatusArray[i] = true;
				break;
			}
		}
		if (i < CONNECTION_POOL_SIZE) {
			return self.socketPool.get(new Integer(i));
		} else {
			// 目前连接池无可用连接时只是简单的新建一个连接
			SocketChannel newClient = allocateSocketChannel();
			CONNECTION_POOL_SIZE++;
			self.socketPool.put(CONNECTION_POOL_SIZE, newClient);
			return newClient;
		}
	}
	 
	 
	 /**
	  * 当获得的socket不可用时，重新获得一个空闲的socket。
	  * 
	  * @param socket
	  *            不可用的socket
	  * @return 新得到的socket
	  * @throws Exception
	  */
	 public static SocketChannel rebuildConnection(SocketChannel socket) throws Exception {
		if (self == null) {
			init();
		}
		SocketChannel newSocket = null;
		try {
			for (int i = 0; i < CONNECTION_POOL_SIZE; i++) {
				if (self.socketPool.get(new Integer(i)) == socket) {
					newSocket = allocateSocketChannel();
					self.socketPool.put(new Integer(i), newSocket);
					self.socketStatusArray[i] = true;
				}
			}
		} catch (Exception e) {
			logger.debug("重建连接失败!");
			throw new RuntimeException(e);
		}
		return newSocket;
	 }
	 
	 
	 /**
	  * 将用完的socket放回池中，调整为空闲状态。此时连接并没有断开。
	  * 
	  * @param socket
	  *            使用完的socket
	  * @throws Exception
	  */
	 public static void releaseConnection(SocketChannel socket) throws Exception {
		if (self == null) {
			init();
		}
		for (int i = 0; i < CONNECTION_POOL_SIZE; i++) {
			if (self.socketPool.get(new Integer(i)) == socket) {
				self.socketStatusArray[i] = false;
				break;
			}
		}
	 }
	 
	 
	 
	 /**
	  * 断开池中所有连接
	  * 
	  * @throws Exception
	  */
	public synchronized static void releaseAllConnection() throws Exception {
		if (self == null) {
			init();
		}
		// 关闭所有连接
		SocketChannel socket = null;
		for (int i = 0; i < CONNECTION_POOL_SIZE; i++) {
			socket = self.socketPool.get(new Integer(i));
			try {
				socket.close();
				self.socketStatusArray[i] = false;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	 
	 
	public static SocketChannel allocateSocketChannel() {
		SERVER_ADDRESS = new InetSocketAddress(IP, PORT);
		SocketChannel socketChannel = null;
		SocketChannel client = null;
		try {
			socketChannel = SocketChannel.open();
			socketChannel.configureBlocking(false);
			selector = Selector.open();
			socketChannel.register(selector, SelectionKey.OP_CONNECT);
			socketChannel.connect(SERVER_ADDRESS);
			Set<SelectionKey> selectionKeys;
			Iterator<SelectionKey> iterator;
			SelectionKey selectionKey;
			selector.select();
			selectionKeys = selector.selectedKeys();
			iterator = selectionKeys.iterator();
			while (iterator.hasNext()) {
				selectionKey = iterator.next();
				if (selectionKey.isConnectable()) {
					client = (SocketChannel) selectionKey.channel();
					if (client.isConnectionPending()) {
						client.finishConnect();
						client.register(selector, SelectionKey.OP_WRITE);
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return client;
	}
	 
	 public static Selector getSelector() {
		  return selector;
	 }
}
