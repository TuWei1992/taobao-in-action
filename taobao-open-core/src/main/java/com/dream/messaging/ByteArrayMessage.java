package com.dream.messaging;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

// TODO: Auto-generated Javadoc
/**
 * The Class ByteArrayMessage represents a message which contains byte array.
 * 
 * <p>
 * 
 * @author Liang Yanpeng
 * @author Zhang Fu
 */
public class ByteArrayMessage extends BaseMessageImpl {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The bos. */
	private transient ByteArrayOutputStream bos = new ByteArrayOutputStream();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dawninfotek.base.packet.common.Message#append(java.lang.Object)
	 */
	public Message append(Object msg) throws IOException {
		if (msg instanceof ByteArrayMessage) {
			bos.write((byte[]) ((ByteArrayMessage) msg).getContent());
		} else if (msg instanceof byte[]) {
			bos.write((byte[]) msg);
		} else if (msg instanceof Byte) {
			bos.write(new byte[] { ((Byte) msg).byteValue() });
		} else if (msg instanceof InputStream) {
			InputStream tmp = (InputStream) msg;
			int length = tmp.available();
			byte[] ba = new byte[length];
			tmp.read(ba);
			bos.write(ba);
		}
		return this;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dawninfotek.base.packet.common.Message#getContent()
	 */
	public Object getContent() {

		return bos.toByteArray();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.dawninfotek.base.packet.common.Message#length()
	 */
	public int length() {

		return bos.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.dawninfotek.base.packet.common.Message#setContent(java.lang.Object)
	 */
	public void setContent(Object content) throws IOException {
		bos.reset();
		append(content);
	}

	public byte[] readFileAsByte(String filename) throws IOException {
		// 修复Unreleased Resource: Streams
		InputStream is = null;
		try {
			is = new FileInputStream(filename);
			int length = is.available();
			byte[] bs = new byte[length];
			is.read(bs);
			return bs;
		} finally {
			if (is != null) {
				is.close();
			}
		}
	}

	public String readFileAsString(String filename) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean writeFile(String filename, byte[] content)
			throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean writeFile(String filename, String content)
			throws IOException {
		// TODO Auto-generated method stub
		return false;
	}

	public String toString() {
		return new String(bos.toByteArray());
	}

}
