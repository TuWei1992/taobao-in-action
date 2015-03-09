
package com.dream.rest.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dream.rest.event.AfterStartedRopEvent;
import com.dream.rest.event.RopEventListener;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Frank
 * @version 1.0
 */
public class SamplePostInitializeEventListener implements RopEventListener<AfterStartedRopEvent> {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

    public void onRopEvent(AfterStartedRopEvent ropRopEvent) {
    	logger.debug("SamplePostInitializeEventListener!");
    }


    public int getOrder() {
        return 0;
    }
}

