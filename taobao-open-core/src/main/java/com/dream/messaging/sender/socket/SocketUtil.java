/**************************************************************************
 * Licensed Material - Property of Dawn InfoTek                           *
 * Copyright (c) Dawn InfoTek Inc. 1999, 2004, 2008 -All rights reserved. * 
 * (<http://www.dawninfotek.com>)                                         *
 *                                                                        *
 * This file contains proprietary intellectual property of                *
 * Dawn InfoTek Inc. The contents of and information in this file         *
 * is only to be used in conjunction with a valid Dawn4J license          *
 * as specified in the Dawn4J license agreement. All other use            *
 * is prohibited.                                                         *
 **************************************************************************/
package com.dream.messaging.sender.socket;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;

import com.dream.messaging.parser.Strings;


/**
 * The Class SocketUtil writes bytes into SocketOutputStream and reads bytes from SocketInputStream.
 * 
 * <p>
 * 
 * @author Liang Yanpeng
 * @author Zhang Fu
 */
public class SocketUtil {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(SocketUtil.class);
	
	/** The Constant buffer. */
	private static final int buffer = 4 * 1024;

	/** The Constant logger. */
	

	/**
	 * Merge byte arraies.
	 * 
	 * @param array1 the array1
	 * @param array2 the array2
	 * 
	 * @return the byte[]
	 */
	public static byte[] mergeByteArraies(byte[] array1, byte[] array2) {

		if (array1 == null) {
			return array2;
		} else if (array2 == null) {
			return array1;
		} else {
			byte[] result = new byte[array1.length + array2.length];
			System.arraycopy(array1, 0, result, 0, array1.length);
			System.arraycopy(array2, 0, result, array1.length, array2.length);
			return result;
		}
	}

	/**
	 * Read bytes from the given InputStream.
	 * 
	 * @param is the is
	 * 
	 * @return the byte[]
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static byte[] read(InputStream is,String encoding) throws IOException {
		int len = -1, totalLen = 0;
		boolean unfinished = true;
		byte[] bytes = new byte[SocketUtil.buffer];
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		do{
			len= is.read(bytes);
			if(len<0){
				unfinished=false;
				break;
			}
			totalLen += len;
			bos.write(bytes, 0, len);
		}while(unfinished);
		
		if(totalLen==0){
			throw new IOException("Get nothing from the input stream ...");
		}
		byte[] blength = new byte[8];
		System.arraycopy(bos.toByteArray(), 0, blength, 0, 8);
		int length = Integer.parseInt(new String(blength,encoding));
		byte[] data = new byte[length];
		System.arraycopy(bos.toByteArray(), 8, data, 0, length);
		return data;
	}

	/**
	 * Write bytes into the OutputStream by given encoding.
	 * 
	 * @param os the os
	 * @param bytes the bytes
	 * @param encoding the encoding
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void writeByte(OutputStream os, byte[] bytes, String encoding)
			throws IOException {
		String length = String.valueOf(bytes.length);
		if (length.length() < 8) {
			length = Strings.fill('0', 8 - length.length()) + length;
		}
		if (SocketUtil.logger.isDebugEnabled()) {
			SocketUtil.logger.debug(SocketUtil.class.getName()+" sending message length is:" + length+",encoding is "+encoding);
		}
		os.write(mergeByteArraies(length.getBytes(encoding), bytes));
		os.flush();
	}
}
