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
public class DemoService {
	
	protected Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private DemoDao demoDao;
	
	public DemoDao getDemoDao() {
		return demoDao;
	}

	public void setDemoDao(DemoDao demoDao) {
		this.demoDao = demoDao;
	}
	
	
	private Demo1Service demo1Service;
	
	
	public Demo1Service getDemo1Service() {
		return demo1Service;
	}

	public void setDemo1Service(Demo1Service demo1Service) {
		this.demo1Service = demo1Service;
	}

	@Transactional(propagation=Propagation.REQUIRED)
	public void require(){
		
		demoDao.require();
		demo1Service.requireNew();
		
	}
	
	
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void requireNew(){
		
		demoDao.requireNew();
		
	}
	
	

}
