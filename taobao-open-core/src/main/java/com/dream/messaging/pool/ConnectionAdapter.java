package com.dream.messaging.pool;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
* <p>socket连接的简单实现</p>
* <p>Description: </p>
* @author Frank
* @version 1.0
*/
public class ConnectionAdapter extends Socket {

	 /**
	   * 连接状态
	   */
	  private volatile boolean status = true;
	  /**
	   * 默认的构造函数
	   */
	  public ConnectionAdapter() {
	    super();
	  }
	  public ConnectionAdapter(String host,int port)throws UnknownHostException,IOException{
	    super(host,port);
	  }
	  /**
	   * 判断此连接是否空闲
	   * @return boolean 空闲返回ture,否则false
	   */
	  public boolean isFree(){
	    return status;
	  }
	  /**
	   * 当使用此连接的时候设置状态为false（忙碌）
	   */
	  public void setBusy(){
	    this.status = false;
	  }
	  /**
	   * 当客户端关闭连接的时候状态设置为true(空闲）
	   */
	  public void close(){
	    status = true;
	  }
	  
	  
	  public void destroy(){
	    close();
	  }
	
}
