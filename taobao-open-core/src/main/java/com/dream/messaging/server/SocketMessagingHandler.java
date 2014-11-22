
package com.dream.messaging.server;

import java.net.Socket;
import java.util.Properties;

/**
 * 
 * @author Frank
 *
 */
public abstract class SocketMessagingHandler extends AbstractMessagingHandler{
	
	/** The socket. */
	protected Socket socket;
	
	/** The prop. */
	protected Properties properties;
	
	/**
	 * Instantiates a new ISocketService.
	 */
	public SocketMessagingHandler(){
		
	}
	
	/**
	 * Instantiates a new socket service.
	 * 
	 * @param socket the socket
	 */
	public SocketMessagingHandler(Socket socket){
		this.socket=socket;
	}
	
	/**
	 * Gets the socket.
	 * 
	 * @return the socket
	 */
	public Socket getSocket() {
		return socket;
	}
	
	/**
	 * Sets the socket.
	 * 
	 * @param socket the new socket
	 */
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	
	/**
	 * Gets the prop.
	 * 
	 * @return the prop
	 */
	public Properties getProperties() {
		return properties;
	}
	
	/**
	 * Sets the prop.
	 * 
	 * @param prop the new prop
	 */
	public void setProperties(Properties prop) {
		this.properties = prop;
	}
	
}
