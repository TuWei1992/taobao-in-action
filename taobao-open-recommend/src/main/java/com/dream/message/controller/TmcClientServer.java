/*******************************************************************************
 * Copyright (c) 2005, 2014 com.dream
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.dream.message.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dream.Constants;
import com.taobao.api.internal.tmc.Message;
import com.taobao.api.internal.tmc.MessageHandler;
import com.taobao.api.internal.tmc.MessageStatus;
import com.taobao.api.internal.tmc.TmcClient;
import com.taobao.top.link.LinkException;
/**
 * 
 * 
 * 
 * 
 * @author Frank
 */
public class TmcClientServer {
	
	protected final Logger logger = LoggerFactory.getLogger(getClass());
	private TmcClient client;
	public void init() throws LinkException{
		
		client = new TmcClient(Constants.APP_KEY, Constants.APP_SECRET,"default");
		client.setMessageHandler(new MessageHandler() {  
		    public void onMessage(Message message, MessageStatus status) {  
		        try {  
		            logger.debug(message.getContent());  
		            logger.debug(message.getTopic());
		            // 默认不抛出异常则认为消息处理成功  
		        } catch (Exception e) {  
		            e.printStackTrace();  
		            status.fail();// 消息处理失败回滚，服务端需要重发  
		        }  
		    }

		});  
		//client.connect("ws://mc.api.tbsandbox.com/");
	}
	
	public void destroy(){
		client.close();
		client = null;
	}

}
