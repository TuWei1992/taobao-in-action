package com.dream.demo.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;


@Repository
public class DemoDao {
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public void require(){
		
		logger.debug("Reqire.");
		
	}
	
	public void requireNew(){
		logger.debug("requireNew.");
	}

}
