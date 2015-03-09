/**
 *
 * 
 */
package com.dream.rest.impl;

import com.dream.rest.security.ServiceAccessController;
import com.dream.rest.session.Session;

/**
 * <pre>
 * 功能说明：对调用的方法进行安全性检查
 * </pre>
 *
 * @author Frank
 * @version 1.0
 */
public class DefaultServiceAccessController implements ServiceAccessController {


    public boolean isAppGranted(String appKey, String method, String version) {
        return true;
    }


    public boolean isUserGranted(Session session, String method, String version) {
        return true;
    }
}

