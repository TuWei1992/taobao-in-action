
package com.dream.rest.sample;

import com.dream.rest.RopRequest;
import com.dream.rest.annotation.ServiceMethod;
import com.dream.rest.annotation.ServiceMethodBean;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Frank
 * @version 1.0
 */
@ServiceMethodBean(version = "1.0")
public class LogoutService {

    @ServiceMethod(method = "user.logout")
    public Object logout(RopRequest request){
        return null;
    }
}

