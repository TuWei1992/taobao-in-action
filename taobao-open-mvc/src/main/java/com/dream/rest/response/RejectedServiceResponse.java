/**
 * 
 * 
 */
package com.dream.rest.response;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.dream.rest.RopRequestContext;
import com.dream.rest.security.MainError;
import com.dream.rest.security.MainErrorType;
import com.dream.rest.security.MainErrors;

/**
 * <pre>
 *   当服务平台资源耗尽（超过最大线程数且列队排满后）
 * </pre>
 *
 * @author Frank
 * @version 1.0
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "error")
public class RejectedServiceResponse extends ErrorResponse  {

    public RejectedServiceResponse() {
    }

    public RejectedServiceResponse(RopRequestContext context) {
        MainError mainError = MainErrors.getError(MainErrorType.FORBIDDEN_REQUEST, context.getLocale(),
                                                  context.getMethod(),context.getVersion());
        setMainError(mainError);
    }
}

