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
package com.dream.messaging.engine.ra;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.dream.messaging.utils.BitConverter;

// TODO: Auto-generated Javadoc
/**
 * The Class RAUtil.
 * 
 * <p>
 * 
 * @author Liang Yanpeng
 * @author Zhang Fu
 */
public class RAUtil {
	
	/** The buffer. */
	private static int buffer = 4* 1024;
	
	/** The header len. */
	private static int headerLen=20;
	
	/**
	 * Write byte.
	 * 
	 * @param os the os
	 * @param bs the bs
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void writeByte(OutputStream os, byte[] bs) throws IOException {
		os.write(bs);
		os.flush();
//		os.close();
	}

	/**
	 * Read.
	 * 
	 * @param is the is
	 * 
	 * @return the byte[]
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static byte[] read(InputStream is) throws IOException {
		byte[] bytes = new byte[buffer];
		int len=-1, totalLen = 0,msgLen=0;
		boolean gotLen = false;
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		do{
			len = is.read(bytes);
			totalLen += len;
			
			bos.write(bytes,0,len);
			
			if(!gotLen && totalLen >= headerLen ){			
				bytes = bos.toByteArray();
				byte[] length = new byte[4];
				System.arraycopy(bytes, headerLen -4, length, 0, 4);
				msgLen = BitConverter.bytesToInt(length);
				System.out.println("msg body len = "+msgLen);
				gotLen = true;
			}
		}while(totalLen < msgLen + headerLen);
		bytes = new byte[msgLen + headerLen];
		System.arraycopy(bos.toByteArray(), 0, bytes, 0, msgLen + headerLen);
		return bytes;
	}

}
