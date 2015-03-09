/**
 * 
 * 
 */
package com.dream.rest.config;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * <pre>
 * 功能说明：
 * </pre>
 *
 * @author Frank
 * @version 1.0
 */
public class RopNamespaceHandler extends NamespaceHandlerSupport {


    public void init() {
        registerBeanDefinitionParser("annotation-driven", new AnnotationDrivenBeanDefinitionParser());
        registerBeanDefinitionParser("interceptors", new InterceptorsBeanDefinitionParser());
        registerBeanDefinitionParser("listeners", new ListenersBeanDefinitionParser());
        registerBeanDefinitionParser("sysparams", new SystemParameterNamesBeanDefinitionParser());
    }
}

