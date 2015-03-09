/**
 * 
 * 
 */
package com.dream.rest.event;

import com.dream.rest.RopContext;

import java.util.EventObject;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Frank
 * @version 1.0
 */
public abstract class RopEvent extends EventObject {

    private RopContext ropContext;

    public RopEvent(Object source, RopContext ropContext) {
        super(source);
        this.ropContext = ropContext;
    }

    public RopContext getRopContext() {
        return ropContext;
    }
}

