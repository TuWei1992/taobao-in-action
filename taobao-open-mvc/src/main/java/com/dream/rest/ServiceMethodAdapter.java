/**
 *
 * 
 */
package com.dream.rest;

/**
 * <pre>
 *    通过该适配器以统一的方式调用ROP方法
 * </pre>
 *
 * @author Frank
 * @version 1.0
 */
public interface ServiceMethodAdapter {
    /**
     * 调用服务方法
     *
     * @param ropRequest
     * @return
     */
    Object invokeServiceMethod(RopRequest ropRequest);

}

