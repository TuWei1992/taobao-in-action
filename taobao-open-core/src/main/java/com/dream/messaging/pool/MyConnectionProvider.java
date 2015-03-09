package com.dream.messaging.pool;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MyConnectionProvider implements ConnectionProvider {
	
	private static final Logger logger = LoggerFactory.getLogger(MyConnectionProvider.class);

	private Properties pro = null;
	private static ConnectionProvider provider = null;
	private static Object object_lock = new Object();
	private String ip;
	private String port;

	/**
	 * 默认的最大连接数
	 */
	private int max_size = 20;

	/**
	 * 默认的最小连接数
	 */
	private int min_size = 10;

	/**
	 * Socket connection池数组
	 */
	private ConnectionAdapter[] socketpool = null;

	/**
	 * 构造对象的时候初始化连接池
	 * 
	 * @throws UnknownHostException
	 *             未知的主机异常
	 * @throws IOException
	 */
	private MyConnectionProvider(Properties pro) throws UnknownHostException,
			IOException {
		ip = pro.getProperty(SERVER_IP);
		port = pro.getProperty(SERVER_PORT);
		String max_size_s = pro.getProperty(MAX_SIZE);
		String min_size_s = pro.getProperty(MIN_SIZE);
		if (max_size_s != null) {
			max_size = Integer.parseInt(max_size_s);
		}
		if (min_size_s != null) {
			min_size = Integer.parseInt(min_size_s);
		}

		init(); // 构造对象的时候初始化连接池
	}

	/**
	 * 判断是否已经池化
	 * 
	 * @return boolean 如果池化返回ture,反之返回false
	 */
	public boolean isPooled() {
		if (socketpool != null) {
			return true;
		} else
			return false;
	}

	/**
	 * 返回一个连接
	 * 
	 * @return a Connection object.
	 */
	public Socket getConnection() {
		ConnectionAdapter s = null;
		for (int i = 0; i < socketpool.length; i++) {
			if (socketpool[i] != null) {
				// 如果有空闲的连接，返回一个空闲连接，如果没有，继续循环
				if (socketpool[i].isFree()) {
					s = socketpool[i];
					logger.debug("Got connection:"+s+",with index:"+i);
					s.setBusy();
					return s;
				} else{
					continue;
				}
			} else { // 如果连接为空，证明超过最小连接数，重新生成连接
				try {
					s = socketpool[i] = new ConnectionAdapter(ip,Integer.parseInt(port));
					logger.debug("Got connection:"+s+",with index:"+i);
					s.setBusy();
					return s;
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
		}
		// 如果连接仍旧为空的话，则超过了最大连接数
		if (s == null) {
			try { // 生成普通连接，由客户端自行关闭，释放资源，不再由连接池管理
				Socket socket = new Socket(ip, Integer.parseInt(port));
				logger.debug("Got connection:"+s);
				return socket;
			} catch (Exception e) { // 此异常永远不会抛出
				logger.error(e.getMessage(), e);
			}
		}
		logger.debug("Null socket will be returned.");
		return s;
	}

	/**
	 * 初始化连接池
	 * 
	 * @throws UnknownHostException
	 *             主机ip找不到
	 * @throws IOException
	 *             此端口号上无server监听
	 */
	public void init() throws UnknownHostException, IOException {
		socketpool = new ConnectionAdapter[max_size];
		for (int i = 0; i < min_size; i++) {
			socketpool[i] = new ConnectionAdapter(ip, Integer.parseInt(port));
			logger.debug("Connection has been init:"+socketpool[i]);
		}
		logger.debug("Connection pool init success.");
	}

	/**
	 * 重新启动连接池
	 * 
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	public void restart() throws UnknownHostException, IOException {
		destroy();
		init();
	}

	/**
	 * 注销此连接池
	 */
	public void destroy() {
		for (int i = 0; i < socketpool.length; i++) {
			if (socketpool[i] != null) {
				ConnectionAdapter adapter = (ConnectionAdapter) socketpool[i];
				adapter.destroy();
				System.out.print(".");
			}
		}
		logger.debug("Destory success ....");
	}

	/**
	 * 静态方法，生成此连接池实现的对象
	 * 
	 * @param pro
	 *            Properties 此连接池所需要的所有参数的封装
	 * @throws UnknownHostException
	 *             主机无法找到
	 * @throws IOException
	 *             与服务器无法建立连接
	 * @return ConnectionProvider 返回父类ConnectionProvider
	 */
	public static ConnectionProvider newInstance(java.util.Properties pro)
			throws UnknownHostException, IOException {
		if (provider == null) {
			synchronized (object_lock) {
				if (provider == null) {
					provider = new MyConnectionProvider(pro);
				}
			}
		}
		return provider;
	}

	/**
	 * 设置系统属性 通过封装系统properties对象来封装所需要的不同值
	 * SERVER_IP，SERVER_PORT，MAX_SIZE,MIN_SIZE等父类定义的不同的参数
	 * 
	 * @param pro
	 *            Properties 传进来的系统属性
	 */
	public void setProperties(Properties pro) {
		this.pro = pro;
	}
	
	public Properties getProperties() {
		return this.pro;
	}
}
