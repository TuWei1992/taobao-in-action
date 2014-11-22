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
package com.dream.messaging.engine.xml;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class XMLUtil {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(XMLUtil.class);
	
	/** The Constant buffer. */
	private static final int buffer = 8 * 1024;

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
	public static byte[] read(InputStream is) throws IOException {
		int len = -1;
		byte[] bytes = new byte[XMLUtil.buffer];
		len = is.read(bytes);
		if(len==0){
			throw new IOException("Get nothing from the input stream ...");
		}
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		bos.write(bytes, 0, len);
		bytes = new byte[len];
		System.arraycopy(bos.toByteArray(), 0, bytes, 0, len);
		return bytes;
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
		if (XMLUtil.logger.isDebugEnabled()) {
			XMLUtil.logger.debug(XMLUtil.class.getName()+" sending message length is:" + length+",encoding is "+encoding);
		}
		os.write(bytes);
		os.flush();
	}
	
	
	
	private static final String END_TAG="</Message>";
	/**
	 * 
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public static final String readString(InputStream is,String charsetName) throws IOException{
		 Scanner in = new Scanner(is,charsetName);
		 StringBuffer response = new StringBuffer();
		 boolean done = false;
         while (!done&&in.hasNextLine())
         {
        	String line =in.nextLine();
        	if (XMLUtil.logger.isDebugEnabled()) {
    			XMLUtil.logger.debug(XMLUtil.class.getName()+" recieved message:"+line);
    		}
            if (line.trim().endsWith(END_TAG))
            	done = true;
            response.append(line);
         }
         return response.toString();
	}
	
	
	/**
	 * 
	 * @param os
	 * @param request
	 * @param charsetName
	 * @throws IOException
	 */
	public static final void writeString(OutputStream os,String request,String charsetName) throws IOException{
		OutputStreamWriter writer = new OutputStreamWriter(os,charsetName);
		BufferedWriter bw = new BufferedWriter(writer);
		bw.write(request);
		bw.write("\r\n");
		bw.flush();
	}
	
	
	
}
