package com.dream.messaging;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;



/**
 * The Class BaseMessageImpl is an abstract implemention of Message.It provides the universal method to its subclass.
 * 
 * <p>
 * 
 * @author Liang Yanpeng
 * @author Zhang Fu
 */
public abstract class BaseMessageImpl implements Message, Serializable {

	

	/**
	 * Comment for <code>serialVersionUID</code>
	 */
	private static final long serialVersionUID = 550561011841449297L;
	

	/** The files. */
	private transient List<String> files = new ArrayList<String>();

	/** The message id. */
	private String messageId;

	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.common.Message#addFile(java.lang.String)
	 */
	public void addFile(String filename) {
		files.add(filename);
	}

	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.common.Message#addFile(java.lang.String[])
	 */
	public void addFile(String[] filesname) {
		for (int i = 0; i < filesname.length; i++) {
			files.add(filesname[i]);
		}
	}

	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.common.Message#getFiles()
	 */
	public String[] getFiles() {
		return files == null ? new String[0] : (String[]) files
				.toArray(new String[files.size()]);
	}

	/**
	 * Gets the message id.
	 * 
	 * @return the message id
	 */
	public String getMessageId() {
		return messageId;
	}

	

	/**
	 * Sets the message id.
	 * 
	 * @param messageId the new message id
	 */
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

}
