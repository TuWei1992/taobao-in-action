/**
 * 
 * 
 */
package com.dream.rest.event;

import com.dream.rest.RopRequestContext;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Frank
 * @version 1.0
 */
public class AfterDoServiceEvent extends RopEvent {

    private RopRequestContext ropRequestContext;

    public AfterDoServiceEvent(Object source, RopRequestContext ropRequestContext) {
        super(source, ropRequestContext.getRopContext());
        this.ropRequestContext = ropRequestContext;
    }

    public long getServiceBeginTime() {
        return ropRequestContext.getServiceBeginTime();
    }

    public long getServiceEndTime() {
        return ropRequestContext.getServiceEndTime();
    }

    public RopRequestContext getRopRequestContext() {
        return ropRequestContext;
    }
}

