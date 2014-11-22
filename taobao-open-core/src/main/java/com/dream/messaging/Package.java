
package com.dream.messaging;

// TODO: Auto-generated Javadoc
/**
 * The Class Package contains envelope and message.
 * 
 * <p>
 * 
 * @author Liang Yanpeng
 * @author Zhang Fu
 */
public abstract class Package implements Message {
	
	

	/* (non-Javadoc)
	 * @see com.iif.gw.core.messaging.common.Message#getMessageId()
	 */
	public String getMessageId() {
		return this.envelope.getMessageId();
	}

	/* (non-Javadoc)
	 * @see com.iif.gw.core.messaging.common.Message#setMessageId(java.lang.String)
	 */
	public void setMessageId(String messageId) {
		this.envelope.setMessageId(messageId);
	}

	/** The envelope. */
	private Envelope envelope;

	/** The message. */
	private Message message;

	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.common.Message#addFile(java.lang.String)
	 */
	public void addFile(String filename) {
	}

	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.common.Message#addFile(java.lang.String[])
	 */
	public void addFile(String[] files) {
	}

	/**
	 * Fill.
	 * 
	 * @param envelope the envelope
	 * @param message the message
	 */
	public void fill(Envelope envelope, Message message) {
		this.envelope = envelope;
		this.message = message;
	}

	/**
	 * Gets the envelope.
	 * 
	 * @return the envelope
	 */
	public Envelope getEnvelope() {
		return envelope;
	}

	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.common.Message#getFiles()
	 */
	public String[] getFiles() {
		return new String[0];
	}

	/**
	 * Gets the message.
	 * 
	 * @return the message
	 */
	public Message getMessage() {
		return message;
	}

	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.common.Message#readFileAsByte(java.lang.String)
	 */
	public byte[] readFileAsByte(String filename) {
		return new byte[0];
	};

	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.common.Message#readFileAsString(java.lang.String)
	 */
	public String readFileAsString(String filename) {
		return null;
	};

	/**
	 * Sets the envelope.
	 * 
	 * @param envelope the new envelope
	 */
	public void setEnvelope(Envelope envelope) {
		this.envelope = envelope;
	};

	/**
	 * Sets the message.
	 * 
	 * @param message the new message
	 */
	public void setMessage(Message message) {
		this.message = message;
	};

	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.common.Message#writeFile(java.lang.String, byte[])
	 */
	public boolean writeFile(String filename, byte[] content) {
		return true;
	};

	/* (non-Javadoc)
	 * @see com.dawninfotek.base.packet.common.Message#writeFile(java.lang.String, java.lang.String)
	 */
	public boolean writeFile(String filename, String content) {
		return true;
	};
}
