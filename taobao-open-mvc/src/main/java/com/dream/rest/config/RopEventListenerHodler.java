/**
 * 
 * 
 */
package com.dream.rest.config;

import com.dream.rest.event.RopEventListener;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Frank
 * @version 1.0
 */
public class RopEventListenerHodler {

    private RopEventListener ropEventListener;

    public RopEventListenerHodler(RopEventListener ropEventListener) {
        this.ropEventListener = ropEventListener;
    }

    public RopEventListener getRopEventListener() {
        return ropEventListener;
    }
}

