
package com.dream.messaging;

import java.io.IOException;

/**
 * The Interface Message defines an interface used as a carrier of transaction data.
 * 
 * <p>
 * 
 * @author Liang Yanpeng
 * @author Zhang Fu
 * @see StringMessage
 * @see ByteArrayMessage
 * @see Package
 */
public interface Message {
	
	/**
	 * Get the message id.
	 * @return
	 */
	public String getMessageId();
	
	
	/**
	 * Set the message id.
	 * @return
	 */
	public void setMessageId(String messageId);

	/**
	 * Adds the file.
	 * 
	 * @param filename the filename
	 */
	public abstract void addFile(String filename);

	/**
	 * Adds the file.
	 * 
	 * @param files the files
	 */
	public abstract void addFile(String[] files);

	/**
	 * Append.
	 * 
	 * @param msg the message.
	 * 
	 * @return the message
	 * 
	 * @throws BaseException the base exception
	 * @throws Exception 
	 */
	public abstract Message append(Object msg) throws IOException;

	/**
	 * Gets the content.
	 * 
	 * @return the content
	 */
	public abstract Object getContent();

	/**
	 * Gets the files.
	 * 
	 * @return the files
	 */
	public abstract String[] getFiles();

	/**
	 * Length.
	 * 
	 * @return the int
	 */
	public abstract int length();

	/**
	 * Read file as byte.
	 * 
	 * @param filename the filename
	 * 
	 * @return the byte[]
	 */
	public abstract byte[] readFileAsByte(String filename)  throws IOException;

	/**
	 * Read file as string.
	 * 
	 * @param filename the filename
	 * 
	 * @return the string
	 */
	public abstract String readFileAsString(String filename)  throws IOException;

	/**
	 * Sets the content.
	 * 
	 * @param content the new content
	 * 
	 * @throws BaseException the base exception
	 */
	public abstract void setContent(Object content) throws IOException;

	/**
	 * Write file.
	 * 
	 * @param filename the filename
	 * @param content the content
	 * 
	 * @return true, if successful
	 */
	public abstract boolean writeFile(String filename, byte[] content)  throws IOException;

	/**
	 * Write file.
	 * 
	 * @param filename the filename
	 * @param content the content
	 * 
	 * @return true, if successful
	 */
	public abstract boolean writeFile(String filename, String content)  throws IOException;
}
