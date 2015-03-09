
package com.dream.rest.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dream.rest.ThreadFerry;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Frank
 * @version 1.0
 */
public class SampleThreadFerry implements ThreadFerry{
	protected final Logger logger = LoggerFactory.getLogger(getClass());

    public void doInSrcThread() {
        logger.debug("doInSrcThread:"+Thread.currentThread().getId());
    }


    public void doInDestThread() {
        logger.debug("doInSrcThread:"+Thread.currentThread().getId());
    }
}

