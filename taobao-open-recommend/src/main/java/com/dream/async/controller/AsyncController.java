/*******************************************************************************
 * Copyright (c) 2005, 2014 com.dream
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.dream.async.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dream.Constants;
import com.taobao.api.ApiException;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.request.TopatsResultGetRequest;
import com.taobao.api.response.TopatsResultGetResponse;

/**
 * ItemController负责打开商品列表和商品详情页面，
 * 
 * 
 * 
 * @author Frank
 */

@Controller
@RequestMapping(value = "/async")
public class AsyncController {
	
	@RequestMapping(value = "detail/{id}",method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model)  throws Exception {
		
		TaobaoClient client = new DefaultTaobaoClient(Constants.TOP_URL, Constants.APP_KEY, Constants.APP_SECRET);
//		TopatsTradesSoldGetRequest req = new TopatsTradesSoldGetRequest();
//		req.setFields("tid,seller_nick,buyer_nick,title,payment,parent_id,type,status,created,orders");
//		req.setStartTime("2012-05-01");
//		req.setEndTime("2012-05-31");
//		TopatsTradesSoldGetResponse response = client.execute(req , sessionKey);
		return "async/detail";
	}
	@RequestMapping(value = "list/{id}",method = RequestMethod.GET)
	public String list() throws ApiException{
		TaobaoClient client = new DefaultTaobaoClient(Constants.TOP_URL, Constants.APP_KEY, Constants.APP_SECRET);
		TopatsResultGetRequest req = new TopatsResultGetRequest();
		req.setTaskId(12345L);
		TopatsResultGetResponse rsp = client.execute(req);
		return "async/list";
	}

}
