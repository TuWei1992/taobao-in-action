/*******************************************************************************
 * Copyright (c) 2005, 2014 com.dream
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.dream.message.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dream.Constants;
import com.dream.rapid.base.BaseController;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TmcGroupAddRequest;
import com.taobao.api.request.TmcUserPermitRequest;
import com.taobao.api.response.TmcGroupAddResponse;
import com.taobao.api.response.TmcUserPermitResponse;

/**
 * ItemController负责打开商品列表和商品详情页面，
 * 
 * 
 * 
 * @author Frank
 */

@Controller
@RequestMapping(value = "/message")
public class MessageController extends BaseController {
	
	
	
	@RequestMapping(value = "detail",method = RequestMethod.GET)
	public String detail( Model model)  throws Exception {
		String url = Constants.TOP_URL;
		String appkey = Constants.APP_KEY;
		String secret = Constants.APP_SECRET;
		String sessionKey = getOAuth().getAccessToken();
		TaobaoClient client=new DefaultTaobaoClient(url, appkey, secret);
		TmcUserPermitRequest req=new TmcUserPermitRequest();
		TmcUserPermitResponse response = client.execute(req , sessionKey);
		logger.debug("Response is {}", response);
		return "message/detail";
	}
	
	@RequestMapping(value = "group",method = RequestMethod.GET)
	public String group( Model model)  throws Exception {
		String url = Constants.TOP_URL;
		String appkey = Constants.APP_KEY;
		String secret = Constants.APP_SECRET;
		TaobaoClient client=new DefaultTaobaoClient(url, appkey, secret);
		TmcGroupAddRequest req=new TmcGroupAddRequest();
		TmcGroupAddResponse response = client.execute(req);
		logger.debug("Response is {}", response);
		return "message/group";
	}

}
