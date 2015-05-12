package com.dream.rest.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Frank
 * @version 1.0
 */

public class HttpBodyHolder {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	private static  ThreadLocal<Object> threadLocal = new ThreadLocal<Object>();
	
	public static void setRequest(Object object){
		threadLocal.set(object);
	}
	
	public static void getRequest(Object object){
		threadLocal.get();
	}
}

