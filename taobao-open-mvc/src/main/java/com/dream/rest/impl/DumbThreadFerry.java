/**
 * 
 * 
 */
package com.dream.rest.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dream.rest.ThreadFerry;

/**
 * 不进行任何操作的实现类,仅为方便逻辑的运行
 * @author : Frank
 * @date: 
 */
public class DumbThreadFerry implements ThreadFerry {
	
	protected  Logger logger = LoggerFactory.getLogger(getClass());

    public void doInSrcThread() {
    	logger.info("Doing nothing in source thread at DumbThreadFerry.");
    }


    public void doInDestThread() {
    	logger.info("Doing nothing in dest thread at  DumbThreadFerry.");
    }
}
