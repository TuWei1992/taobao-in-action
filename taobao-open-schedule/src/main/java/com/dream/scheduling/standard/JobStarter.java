package com.dream.scheduling.standard;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * Job Interface.
 * @author Frank
 *
 */
public class JobStarter {
	
	private static final Logger logger = (Logger) LoggerFactory.getLogger(JobStarter.class);
	
	
	public static void main(String[] args){
		
		if(args==null){
			if(logger.isErrorEnabled()){
				logger.error("Argument arrays can not be null");
			}
			return;
		}
		if(args[0]==null){
			if(logger.isErrorEnabled()){
				logger.error("Frist argument can not be null");
			}
			return;
		}
		if(logger.isInfoEnabled()){
			logger.info("Starting init application context");
		}
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:application.xml");
		context.start();
		if(logger.isInfoEnabled()){
			logger.info("Ending init application context");
		}
		
		String key = args[0];
		if(logger.isInfoEnabled()){
			logger.info("Going to execute job with job name:"+key);
		}
		
		Job job = context.getBean(key, Job.class);
		if(job == null){
			if(logger.isErrorEnabled()){
				logger.error("Job with job name:"+key+" is null.");
			}
		}
		Map parameter = new HashMap();
		try{
			job.execute(parameter);
		}catch(Exception ex){
			if(logger.isErrorEnabled()){
				logger.error("Exception:",ex);
			}
		}catch(Throwable th){
			if(logger.isErrorEnabled()){
				logger.error("Throwable:",th);
			}
		}
	}
}
