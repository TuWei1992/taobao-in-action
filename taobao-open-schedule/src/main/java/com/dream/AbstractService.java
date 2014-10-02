package com.dream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;



public class AbstractService  implements ApplicationContextAware{
	protected ApplicationContext applicationContext;
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
		
	}}
