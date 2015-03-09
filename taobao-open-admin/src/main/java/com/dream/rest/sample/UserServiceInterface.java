
package com.dream.rest.sample;

import com.dream.rest.annotation.NeedInSessionType;
import com.dream.rest.annotation.ServiceMethod;
import com.dream.rest.sample.request.LogonRequest;

/**
 * @author : Frank
 * @date: 14-3-6
 */
public interface UserServiceInterface {

    @ServiceMethod(method = "user.getSession",version = "1.0",needInSession = NeedInSessionType.NO)
    Object getSession(LogonRequest request);
}
