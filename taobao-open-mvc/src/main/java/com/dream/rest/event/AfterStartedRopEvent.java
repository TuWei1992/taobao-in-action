/**
 * 
 * 
 */
package com.dream.rest.event;

import com.dream.rest.RopContext;

/**
 * <pre>
 *   在Rop框架初始化后产生的事件
 * </pre>
 *
 * @author Frank
 * @version 1.0
 */
public class AfterStartedRopEvent extends RopEvent {

    public AfterStartedRopEvent(Object source, RopContext ropContext) {
        super(source, ropContext);
    }

}

