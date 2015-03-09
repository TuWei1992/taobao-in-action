/**
 *
 * 
 */
package com.dream.rest;

/**
 * <pre>
 *    ROP服务的请求对象，请求方法的入参必须是继承于该类。
 * </pre>
 *
 * @author Frank
 * @version 1.0
 */
public interface RopRequest {

    /**
     * 获取服务方法的上下文
     *
     * @return
     */
    RopRequestContext getRopRequestContext();

}

