/**
 * 
 * 
 */
package com.dream.rest.security;

import com.dream.rest.session.Session;

/**
 * <pre>
 *    默认的实现
 * </pre>
 *
 * @author Frank
 * @version 1.0
 */
public class DefaultInvokeTimesController implements InvokeTimesController {


    public void caculateInvokeTimes(String appKey, Session session) {
    }


    public boolean isUserInvokeLimitExceed(String appKey, Session session) {
        return false;
    }


    public boolean isSessionInvokeLimitExceed(String appKey, String sessionId) {
        return false;
    }


    public boolean isAppInvokeLimitExceed(String appKey) {
        return false;
    }


    public boolean isAppInvokeFrequencyExceed(String appKey) {
        return false;
    }
}

