
package com.dream.rest.sample;

import com.dream.rest.sample.response.InterceptorResponse;
import com.dream.rest.AbstractInterceptor;
import com.dream.rest.RopRequestContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <pre>
 *    该拦截器仅对method为“user.add”进行拦截，你可以在{@link #isMatch(com.dream.rest.RopRequestContext)}方法中定义拦截器的匹配规则。
 *  你可以通过{@link com.dream.rest.RopRequestContext#getServiceMethodDefinition()}获取服务方法的注解信息，通过这些信息进行拦截匹配规则
 *  定义。
 * </pre>
 *
 * @author Frank
 * @version 1.0
 */

@Component
public class ReservedUserNameInterceptor extends AbstractInterceptor {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 在数据绑定后，服务方法调用前执行该拦截方法
     *
     * @param ropRequestContext
     */
    public void beforeService(RopRequestContext ropRequestContext) {
        logger.debug("beforeService ...");

        if ("jhonson".equals(ropRequestContext.getParamValue("userName"))) {
            InterceptorResponse response = new InterceptorResponse();
            response.setTestField("the userName can't be jhonson!");
            //设置了RopResponse后，后续的服务将不执行，直接返回这个RopResponse响应
            ropRequestContext.setRopResponse(response);
        }
    }

    /**
     * 在服务执行完成后，响应返回前执行该拦截方法
     *
     * @param ropRequestContext
     */

    public void beforeResponse(RopRequestContext ropRequestContext) {
        logger.debug("beforeResponse ...");
    }

    /**
     * 对method为user.add的方法进行拦截，你可以通过methodContext中的信息制定拦截方案
     *
     * @param ropRequestContext
     * @return
     */

    public boolean isMatch(RopRequestContext ropRequestContext) {
        return "user.add".equals(ropRequestContext.getMethod());
    }
}

