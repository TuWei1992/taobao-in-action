/*******************************************************************************
 * Copyright (c) 2005, 2014 com.dream
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.dream.item.controller;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dream.oauth.OAuth;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import com.taobao.api.internal.util.WebUtils;
import com.taobao.api.request.ItemGetRequest;
import com.taobao.api.request.ItemsInventoryGetRequest;
import com.taobao.api.request.ItemsOnsaleGetRequest;
import com.taobao.api.response.ItemGetResponse;
import com.taobao.api.response.ItemsInventoryGetResponse;
import com.taobao.api.response.ItemsOnsaleGetResponse;

/**
 * ItemController负责打开商品列表和商品详情页面，
 * 
 * 
 * 
 * @author Frank
 */
@Controller
@RequestMapping(value = "/item")
public class ItemController {
	
	public static Map<String,String> sessions = new HashMap<String,String>();
	

	@RequestMapping(value = "list",method = RequestMethod.GET)
	public String list(@RequestParam(value = "id", defaultValue = "-1") String code, Model model) throws Exception {
		
		//保存auth
		if(!sessions.containsKey("1021697746")){
			WebUtils.setIgnoreSSLCheck(true);
			String url = "https://oauth.tbsandbox.com/token";
			Map<String, String> props = new HashMap<String, String>();
			props.put("grant_type", "authorization_code");
			/* 测试时，需把test参数换成自己应用对应的值 */
			props.put("code", code);
			props.put("client_id", "1021697746");
			props.put("client_secret", "sandbox18b7c5cc19a602a635ebb8bcd");
			props.put("redirect_uri", "http://www.dreamlabs.com:8080/taobao-open");
			props.put("view", "web");
			String s = "";
			try {
				s = WebUtils.doPost(url, props, 30000, 30000);
				System.out.println(s);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			ObjectMapper objectMapper =  new ObjectMapper();
			OAuth result = objectMapper.readValue(s, OAuth.class);
			sessions.put("1021697746", result.getAccess_token());
			
		}
		
		String sessionKey = sessions.get("1021697746");
		TaobaoClient client=new DefaultTaobaoClient("http://gw.api.tbsandbox.com/router/rest", "1021697746", "sandbox18b7c5cc19a602a635ebb8bcd");
		ItemsOnsaleGetRequest req=new ItemsOnsaleGetRequest();
		req.setFields("approve_status,num_iid,title,nick,type,cid,pic_url,num,props,valid_thru,list_time,price,has_discount,has_invoice,has_warranty,has_showcase,modified,delist_time,postage_id,seller_cids,outer_id");
		ItemsOnsaleGetResponse response = client.execute(req , sessionKey);
		if(!response.isSuccess())
		{	
			throw new ServletException("Error");
		}
		model.addAttribute("onsale", response.getItems());
		
		ItemsInventoryGetRequest req1=new ItemsInventoryGetRequest();
		req1.setFields("approve_status,num_iid,title,nick,type,cid,pic_url,num,props,valid_thru,list_time,price,has_discount,has_invoice,has_warranty,has_showcase,modified,delist_time,postage_id,seller_cids,outer_id");
		ItemsInventoryGetResponse response1 = client.execute(req1 , sessionKey);
		if(!response1.isSuccess())
		{	
			throw new ServletException("Error");
		}
		model.addAttribute("inventory", response1.getItems());
		
		return "item/list";
	}
	
	@RequestMapping(value = "detail/{id}",method = RequestMethod.GET)
	public String detail(@PathVariable("id") Long id, Model model)  throws Exception {
		TaobaoClient client=new DefaultTaobaoClient("http://gw.api.tbsandbox.com/router/rest", "1021697746", "sandbox18b7c5cc19a602a635ebb8bcd");
		ItemGetRequest req=new ItemGetRequest();
		req.setFields("num_iid,title,price,desc_modules,sell_point");
		req.setNumIid(id);
		ItemGetResponse response = client.execute(req , sessions.get("1021697746"));
		model.addAttribute("detail", response.getItem());
		return "item/detail";
	}

}
