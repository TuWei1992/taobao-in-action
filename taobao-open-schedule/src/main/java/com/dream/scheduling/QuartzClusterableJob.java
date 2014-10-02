/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.dream.scheduling;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * 被Spring的Quartz JobDetailBean定时执行的Job类, 支持持久化到数据库实现Quartz集群.
 * 
 * 因为需要被持久化, 不能有用XXService等不能被持久化的成员变量,
 * 只能在每次调度时从QuartzJobBean注入的applicationContext中动态取出.
 * 
 * @author Frank
 */
public class QuartzClusterableJob extends QuartzJobBean {

	private static Logger logger = LoggerFactory
			.getLogger(QuartzClusterableJob.class.getName()
					+ ".quartz cluster job");

	
	@Override
	protected void executeInternal(JobExecutionContext ctx)
			throws JobExecutionException {
		CloseableHttpClient client = HttpClientBuilder.create().build();
		String url = "http://www.dreamlabs.com:8080/taobao-open/recommend";
		try {
			CloseableHttpResponse response = client.execute(new HttpGet(url));
			int code = response.getStatusLine().getStatusCode();
			logger.info("Response is {}", code);
		} catch (ClientProtocolException e) {
			logger.info("Cant not connect to {}", url);
			throw new JobExecutionException("Cant not connect.");
		} catch (IOException e) {
			logger.info("Cant not connect to {}", url);
			throw new JobExecutionException("Cant not connect.");
		}
	}
}
