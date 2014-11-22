/**
 * 
 */
package com.dream.messaging.server;

/**
 * @author Frank
 * 
 */
public interface MessagingServer {

	public void start() throws Exception;

	public void stop() throws Exception;

	public void reset() throws Exception;

}
