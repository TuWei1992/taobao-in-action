/**
 * 
 * 
 */
package com.dream.rest.config;

import com.dream.rest.Interceptor;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Frank
 * @version 1.0
 */
public class InterceptorHolder {

    private Interceptor interceptor;

    public InterceptorHolder(Interceptor interceptor) {
        this.interceptor = interceptor;
    }

    public Interceptor getInterceptor() {
        return interceptor;
    }
}

