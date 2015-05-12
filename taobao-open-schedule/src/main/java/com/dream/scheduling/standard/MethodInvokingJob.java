package com.dream.scheduling.standard;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.MethodInvoker;

/**
 * 
 * @author Frank
 *
 */
public class MethodInvokingJob implements Job ,ApplicationContextAware{
	private static final Logger logger = (Logger) LoggerFactory.getLogger(MethodInvokingJob.class);
	
	private ApplicationContext applicationContext;
	
	private String targetClass;
	private Object targetObject;
	private String targetService;
	private String targetMethod;
	private String staticMethod;
	private Object[] arguments;
	
	
	public String getTargetClass() {
		return targetClass;
	}
	public void setTargetClass(String targetClass) {
		this.targetClass = targetClass;
	}
	public Object getTargetObject() {
		return targetObject;
	}
	public void setTargetObject(Object targetObject) {
		this.targetObject = targetObject;
	}
	public String getTargetService() {
		return targetService;
	}
	public void setTargetService(String targetService) {
		this.targetService = targetService;
	}
	public String getTargetMethod() {
		return targetMethod;
	}
	public void setTargetMethod(String targetMethod) {
		this.targetMethod = targetMethod;
	}
	public String getStaticMethod() {
		return staticMethod;
	}
	public void setStaticMethod(String staticMethod) {
		this.staticMethod = staticMethod;
	}
	public Object[] getArguments() {
		return arguments;
	}
	public void setArguments(Object[] arguments) {
		this.arguments = arguments;
	}
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
	
	
	@Override
	public void execute(Map<String, Object> context) {
		try {
			logger.debug("start");
			logger.debug("targetClass is " + targetClass);
			Class targetClassClass = null;
			if (targetClass != null) {
				targetClassClass = Class.forName(targetClass); // Could
			}
			logger.debug("targetObject is " + targetObject);
            logger.debug("targetService is " + targetService);
            logger.debug("targetMethod is " + targetMethod);
			logger.debug("staticMethod is " + staticMethod);
			logger.debug("arguments are " + arguments);

			logger.debug("creating MethodInvoker");
			MethodInvoker methodInvoker = new MethodInvoker();
			methodInvoker.setTargetClass(targetClassClass);
			if (targetObject == null) {
			    targetObject = applicationContext.getBean(targetService);
			}
			methodInvoker.setTargetObject(targetObject);
			methodInvoker.setTargetMethod(targetMethod);
			methodInvoker.setStaticMethod(staticMethod);
			methodInvoker.setArguments(arguments);
			methodInvoker.prepare();
			logger.info("Invoking: " + methodInvoker.getPreparedMethod().toGenericString());
			methodInvoker.invoke();
		} catch (Exception e) {
			logger.error("execute ",e);
		} finally {
			logger.debug("end");
		}
	}


}
