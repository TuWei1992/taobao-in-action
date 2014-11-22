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
package com.dream.messaging.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

/**
 * The Class FileUtil wraps the mehtods read and write files.
 * 
 * <p>
 * 
 * @author Liang Yanpeng
 * @author Zhang Fu
 */
public  class FileUtil {
	
	/** The Constant BUFFER_LENGTH. */
	public static final int BUFFER_LENGTH = 1024 * 4;
	
	/**
	 * Read.
	 * 
	 * @param is the is
	 * 
	 * @return the byte[]
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static byte[] read(InputStream is) throws IOException{
		byte[] bytes = new byte[BUFFER_LENGTH];
		int len = is.read(bytes);
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		while (len > 0){
			bos.write(bytes, 0, len);
			len = is.read(bytes);
        }
        return bytes;
	}
	
	/**
	 * Read.
	 * 
	 * @param reader the reader
	 * 
	 * @return the string
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static String read(Reader reader) throws IOException{
		char[] ch= new char[BUFFER_LENGTH];
		StringBuffer target = new StringBuffer();
		int len = reader.read(ch);
		while (len > 0){
			target.append(ch,0,len);
			len = reader.read(ch);
		}
		return target.toString();
	}
	
	/**
	 * Write.
	 * 
	 * @param os the os
	 * @param bytes the bytes
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void write(OutputStream os,byte[] bytes) throws IOException{
		os.write(bytes);
		os.flush();
		os.close();
	}
	
	/**
	 * Write.
	 * 
	 * @param writer the writer
	 * @param content the content
	 * 
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void write(Writer writer,String content) throws IOException{
		writer.write(content);
		writer.flush();
		writer.close();
	}
}
