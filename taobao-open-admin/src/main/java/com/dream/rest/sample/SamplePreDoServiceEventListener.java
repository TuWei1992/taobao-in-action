
package com.dream.rest.sample;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dream.rest.RopRequestContext;
import com.dream.rest.event.PreDoServiceEvent;
import com.dream.rest.event.RopEventListener;
import com.dream.rest.marshaller.MessageMarshallerUtils;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Frank
 * @version 1.0
 */
public class SamplePreDoServiceEventListener implements RopEventListener<PreDoServiceEvent> {
	protected final Logger logger = LoggerFactory.getLogger(getClass());

    public void onRopEvent(PreDoServiceEvent ropEvent) {
        RopRequestContext ropRequestContext = ropEvent.getRopRequestContext();
        if(ropRequestContext != null){
            Map<String,String> allParams = ropRequestContext.getAllParams();
            String message = MessageMarshallerUtils.asUrlString(allParams);
            logger.debug("message("+ropEvent.getServiceBeginTime()+")"+message);
        }
    }


    public int getOrder() {
        return 1;
    }
}

