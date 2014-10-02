package com.dream.scheduling.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dream.SchedulingException;
import com.dream.scheduling.service.ScheduleListService;

public class ScheduleListServiceImpl implements ScheduleListService{

	private final static Logger logger = LoggerFactory.getLogger(ScheduleListServiceImpl.class);
	@Override
//	@BeScheduleAMC
	public void productUp() throws SchedulingException {
		
		logger.debug("=============================商品定时上架=======================");
	}

}
