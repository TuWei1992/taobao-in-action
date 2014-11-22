package com.dream.demo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dream.demo.dao.DemoDao;


@Service
@Transactional
public class Demo1Service {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DemoDao demoDao;
	
	public DemoDao getDemoDao() {
		return demoDao;
	}

	public void setDemoDao(DemoDao demoDao) {
		this.demoDao = demoDao;
	}
	
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void require(){
		
		demoDao.require();
		
	}
	
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void requireNew(){
		
		demoDao.requireNew();
		
	}
	
	

}
