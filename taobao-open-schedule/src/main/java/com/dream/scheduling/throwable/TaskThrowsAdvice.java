package com.dream.scheduling.throwable;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;

public class TaskThrowsAdvice extends Throwable implements ThrowsAdvice{
	
	private static final Logger logger = LoggerFactory.getLogger(TaskThrowsAdvice.class);
//	public TaskThrowsAdvice(){
//		logger.debug("==================TaskThrowsAdvice running==============");
//	}
//	public TaskThrowsAdvice(String message){
//		logger.debug("==================TaskThrowsAdvice running message : " + message + "==============");
//		
//	}
	
	public void afterThrowing(Exception re){
		logger.debug("********************* runtime Exception *********************");
	}
	
	public void afterThrowing(Method method, Object[] args, Object target, Exception ex) throws Throwable{
		logger.debug("!!!!!!!!!!!!!=========Exception class: " + ex.getClass().getName()+"========");
	}
	
	
}
