/*******************************************************************************
 * Copyright (c) 2005, 2014 com.dream
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package com.dream.index.controller;


import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dream.auth.model.Auth;
import com.dream.auth.service.AuthService;
import com.dream.oauth.OAuth;
import com.dream.rapid.base.BaseController;
import com.dream.shop.model.Shop;
import com.dream.shop.service.ShopService;
import com.taobao.api.ApiException;
import com.taobao.api.internal.util.WebUtils;
import com.taobao.api.request.ShopGetRequest;
import com.taobao.api.response.ShopGetResponse;

/**
 * 淘宝OAuth完成后的处理类，主要完成用户信息和店铺信息的同步。
 * 
 * @author Frank
 */

@Controller
@RequestMapping(value = "/")
public class IndexController extends BaseController {
	
	
	@Autowired
	private AuthService authService;
	
	public void setAuthService(AuthService authService){
		this.authService = authService;
	}
	
	@Autowired
	private ShopService shopService;
	
	public void setShopService(ShopService shopService){
		this.shopService = shopService;
	}
	
	
	@RequestMapping(method = RequestMethod.GET)
	public String index ( @RequestParam(value = "code", defaultValue = "-1") String code,HttpServletRequest request)  throws Exception {
		String result = "redirect:dashboard";
		
		if(getOAuth() != null){
			logger.debug("The user has been authorized with :", getOAuth());
			return result;
		}
		
		if(code.equals("-1") ){
			logger.error("The code can not be null");
			return "redirect:t";
		}
		
		//如果是第一次授权，需要将用户信息、店铺信息同步至数据库中
		
		try{
			doAuth(code, request);
			syncAuth(request);
			syncShop(request);
		}catch(Exception e){
			logger.error("Auth or sync failed,please relogin.", e);
			request.getSession().removeAttribute(TOP_OAUTH);
			request.getSession().removeAttribute(TOP_AUTH);
			request.getSession().removeAttribute(TOP_SHOP);
			throw new ServletException("Auth or sync failed,please relogin.");
		}
		return result;
	}
	
	
	
	private void doAuth(String code,HttpServletRequest servletRequest) throws IOException{
		WebUtils.setIgnoreSSLCheck(true);
		Map<String, String> props = new HashMap<String, String>();
		props.put("grant_type", "authorization_code");
		/* 测试时，需把test参数换成自己应用对应的值 */
		props.put("code", code);
		props.put("client_id", getSysParamValue("top.appkey"));
		props.put("client_secret", getSysParamValue("top.appsecret"));
		props.put("redirect_uri", getSysParamValue("ssk.redirecturl"));
		props.put("view", "web");
		String result = WebUtils.doPost(getSysParamValue("top.oauthurl"), props, Integer.valueOf(getSysParamValue("top.connectiontimeout")), Integer.valueOf(getSysParamValue("top.readtimeout")));
		logger.debug("The code has been authorized：{}",result);
		ObjectMapper objectMapper =  new ObjectMapper();
		OAuth auth = objectMapper.readValue(result, OAuth.class);
		servletRequest.getSession().setAttribute(TOP_OAUTH, auth);
	}
	
	private void syncAuth(HttpServletRequest servletRequest) throws ApiException{
		Auth localAuth = new Auth();
		BeanUtils.copyProperties(getOAuth(),localAuth);
		localAuth.setIsEnable("1");
		localAuth.setIsLock("1");
		localAuth.setRefreshedTime(new Date());
		//先判断用户是否已经存在
		Auth result = authService.getByTaobaoUserId(localAuth.getTaobaoUserId());
		if(result == null){
			authService.save(localAuth);
		} else {
			localAuth.setUserId(result.getUserId());
			authService.update(localAuth);
		}
		servletRequest.getSession().setAttribute(TOP_AUTH, localAuth);
	}
	
	private void syncShop(HttpServletRequest servletRequest) throws ApiException{
		ShopGetRequest request = new ShopGetRequest();
		request.setFields("sid,cid,title,nick,desc,bulletin,pic_path,created,modified");
		request.setNick(getOAuth().getTaobaoUserNick());
		ShopGetResponse response = (ShopGetResponse) getTaobaoResponse(request, getOAuth().getTaobaoUserNick());
		Shop shop = new Shop();
		BeanUtils.copyProperties(response.getShop(), shop);
		//先判断店铺信息是否存在
		Shop result = shopService.getByNick(getOAuth().getTaobaoUserNick());
		if(result == null){
			shopService.save(shop);
		}else{
			shop.setSid(result.getSid());
			shopService.update(shop);
		}
		servletRequest.getSession().setAttribute(TOP_SHOP, shop);
	}

}
