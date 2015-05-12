
package com.dream.rest.sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import javax.servlet.ServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dream.messaging.MessagingException;
import com.dream.messaging.StringMessage;
import com.dream.messaging.engine.MessageEngine;
import com.dream.rest.AbstractInterceptor;
import com.dream.rest.RopRequestContext;

/**
 *
 * @author Frank
 * @version 1.0
 */

public class HttpBodyInterceptor extends AbstractInterceptor {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	
	private MessageEngine messageEngine;
	
	private static final String DEFAULT_PREFIX = "internal_";
    
	private String prefix = DEFAULT_PREFIX;
	/**
     *
     * @param ropRequestContext
     * @throws  
     */
    public void beforeService(RopRequestContext ropRequestContext) {
        logger.debug("Xml format before service ...");
        ServletRequest request = (ServletRequest) ropRequestContext.getRawRequestObject();
        try {
			InputStreamReader inputReader = new InputStreamReader( request.getInputStream(), "UTF-8"); 
			BufferedReader bufferReader = new BufferedReader(inputReader); 
			StringBuilder content = new StringBuilder(); 
			String line = null; 
			while ((line = bufferReader.readLine()) != null) { 
				content.append(line); 
			} 
			StringMessage stringMessage =  new StringMessage();
			stringMessage.setContent(content);
			//Parse XML using message engine.
			Object requestObject = messageEngine.toObject(stringMessage, ropRequestContext.getMethod(), ropRequestContext.getVersion(), "xml", new HashMap());
			HttpBodyHolder.setRequest(requestObject);
		} catch (IOException e) {
			logger.error("Error...",e);
		}catch (MessagingException e) {
			logger.error("Error...",e);
		}
        
    }
    
    
    

    /**
     * 在服务执行完成后，响应返回前执行该拦截方法
     *
     * @param ropRequestContext
     */

    public void beforeResponse(RopRequestContext ropRequestContext) {
        logger.debug("Xml format before response ...");
        HttpBodyHolder.setRequest(null);
    }

    /**
     * 对method为user.add的方法进行拦截，你可以通过methodContext中的信息制定拦截方案
     *
     * @param ropRequestContext
     * @return
     */

    public boolean isMatch(RopRequestContext ropRequestContext) {
    	return ropRequestContext.getMethod().startsWith(prefix);
    }
}

