/**
 * 
 * 
 */
package com.dream.rest.event;

import com.dream.rest.RopContext;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Frank
 * @version 1.0
 */
public class PreCloseRopEvent extends RopEvent {
    public PreCloseRopEvent(Object source, RopContext ropContext) {
        super(source, ropContext);
    }
}

